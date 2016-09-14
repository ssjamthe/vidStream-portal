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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.catalina.util.Base64;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Nileh Diore
 */
public class ListAllCategories extends ActionSupport implements SessionAware {

    private Connection con;
    private PreparedStatement stmt, pst_getimg, pst_app_id, pst_video_list;
    private ResultSet rs, rs_get_img, rs_app_id, rs_video_list;
    private String SQL, categorization, cat_name, SQl_video_list, categorization_name;
    String encodedImage, encodedImage1;
    private List categories_list, all_video_list, nested_list;
    private List<Integer> child_category_list;
    private SessionMap<String, Object> sessionMap;
    private String categorization_ID;
    private String nested_category_name;
    int previous_video_count;
    int val;
    int total_video_count;
    HttpSession session = ServletActionContext.getRequest().getSession(false);

    public String getCategorization() {
        return categorization;
    }

    public void setCategorization(String categorization) {
        this.categorization = categorization;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public ListAllCategories() {
    }

    @Override
    public String execute() throws Exception {
        try {
            previous_video_count = 0;
            int middle_vid_count = 0;
            val = 0;
            total_video_count = 0;
            child_category_list = new <Integer> ArrayList();
            nested_list = new ArrayList();
            //categorization_name = getCat_name();
            categorization_ID = getCat_name();
            String SQL_get_cat_id = "select name from categorization where id='" + categorization_ID + "'";

            con = com.appify.vidstream.portal.util.DataConnection.getConnection();
            PreparedStatement pst_getcat_id = con.prepareStatement(SQL_get_cat_id);
            ResultSet rs_getcat_id = pst_getcat_id.executeQuery();
            rs_getcat_id.next();
            categorization_name = rs_getcat_id.getString(1);

            String SQl_child_category_list = "select distinct child_category_id from parent_child_category_mappings order by child_category_id asc";
            PreparedStatement pst_child_category_list = con.prepareStatement(SQl_child_category_list);
            ResultSet rs_child_category_list = pst_child_category_list.executeQuery();
            while (rs_child_category_list.next()) {
                child_category_list.add(rs_child_category_list.getInt(1));
            }


            String SQL_App_id = "Select app_id from categorization  where name ='" + categorization_name + "' ";
            pst_app_id = con.prepareStatement(SQL_App_id);
            rs_app_id = pst_app_id.executeQuery();
            rs_app_id.next();
            categories_list = new ArrayList();
            all_video_list = new ArrayList();

            SQL = "SELECT id, name,image,date(date_created),date(date_modified) FROM category where categorization_id='" + categorization_ID + "' order by date(date_modified) desc;";
            // System.out.println("SQL-----------------------------------" + SQL);
            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                boolean match_found = true;
                int catid = rs.getInt(1);
                for (int k = 0; k < child_category_list.size(); k++) {
                    if (child_category_list.get(k) == catid) {
                        match_found = false;
                    }

                }
                if (match_found == true) {
                    categories_list.add(rs_app_id.getString("app_id"));
                    categories_list.add(rs.getString("name"));
                    categories_list.add(rs.getString("id"));
                    String SQL_get_img = "select contents from images where id='" + rs.getString("image") + "' ";
                    pst_getimg = con.prepareStatement(SQL_get_img);
                    rs_get_img = pst_getimg.executeQuery();
                    while (rs_get_img.next()) {
                        byte[] rb = rs_get_img.getBytes("contents");
                        encodedImage = Base64.encode(rb);
                        categories_list.add(encodedImage);
                    }

                    String SQL_video = "select count(*) from youtube_video_category_mapping where category_id='" + Integer.parseInt(rs.getString("id")) + "'";
                    System.err.println("SQL_video::::::" + SQL_video);
                    PreparedStatement pst_tot_video = con.prepareStatement(SQL_video);
                    ResultSet rs_ot_videos = pst_tot_video.executeQuery();
                    rs_ot_videos.next();
                    System.out.println("count of videos::::::" + rs_ot_videos.getString(1));
                    previous_video_count = Integer.parseInt(rs_ot_videos.getString(1));
                    val = Integer.parseInt(rs.getString("id"));
                    boolean loop_test = true;
                    boolean inner_loop_test = true;
                    boolean loop_break_flag = true;
                    boolean inner_loop_break_flag = true;
                    while (loop_test) {
                        loop_break_flag = true;
                        String SQL_check_child_cat = "SELECT child_category_id FROM parent_child_category_mappings where parent_category_id='" + val + "'";
                        System.out.println("SQL_check_child_cat----------" + SQL_check_child_cat);
                        PreparedStatement pst_check_child_cat = con.prepareStatement(SQL_check_child_cat);
                        ResultSet rs_check_chilld_cat = pst_check_child_cat.executeQuery();
                        while (rs_check_chilld_cat.next()) {
                            inner_loop_test =true;
                            int val_temp = rs_check_chilld_cat.getInt(1);
                            System.out.println("val_temp---------------------" + val_temp);
                            String SQl_get_video_count1 = "SELECT count(video_id) FROM youtube_video_category_mapping where category_id='" + val_temp + "' ;";
                            System.out.println("SQl_get_video_count1------------" + SQl_get_video_count1);
                            PreparedStatement pst_get_video_count1 = con.prepareStatement(SQl_get_video_count1);
                            ResultSet rs_get_video_count1 = pst_get_video_count1.executeQuery();
                            rs_get_video_count1.next();
                            middle_vid_count = rs_get_video_count1.getInt(1);
                            System.out.println("middle_vid_count---" + middle_vid_count);
                              boolean empty_llist_cat = true;
                            while (inner_loop_test) {
                                String SQL_Insidecheck_child_cat = "SELECT child_category_id FROM parent_child_category_mappings where parent_category_id='" + val_temp + "'";
                                System.out.println("SQL_Insidecheck_child_cat----------" + SQL_Insidecheck_child_cat);
                                PreparedStatement pst_insidecheck_child_cat = con.prepareStatement(SQL_Insidecheck_child_cat);
                                ResultSet rs_Insidecheck_chilld_cat = pst_insidecheck_child_cat.executeQuery();
                              
                                while (rs_Insidecheck_chilld_cat.next()) {
                                    val_temp = rs_Insidecheck_chilld_cat.getInt(1);
                                    System.out.println("rs_Insidecheck_chilld_" + rs_Insidecheck_chilld_cat.getInt(1));
                                    String SQl_get_video_count = "SELECT count(video_id) FROM youtube_video_category_mapping where category_id='" + val_temp + "' ;";
                                    System.out.println("SQl_get_video_count------------" + SQl_get_video_count);
                                    PreparedStatement pst_get_video_count = con.prepareStatement(SQl_get_video_count);
                                    ResultSet rs_get_video_count = pst_get_video_count.executeQuery();
                                    rs_get_video_count.next();
                                    int test_total_video_count = rs_get_video_count.getInt(1);
                                    total_video_count = total_video_count + test_total_video_count;
                                    empty_llist_cat = true;
                                }
                                if (empty_llist_cat) {
                                    // Empty result set
                                    inner_loop_test = false;
                                    inner_loop_break_flag = false;
                                    break;
                                }
                             
                                if (inner_loop_break_flag == false) {
                                   
                                     // inner_loop_test = false;
                                    break;
                                }
                            }

                        }
                        if (loop_break_flag == true) {
                            break;
                        }
                    }
                    total_video_count = total_video_count + previous_video_count + middle_vid_count;
                    System.out.println(" total_video_count-----" + total_video_count);
                    System.out.println(" previous_video_count----" + previous_video_count);
                    System.out.println(" middle_vid_count----" + middle_vid_count);
                    categories_list.add(total_video_count);
                    String convert_create_date = "", convert_modified_date = "";
                    total_video_count = 0;
                    previous_video_count = 0;
                    middle_vid_count = 0;
                    try {
                        String createDate = rs.getString(4);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date tempcreateDate = simpleDateFormat.parse(createDate);
                        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-YYYY");
                        convert_create_date = outputDateFormat.format(tempcreateDate);
                        //System.out.println("Output date is = " + outputDateFormat.format(tempcreateDate));
                    } catch (ParseException ex) {
                        System.out.println("Parse Exception" + ex);
                    }
                    categories_list.add(convert_create_date);

                    try {
                        String modifiedDate = rs.getString(5);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date tempmodifiedDate = simpleDateFormat.parse(modifiedDate);
                        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-YYYY");
                        convert_modified_date = outputDateFormat.format(tempmodifiedDate);
                        //System.out.println("Output date is = " + outputDateFormat.format(tempmodifiedDate));
                    } catch (ParseException ex) {
                        System.out.println("Parse Exception" + ex);
                    }



                    categories_list.add(convert_modified_date);
                } else {
                }
            }


            SQl_video_list = "select id,name,date(date_added) from youtube_video ";
            System.out.println("SQl_video_list:::" + SQl_video_list);
            pst_video_list = con.prepareStatement(SQl_video_list);
            rs_video_list = pst_video_list.executeQuery();
            while (rs_video_list.next()) {

                all_video_list.add(rs_video_list.getString(1));
                all_video_list.add(rs_video_list.getString(2));
                all_video_list.add(rs_video_list.getString(3));

            }


            List test = new ArrayList();
            //session.removeAttribute("category_name");
            sessionMap.put("nested_categorization", test);
            sessionMap.put("all_video_list", all_video_list);
            sessionMap.put("categories_list", categories_list);

            sessionMap.put("categorization_name", categorization_name);
            sessionMap.put("categorization_ID", categorization_ID);
        } catch (Exception e) {
            System.out.println("exception" + e);
            return ERROR;
        } finally {
            try {
                rs.close();
                stmt.close();

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
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap) map; //To change body of generated methods, choose Tools | Templates.
    }
}