package main;

import dao.GruppaDAO;
import dao.StudentDAO;
import data.Gruppa;
import data.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private StudentDAO studentDAO;
    private GruppaDAO gruppaDAO;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/demo3003", "student", "123");
            studentDAO = new StudentDAO(connection);
            gruppaDAO = new GruppaDAO(connection);

            mainloop: while (true) {
                int m = menu();
                switch (m) {
                    case 0: break mainloop;
                    case 1: showGroups(); break;
                    case 2: showStudents(); break;
                    case 3: showStudentsByGroups(); break;
                    case 4:
                        Scanner in = new Scanner(System.in);
                        String groupNo = in.nextLine();
                        showStudents(groupNo);
                        break;
                }
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showStudents(String groupNo) {
        Gruppa gruppa = gruppaDAO.findByNo(groupNo);
        List<Student> students= studentDAO.findByGroup(gruppa);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void showStudentsByGroups() {
        List<Student> students = studentDAO.findAllWithGroups();
        Map<Gruppa, List<Student>> map = students.stream().collect(Collectors.groupingBy(Student::getGroup));
        for (Map.Entry<Gruppa, List<Student>> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            for (Student student : entry.getValue()) {
                System.out.print("    ");
                System.out.println(student);
            }
        }
    }

    private void showStudents() {
        List<Student> students = studentDAO.findAllWithGroups();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void showGroups() {
        List<Gruppa> groups = gruppaDAO.findAll();
        for (Gruppa group : groups) {
            System.out.println(group);
        }
    }

    private int menu() {
        System.out.println("1. Show All groups");
        System.out.println("2. Show All students");
        System.out.println("3. Show All students by groups");
        System.out.println("4. Show students of group");
        System.out.println("0. Exit");
        return new Scanner(System.in).nextInt();
    }

    private void printList(List<Student> students) {
        students.forEach(System.out::println);
    }
}
