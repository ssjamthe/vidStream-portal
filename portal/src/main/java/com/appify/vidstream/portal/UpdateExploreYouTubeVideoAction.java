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

/**
 *
 * @author Nileh Diore
 */
public class UpdateExploreYouTubeVideoAction extends ActionSupport {

    private Connection con;
    private ResultSet rs_upload_img;
    private String sql, hidden_ExpYTupdate_video_Id, ExpYTupdate_Video_Name, ExpYTupdate_Video_Category,ExpYTupdate_video_Id;
    PreparedStatement prest,pst_map_insert;
    private String ExpYTupdate_Video_view_count,ExpYTupdate_Video_published_date,Edit_ExpYTupdate_Application_Name,ExpYTubeupdate_Categorization_Name;

    public UpdateExploreYouTubeVideoAction() {
    }

    public String getExpYTupdate_Video_view_count() {
        return ExpYTupdate_Video_view_count;
    }

    public void setExpYTupdate_Video_view_count(String ExpYTupdate_Video_view_count) {
        this.ExpYTupdate_Video_view_count = ExpYTupdate_Video_view_count;
    }

    public String getExpYTupdate_Video_published_date() {
        return ExpYTupdate_Video_published_date;
    }

    public void setExpYTupdate_Video_published_date(String ExpYTupdate_Video_published_date) {
        this.ExpYTupdate_Video_published_date = ExpYTupdate_Video_published_date;
    }

    public String getEdit_ExpYTupdate_Application_Name() {
        return Edit_ExpYTupdate_Application_Name;
    }

    public void setEdit_ExpYTupdate_Application_Name(String Edit_ExpYTupdate_Application_Name) {
        this.Edit_ExpYTupdate_Application_Name = Edit_ExpYTupdate_Application_Name;
    }

    public String getExpYTubeupdate_Categorization_Name() {
        return ExpYTubeupdate_Categorization_Name;
    }

    public void setExpYTubeupdate_Categorization_Name(String ExpYTubeupdate_Categorization_Name) {
        this.ExpYTubeupdate_Categorization_Name = ExpYTubeupdate_Categorization_Name;
    }

    public String getExpYTupdate_video_Id() {
        return ExpYTupdate_video_Id;
    }

    public void setExpYTupdate_video_Id(String ExpYTupdate_video_Id) {
        this.ExpYTupdate_video_Id = ExpYTupdate_video_Id;
    }

    public String getHidden_ExpYTupdate_video_Id() {
        return hidden_ExpYTupdate_video_Id;
    }

    public void setHidden_ExpYTupdate_video_Id(String hidden_ExpYTupdate_video_Id) {
        this.hidden_ExpYTupdate_video_Id = hidden_ExpYTupdate_video_Id;
    }

    public String getExpYTupdate_Video_Name() {
        return ExpYTupdate_Video_Name;
    }

    public void setExpYTupdate_Video_Name(String ExpYTupdate_Video_Name) {
        this.ExpYTupdate_Video_Name = ExpYTupdate_Video_Name;
    }

    public String getExpYTupdate_Video_Category() {
        return ExpYTupdate_Video_Category;
    }

    public void setExpYTupdate_Video_Category(String ExpYTupdate_Video_Category) {
        this.ExpYTupdate_Video_Category = ExpYTupdate_Video_Category;
    }

    @Override
    public String execute() throws Exception {
        try {
            con = null;
            sql = null;
            prest = null;
            con = com.appify.vidstream.portal.util.DataConnection.getConnection();

            String vid = getHidden_ExpYTupdate_video_Id();
            String VName = getExpYTupdate_Video_Name();
            String Category_name = getExpYTupdate_Video_Category();
            System.out.println("vid:::::" + vid);
            sql = "update youtube_video set name =? where id='" + vid + "'";
            prest = con.prepareStatement(sql);
            prest.setString(1, VName);
            prest.executeUpdate();
            System.out.println("Update Successfully in youtube_video Table with Videiid " + vid);
            String sql_getcategory_id="select id from category where name='"+Category_name+"' ";
            PreparedStatement prepareStatement_catid = con.prepareStatement(sql_getcategory_id);
            ResultSet rs_catid = prepareStatement_catid.executeQuery();
            rs_catid.next();
            String CATID= rs_catid.getString(1);
            String sql_get_ref_videioid="select id  from youtube_video where id='"+vid+"'";
            PreparedStatement prepareStatement_ref_videoid = con.prepareStatement(sql_get_ref_videioid);
            ResultSet rs_ref_videioid = prepareStatement_ref_videoid.executeQuery();
            rs_ref_videioid.next();
            String REFVID=rs_ref_videioid.getString(1);
            
             String SQL_video_category_mapping = "update youtube_video_category_mapping set category_id=? where video_id='" +Integer.parseInt(REFVID)+"' ";
            pst_map_insert = con.prepareStatement(SQL_video_category_mapping);
            pst_map_insert.setInt(1, Integer.parseInt(CATID));
            pst_map_insert.executeUpdate();
            System.out.println("Update successfully in youtube_video_category_mapping");

        } catch (Exception exp) {
            System.out.println("Exception in UpdateVideoAction::::" + exp);
            return ERROR;
        } finally {
            try {
                prest.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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