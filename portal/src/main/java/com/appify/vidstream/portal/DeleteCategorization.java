/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Nileh Diore
 */
public class DeleteCategorization extends ActionSupport {

    private Connection con;
    private String hidden_app_id, hidden_categorization_name, hidden_categorization_id;

    public String getHidden_categorization_id() {
        return hidden_categorization_id;
    }

    public void setHidden_categorization_id(String hidden_categorization_id) {
        this.hidden_categorization_id = hidden_categorization_id;
    }

    public String getHidden_app_id() {
        return hidden_app_id;
    }

    public void setHidden_app_id(String hidden_app_id) {
        this.hidden_app_id = hidden_app_id;
    }

    public String getHidden_categorization_name() {
        return hidden_categorization_name;
    }

    public void setHidden_categorization_name(String hidden_categorization_name) {
        this.hidden_categorization_name = hidden_categorization_name;
    }

    public DeleteCategorization() {
    }

    public String execute() throws Exception {
        try {
            con = null;




            con = com.appify.vidstream.portal.util.DataConnection.getConnection();

            String app_id = getHidden_app_id();
            String cat_name = getHidden_categorization_name();
            String cat_id = getHidden_categorization_id();



            try {
                String oldChar = "\'";
                String newChar = "\'\'";
                if (cat_name.contains("'")) {
                    cat_name = cat_name.replace(oldChar, newChar);
                    System.out.println("New Generated Category_name" + cat_name.replace(oldChar, newChar));
                } else {
                }
            } catch (Exception exp1) {
                System.out.println("Exception in New Generating Category_name");
            }


            String sql = "DELETE FROM categorization WHERE id ='" + Integer.parseInt(cat_id) + "' and name='" + cat_name + "'";
            System.out.println("sql_for delete categorization:::" + sql);
            Statement createStatement = con.createStatement();
            createStatement.execute(sql);
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return ERROR;
        } finally {
            try {


                con.close();
            } catch (Exception e) {
                try {
                    if (null != con) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        }
    }
}