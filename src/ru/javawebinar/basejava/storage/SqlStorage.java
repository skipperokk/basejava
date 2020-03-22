package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() != 1) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            deleteContacts(conn, resume);
            deleteSections(conn, resume);
            insertContacts(resume, conn);
            insertSection(resume, conn);
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, resume.getUuid());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }
                    insertContacts(resume, conn);
                    insertSection(resume, conn);
                    return null;
                }
        );
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume resume;
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume WHERE uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));
            }

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContact(rs, resume);
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(rs, resume);
                }
            }
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume r " +
                "LEFT JOIN contact c ON r.uuid = c.resume_uuid" +
                " ORDER BY full_name, uuid", ps -> {
            ResultSet rs = ps.executeQuery();
            Map<String, Resume> resultMap = new LinkedHashMap<>();
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                String fullName = rs.getString("full_name");
                Resume resume = resultMap.computeIfAbsent(uuid, k -> new Resume(uuid, fullName));
                addContact(rs, resume);
            }
            return new ArrayList<>(resultMap.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void insertContacts(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertSection(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, AbstractSection> e : resume.getSections().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                SectionType sectionType = e.getKey();
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        ps.setString(3, String.valueOf(e.getValue()));
                        break;
                    case ACHIEVEMENT:
                        ListSection ls = new ListSection(String.valueOf(e.getValue()));
                        List<String> intList = ls.getItems();
                        String result = intList.stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining("\n"));
//                    ListSection section = new ListSection(String.valueOf(e.getValue()));
//                    String text = String.join("\n", new ArrayList<CharSequence>(ls.getItems()));
                        ps.setString(3, result);
                }

//                if (e.getKey() == SectionType.OBJECTIVE) {
//                    ps.setString(3, String.valueOf(e.getValue()));
//                } else if (e.getKey() == SectionType.PERSONAL) {
//                    ps.setString(3, String.valueOf(e.getValue()));
//                } else if (e.getKey() == SectionType.ACHIEVEMENT) {
//                    ListSection ls = new ListSection(String.valueOf(e.getValue()));
//                    List<String> intList = ls.getItems();
//                    String result = intList.stream()
//                            .map(String::valueOf)
//                            .collect(Collectors.joining(", "));
////                    ListSection section = new ListSection(String.valueOf(e.getValue()));
////                    String text = String.join("\n", new ArrayList<CharSequence>(ls.getItems()));
//                    ps.setString(3, result);
//                }
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContacts(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    private void deleteSections(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM section WHERE resume_uuid = ?")) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    private void addContact(ResultSet resultSet, Resume resume) throws SQLException {
        String value = resultSet.getString("value");
        if (value != null) {
            resume.addContact(ContactType.valueOf(resultSet.getString("type")), value);
        }
    }

    private void addSection(ResultSet resultSet, Resume resume) throws SQLException {
        String value = resultSet.getString("value");
        if (value != null) {
            SectionType sectionType = SectionType.valueOf(resultSet.getString("type"));
            switch (sectionType) {
                case OBJECTIVE:
                case PERSONAL:
                    resume.addSection(SectionType.PERSONAL, new TextSection(value));
                case ACHIEVEMENT:
                    ListSection ls = new ListSection(value);
                    List<String> intList = ls.getItems();
                    String result = intList.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining("\n"));
                    // String text = String.join("\n", new ArrayList<CharSequence>(new ListSection(value).getItems()));
                    resume.addSection(SectionType.ACHIEVEMENT, new ListSection(result));
            }
//            if (SectionType.valueOf(resultSet.getString("type")) == SectionType.OBJECTIVE) {
//                resume.addSection(SectionType.OBJECTIVE, new TextSection(value));
//            } else if (SectionType.valueOf(resultSet.getString("type")) == SectionType.PERSONAL) {
//                resume.addSection(SectionType.PERSONAL, new TextSection(value));
//            } else if (SectionType.valueOf(resultSet.getString("type")) == SectionType.ACHIEVEMENT) {
//                ListSection ls = new ListSection(value);
//                List<String> intList = ls.getItems();
//                String result = intList.stream()
//                        .map(String::valueOf)
//                        .collect(Collectors.joining("\n"));
//                // String text = String.join("\n", new ArrayList<CharSequence>(new ListSection(value).getItems()));
//                resume.addSection(SectionType.ACHIEVEMENT, new ListSection(result));
//            }
        }
    }
}
