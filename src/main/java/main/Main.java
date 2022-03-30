package main;

import dao.StudentDAO;
import data.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private StudentDAO studentDAO;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/demo3003", "student", "123");
            studentDAO = new StudentDAO(connection);

            List<Student> students = studentDAO.findAll();
            printList(students);
            System.out.println("------------");
            //studentDAO.delete(2);
            studentDAO.add("Petya", 2002, 75);

            students = studentDAO.findAll();
            printList(students);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printList(List<Student> students) {
        students.forEach(System.out::println);
    }
}
