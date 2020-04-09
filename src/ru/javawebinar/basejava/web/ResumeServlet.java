package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
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

        Resume resume;
        if (uuid == null || uuid.length() == 0) {
            resume = new Resume(fullName);
        } else {
            resume = storage.get(uuid);
            resume.setFullName(fullName);
        }

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                resume.addContact(type, value);
            } else {
                resume.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            if (value != null && value.trim().length() != 0 && values.length >= 1) {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.addSection(type, new TextSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.addSection(type, new ListSection(value.split("\n")));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> listOrg = new ArrayList<>();
                        String[] urls = request.getParameterValues(type.name());

                        for (int i = 0; i < values.length; i++) {
                            String val = values[i];
                            if (val != null && val.trim().length() != 0) {
                                List<Organization.Position> listPos = new ArrayList<>();
                                String[] startDates = request.getParameterValues("startDate");
                                String[] endDates = request.getParameterValues("endDate");
                                String[] titles = request.getParameterValues("title");
                                String[] descriptions = request.getParameterValues("description");

                                for (int j = 0; j < titles.length; j++) {
                                    if (titles[j] != null && titles[j].trim().length() != 0) {
                                        YearMonth start = YearMonth.parse(startDates[j]);
                                        YearMonth end = YearMonth.parse(endDates[j]);

                                        listPos.add(new Organization.Position(
                                                LocalDate.of(start.getYear(), start.getMonth(), 1),
                                                LocalDate.of(end.getYear(), end.getMonth(), 1),
                                                titles[j],
                                                descriptions[j]));
                                    }
                                }
                                listOrg.add(new Organization(new Link(values[i], urls[i]), listPos));
                            }
                        }
                        resume.addSection(type, new OrganizationSection(listOrg));
                        break;
                }
            } else {
                resume.getSections().remove(type);
            }
        }
        if (uuid == null || uuid.length() == 0) {
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
                resume = new Resume();
                addEmpty(resume);
                break;
            case "view":
                resume = storage.get(uuid);
                break;
            case "edit":
                resume = storage.get(uuid);
                addEmpty(resume);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    private void addEmpty(Resume resume) {
        for (SectionType sectionType : SectionType.values()) {
            AbstractSection section = resume.getSection(sectionType);
            switch (sectionType) {
                case OBJECTIVE:
                case PERSONAL:
                    if (section == null) {
                        section = new TextSection("");
                    }
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    if (section == null) {
                        section = new ListSection("");
                    }
                    break;
                case EXPERIENCE:
                case EDUCATION:
                    OrganizationSection organizationSection = (OrganizationSection) section;
                    List<Organization> orgIsEmpty = new ArrayList<>();

                    orgIsEmpty.add(new Organization("", "", new Organization.Position()));

                    if (organizationSection != null) {
                        for (Organization organization : organizationSection.getOrganizations()) {
                            List<Organization.Position> posIsEmpty = new ArrayList<>();

                            posIsEmpty.add(new Organization.Position());
                            posIsEmpty.addAll(organization.getPositions());
                            orgIsEmpty.add(new Organization(organization.getHomePage(), posIsEmpty));
                        }
                    }
                    section = new OrganizationSection(orgIsEmpty);
                    break;
            }
            resume.addSection(sectionType, section);
        }
    }
}