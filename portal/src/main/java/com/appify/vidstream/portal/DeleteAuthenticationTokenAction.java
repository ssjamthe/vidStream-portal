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
public class DeleteAuthenticationTokenAction extends ActionSupport {
    
    public DeleteAuthenticationTokenAction() {
    }
      private Connection con;
    private String hidden_del_auth_sr_id,hidden_del_auth_app_id,hidden_del_auth_app_auth_token;
    @Override
    
    public String execute() throws Exception {
           try {
            con = com.appify.vidstream.portal.util.DataConnection.getConnection();

            String sql = "DELETE FROM auth_token WHERE id='" + hidden_del_auth_sr_id + "' and app_id='"+hidden_del_auth_app_id+"' and token='"+hidden_del_auth_app_auth_token+"'";
            System.out.println("sql_for delete auth_token:::" + sql);
            Statement createStatement = con.createStatement();
            createStatement.execute(sql);
              System.out.println("Delete auth_token  Successfully");
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return ERROR;
        } finally {
                try {
                    if (null != con) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                }
            
        }
    }

    public String getHidden_del_auth_sr_id() {
        return hidden_del_auth_sr_id;
    }

    public void setHidden_del_auth_sr_id(String hidden_del_auth_sr_id) {
        this.hidden_del_auth_sr_id = hidden_del_auth_sr_id;
    }

    public String getHidden_del_auth_app_id() {
        return hidden_del_auth_app_id;
    }

    public void setHidden_del_auth_app_id(String hidden_del_auth_app_id) {
        this.hidden_del_auth_app_id = hidden_del_auth_app_id;
    }

    public String getHidden_del_auth_app_auth_token() {
        return hidden_del_auth_app_auth_token;
    }

    public void setHidden_del_auth_app_auth_token(String hidden_del_auth_app_auth_token) {
        this.hidden_del_auth_app_auth_token = hidden_del_auth_app_auth_token;
    }
}