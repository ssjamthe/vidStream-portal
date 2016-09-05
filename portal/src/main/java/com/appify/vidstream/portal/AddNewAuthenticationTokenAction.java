/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Nileh Diore
 */
public class AddNewAuthenticationTokenAction extends ActionSupport {

    private String Sql_Insert;
    private Connection con;
    private PreparedStatement pst_insert;
    private String auth_app_name, auth_app_id, auth_token, auth_token_description,hidden_auth_app_id;

    public String getHidden_auth_app_id() {
        return hidden_auth_app_id;
    }

    public void setHidden_auth_app_id(String hidden_auth_app_id) {
        this.hidden_auth_app_id = hidden_auth_app_id;
    }

    public AddNewAuthenticationTokenAction() {
    }

    public String getAuth_app_name() {
        return auth_app_name;
    }

    public void setAuth_app_name(String auth_app_name) {
        this.auth_app_name = auth_app_name;
    }

    public String getAuth_app_id() {
        return auth_app_id;
    }

    public void setAuth_app_id(String auth_app_id) {
        this.auth_app_id = auth_app_id;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    public String getAuth_token_description() {
        return auth_token_description;
    }

    public void setAuth_token_description(String auth_token_description) {
        this.auth_token_description = auth_token_description;
    }

    @Override
    public String execute() throws Exception {
        try {
            Sql_Insert = "INSERT INTO auth_token(\n"
                    + "            token, app_id, description)\n"
                    + "    VALUES ( ?, ?, ?);";
            con = com.appify.vidstream.portal.util.DataConnection.getConnection();

            pst_insert = con.prepareStatement(Sql_Insert);
            pst_insert.setString(1, auth_token);
            pst_insert.setInt(2, Integer.parseInt(hidden_auth_app_id));
             pst_insert.setString(3, auth_token_description);
            pst_insert.executeUpdate();
            System.out.println("Added Successfully in auth_token");

        } catch (Exception e) {

            System.out.println("Exception in try for insert in auth_token----" + e);
            return ERROR;
        }finally {
            try {

            	pst_insert.close();
            } catch (Exception e) {
            }
            try {
                if (null != con) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Exception" + ex);
            }
        }

        return SUCCESS;
    }
}