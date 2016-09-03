/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conn.appify.vidstream.portal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Nileh Diore
 */
public class DataConnection {

    private static Connection conn;
    private static String DB_DRIVER, DB_SERVER, DB_PORT, DB_NAME, DB_USER, DB_PASSWORD;

    public static void getConfig() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            Path path =FileSystems.getDefault().getPath("/appify/conf/vidStreamPortal/conf.properties");
           // System.out.println("path---"+path);
            File f= new File(path.toString());
            input = new FileInputStream(f);
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            DB_DRIVER = prop.getProperty("DB_DRIVER");
            DB_SERVER = prop.getProperty("DB_SERVER");
            DB_PORT = prop.getProperty("DB_PORT");
            DB_NAME = prop.getProperty("DB_NAME");
            DB_USER = prop.getProperty("DB_USER");
            DB_PASSWORD = prop.getProperty("DB_PASSWORD");
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

    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        getConfig();
        Class.forName("org.postgresql.Driver");
        String url = "" + DB_DRIVER + "://" + DB_SERVER + ":" + DB_PORT + "/" + DB_NAME + "?user=" + DB_USER + "&password=" + DB_PASSWORD;
        Properties props = new Properties();
        props.setProperty("loglevel", "0");
        conn = DriverManager.getConnection(url, props);
        System.out.println("Connected to: " + url);
        return conn;
    }
}
