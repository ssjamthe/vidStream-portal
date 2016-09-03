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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Nileh Diore
 */
public class AddVideoLinkAction extends ActionSupport {

    private ResultSet rs_st, rs_categories, rs_map_insert;
    PreparedStatement prest, prest_up, prst_categories, pst_map_insert;
    Statement stmt;
    private Connection con;
    private String hidden_video_id_new, video_title, new_category_id, video_published_date, video_views_count;
    private String sql_insert;
    private String current_video_id, current_video_name, current_video_pdate, current_video_vcount;
    private String updated_video_name;

    public String getVideo_published_date() {
        return video_published_date;
    }

    public void setVideo_published_date(String video_published_date) {
        this.video_published_date = video_published_date;
    }

    public String getVideo_views_count() {
        return video_views_count;
    }

    public void setVideo_views_count(String video_views_count) {
        this.video_views_count = video_views_count;
    }

    public String getNew_category_id() {
        return new_category_id;
    }

    public void setNew_category_id(String new_category_id) {
        this.new_category_id = new_category_id;
    }

    public AddVideoLinkAction() {
    }

    public String getHidden_video_id_new() {
        return hidden_video_id_new;
    }

    public void setHidden_video_id_new(String hidden_video_id_new) {
        this.hidden_video_id_new = hidden_video_id_new;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    @Override
    public String execute() throws Exception {
        con = null;
        sql_insert = null;
        prest = null;

        try {
            int cate = Integer.parseInt(new_category_id);
            ArrayList<String> vidlistitems = new ArrayList<String>(Arrays.asList(hidden_video_id_new.split("\\s*,\\s*")));
            ArrayList<String> vidnamelistitems = new ArrayList<String>(Arrays.asList(video_title.split("\\s*,\\s*")));
            ArrayList<String> vidpdlistitems = new ArrayList<String>(Arrays.asList(video_published_date.split("\\s*,\\s*")));
            ArrayList<String> vidvcntlistitems = new ArrayList<String>(Arrays.asList(video_views_count.split("\\s*,\\s*")));
            con = conn.appify.vidstream.portal.DataConnection.getConnection();
            for (int i = 0; i < vidlistitems.size(); i++) {
                current_video_id = vidlistitems.get(i);
                System.out.println("current_video_id----------" + current_video_id);
                current_video_name = vidnamelistitems.get(i);
                System.out.println("current_video_name----------" + current_video_name);
                if ((current_video_name.contains(""))) {
                    System.out.println("Loop 1");
                    updated_video_name = current_video_name.replaceAll("[\"]", "");
                    /// System.out.println(result);

                    //current_video_name.replace('\"', ' ');
                    System.out.println("Lopp current_video_name" + updated_video_name);
                } else {
                }
                current_video_pdate = vidpdlistitems.get(i);
                System.out.println("current_video_pdate----------" + current_video_pdate);
                current_video_vcount = vidvcntlistitems.get(i);
                System.out.println("current_video_vcount----------" + current_video_vcount);


                try {
                    sql_insert = "INSERT INTO youtube_video(id,name) VALUES (?,?)";
                    System.out.println("sql_insert:::" + sql_insert);
                    prest = con.prepareStatement(sql_insert);
                    prest.setString(1, current_video_id);
                    prest.setString(2, updated_video_name);
                    prest.executeUpdate();
                    System.out.println("added successfully in youtube_video");
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


                try {
                    SimpleDateFormat originalFormat;
                    java.util.Date date = new java.util.Date();
                    System.out.println(new Timestamp(date.getTime()));
                    Timestamp mod_date = new Timestamp(date.getTime());


                    String sql_update_mod_date = "UPDATE category set date_modified=?  where id='" + cate + "'";
                    PreparedStatement pst_update_mod_date = con.prepareStatement(sql_update_mod_date);
                    pst_update_mod_date.setTimestamp(1, mod_date);
                    System.out.println("sql_update_mod_date-----------" + sql_update_mod_date);
                    pst_update_mod_date.executeUpdate();

                    int parent_category_ID;
                    parent_category_ID = 0;
                    int temp_parent_category_ID;

                    String SQl_get_Parent_cat = "SELECT parent_category_id FROM parent_child_category_mappings where child_category_id='" + cate + "'";
                    PreparedStatement pst_get_Parent_cat = con.prepareStatement(SQl_get_Parent_cat);
                    ResultSet rs_get_Parent_cat = pst_get_Parent_cat.executeQuery();
                    boolean loop_test = true;
                    boolean loop_break_flag = true;
                    while (rs_get_Parent_cat.next()) {
                        parent_category_ID = rs_get_Parent_cat.getInt(1);
                        temp_parent_category_ID = parent_category_ID;
                        String sql_out_loop_mod_date = "UPDATE category set date_modified=?  where id='" + parent_category_ID + "'";
                        PreparedStatement pst_out_loop_mod_date = con.prepareStatement(sql_out_loop_mod_date);
                        pst_out_loop_mod_date.setTimestamp(1, mod_date);
                        System.out.println("sql_update_mod_date-----------" + sql_out_loop_mod_date);
                        pst_out_loop_mod_date.executeUpdate();
                        while (loop_test) {
                                   loop_break_flag = true;

                            String SQl_get_Parent_Subcat = "SELECT parent_category_id FROM parent_child_category_mappings where child_category_id='" + temp_parent_category_ID + "'";
                            PreparedStatement pst_sub_Parent_child_cat = con.prepareStatement(SQl_get_Parent_Subcat);
                            ResultSet rs_sub_Parent_child_cat = pst_sub_Parent_child_cat.executeQuery();
                            while (rs_sub_Parent_child_cat.next()) {
                                int loop_parent_id = rs_sub_Parent_child_cat.getInt(1);
                                temp_parent_category_ID = loop_parent_id;
                                String sql_loop_mod_date = "UPDATE category set date_modified=?  where id='" + temp_parent_category_ID + "'";
                                PreparedStatement pst_loop_mod_date = con.prepareStatement(sql_loop_mod_date);
                                pst_loop_mod_date.setTimestamp(1, mod_date);
                                System.out.println("sql_update_mod_date-----------" + sql_loop_mod_date);
                                pst_loop_mod_date.executeUpdate();
                                System.out.println("temp_parent_category_ID---------------------" + temp_parent_category_ID);
                                loop_break_flag = false;
                            }
                            if (loop_break_flag == true) {
                                break;
                            }
                        }


                    }

                    String SQl_get_categorization = "SELECT categorization_id FROM category  where id='" + cate + "'";
                    PreparedStatement pst_get_catzion = con.prepareStatement(SQl_get_categorization);
                    ResultSet rs_get_catzion = pst_get_catzion.executeQuery();
                    rs_get_catzion.next();

                    String SQl_getappid = "select app_id from categorization where id='" + rs_get_catzion.getInt(1) + "'";
                    PreparedStatement pst_getappid = con.prepareStatement(SQl_getappid);
                    ResultSet rs_getappid = pst_getappid.executeQuery();
                    rs_getappid.next();


                    String sql_updateCatzion_mod_date = "UPDATE categorization set date_modified=?  where id='" + rs_get_catzion.getInt(1) + "'";

                    PreparedStatement pst_update_Catzionmod_date = con.prepareStatement(sql_updateCatzion_mod_date);
                    pst_update_Catzionmod_date.setTimestamp(1, mod_date);
                    System.out.println("sql_update_mod_date-----------" + sql_updateCatzion_mod_date);
                    pst_update_Catzionmod_date.executeUpdate();

                    String sql_app_update_mod_date = "UPDATE application set date_modified=?  where id='" + rs_getappid.getInt(1) + "'";

                    PreparedStatement pst_update_appmod_date = con.prepareStatement(sql_app_update_mod_date);
                    pst_update_appmod_date.setTimestamp(1, mod_date);
                    System.out.println("sql_update_mod_date-----------" + sql_app_update_mod_date);
                    pst_update_appmod_date.executeUpdate();

                } catch (Exception exp) {
                    System.out.println("Exception in try----" + exp);
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


                String compare_date = "1980-01-01";
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
                        pst_video_attribute_value_vc.setInt(3, Integer.parseInt(current_video_vcount));
                        pst_video_attribute_value_vc.executeUpdate();
                        System.out.println("added successfully in video_attribute_value for attribute_id" + vcount_ref_id);

                        String SQL_video_attribute_value_pd = "insert into video_attribute_value(video_id,attribute_id,value) values(?,?,?)";
                        PreparedStatement pst_video_attribute_value_pd = con.prepareStatement(SQL_video_attribute_value_pd);
                        pst_video_attribute_value_pd.setString(1, current_video_id);
                        pst_video_attribute_value_pd.setInt(2, pdate_ref_id);
                        pst_video_attribute_value_pd.setInt(3, total_day);
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
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception" + e);
            return "error";
        } finally {
            try {

                stmt.close();
                prest.close();
            } catch (Exception e) {
            }
            try {
                rs_st.close();

            } catch (Exception e) {
            }
            try {
                if (null != con) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Exception" + ex);
            }
        }

    }
}