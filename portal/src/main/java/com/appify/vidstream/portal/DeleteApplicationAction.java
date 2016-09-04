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
public class DeleteApplicationAction extends ActionSupport {

    private Connection con;
    private String hidden_app_id;

    public DeleteApplicationAction() {
    }

    public String getHidden_app_id() {
        return hidden_app_id;
    }

    public void setHidden_app_id(String hidden_app_id) {
        this.hidden_app_id = hidden_app_id;
    }

    @Override
    public String execute() throws Exception {
        try {
            con = null;

            con = com.appify.vidstream.portal.util.DataConnection.getConnection();

            String app_id = getHidden_app_id();
            String sql = "DELETE FROM application WHERE id='" + app_id + "'";
            System.out.println("sql_for delete application:::" + sql);
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