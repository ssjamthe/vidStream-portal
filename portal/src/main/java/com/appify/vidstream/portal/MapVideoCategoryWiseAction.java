/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 *
 * @author Nileh Diore
 */
public class MapVideoCategoryWiseAction extends ActionSupport {

    private Connection con;
    private String Map_video_AppId, Map_video_ID, Map_videos_Categorization_Name, Map_videos_Video_Category, last_child_category;
    PreparedStatement pst_map_insert;

    public String getLast_child_category() {
        return last_child_category;
    }

    public void setLast_child_category(String last_child_category) {
        this.last_child_category = last_child_category;
    }

    public MapVideoCategoryWiseAction() {
    }

    public String getMap_video_AppId() {
        return Map_video_AppId;
    }

    public void setMap_video_AppId(String Map_video_AppId) {
        this.Map_video_AppId = Map_video_AppId;
    }

    public String getMap_video_ID() {
        return Map_video_ID;
    }

    public void setMap_video_ID(String Map_video_ID) {
        this.Map_video_ID = Map_video_ID;
    }

    public String getMap_videos_Categorization_Name() {
        return Map_videos_Categorization_Name;
    }

    public void setMap_videos_Categorization_Name(String Map_videos_Categorization_Name) {
        this.Map_videos_Categorization_Name = Map_videos_Categorization_Name;
    }

    public String getMap_videos_Video_Category() {
        return Map_videos_Video_Category;
    }

    public void setMap_videos_Video_Category(String Map_videos_Video_Category) {
        this.Map_videos_Video_Category = Map_videos_Video_Category;
    }

    @Override
    public String execute() throws Exception {

        try {
            String youtubeid, AppID, Map_catogory, lst_cat;
            youtubeid = Map_video_ID;
            AppID = Map_video_AppId;
            Map_catogory = Map_videos_Video_Category;
            lst_cat = last_child_category;

            System.out.println("youtubeid" + youtubeid);
            System.out.println("AppID" + AppID);
            System.out.println("Map_catogory" + Map_catogory);
            System.out.println("last_child_category" + last_child_category);


            con = com.appify.vidstream.portal.util.DataConnection.getConnection();

           String SQl_getMapcatid = "select id from category where name='" + last_child_category + "'";
            PreparedStatement pst_Mapcatid = con.prepareStatement(SQl_getMapcatid);
            ResultSet rs_Mapcatid = pst_Mapcatid.executeQuery();
            rs_Mapcatid.next();
            int Map_category_id = rs_Mapcatid.getInt(1);



            try {
                String check_data_into_video_cat_mapping = "SELECT  category_id, video_id\n"
                        + "  FROM youtube_video_category_mapping where category_id='" + Map_category_id + "' and video_id='" + youtubeid + "' ";
                System.out.println("check_data_into_video_cat_mapping::-" + check_data_into_video_cat_mapping);
                PreparedStatement pst_check_data = con.prepareStatement(check_data_into_video_cat_mapping);
                ResultSet rs_check_data = pst_check_data.executeQuery();
                if (!rs_check_data.next()) {
                    String SQL_video_category_mapping = "insert into youtube_video_category_mapping(category_id,video_id) values(?,?)";
                    pst_map_insert = con.prepareStatement(SQL_video_category_mapping);
                    pst_map_insert.setInt(1, Map_category_id);
                    pst_map_insert.setString(2, youtubeid);
                    pst_map_insert.executeUpdate();
                    System.out.println("added successfully in youtube_video_category_mapping");

                } else {
                    System.out.println("Allready category and Video available in youtube_video_category_mapping ");
                }

            } catch (Exception exp) {
                System.out.println("Exception:::" + exp);
            }




            String getvideo_attribute_vcount = "select id from video_attribute where name='views_count'";
            PreparedStatement pst_attribute_vcount = con.prepareStatement(getvideo_attribute_vcount);
            ResultSet rs_attribute_vcount = pst_attribute_vcount.executeQuery();
            rs_attribute_vcount.next();
            int vcount_ref_id = rs_attribute_vcount.getInt(1);
            String getvideo_attribute_published_date = "select id from video_attribute where name='published_date'";
            PreparedStatement pst_attribute_pdate = con.prepareStatement(getvideo_attribute_published_date);
            ResultSet rs_attribute_pdate = pst_attribute_pdate.executeQuery();
            rs_attribute_pdate.next();
            int pdate_ref_id = rs_attribute_pdate.getInt(1);

            try {

                String check_data_for_video_attribute_category_mappings = "SELECT id, video_attribute_id, category_id FROM video_attribute_category_mappings where category_id='" + Map_category_id + "' and video_attribute_id in('" + vcount_ref_id + "','" + pdate_ref_id + "')";
                System.out.println("check_data_for_video_attribute_category_mappings:" + check_data_for_video_attribute_category_mappings);
                PreparedStatement pst_check_data_for_video_attribute_category_mappings = con.prepareStatement(check_data_for_video_attribute_category_mappings);
                ResultSet rs_check_data_for_video_attribute_category_mappings = pst_check_data_for_video_attribute_category_mappings.executeQuery();
                if (!rs_check_data_for_video_attribute_category_mappings.next()) {

                    String SQL_insert_video_attribute_category_mappings_vc = "insert into video_attribute_category_mappings(video_attribute_id,category_id) values(?,?)";
                    PreparedStatement pst_video_attribute_category_mappings_vc = con.prepareStatement(SQL_insert_video_attribute_category_mappings_vc);
                    pst_video_attribute_category_mappings_vc.setInt(2, Map_category_id);
                    pst_video_attribute_category_mappings_vc.setInt(1, vcount_ref_id);
                    pst_video_attribute_category_mappings_vc.executeUpdate();
                    System.out.println("added in video_attribute_category_mappings for vcount_ref_id:-" + vcount_ref_id);

                    String SQL_insert_video_attribute_category_mappings_pd = "insert into video_attribute_category_mappings(video_attribute_id,category_id) values(?,?)";
                    PreparedStatement pst_video_attribute_category_mappings_pd = con.prepareStatement(SQL_insert_video_attribute_category_mappings_pd);
                    pst_video_attribute_category_mappings_pd.setInt(2, Map_category_id);
                    pst_video_attribute_category_mappings_pd.setInt(1, pdate_ref_id);
                    pst_video_attribute_category_mappings_pd.executeUpdate();
                    System.out.println("added in video_attribute_category_mappings for pdate_ref_id:- " + pdate_ref_id);

                } else {
                    System.out.println("Allready category and Video_Attributes_id available in video_attribute_category_mappings ");

                }

            } catch (Exception exp) {
                System.out.println("Exception:::::" + exp);
            }
            return SUCCESS;
        } catch (SQLException | ClassNotFoundException exp) {
            System.out.println("Exception" + exp);
            return ERROR;
        } finally {
            try {
                pst_map_insert.close();
            } catch (Exception e) {
                System.out.println("INNER EXCEPTION1" + e);
            }
            try {
                if (null != con) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Last Exception" + ex);
            }
        }


    }
}