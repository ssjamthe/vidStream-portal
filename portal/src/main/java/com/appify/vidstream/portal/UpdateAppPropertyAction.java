/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Nileh Diore
 */
public class UpdateAppPropertyAction extends ActionSupport {
    
     private Connection con;
    private ResultSet rs_update;
    PreparedStatement  pst_update;
    Statement stmt;
    private String hidden_edit_prop_name, edit_prop_value;
    private String SQl_update;
    public UpdateAppPropertyAction() {
    }

    public String getHidden_edit_prop_name() {
        return hidden_edit_prop_name;
    }

    public void setHidden_edit_prop_name(String hidden_edit_prop_name) {
        this.hidden_edit_prop_name = hidden_edit_prop_name;
    }

    public String getEdit_prop_value() {
        return edit_prop_value;
    }

    public void setEdit_prop_value(String edit_prop_value) {
        this.edit_prop_value = edit_prop_value;
    }
    
     @Override
    public String execute() throws Exception {
          con = com.appify.vidstream.portal.util.DataConnection.getConnection();
         try {
             SQl_update="update property_table set prop_name=?,prop_value=? where prop_name='"+hidden_edit_prop_name+"' ";
           pst_update =  con.prepareStatement(SQl_update);
           pst_update.setString(1, hidden_edit_prop_name);
              pst_update.setString(2, edit_prop_value);
              pst_update.executeUpdate();
              System.out.println("Update Successfully");
             
         } catch (Exception e) {
             
             System.out.println("Exception in Try------------"+e);
             return ERROR;
         }
         return SUCCESS;
        
    }
}