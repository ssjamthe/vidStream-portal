/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Nileh Diore
 */
public class UpdateApplicationStatusAction extends ActionSupport {
     private Connection con;
    private ResultSet rs_cmp;
     PreparedStatement prest;
      private String hidden_update_app_id, update_status,Sql_app_update;
    public UpdateApplicationStatusAction() {
    }

    public String getHidden_update_app_id() {
        return hidden_update_app_id;
    }

    public void setHidden_update_app_id(String hidden_update_app_id) {
        this.hidden_update_app_id = hidden_update_app_id;
    }

    public String getUpdate_status() {
        return update_status;
    }

    public void setUpdate_status(String update_status) {
        this.update_status = update_status;
    }
    
    @Override
    public String execute() throws Exception {
            try {
            con = null;
            Sql_app_update = null;
            prest = null;

            SimpleDateFormat originalFormat;

            con = com.appify.vidstream.portal.util.DataConnection.getConnection();
           
            String App_id = getHidden_update_app_id();
            String status_update = getUpdate_status();

                 Sql_app_update = "update application set status=? where id='" + App_id + "'";

                prest = con.prepareStatement(Sql_app_update);
               prest.setString(1, status_update);
                prest.executeUpdate();
           
            System.out.println("Status Update in application table");

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return ERROR;
        } finally {
            try {
                prest.close();

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
        return SUCCESS;
    }
}