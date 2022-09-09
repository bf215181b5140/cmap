package com.changemyavatarparams.cmap.controller;

import com.changemyavatarparams.cmap.object.Client;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DbController {
    private ResultSet resultSet = null;
    private Statement statement = null;
    private final String databaseUrl = "jdbc:sqlite:model\\cmap.db";
    // "jdbc:sqlite:src\\main\\resources\\META-INF\\resources\\model\\cmap.db"

    private Connection connection() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(this.databaseUrl);
        } catch (Exception e) {
            System.out.println(java.time.LocalDateTime.now() + " Failed making a connection to database. Exception: " + e.getMessage());
        }
        return connection;
    }

    public void addParameter(String paramName, Object paramValue) {
        try (Connection connection = this.connection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO PARAMETERS_USED (NAME, VALUE, DATE) VALUES (?, ?, ?);");
            statement.setString(1, paramName);
            statement.setString(2, paramValue.toString());
            statement.setString(3, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd kk:mm:ss")));
            statement.execute();
        } catch (Exception e) {
            System.out.println(java.time.LocalDateTime.now() + " Failed inserting Parameter into database. Exception: " + e.getMessage());
        }
    }

    public void addClient(Client client) {
        try (Connection connection = this.connection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENTS (UI_ID, VIEW, IP, USER_AGENT, LOCALE, COOKIE, DATE)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?);");
            statement.setInt(1, client.getUi().getUIId());
            statement.setString(2, client.getView().getClassName());
            statement.setString(3, client.getHeaders().get("cf-connecting-ip"));
            statement.setString(4, client.getUserAgent());
            statement.setString(5, client.getLocale());
            statement.setString(6, client.getHeaders().get("cookie"));
            statement.setString(7, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd kk:mm:ss")));
            statement.execute();
        } catch (Exception e) {
            System.out.println(java.time.LocalDateTime.now() + " Failed inserting Client into database. Exception: " + e.getMessage());
        }
    }

//    public static void readFromDb() {
//        try {
//            Class.forName("org.sqlite.JDBC");
//            connection = DriverManager.getConnection(databaseUrl);
//            statement = connection.createStatement();
//            resultSet = statement
//                    .executeQuery("SELECT VALUE FROM PARAMETERS");
//            while (resultSet.next()) {
//                System.out.println("PARAMETER VALUES NAME:"
//                        + resultSet.getString("VALUE"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                resultSet.close();
//                statement.close();
//                connection.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
