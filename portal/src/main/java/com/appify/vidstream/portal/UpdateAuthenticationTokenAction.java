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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Nileh Diore
 */
public class UpdateAuthenticationTokenAction extends ActionSupport {
        private Connection con;
    private ResultSet rs_update;
    PreparedStatement  pst_update;
    Statement stmt;
    private String hidden_edit_auth_sr_id, eddit_auth_app_name,hidden_edit_auth_app_id,edit_auth_token,edit_auth_token_description;
    private String SQl_update;
    public UpdateAuthenticationTokenAction() {
    }

 

    public String getHidden_edit_auth_sr_id() {
        return hidden_edit_auth_sr_id;
    }

    public void setHidden_edit_auth_sr_id(String hidden_edit_auth_sr_id) {
        this.hidden_edit_auth_sr_id = hidden_edit_auth_sr_id;
    }

    public String getEddit_auth_app_name() {
        return eddit_auth_app_name;
    }

    public void setEddit_auth_app_name(String eddit_auth_app_name) {
        this.eddit_auth_app_name = eddit_auth_app_name;
    }

    public String getHidden_edit_auth_app_id() {
        return hidden_edit_auth_app_id;
    }

    public void setHidden_edit_auth_app_id(String hidden_edit_auth_app_id) {
        this.hidden_edit_auth_app_id = hidden_edit_auth_app_id;
    }

    public String getEdit_auth_token() {
        return edit_auth_token;
    }

    public void setEdit_auth_token(String edit_auth_token) {
        this.edit_auth_token = edit_auth_token;
    }

    public String getEdit_auth_token_description() {
        return edit_auth_token_description;
    }

    public void setEdit_auth_token_description(String edit_auth_token_description) {
        this.edit_auth_token_description = edit_auth_token_description;
    }
    
        @Override
    public String execute() throws Exception {
            try{   
        	con = com.appify.vidstream.portal.util.DataConnection.getConnection();
               System.out.println("hidden_edit_auth_sr_id----"+hidden_edit_auth_sr_id);
                System.out.println("hidden_edit_auth_app_id----"+hidden_edit_auth_app_id); 
                System.out.println("edit_auth_token----"+edit_auth_token);
                        System.out.println("edit_auth_token_description----"+edit_auth_token_description);
             SQl_update="update auth_token set token=?,app_id=?,description=? where id='"+hidden_edit_auth_sr_id+"' ";
           pst_update =  con.prepareStatement(SQl_update);
           pst_update.setString(1, edit_auth_token);
              pst_update.setInt(2, Integer.parseInt(hidden_edit_auth_app_id));
               pst_update.setString(3, edit_auth_token_description);
              pst_update.executeUpdate();
              System.out.println("Update Successfully");
             
         } catch (Exception e) {
             System.out.println("Exception in Try------------"+e);
             return ERROR;
         }finally {
             try {
                 if (null != con) {
                     con.close();
                 }
             } catch (SQLException ex) {
                 ex.printStackTrace(System.out);
             }
         }
         return SUCCESS;
    }
}