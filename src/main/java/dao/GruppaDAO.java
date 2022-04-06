package dao;

import data.Gruppa;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GruppaDAO {
    private Connection connection;

    public GruppaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Gruppa> findAll() {
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from gruppa")
        ) {
            List<Gruppa> result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                result.add(new Gruppa(id, title));
            }
            return result;
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    public Gruppa findByNo(String groupNo) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from gruppa where title = ? limit 1")) {
            preparedStatement.setString(1, groupNo);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Gruppa(rs.getInt("id"), rs.getString("title"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }
}
