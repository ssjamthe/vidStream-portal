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
import java.util.Iterator;
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
public class ListAllVideoAction extends ActionSupport implements SessionAware {

    private String SQl_list_videos, categoies_Name, SQl_getvideo_id, category_id, SQL_Child_Category_details;
    private Connection con;
    String encodedImage, encodedImage1;
    private PreparedStatement pstmt, prst_categories, pst_child_category_details, pst_getimg;
    private ResultSet rs, rs_categories, rs_child_category_details, rs_get_img;
    private SessionMap<String, Object> sessionMap;
    private List cate_video_list, video_id_list, child_category_list, nested_list;
    private String nested_category_name;
    private String category_name;
    HttpSession session = ServletActionContext.getRequest().getSession(false);

    public String getCategoies_Name() {
        return categoies_Name;
    }

    public void setCategoies_Name(String categoies_Name) {
        this.categoies_Name = categoies_Name;
    }

    public ListAllVideoAction() {
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public String execute() throws Exception {
        try {
            cate_video_list = new ArrayList();
            video_id_list = new ArrayList();
            child_category_list = new ArrayList();
            nested_list = new ArrayList();
            category_id = getCategoies_Name();
            System.out.println("category_name inside ListAllVideoAction catid--------::::" + category_id);
            con = com.appify.vidstream.portal.util.DataConnection.getConnection();
            String SQL_get_cate_id = "select name from category where id='" + category_id + "'";
            prst_categories = con.prepareStatement(SQL_get_cate_id);
            rs_categories = prst_categories.executeQuery();
            rs_categories.next();
            category_name = rs_categories.getString(1);
            // int cat_id = Integer.parseInt(category_id);

            nested_list = (ArrayList) session.getAttribute("nested_categorization");
            // System.out.println("Upadted nested_list::" + nested_list);


            nested_category_name = (String) session.getAttribute("category_name");
            //System.out.println("nested_category_name::::::::" + nested_category_name);




            SQl_getvideo_id = "select video_id from youtube_video_category_mapping where category_id='" + category_id + "'";
            pstmt = con.prepareStatement(SQl_getvideo_id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SQl_list_videos = "select id,name,date(date_added) from youtube_video where id='" + rs.getString(1) + "'";
                PreparedStatement prepareStatement = con.prepareStatement(SQl_list_videos);
                ResultSet executeQuery = prepareStatement.executeQuery();
                while (executeQuery.next()) {
                    cate_video_list.add(executeQuery.getString(1));
                    cate_video_list.add(executeQuery.getString(2));
                    try {
                        String getvid_view_count = "SELECT value FROM video_attribute_value where video_id='" + executeQuery.getString(1) + "' and attribute_id =(SELECT id  FROM video_attribute where name='views_count');";
                        System.out.println("getvid_view_count---------------->>>" + getvid_view_count);
                        PreparedStatement pst_getvid_views_count = con.prepareStatement(getvid_view_count);
                        ResultSet rs_vid_view_count = pst_getvid_views_count.executeQuery();
                        rs_vid_view_count.next();
                        cate_video_list.add(rs_vid_view_count.getInt(1));
                    } catch (Exception exp) {
                        System.err.println("Exceptipn in getvid_view_count---"+exp);
                    }
                    String convert_you_tube_added_date = "";
                    try {
                        String yt_date_added = executeQuery.getString(3);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date tempyt_date_added = simpleDateFormat.parse(yt_date_added);
                        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-YYYY");
                        convert_you_tube_added_date = outputDateFormat.format(tempyt_date_added);
                        System.out.println("Output date is = " + outputDateFormat.format(tempyt_date_added));
                    } catch (ParseException ex) {
                        System.out.println("Parse Exception");
                    }
                    cate_video_list.add(convert_you_tube_added_date);
                }
            }
            String SQL_getchild_Category = "select child_category_id from parent_child_category_mappings where parent_category_id='" + category_id + "' ";
            PreparedStatement pst_child_category = con.prepareStatement(SQL_getchild_Category);
            ResultSet rs_child_category = pst_child_category.executeQuery();
            while (rs_child_category.next()) {
                SQL_Child_Category_details = "select name, image, date(date_created),date(date_modified),id from category where id='" + rs_child_category.getInt(1) + "'";
                pst_child_category_details = con.prepareStatement(SQL_Child_Category_details);
                rs_child_category_details = pst_child_category_details.executeQuery();
                while (rs_child_category_details.next()) {
                    child_category_list.add(rs_child_category_details.getString(1));
                    child_category_list.add(rs_child_category_details.getString(5));

                    String SQL_get_img = "select contents from images where id='" + rs_child_category_details.getString("image") + "' ";
                    pst_getimg = con.prepareStatement(SQL_get_img);
                    rs_get_img = pst_getimg.executeQuery();
                    while (rs_get_img.next()) {
                        byte[] rb = rs_get_img.getBytes("contents");
                        encodedImage = Base64.encode(rb);
                        child_category_list.add(encodedImage);

                    }
                    String SQL_video = "select count(*) from youtube_video_category_mapping where category_id='" + rs_child_category.getInt(1) + "'";
                    PreparedStatement pst_tot_video = con.prepareStatement(SQL_video);
                    ResultSet rs_ot_videos = pst_tot_video.executeQuery();
                    rs_ot_videos.next();
                    child_category_list.add(rs_ot_videos.getString(1));

                    String convert_category_create_date = "", convert_category_modified_date = "";

                    try {
                        String cat_currentDate = rs_child_category_details.getString(3);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date tempcat_currentDate = simpleDateFormat.parse(cat_currentDate);
                        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-YYYY");
                        convert_category_create_date = outputDateFormat.format(tempcat_currentDate);
                        System.out.println("convert_category_create_date date is = " + outputDateFormat.format(tempcat_currentDate));
                    } catch (ParseException ex) {
                        System.out.println("Parse Exception");
                    }

                    child_category_list.add(convert_category_create_date);

                    try {
                        String cat_modifiedDate = rs_child_category_details.getString(4);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date tempcat_modifiedDate = simpleDateFormat.parse(cat_modifiedDate);
                        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-YYYY");
                        convert_category_modified_date = outputDateFormat.format(tempcat_modifiedDate);
                        System.out.println("convert_category_modified_date date is = " + outputDateFormat.format(tempcat_modifiedDate));
                    } catch (ParseException ex) {
                        System.out.println("Parse Exception" + ex);
                    }


                    child_category_list.add(convert_category_modified_date);

                }

            }


            String SQL_video_list = "select distinct id from youtube_video";
            PreparedStatement pst_video_list = con.prepareStatement(SQL_video_list);
            ResultSet rs_video_list = pst_video_list.executeQuery();
            //System.out.println("SQL_video_list:::" + SQL_video_list);
            while (rs_video_list.next()) {
                video_id_list.add(rs_video_list.getString(1));
            }


            if (nested_list.isEmpty()) {
                nested_list.add(category_id);
                nested_list.add(category_name);



                //  System.out.println("Arrayylist is empty:--------------");

            } else {

                if (nested_list.contains(category_name)) {
                    //  System.out.println("Available::::::::::---" + category_name);
                    Iterator<String> itr = nested_list.iterator();
                    /*    for (Iterator<String> iter = nested_list.listIterator(); iter.hasNext();) {
                     String clik = iter.next();
                     if (!clik.equals(category_name)) {
                     System.out.println("inside remove logic");
                     System.out.println("category-------"+clik);
                     System.out.println("Index no of arralist----" + nested_list.indexOf(clik));
                     iter.remove();
                     } else {
                     System.out.println("inside equals category" + clik);
                     }

                     }*/
                    while (itr.hasNext()) {
                        String elemt = itr.next().toString();
                        //System.out.println("Element iterator---" + elemt);
                        if (category_name.equals(elemt)) {
                            System.out.println("Selected Element ---" + elemt);
                            // itr.remove();
                            while (itr.hasNext()) {

                                System.out.println("delete elemnet---" + itr.next());
                                itr.remove();
                            }
                            System.out.println("updated list" + nested_list);

                        }

                    }

                } else {
                    nested_list.add(category_id);
                    nested_list.add(category_name);

                    System.out.println("Added category name:::::::::" + category_name);


                }

            }

            sessionMap.put("category_name", category_name);
            sessionMap.put("nested_list", nested_list);
            sessionMap.put("video_id_list", video_id_list);
            sessionMap.put("cate_video_list", cate_video_list);
            sessionMap.put("cateory_id", category_id);

            System.out.println("Updated nested List---::::::::::---" + nested_list);
            sessionMap.put("child_category_list", child_category_list);

            return SUCCESS;
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            System.out.println("exception" + e);
            return ERROR;
        } finally {
            try {
                rs.close();
                prst_categories.close();
                pstmt.close();
            } catch (Exception e) {
                System.out.println("exception in finally try" + e);
            }
            
            try {
                if (null != con) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }


    }

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap) map; //To change body of generated methods, choose Tools | Templates.
    }
}