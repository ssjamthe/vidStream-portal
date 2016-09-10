/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.net.httpserver.Authenticator;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Nileh Diore
 */
public class AddNewAppPropertyAction extends ActionSupport {

    private String Sql_Insert;
    private Connection con;
    private PreparedStatement pst_insert;
    private String property_name, property_value;

    public AddNewAppPropertyAction() {
    }

    public String getProperty_name() {
        return property_name;
    }

    public void setProperty_name(String property_name) {
        this.property_name = property_name;
    }

    public String getProperty_value() {
        return property_value;
    }

    public void setProperty_value(String property_value) {
        this.property_value = property_value;
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public String execute() throws Exception {
        try {
            Sql_Insert = "INSERT INTO property_table(\n"
                    + "            prop_name, prop_value)\n"
                    + "    VALUES (?, ?); ";
            con = com.appify.vidstream.portal.util.DataConnection.getConnection();
            
           pst_insert = con.prepareStatement(Sql_Insert);
           pst_insert.setString(1, property_name);
            pst_insert.setString(2, property_value);
           pst_insert.executeUpdate();
            System.out.println("Added Successfully in property_table");
            
        } catch (Exception e) {
            
             System.out.println("Exception in try for insert----"+e);
             return ERROR;
        }

      return SUCCESS;

    }
}