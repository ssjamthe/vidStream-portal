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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Nileh Diore
 */
public class AllVideoAction extends ActionSupport implements SessionAware {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs, rs_cat_nme, rs_Get_Categorization_Name;
    private String SQL_Main, app_id;
    private List all_video_list_main, compare_video_id_list;
    private SessionMap<String, Object> sessionMap;

    public AllVideoAction() {
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    @Override
    public String execute() throws Exception {

        try {
            con = com.appify.vidstream.portal.util.DataConnection.getConnection();
            all_video_list_main = new ArrayList();
            compare_video_id_list = new ArrayList();
            //  System.out.println("Inside AllVideoAction Class:::::");
            int previous = 0;
            String query_1 = "select distinct id from categorization where app_id='" + app_id + "' order by id asc ";
            //  System.out.println("query_1:::::" + query_1);
            PreparedStatement pst_query1 = con.prepareStatement(query_1);
            ResultSet executeQuery_1 = pst_query1.executeQuery();
            while (executeQuery_1.next()) {

                int Categorizrion_ID = executeQuery_1.getInt(1);
                String main_sub_query = "select distinct id  from category where categorization_id='" + Categorizrion_ID + "' order by id asc";
                // System.out.println("main_sub_query:::::" + main_sub_query);
                PreparedStatement pst_main_sub_query = con.prepareStatement(main_sub_query);
                ResultSet rs_main_sub_query = pst_main_sub_query.executeQuery();
                while (rs_main_sub_query.next()) {

                    String query_category_mapping_video = "select distinct video_id from youtube_video_category_mapping where category_id='" + rs_main_sub_query.getInt(1) + "'";
                    //  System.out.println("query_category_mapping_video:::::" + query_category_mapping_video);
                    PreparedStatement pst_category_mapping_video = con.prepareStatement(query_category_mapping_video);
                    ResultSet rs_category_mapping_video = pst_category_mapping_video.executeQuery();

                    while (rs_category_mapping_video.next()) {
                        boolean match_found = true;
                        String SQl_get_video_details = "select distinct yt.id,yt.name,date(yt.date_added), ytcm.category_id from youtube_video yt,youtube_video_category_mapping ytcm where ytcm.video_id=yt.id and yt.id='" + rs_category_mapping_video.getString(1) + "'";
                        // String SQl_get_video_details="select youtube_id,name,date_added from youtube_video where id='"+rs_category_mapping_video.getInt(1)+"'";
                        PreparedStatement pst_video_details = con.prepareStatement(SQl_get_video_details);
                        ResultSet rs_video_details = pst_video_details.executeQuery();
                        //  System.out.println("SQl_get_video_details---" + SQl_get_video_details);

                        for (int k = 0; k < compare_video_id_list.size(); k++) {

                            if (compare_video_id_list.get(k).equals(rs_category_mapping_video.getString(1).toString())) {
                                match_found = false;
                            }

                        }
                        String prev = "";
                        if (match_found == true) {
                            while (rs_video_details.next()) {
                                // all_video_list_main.add(rs_video_details.getInt(4));
                                System.out.println("Ditinct Video Id---------------------------------" + rs_video_details.getString(1));

                                if (prev.equals(rs_video_details.getString(1))) {
                                    //System.out.println("Same video Id:::::::::::"+prev);
                                } else {
                                }
                                String Get_Categorization = "select distinct categorization_id from category where id='" + rs_video_details.getInt(4) + "'";
                                // System.out.println("Get_Categorization:::" + Get_Categorization);
                                PreparedStatement pst_Get_Categorization = con.prepareStatement(Get_Categorization);
                                ResultSet rs_Get_Categorization = pst_Get_Categorization.executeQuery();
                                rs_Get_Categorization.next();
                                String Get_Categorization_Name = "select distinct name from categorization where id='" + rs_Get_Categorization.getInt(1) + "' ";
                                PreparedStatement pst_Get_Categorization_Name = con.prepareStatement(Get_Categorization_Name);
                                rs_Get_Categorization_Name = pst_Get_Categorization_Name.executeQuery();
                                rs_Get_Categorization_Name.next();
                                all_video_list_main.add(rs_Get_Categorization_Name.getString(1));



                                String SQL_catname = "select distinct name from category where id='" + rs_video_details.getInt(4) + "'";
                                // System.out.println("SQL_catname" + SQL_catname);
                                PreparedStatement prepareStatement_cat_name = con.prepareStatement(SQL_catname);
                                rs_cat_nme = prepareStatement_cat_name.executeQuery();
                                rs_cat_nme.next();
                                all_video_list_main.add(rs_cat_nme.getString(1));
                                all_video_list_main.add(rs_video_details.getString(1));
                                all_video_list_main.add(rs_video_details.getString(2));
                                String YT_Added_Date = "";

                                try {
                                    String yt_AddedDate = rs_video_details.getString(3);
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    Date tempyt_AddedDate = simpleDateFormat.parse(yt_AddedDate);
                                    SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-YYYY");
                                    YT_Added_Date = outputDateFormat.format(tempyt_AddedDate);
                                    // System.out.println("Output date is = " + outputDateFormat.format(tempyt_AddedDate));
                                } catch (ParseException ex) {
                                    System.out.println("Parse Exception");
                                }

                                all_video_list_main.add(YT_Added_Date);


                                prev = rs_video_details.getString(1);
                                //System.out.println("Arrylist:-----" + all_video_list_main);

                            }
                            compare_video_id_list.add(rs_category_mapping_video.getString(1));

                            // System.err.println("compare_video_id_list:-----" + compare_video_id_list);
                        }



                    }



                }

            }

            System.out.println("all_video_list_main------" + all_video_list_main);
            int ii = 0, jj = 5;
        String new_var = "", prev = "", temp_string = "";
        boolean flag12 = true;
        boolean flagchange = true;

        StringBuffer sb = new StringBuffer();
        String sb1 = null;

        boolean flag1 = true;
        ArrayList<String> final_arraylist_exp = new ArrayList<String>();
        try {
            for (int i = 0; i < (all_video_list_main.size() / 5); i++) {



                List<String> head = all_video_list_main.subList(ii, jj);
                System.out.println("head--" + head);

                //flag1 = true;
                if (head.get(2).equals(prev)) {
                    // final_arraylist_exp.remove(final_arraylist_exp.size() -1);
                    //String last = final_arraylist_exp.get(final_arraylist_exp.size() -1);
                    if (!temp_string.equals("") && flagchange == true) {
                        int ret = final_arraylist_exp.lastIndexOf(temp_string);
                        System.out.println("ret--------------" + ret);
                        final_arraylist_exp.remove(ret);
                        flagchange = false;
                    } else {
                    }
                    String test = head.get(1).toString();
                    sb.append(test + " & ");

                    flag1 = false;
                    ii = ii + 5;
                    jj = jj + 5;
                    try {
                        //List<String> subList = arrlist.subList(ii, jj);
                        if ((ii >= all_video_list_main.size() && jj >= all_video_list_main.size())) {
                            if (flag1 == false) {

                                String final_category = sb.substring(0, sb.length() - 1);
                                //System.out.println("final category---------" + final_category + ".");
                                final_arraylist_exp.add(final_category);
                                sb.setLength(0);
                                flagchange = true;
                              ii = ii + 5;
                              jj = jj + 5;

                            }
                        }
                    } catch (Exception exp) {
                        System.out.println("Exception----------" + exp);
                    }

                } else {
                       if (flag1 == false) {

                                String final_category = sb.substring(0, sb.length() - 1);
                                //System.out.println("final category---------" + final_category + ".");
                                final_arraylist_exp.add(final_category);
                                sb.setLength(0);
                                flagchange = true;
                                //ii = ii + 5;
                               // jj = jj + 5;

                            }

                    prev = head.get(2);
                    sb.setLength(0);
                    String test = head.get(1).toString();
                    sb.append(test + " & ");
                    final_arraylist_exp.add(head.get(0));
                    final_arraylist_exp.add(head.get(2));
                    final_arraylist_exp.add(head.get(3));
                    final_arraylist_exp.add(head.get(4));
                    String final_category = sb.substring(0, sb.length() - 3);
                    //  String final_category = sb.toString();
                    // System.out.println("final category---------" + final_category + ".");
                    temp_string = final_category;

                    final_arraylist_exp.add(final_category);
                    //sb.setLength(0);
                    flag12 = false;
                    flag1 = true;
                    ii = ii + 5;
                    jj = jj + 5;
                }


            }

            System.out.println("final Arraylist---------" + final_arraylist_exp);

        } catch (Exception wxp) {
            System.out.println("Exception--" + wxp);
            wxp.printStackTrace();
        }

            //System.out.println("all_video_list_main------" + all_video_list_main);
            sessionMap.put("all_video_list_main", final_arraylist_exp);
            sessionMap.put("map_vido_app_id", app_id);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("exception" + e);
            return ERROR;
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (Exception e) {
                
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

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap) map;
    }
}
