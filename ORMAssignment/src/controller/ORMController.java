/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBConnection;
import db.DBHandler;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class ORMController {

    public int save(String db, String userName, String password, Object ob) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, SQLException {
        int res = 0;
        try {

            Field[] fields = ob.getClass().getDeclaredFields();
            String val = "";
            for (Field field : fields) {
                field.setAccessible(true);

                val += "'" + (field.get(ob) + "") + "',";

            }
            val = val.substring(0, val.length() - 1);
            String sql = "INSERT INTO " + ob.getClass().getSimpleName() + " Values (" + val + ")";
            res = DBHandler.setData(DBConnection.getDBConnection(db, userName, password), sql);

        } catch (SQLException ex) {

            String sql = "CREATE TABLE `" + ob.getClass().getSimpleName() + "` (";
            Field[] fields = ob.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                sql += "    " + (field.getName()) + " varchar(100) NOT NULL,\n";
            }
            sql += "    PRIMARY KEY (" + fields[0].getName() + ")";

            sql += ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
            res = DBHandler.setData(DBConnection.getDBConnection(db, userName, password), sql);
            String val = "";
            for (Field field : fields) {
                field.setAccessible(true);

                val += "'" + (field.get(ob) + "") + "',";

            }
            val = val.substring(0, val.length() - 1);
            String sql1 = "INSERT INTO " + ob.getClass().getSimpleName() + " Values (" + val + ")";
            res = DBHandler.setData(DBConnection.getDBConnection(db, userName, password), sql1);

        }

        return res;

    }

    public int dropTable(String db, String userName, String password, Object ob) throws ClassNotFoundException, SQLException {
        String sql = "DROP TABLE IF EXISTS " + ob.getClass().getSimpleName() + "";
        int res = DBHandler.setData(DBConnection.getDBConnection(db, userName, password), sql);
        return res;

    }

    public int update(String db, String userName, String password, Object ob) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, SQLException {
        int res = 0;
        try {

            Field[] fields = ob.getClass().getDeclaredFields();
            String val = "";
            for (Field field : fields) {
                field.setAccessible(true);
                if (field != fields[0]) {
                    val += "" + field.getName() + "=' " + (field.get(ob) + "") + "',";
                }

            }
            val = val.substring(0, val.length() - 1);
            String sql = "UPDATE " + ob.getClass().getSimpleName() + " SET  " + val + " WHERE " + fields[0].getName() + "=  '" + (fields[0].get(ob) + "") + "'";
            System.out.println(sql);
            res = DBHandler.setData(DBConnection.getDBConnection(db, userName, password), sql);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return res;

    }

    public int delete(String db, String userName, String password, Object ob) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, SQLException {
        Field[] fields = ob.getClass().getDeclaredFields();
        fields[0].setAccessible(true);
        String sql = "Delete From " + ob.getClass().getSimpleName() + " where  " + fields[0].getName() + "='" + fields[0].get(ob) + "'";

        int res = DBHandler.setData(DBConnection.getDBConnection(db, userName, password), sql);
        return res;

    }

    public void getAllData(String db, String userName, String password, Object ob) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM " + ob.getClass().getSimpleName() + "";
        System.out.println(sql);
        ResultSet rs = DBHandler.getData(DBConnection.getDBConnection(db, userName, password), sql);
        if (rs != null) {
//            if (rs.next()) {
//
//                ResultSetMetaData rsmd = rs.getMetaData();
//                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//                    if (i > 1) {
//                        System.out.print(",");
//                    }
//                    System.out.print(rsmd.getColumnName(i));
//                }
//            }
            System.out.println("");
            while (rs.next()) {
                
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    if (i > 1) {
                        System.out.print(",");
                    }

                    System.out.print(rs.getString(i));

                }

                System.out.println("");
            }
        }
    }

}
