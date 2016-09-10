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
public class DeleteExploreYouTubeVideoAction extends ActionSupport {

    private Connection con;
    private String hidden_explorevideoID, hidden_explorevideoCategoryID, hidden_explorevideoAppID;

    public DeleteExploreYouTubeVideoAction() {
    }

    public String getHidden_explorevideoID() {
        return hidden_explorevideoID;
    }

    public void setHidden_explorevideoID(String hidden_explorevideoID) {
        this.hidden_explorevideoID = hidden_explorevideoID;
    }

    public String getHidden_explorevideoCategoryID() {
        return hidden_explorevideoCategoryID;
    }

    public void setHidden_explorevideoCategoryID(String hidden_explorevideoCategoryID) {
        this.hidden_explorevideoCategoryID = hidden_explorevideoCategoryID;
    }

    public String getHidden_explorevideoAppID() {
        return hidden_explorevideoAppID;
    }

    public void setHidden_explorevideoAppID(String hidden_explorevideoAppID) {
        this.hidden_explorevideoAppID = hidden_explorevideoAppID;
    }

    @Override
    public String execute() throws Exception {
        try {
            con = null;
            con = com.appify.vidstream.portal.util.DataConnection.getConnection();

            String vid = getHidden_explorevideoID();

            String getappID = "select id from application where name='" + hidden_explorevideoAppID + "'";
            PreparedStatement pst_getappID = con.prepareStatement(getappID);
            ResultSet rs_getAppId = pst_getappID.executeQuery();
            rs_getAppId.next();

            String getcatid = "select id from category where name='" + hidden_explorevideoCategoryID + "'";
            PreparedStatement pst_getcatid = con.prepareStatement(getcatid);
            ResultSet rs_getcatid = pst_getcatid.executeQuery();
            rs_getcatid.next();

            String getcategorization = "select id from categorization where app_id='" + rs_getAppId.getInt(1) + "'";
            PreparedStatement pst_get_categorizationID = con.prepareStatement(getcategorization);
            ResultSet rs_getcatgorizationID = pst_get_categorizationID.executeQuery();
            rs_getcatgorizationID.next();

            String SQL_getSelected_video_category = "select id from category where name='" + hidden_explorevideoCategoryID + "' and categorization_id='" + rs_getcatgorizationID.getInt(1) + "' ";
            PreparedStatement pst_getSelected_video_category = con.prepareStatement(SQL_getSelected_video_category);
            ResultSet rs_getSelected_video_categoryuery = pst_getSelected_video_category.executeQuery();
            rs_getSelected_video_categoryuery.next();

            String SQL_Check_Video_mapping_Category = "SELECT category_id FROM youtube_video_category_mapping where video_id='" + vid + "'";
            PreparedStatement pst_check_video_cat_mapping = con.prepareStatement(SQL_Check_Video_mapping_Category);
            ResultSet rs_check_video_cat_mapping = pst_check_video_cat_mapping.executeQuery();

            if (!rs_check_video_cat_mapping.next()) {
                String sql = "DELETE FROM youtube_video WHERE id ='" + vid + "'";
                System.out.println("sql_for delete youtube_video:::" + sql);
                Statement createStatement = con.createStatement();
                createStatement.execute(sql);

            } else {
                String SQL_Delete_video_category_mapping = "DELETE FROM youtube_video_category_mapping where category_id='" + rs_getSelected_video_categoryuery.getInt(1) + "' and video_id='" + vid + "' ";
                System.out.println("SQL_Delete_video_category_mapping in ExploreYouTube:::" + SQL_Delete_video_category_mapping);
                Statement createStatement_mapping_cat = con.createStatement();
                createStatement_mapping_cat.execute(SQL_Delete_video_category_mapping);
                
                
                
                    String SQL_Inside_Check_Video_mapping_Category = "SELECT category_id FROM youtube_video_category_mapping where video_id='" + vid + "'";
            PreparedStatement pst_Inside_check_video_cat_mapping = con.prepareStatement(SQL_Inside_Check_Video_mapping_Category);
            ResultSet rs_Inside_check_video_cat_mapping = pst_Inside_check_video_cat_mapping.executeQuery();
            
            if(!rs_Inside_check_video_cat_mapping.next()){
                   String sql_lnside = "DELETE FROM youtube_video WHERE id ='" + vid + "'";
                System.out.println("sql_lnside delete youtube_video :::" + sql_lnside);
                Statement createStatement_inside = con.createStatement();
                createStatement_inside.execute(sql_lnside);
            
            }else{
            
            
            }
                
                
                

            }

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