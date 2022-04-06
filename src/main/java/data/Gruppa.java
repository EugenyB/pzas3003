package data;

import java.util.Objects;

public class Gruppa {
    private int id;
    private String title;

    public Gruppa(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Gruppa(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gruppa)) return false;
        Gruppa gruppa = (Gruppa) o;
        return id == gruppa.id && Objects.equals(title, gruppa.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
