/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author HP
 */
public class PropertiesController {

    public void writeProperties(String db, String u, String p, int s) {
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("./src/config.properties");

            // set the properties value
            prop.setProperty("database", db);
            prop.setProperty("userName", u);
            prop.setProperty("password", p);
            if (s == 1) {
                prop.setProperty("strategy", "Drop & Create");
            } else {
                prop.setProperty("strategy", "Create");
            }

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();

        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static Map viewProperties() {
        Properties prop = new Properties();
        InputStream input = null;
        Map propa = new HashMap();

        try {

            input = new FileInputStream("./src/config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("database"));
            System.out.println(prop.getProperty("userName"));
            System.out.println(prop.getProperty("password"));
            System.out.println(prop.getProperty("strategy"));

            propa.put("database", prop.getProperty("database"));
            propa.put("userName", prop.getProperty("userName"));
            propa.put("password", prop.getProperty("password"));
            propa.put("strategy", prop.getProperty("strategy"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return propa;
    }

}
