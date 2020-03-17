package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume", PreparedStatement::execute);

//        try (Connection conn = connectionFactory.getConnection();
//              PreparedStatement ps = conn.prepareStatement("DELETE FROM resume")) {
//            ps.execute();
//        } catch (SQLException e) {
//            throw new StorageException(e);
//        }
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.execute("UPDATE resume SET full_name= ?", ps -> {
            ps.setString(1, resume.getFullName());
            ps.execute();
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(resume.getUuid());
            }
            return null;
        });
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name= ?")) {
//            ps.setString(1, resume.getFullName());
//            ps.execute();
//        } catch (SQLException e) {
//            throw new NotExistStorageException(resume.getUuid());
//        }
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.execute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", ps -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
            return null;
        });
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
//            ps.setString(1, resume.getUuid());
//            ps.setString(2, resume.getFullName());
//            ps.execute();
//        } catch (SQLException e) {
//            throw new ExistStorageException(resume.getUuid());
//        }
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resume r WHERE r.uuid =?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r WHERE r.uuid =?")) {
//            ps.setString(1, uuid);
//            ResultSet rs = ps.executeQuery();
//            if (!rs.next()) {
//                throw new NotExistStorageException(uuid);
//            }
//            return new Resume(uuid, rs.getString("full_name"));
//        } catch (SQLException e) {
//            throw new StorageException(e);
//        }
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume r WHERE r.uuid=?", ps -> {
            ps.setString(1, uuid);
            ps.execute();
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("DELETE * FROM resume r WHERE r.uuid=?")) {
//            ps.setString(1, uuid);
//            ps.execute();
//        } catch (SQLException e) {
//            throw new NotExistStorageException(uuid);
//        }
    }

    @Override
    public List<Resume> getAllSorted() {

        return sqlHelper.execute("SELECT * FROM resume ORDER BY uuid", ps -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Resume(rs.getString(1).trim(), rs.getString(2)));
            }
            return list;
        });
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ORDER BY uuid")) {
//            ResultSet rs = ps.executeQuery();
//            list = new ArrayList<>();
//            while (rs.next()) {
//                list.add(new Resume(rs.getString(1).trim(), rs.getString(2)));
//            }
//            return list;
//        } catch (SQLException e) {
//            throw new StorageException(e);
//        }
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(uuid) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            if (!rs.next()){
                return 0;
            }
            return rs.getInt(1);
        });
//        int size;
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(uuid) FROM resume")) {
//            ResultSet rs = ps.executeQuery();
//            if (!rs.next()) {
//                throw new NotExistStorageException("kek");
//            }
//            size = rs.getInt(1);
//            return size;
//        } catch (SQLException e) {
//            throw new StorageException(e);
//        }
    }
}
