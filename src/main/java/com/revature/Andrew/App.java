package com.revature.Andrew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;INIT=runscript from 'prof.sql'", "sa", "");
            //Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
            Scanner scanner = new Scanner(System.in)
        ) {
            String query;
            while (true) {
                Statement statement = connection.createStatement();
                System.out.print("h2> ");
                query = scanner.nextLine();
            
                if ("exit".equalsIgnoreCase(query)) {
                    break;
                }

                while (!query.endsWith(";")) {
                    System.out.print("---->  ");
                    query += scanner.nextLine();
                }

                boolean isResultSet = statement.execute(query);
                if (isResultSet) {
                    ResultSet resultSet = statement.getResultSet();
                    Printer.printResultSet(resultSet);
                } else {
                    int linesUpdated = statement.getUpdateCount();
                    System.out.println(linesUpdated + ((linesUpdated == 1) ? " row" : " rows") + " updated.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}