/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal;

import static com.appify.vidstream.portal.AdminPrtalConstant.YOUTUBE_API_KEY;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Nileh Diore
 */
public class ExploreYoutubeAction extends ActionSupport implements SessionAware {

    private String SQL_video_list, SQL_Category_Wise_Video_List;
    private PreparedStatement pst_video_list, pst_category_wise_video_list;
    private Connection con;
    ResultSet rs_video_list, rs_category_wise_video_list;
    private SessionMap<String, Object> sessionMap;
    private List video_id_list, category_idandname_list, Category_Wise_Video_List;
    private String YT_API;
   
    HttpSession session = ServletActionContext.getRequest().getSession(false);

    public ExploreYoutubeAction() {
    }

    @Override
    public String execute() throws Exception {
        try {
            video_id_list = new ArrayList();
           
            System.out.println("YOUTUBE_API_KEY----------------"+YOUTUBE_API_KEY);
            Category_Wise_Video_List = new ArrayList();
            category_idandname_list = new ArrayList();
            con = com.appify.vidstream.portal.util.DataConnection.getConnection();
            SQL_video_list = "select distinct id from youtube_video order by id";
            pst_video_list = con.prepareStatement(SQL_video_list);
            rs_video_list = pst_video_list.executeQuery();
            System.out.println("SQL_video_list:::" + SQL_video_list);
            while (rs_video_list.next()) {
                video_id_list.add(rs_video_list.getString(1));
            }
            String SQL_Distinct_category = "select distinct name from category order by name ";
            PreparedStatement pst_category_idname = con.prepareStatement(SQL_Distinct_category);
            ResultSet rs_catnamid = pst_category_idname.executeQuery();
            while (rs_catnamid.next()) {
                category_idandname_list.add(rs_catnamid.getString(1));

            }

            SQL_Category_Wise_Video_List = "select distinct yvc.category_id, yv.id,yv.name,date(yv.date_added) from youtube_video yv,youtube_video_category_mapping yvc where yvc.video_id=yv.id  order by yv.id,yvc.category_id asc,date(yv.date_added)asc";
            pst_category_wise_video_list = con.prepareStatement(SQL_Category_Wise_Video_List);
            rs_category_wise_video_list = pst_category_wise_video_list.executeQuery();
            while (rs_category_wise_video_list.next()) {
                //Category_Wise_Video_List.add(rs_category_wise_video_list.getInt(1));

                String getcategorization_query = "select categorization_id from category where id='" + rs_category_wise_video_list.getInt(1) + "'";
                PreparedStatement pst_get_cate_id = con.prepareStatement(getcategorization_query);
                ResultSet rs_get_cateid = pst_get_cate_id.executeQuery();
                rs_get_cateid.next();
                int categorization_id = rs_get_cateid.getInt(1);

                String getappid_query = "select app_id from categorization where id='" + categorization_id + "'";
                PreparedStatement pst_get_appid = con.prepareStatement(getappid_query);
                ResultSet rs_get_appid = pst_get_appid.executeQuery();
                rs_get_appid.next();
                int ApplicationId = rs_get_appid.getInt(1);



                String getappName_query = "select name from application where id='" + ApplicationId + "'";
                PreparedStatement pst_get_appName = con.prepareStatement(getappName_query);
                ResultSet rs_get_appName = pst_get_appName.executeQuery();
                rs_get_appName.next();

                Category_Wise_Video_List.add(rs_get_appName.getString(1).toString());

                String SQL_getcategory_name = "select name from category where id='" + rs_category_wise_video_list.getInt(1) + "'";
                PreparedStatement prepareStatement_catname = con.prepareStatement(SQL_getcategory_name);
                ResultSet executeQuery_cat_name = prepareStatement_catname.executeQuery();
                executeQuery_cat_name.next();
                String Category_Name = executeQuery_cat_name.getString(1);
                //Category_Wise_Video_List.add(rs_category_wise_video_list.getInt(2));
                Category_Wise_Video_List.add(Category_Name.toString());
                Category_Wise_Video_List.add(rs_category_wise_video_list.getString(2));
                Category_Wise_Video_List.add(rs_category_wise_video_list.getString(3));
                Category_Wise_Video_List.add(rs_category_wise_video_list.getString(4));


            }
            System.out.println("Category_Wise_Video_List---------" + Category_Wise_Video_List);

            int ii = 0, jj = 5;
            String new_var = "", prev = "", temp_string = "";
            boolean flag12 = true;
                boolean flagchange = true;
            /*ArrayList to Array Conversion */
            StringBuffer sb = new StringBuffer();
            String sb1 = null;
            String redirect = "";
            boolean flag1 = true;
            ArrayList<String> final_arraylist_exp = new ArrayList<String>();
            try {
                for (int i = 0; i < (Category_Wise_Video_List.size()); i++) {

                    if (Category_Wise_Video_List.size() >= jj) {

                        List<String> head = Category_Wise_Video_List.subList(ii, jj);
                        // System.out.println("head--" + head);
                        ii = ii + 5;
                        jj = jj + 5;
                        //flag1 = true;
                        if (head.get(2).equals(prev)) {
                            // final_arraylist_exp.remove(final_arraylist_exp.size() -1);
                            //String last = final_arraylist_exp.get(final_arraylist_exp.size() -1);
                            if (!temp_string.equals("")&& flagchange==true ) {
                                int ret = final_arraylist_exp.lastIndexOf(temp_string);
                                System.out.println("ret--------------" + ret);
                                final_arraylist_exp.remove(ret);
                                flagchange=false;
                            }else{
                            
                            }
                            String test = head.get(1).toString();
                            sb.append(test + " & ");
                            redirect = test;
                            flag1 = false;


                        } else {
                            if (flag1 == false) {
                                String final_category = sb.substring(0, sb.length() - 1);
                                //System.out.println("final category---------" + final_category + ".");
                                final_arraylist_exp.add(final_category);
                                sb.setLength(0);
                                flagchange=true;
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

                        }
                    }

                }

                System.out.println("final Arraylist---------" + final_arraylist_exp);

            } catch (Exception wxp) {
                //System.out.println("Exception--" + wxp);
                wxp.printStackTrace();
            }



            //sessionMap.put("Category_Wise_Video_List", Category_Wise_Video_List);
            sessionMap.put("Category_Wise_Video_List", final_arraylist_exp);
            sessionMap.put("video_id_list", video_id_list);
            sessionMap.put("category_idandname_list", category_idandname_list);
            
            
            return SUCCESS;
        } catch (Exception exp) {
            System.out.println("Exception in try ExploreYoutubeAction:::" + exp);
            return ERROR;
        }
    }

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap) map; //To change body of generated methods, choose Tools | Templates.
    }
}