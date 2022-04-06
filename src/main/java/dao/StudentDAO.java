package dao;

import data.Gruppa;
import data.Student;

import java.sql.*;
import java.util.*;

public class StudentDAO {
    private Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Student> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from student");
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int year = resultSet.getInt("year");
                double rating = resultSet.getDouble("rating");
                students.add(new Student(id, name, year, rating));
            }
            return students;
        } catch (SQLException ex) {
            return Collections.emptyList();
        }
    }

    public List<Student> findAllWithGroups() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from student s join gruppa g on g.id = s.gruppa_id");
            List<Student> students = new ArrayList<>();
            Set<Gruppa> groups = new HashSet<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("s.id");
                String name = resultSet.getString("name");
                int year = resultSet.getInt("year");
                double rating = resultSet.getDouble("rating");
                int grId = resultSet.getInt("g.id");
                String title = resultSet.getString("title");

                groups.add(new Gruppa(grId, title));
                Gruppa gruppa = groups.stream().filter(g -> g.getId() == grId).findFirst().get();
                students.add(new Student(id, name, year, rating, gruppa));
            }
            return students;
        } catch (SQLException ex) {
            return Collections.emptyList();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("delete from student where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(String name, int year, double rating) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into student (name, year, rating) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setInt(2, year);
            ps.setDouble(3, rating);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> findByGroup(Gruppa gruppa) {
        try (PreparedStatement ps = connection.prepareStatement("select * from student where gruppa_id = ?")) {
            List<Student> students = new ArrayList<>();
            ps.setInt(1, gruppa.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("s.id");
                String name = rs.getString("name");
                int year = rs.getInt("year");
                double rating = rs.getDouble("rating");
                Student student = new Student(id, name, year, rating, gruppa);
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }
}
