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
public class DeleteExploreYouTubeVideoAction extends ActionSupport {
     private Connection con;
    private String hidden_explorevideoID;
    public DeleteExploreYouTubeVideoAction() {
    }

    public String getHidden_explorevideoID() {
        return hidden_explorevideoID;
    }

    public void setHidden_explorevideoID(String hidden_explorevideoID) {
        this.hidden_explorevideoID = hidden_explorevideoID;
    }
    
     @Override
    public String execute() throws Exception {
       try {
            con = null;
            con = com.appify.vidstream.portal.util.DataConnection.getConnection();

            String vid = getHidden_explorevideoID();
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