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
public class DeleteVideoAction extends ActionSupport {

    private Connection con;
    private String hidden_video_id;

    public DeleteVideoAction() {
    }

    public String getHidden_video_id() {
        return hidden_video_id;
    }

    public void setHidden_video_id(String hidden_video_id) {
        this.hidden_video_id = hidden_video_id;
    }

    @Override
    public String execute() throws Exception {
        try {
            con = null;
            con = conn.appify.vidstream.portal.DataConnection.getConnection();

            String vid = getHidden_video_id();
            String sql = "DELETE FROM youtube_video WHERE id ='" + vid + "'";
            System.out.println("sql_for delete youtube_video:::" + sql);
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