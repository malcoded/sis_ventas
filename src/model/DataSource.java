/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author developer1
 */
public class DataSource {

    Connection cn;
    String url = "jdbc:mysql://localhost:3307/salesDB?useTimezone=true&serverTimeZone=UTC";
    String user = "root";
    String pass = "12345678";

    public Connection Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {

        }

        return cn;
    }
}
