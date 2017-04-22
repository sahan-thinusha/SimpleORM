/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ormassignment;

import controller.ORMController;
import controller.PropertiesController;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Book;

/**
 *
 * @author HP
 */
public class ORMAssignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Map prop = PropertiesController.viewProperties();
            String database = prop.get("database") + "";
            String userName = prop.get("userName") + "";
            String password = prop.get("password") + "";
            String stretagy = prop.get("strategy") + "";
            ORMController orm = new ORMController();
            Book b = new Book("1", "Java");
            int x = 0;
            if ("Create".equals(stretagy)) {
                // x = orm.save(database,userName,password, b);  
            } else {
                orm.dropTable(database, userName, password, b);
                x = orm.save(database, userName, password, b);
            }
            orm.getAllData(database, userName, password, b);
            // x = orm.update(database,userName,password, b);
            // x = orm.delete(database,userName,password,b);
            System.out.println(x);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ORMAssignment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ORMAssignment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ORMAssignment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ORMAssignment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
