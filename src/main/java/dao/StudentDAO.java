package dao;

import data.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}
