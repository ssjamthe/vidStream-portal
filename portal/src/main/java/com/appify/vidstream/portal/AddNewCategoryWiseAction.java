/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Nileh Diore
 */
public class AddNewCategoryWiseAction extends ActionSupport {

    private ResultSet rs_st, rs_categories, rs_map_insert;
    PreparedStatement prest, prest_up, prst_categories, pst_map_insert;
    Statement stmt;
    private Connection con;
    private String hidden_Explore_video_id, explore_video_title, explore_video_category_wise;
    private String sql_insert;
    private String explore_video_published_date, explore_video_view_count, ExpYTupdate_Application_Name, ExpYTube_Categorization;
    private String current_video_id, current_video_pdate, current_video_vcount, current_video_name;
    private String Exp_last_child_category;
    private String updated_video_name;

    public String getExp_last_child_category() {
        return Exp_last_child_category;
    }

    public void setExp_last_child_category(String Exp_last_child_category) {
        this.Exp_last_child_category = Exp_last_child_category;
    }

    public String getExpYTube_Categorization() {
        return ExpYTube_Categorization;
    }

    public void setExpYTube_Categorization(String ExpYTube_Categorization) {
        this.ExpYTube_Categorization = ExpYTube_Categorization;
    }

    public String getExpYTupdate_Application_Name() {
        return ExpYTupdate_Application_Name;
    }

    public void setExpYTupdate_Application_Name(String ExpYTupdate_Application_Name) {
        this.ExpYTupdate_Application_Name = ExpYTupdate_Application_Name;
    }

    public String getExplore_video_view_count() {
        return explore_video_view_count;
    }

    public void setExplore_video_view_count(String explore_video_view_count) {
        this.explore_video_view_count = explore_video_view_count;
    }

    public String getExplore_video_published_date() {
        return explore_video_published_date;
    }

    public void setExplore_video_published_date(String explore_video_published_date) {
        this.explore_video_published_date = explore_video_published_date;
    }

    public AddNewCategoryWiseAction() {
    }

    public String getExplore_video_title() {
        return explore_video_title;
    }

    public void setExplore_video_title(String explore_video_title) {
        this.explore_video_title = explore_video_title;
    }

    public String getExplore_video_category_wise() {
        return explore_video_category_wise;
    }

    public void setExplore_video_category_wise(String explore_video_category_wise) {
        this.explore_video_category_wise = explore_video_category_wise;
    }

    public String getHidden_Explore_video_id() {
        return hidden_Explore_video_id;
    }

    public void setHidden_Explore_video_id(String hidden_Explore_video_id) {
        this.hidden_Explore_video_id = hidden_Explore_video_id;
    }

    @Override
    public String execute() throws Exception {
        System.out.println("hidden_Explore_video_id---------------" + hidden_Explore_video_id);
        System.out.println("explore_video_title--------------------" + explore_video_title);
        System.out.println("explore_video_published_date---=======" + explore_video_published_date);
        System.out.println("explore_video_view_count---------------" + explore_video_view_count);

        System.out.println("Exp_last_child_category---------------" + Exp_last_child_category);

        ArrayList<String> vidlistitems = new ArrayList<String>(Arrays.asList(hidden_Explore_video_id.split("\\s*,\\s*")));
        ArrayList<String> vidnamelistitems = new ArrayList<String>(Arrays.asList(explore_video_title.split("\\s*,\\s*")));
        ArrayList<String> vidpdlistitems = new ArrayList<String>(Arrays.asList(explore_video_published_date.split("\\s*,\\s*")));
        ArrayList<String> vidvcntlistitems = new ArrayList<String>(Arrays.asList(explore_video_view_count.split("\\s*,\\s*")));
        con = conn.appify.vidstream.portal.DataConnection.getConnection();



        try {
            String explore_you_tube_video_count;

            for (int i = 0; i < vidlistitems.size(); i++) {
                current_video_id = vidlistitems.get(i);
                current_video_name = vidnamelistitems.get(i);
                System.out.println("Before Video Name-------------" + current_video_name);
                // current_video_name.startsWith("\"");
                //current_video_name.endsWith("\"");

                if ((current_video_name.contains(""))) {
                    System.out.println("Loop 1");
                   updated_video_name = current_video_name.replaceAll("[\"]", "");
                   /// System.out.println(result);

                    //current_video_name.replace('\"', ' ');
                    System.out.println("Lopp current_video_name" + updated_video_name);
                } else {
                    
                }
                
                current_video_pdate = vidpdlistitems.get(i);
                current_video_vcount = vidvcntlistitems.get(i);
                explore_you_tube_video_count = vidvcntlistitems.get(i);
                String SQl_getcatid = "select id from category where name='" + Exp_last_child_category + "'";
                PreparedStatement pst_getcatid = con.prepareStatement(SQl_getcatid);
                ResultSet rs_getcatid = pst_getcatid.executeQuery();
                rs_getcatid.next();
                String category_id = rs_getcatid.getString(1);
                int cate = Integer.parseInt(category_id);
                try {
                    sql_insert = "INSERT INTO youtube_video(id,name) VALUES (?,?)";
                    System.out.println("sql_insert:::" + sql_insert);
                    prest = con.prepareStatement(sql_insert);
                    prest.setString(1, current_video_id);
                    prest.setString(2, updated_video_name);
                    prest.executeUpdate();
                    System.out.println(" added successfully in youtube_video");
                } catch (Exception exp) {
                    System.out.println("Allready in Database in youtube_video" + current_video_id);
                    System.out.println("Exception in try for insert in youtube_video" + exp);
                }

                try {
                    String check_data_into_video_cat_mapping = "SELECT  category_id, video_id\n"
                            + "  FROM youtube_video_category_mapping where category_id='" + cate + "' and video_id='" + current_video_id + "' ";
                    System.out.println("check_data_into_video_cat_mapping::-" + check_data_into_video_cat_mapping);
                    PreparedStatement pst_check_data = con.prepareStatement(check_data_into_video_cat_mapping);
                    ResultSet rs_check_data = pst_check_data.executeQuery();
                    if (!rs_check_data.next()) {
                        String SQL_video_category_mapping = "insert into youtube_video_category_mapping(category_id,video_id) values(?,?)";
                        pst_map_insert = con.prepareStatement(SQL_video_category_mapping);
                        pst_map_insert.setInt(1, cate);
                        pst_map_insert.setString(2, current_video_id);
                        pst_map_insert.executeUpdate();
                        System.out.println("added successfully in youtube_video_category_mapping");
                    } else {
                        System.out.println("Allready category and Video available in youtube_video_category_mapping ");
                    }


                } catch (Exception exp) {
                    System.out.println("Expection in try" + exp);
                }

                 SimpleDateFormat originalFormat;
            java.util.Date date = new java.util.Date();
            System.out.println(new Timestamp(date.getTime()));
            Timestamp mod_date = new Timestamp(date.getTime());

             
                String sql_update_mod_date = "UPDATE category set date_modified=?  where id='" + cate + "'";
                
                PreparedStatement pst_update_mod_date = con.prepareStatement(sql_update_mod_date);
                pst_update_mod_date.setTimestamp(1, mod_date);
                System.out.println("sql_update_mod_date-----------"+sql_update_mod_date);
                pst_update_mod_date.executeUpdate();
                

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

                    String check_data_for_video_attribute_category_mappings = "SELECT id, video_attribute_id, category_id FROM video_attribute_category_mappings where category_id='" + cate + "' and video_attribute_id in('" + vcount_ref_id + "','" + pdate_ref_id + "')";
                    System.out.println("check_data_for_video_attribute_category_mappings:" + check_data_for_video_attribute_category_mappings);
                    PreparedStatement pst_check_data_for_video_attribute_category_mappings = con.prepareStatement(check_data_for_video_attribute_category_mappings);
                    ResultSet rs_check_data_for_video_attribute_category_mappings = pst_check_data_for_video_attribute_category_mappings.executeQuery();

                    if (!rs_check_data_for_video_attribute_category_mappings.next()) {

                        String SQL_insert_video_attribute_category_mappings_vc = "insert into video_attribute_category_mappings(video_attribute_id,category_id) values(?,?)";
                        PreparedStatement pst_video_attribute_category_mappings_vc = con.prepareStatement(SQL_insert_video_attribute_category_mappings_vc);
                        pst_video_attribute_category_mappings_vc.setInt(2, cate);
                        pst_video_attribute_category_mappings_vc.setInt(1, vcount_ref_id);
                        pst_video_attribute_category_mappings_vc.executeUpdate();
                        System.out.println("added in video_attribute_category_mappings for vcount_ref_id:-" + vcount_ref_id);

                        String SQL_insert_video_attribute_category_mappings_pd = "insert into video_attribute_category_mappings(video_attribute_id,category_id) values(?,?)";
                        PreparedStatement pst_video_attribute_category_mappings_pd = con.prepareStatement(SQL_insert_video_attribute_category_mappings_pd);
                        pst_video_attribute_category_mappings_pd.setInt(2, cate);
                        pst_video_attribute_category_mappings_pd.setInt(1, pdate_ref_id);
                        pst_video_attribute_category_mappings_pd.executeUpdate();
                        System.out.println("added in video_attribute_category_mappings for pdate_ref_id:- " + pdate_ref_id);


                    } else {
                        System.out.println("Allready category and Video_Attributes_id available in video_attribute_category_mappings ");
                    }
                } catch (Exception exp) {
                    System.out.println("Exception:::::" + exp);
                }



                String compare_date = "1980-01-01 12:38:45.000";
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d1 = null;
                Date d2 = null;

                d1 = format.parse(compare_date);
                d2 = format.parse(current_video_pdate);

                //in milliseconds
                long diff = d2.getTime() - d1.getTime();

                // long diffSeconds = diff / 1000 % 60;
                // long diffMinutes = diff / (60 * 1000) % 60;
                //long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);

                System.out.print(diffDays + " days, ");

                Long obj = new Long(diffDays);

                // returns the value of this Long as an int
                int total_day = obj.intValue();
                System.out.println("Value of i = " + total_day);

                try {
                    String check_data_for_video_attribute_value = "SELECT  distinct video_id, attribute_id, value FROM video_attribute_value where video_id='" + current_video_id + "'";
                    PreparedStatement pst_check_data_for_video_attribute_value = con.prepareStatement(check_data_for_video_attribute_value);
                    ResultSet rs_check_data_for_video_attribute_value = pst_check_data_for_video_attribute_value.executeQuery();

                    if (!rs_check_data_for_video_attribute_value.next()) {

                        String SQL_video_attribute_value_vc = "insert into video_attribute_value(video_id,attribute_id,value) values(?,?,?)";
                        PreparedStatement pst_video_attribute_value_vc = con.prepareStatement(SQL_video_attribute_value_vc);
                        pst_video_attribute_value_vc.setString(1, current_video_id);
                        pst_video_attribute_value_vc.setInt(2, vcount_ref_id);
                        pst_video_attribute_value_vc.setInt(3, Integer.parseInt(explore_you_tube_video_count));
                        System.out.println("SQL_video_attribute_value_vc----------------------------" + SQL_video_attribute_value_vc);
                        pst_video_attribute_value_vc.executeUpdate();
                        System.out.println("added successfully in video_attribute_value for attribute_id" + vcount_ref_id);

                        String SQL_video_attribute_value_pd = "insert into video_attribute_value(video_id,attribute_id,value) values(?,?,?)";
                        PreparedStatement pst_video_attribute_value_pd = con.prepareStatement(SQL_video_attribute_value_pd);
                        pst_video_attribute_value_pd.setString(1, current_video_id);
                        pst_video_attribute_value_pd.setInt(2, pdate_ref_id);
                        pst_video_attribute_value_pd.setInt(3, total_day);
                        System.out.println("SQL_video_attribute_value_pd----------------------------" + SQL_video_attribute_value_pd);
                        pst_video_attribute_value_pd.executeUpdate();
                        System.out.println("added successfully in video_attribute_value for attribute_id" + pdate_ref_id);
                    } else {
                        System.out.println("Allready Video and its attributes available in video_attribute_value ");

                    }


                } catch (Exception exp) {
                    System.out.println("Allready in Database in youtube_video " + current_video_id);
                    System.out.println("Exception in try for insert in video_attribute_value" + exp);
                }

            }

            con.close();
            return SUCCESS;
        } catch (Exception exp) {
            System.out.println("Exception" + exp);
            return ERROR;
        } finally {
            try {
                prest.close();
                pst_map_insert.close();
            } catch (Exception e) {
                System.out.println("INNER EXCEPTION1" + e);
            }
            try {
            } catch (Exception e) {
                System.out.println("INNER EXCEPTION2" + e);
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