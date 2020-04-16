package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.util.DateUtil;
import ru.javawebinar.basejava.util.HtmlUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    private Storage storage; // = Config.get().getStorage();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");

        final boolean isCreate = (uuid == null || uuid.length() == 0);

        Resume resume;

        if (isCreate) {
            resume = new Resume(fullName);
        } else {
            resume = storage.get(uuid);
            resume.setFullName(fullName);
        }

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (HtmlUtil.isEmpty(value)) {
                resume.getContacts().remove(type);
            } else {
                resume.setContact(type, value);
            }
        }

        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            if (HtmlUtil.isEmpty(value) && values.length < 2) {
                resume.getSections().remove(type);
            } else {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.setSection(type, new TextSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.setSection(type, new ListSection(value.split("\n")));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> listOrg = new ArrayList<>();
                        String[] urls = request.getParameterValues(type.name() + "url");

                        for (int i = 0; i < values.length; i++) {
                            String val = values[i];
                            if (!HtmlUtil.isEmpty(val)) {
                                List<Organization.Position> listPos = new ArrayList<>();
                                String pref = type.name() + i;
                                String[] startDates = request.getParameterValues(pref + "startDate");
                                String[] endDates = request.getParameterValues(pref + "endDate");
                                String[] titles = request.getParameterValues(pref + "title");
                                String[] descriptions = request.getParameterValues(pref + "description");

                                for (int j = 0; j < titles.length; j++) {
                                    if (titles[j] != null && titles[j].trim().length() != 0) {
                                        listPos.add(new Organization.Position(
                                                DateUtil.parse(startDates[j]),
                                                DateUtil.parse(endDates[j]),
                                                titles[j],
                                                descriptions[j]));
                                    }
                                }
                                listOrg.add(new Organization(new Link(val, urls[i]), listPos));
                            }
                        }
                        resume.setSection(type, new OrganizationSection(listOrg));
                        break;
                }
            }
        }
        if (isCreate) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "add":
                resume = Resume.EMPTY;
                break;
            case "view":
                resume = storage.get(uuid);
                break;
            case "edit":
                resume = storage.get(uuid);
                for (SectionType sectionType : SectionType.values()) {
                    AbstractSection section = resume.getSection(sectionType);
                    switch (sectionType) {
                        case OBJECTIVE:
                        case PERSONAL:
                            if (section == null) {
                                section = TextSection.EMPTY;
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (section == null) {
                                section = ListSection.EMPTY;
                            }
                            break;
                        case EXPERIENCE:
                        case EDUCATION:
                            OrganizationSection organizationSection = (OrganizationSection) section;
                            List<Organization> orgIsEmpty = new ArrayList<>();

                            if (organizationSection != null) {
                                for (Organization org : organizationSection.getOrganizations()) {

                                    List<Organization.Position> posIsEmpty = new ArrayList<>(org.getPositions());
                                    posIsEmpty.add(Organization.Position.EMPTY);
                                    orgIsEmpty.add(new Organization(org.getHomePage(), posIsEmpty));
                                }
                            }
                            orgIsEmpty.add(Organization.EMPTY);
                            section = new OrganizationSection(orgIsEmpty);
                            break;
                    }
                    resume.setSection(sectionType, section);
                }
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}