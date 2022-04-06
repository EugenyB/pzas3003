package data;

import java.util.Objects;

public class Student {
    private int id;
    private String name;
    private int year;
    private double rating;
    private Gruppa group;

    public Student(int id, String name, int year, double rating, Gruppa group) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.group = group;
    }

    public Student(int id, String name, int year, double rating) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Gruppa getGroup() {
        return group;
    }

    public void setGroup(Gruppa group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", group=" + (group==null ? "no group" : group) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return id == student.id && year == student.year && Double.compare(student.rating, rating) == 0 && Objects.equals(name, student.name) && Objects.equals(group, student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year, rating, group);
    }
}
