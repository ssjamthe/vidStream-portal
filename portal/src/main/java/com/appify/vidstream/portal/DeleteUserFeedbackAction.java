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
public class DeleteUserFeedbackAction extends ActionSupport {
     private Connection con;
    private String hidden_del_view_appid,hidden_del_view_device_id,hidden_del_view_user_feedback;
    public DeleteUserFeedbackAction() {
    }

    public String getHidden_del_view_appid() {
        return hidden_del_view_appid;
    }

    public void setHidden_del_view_appid(String hidden_del_view_appid) {
        this.hidden_del_view_appid = hidden_del_view_appid;
    }

    public String getHidden_del_view_device_id() {
        return hidden_del_view_device_id;
    }

    public void setHidden_del_view_device_id(String hidden_del_view_device_id) {
        this.hidden_del_view_device_id = hidden_del_view_device_id;
    }

    public String getHidden_del_view_user_feedback() {
        return hidden_del_view_user_feedback;
    }

    public void setHidden_del_view_user_feedback(String hidden_del_view_user_feedback) {
        this.hidden_del_view_user_feedback = hidden_del_view_user_feedback;
    }
    
     @Override
    public String execute() throws Exception {
           try {
            con = null;
            con = conn.appify.vidstream.portal.DataConnection.getConnection();

            String sql = "DELETE FROM feedback WHERE app_id='" + hidden_del_view_appid + "' and device_id='"+hidden_del_view_device_id+"' and user_comment='"+hidden_del_view_user_feedback+"'";
            System.out.println("sql_for delete feedback:::" + sql);
            Statement createStatement = con.createStatement();
            createStatement.execute(sql);
              System.out.println("Delete feedback  Successfully");
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