/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author HP
 */
public class DBHandler {

    public static int setData(Connection connnection, String sql) throws SQLException {

        Statement statement = connnection.createStatement();
        int res = statement.executeUpdate(sql);
        return res;

    }

    public static ResultSet getData(Connection connection, String sql) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet rst = statement.executeQuery(sql);
        return rst;

    }
}