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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.apache.catalina.util.Base64;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Nileh Diore
 */
public class LoginAction extends ActionSupport implements SessionAware {

    private String username;
    private String password;
    String company;
    private SessionMap<String, Object> sessionMap;
    private List list_category, list_ap_id, list_app_name, list_app_details, list_categorization, list_user_role;
    private String sql_login, sql_details, encodedImage, User_Role_Name;
    private PreparedStatement pstmt_login, pst_category, pst_app_dets1, pst_getimg, pst_cate, pst_video;
    ResultSet rs_login, rs_details, rs_app_dets1, rs_get_img, rs_cate, rs_category, rs_video;
    List device_list, myProfileList;
    // HttpSession session = ServletActionContext.getRequest().getSession(false);
    Connection con;
    private int User_Role_Id;
    String pwd, userid;
    String Check_password, Check_userName;
    String sql_login_try;
    PreparedStatement pstmt_login_try;
    ResultSet rs_login_try;

    @Override
    public String execute() throws SQLException {
        sql_login = "";
        sql_details = "";
        Check_password = "";
        Check_userName = "";
        list_ap_id = new ArrayList();
        list_app_name = new ArrayList();
        list_app_details = new ArrayList();
        list_categorization = new ArrayList();
        list_user_role = new ArrayList();
        list_category = new ArrayList();


        try {
            username = username;
            password = password;

            sessionMap.put("userid", userid);

            if (username == null) {
                HttpSession session = ServletActionContext.getRequest().getSession();
                password = (String) session.getAttribute("uPass");
                username = (String) session.getAttribute("uName");
            }
           con = com.appify.vidstream.portal.util.DataConnection.getConnection();

            String Sql_get_user_role = "select role_id from portal_user where user_name='" + username + "'";
            PreparedStatement pst_user_role_id = con.prepareStatement(Sql_get_user_role);
            ResultSet rs_user_role_Id = pst_user_role_id.executeQuery();
            rs_user_role_Id.next();
            User_Role_Id = rs_user_role_Id.getInt(1);

            String Sql_role_name = "select name from portal_user_role where id ='" + User_Role_Id + "'";
            PreparedStatement pst_Role_Name = con.prepareStatement(Sql_role_name);
            ResultSet Rs_Role_Name = pst_Role_Name.executeQuery();
            Rs_Role_Name.next();
            User_Role_Name = Rs_Role_Name.getString(1);

            sessionMap.put("user_role_name", User_Role_Name);


            sql_login = "select password FROM portal_user where user_name=?";
            sessionMap.put("uName", username);

            sessionMap.put("uPass", password);

            pstmt_login = con.prepareStatement(sql_login);
            pstmt_login.setString(1, username);
            rs_login = pstmt_login.executeQuery();
            rs_login.next();
            Check_password = rs_login.getString(1).toString();
            System.out.println("Check_password---------------->>>>>>>>>>>" + Check_password);
            if (Check_password.equals(password)) {
                String Sql_portal_user_role = "select distinct name ,id from portal_user_role order by id asc";
                System.out.println("Sql_portal_user_role:::" + Sql_portal_user_role);
                PreparedStatement prepareStrole = con.prepareStatement(Sql_portal_user_role);
                ResultSet rs_user_role = prepareStrole.executeQuery();
                while (rs_user_role.next()) {

                    list_user_role.add(rs_user_role.getString("id"));
                    list_user_role.add(rs_user_role.getString("name"));
                }

                String Sql_appname_id = "select distinct name ,id from application order by id asc";
                System.out.println("Sql_appname_id:::" + Sql_appname_id);
                PreparedStatement pst_appnameid = con.prepareStatement(Sql_appname_id);
                ResultSet rs_appnameid = pst_appnameid.executeQuery();
                while (rs_appnameid.next()) {
                    list_app_name.add(rs_appnameid.getInt("id"));
                    list_app_name.add(rs_appnameid.getString("name"));
                }
                String Sql_App_Deatils = "select id,name,bg_image,status,date(date_created),date(date_modified) from application order by (date_modified) desc";
                System.out.println("Sql_App_Name:::" + Sql_App_Deatils);
                pst_app_dets1 = con.prepareStatement(Sql_App_Deatils);
                rs_app_dets1 = pst_app_dets1.executeQuery();
                while (rs_app_dets1.next()) {
                    list_app_details.add(rs_app_dets1.getString(1));
                    list_app_details.add(rs_app_dets1.getString(2));

                    String SQL_get_img = "select contents from images where id='" + rs_app_dets1.getInt(3) + "' and name='" + rs_app_dets1.getString(2) + "'";
                    pst_getimg = con.prepareStatement(SQL_get_img);
                    rs_get_img = pst_getimg.executeQuery();
                    while (rs_get_img.next()) {
                        byte[] rb = rs_get_img.getBytes("contents");
                        encodedImage = Base64.encode(rb);
                        list_app_details.add(encodedImage);

                    }
                    list_app_details.add(rs_app_dets1.getString(4));

                    int count_categorization = 0;

                    String SQL_categorization_id = "select count(*) from categorization where app_id='" + Integer.parseInt(rs_app_dets1.getString(1)) + "'";
                    System.out.println("::::SQL_categorization_id:::::" + SQL_categorization_id);
                    pst_cate = con.prepareStatement(SQL_categorization_id);
                    rs_cate = pst_cate.executeQuery();
                    while (rs_cate.next()) {
                        list_app_details.add(rs_cate.getString(1));
                    }
                    String cat_id_sql = "select id from categorization where app_id='" + Integer.parseInt(rs_app_dets1.getString(1)) + "'";
                    PreparedStatement pst_cat_id_new = con.prepareStatement(cat_id_sql);
                    ResultSet rs_cat_id_new = pst_cat_id_new.executeQuery();
                    int count_category = 0;
                    int tot = 0;
                    while (rs_cat_id_new.next()) {

                        String sql_category_id = "select count(*) from category where categorization_id ='" + Integer.parseInt(rs_cat_id_new.getString(1)) + "' ";
                        System.out.println("::::sql_category_id:::::" + sql_category_id);

                        pst_category = con.prepareStatement(sql_category_id);
                        rs_category = pst_category.executeQuery();
                        while (rs_category.next()) {
                            //list_app_details.add(rs_category.getString(1));
                            count_category = count_category + Integer.parseInt(rs_category.getString(1));
                        }


                        String catogory_id_sql_new = "select id from category where categorization_id='" + Integer.parseInt(rs_cat_id_new.getString(1)) + "'";
                        System.out.println("::::catogory_id_sql_new:::::" + catogory_id_sql_new);
                        PreparedStatement pst_catogory_id_new = con.prepareStatement(catogory_id_sql_new);
                        ResultSet rs_catory_id_new = pst_catogory_id_new.executeQuery();



                        while (rs_catory_id_new.next()) {

                            String SQL_video = "select count(*) from youtube_video_category_mapping where category_id='" + Integer.parseInt(rs_catory_id_new.getString(1)) + "'";
                            System.out.println("SQL_video:::" + SQL_video);
                            pst_video = con.prepareStatement(SQL_video);
                            rs_video = pst_video.executeQuery();

                            while (rs_video.next()) {
                                // list_app_details.add(rs_video.getString(1));

                                tot = tot + Integer.parseInt(rs_video.getString(1));
                            }

                        }

                    }


                    list_app_details.add(String.valueOf(count_category));
                    list_app_details.add(String.valueOf(tot));
                    String convert_create_date = "", convert_modified_date = "";
                    try {
                        String createDate = rs_app_dets1.getString(5);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date tempcreateDate = simpleDateFormat.parse(createDate);
                        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-YYYY");
                        convert_create_date = outputDateFormat.format(tempcreateDate);
                        System.out.println("Output date is = " + outputDateFormat.format(tempcreateDate));
                    } catch (ParseException ex) {
                        System.out.println("Parse Exception");
                    }
                    list_app_details.add(convert_create_date);

                    try {
                        String modifiedDate = rs_app_dets1.getString(6);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date tempmodifiedDate = simpleDateFormat.parse(modifiedDate);
                        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-YYYY");
                        convert_modified_date = outputDateFormat.format(tempmodifiedDate);
                        System.out.println("Output date is = " + outputDateFormat.format(tempmodifiedDate));
                    } catch (ParseException ex) {
                        System.out.println("Parse Exception");
                    }

                    list_app_details.add(convert_modified_date);
                }

                String SQL_distinct_category = "select distinct name from category order by name";
                PreparedStatement pst_dist_category = con.prepareStatement(SQL_distinct_category);
                ResultSet rs_dist_cate = pst_dist_category.executeQuery();

                while (rs_dist_cate.next()) {
                    list_category.add(rs_dist_cate.getString(1));
                }
                String numberOnly = "", numberOnly2 = "";
                try {
                    String SQl_getBgimagesizelimit = "select prop_value from property_table where prop_name='portal_background_image' ";
                    PreparedStatement pst_bgimgsizelimit = con.prepareStatement(SQl_getBgimagesizelimit);
                    ResultSet rs_bg_img_size_lim = pst_bgimgsizelimit.executeQuery();
                    rs_bg_img_size_lim.next();
                    String Bg_prop_value = rs_bg_img_size_lim.getString(1);
                    System.out.println("total_bg_img_limit---------" + Bg_prop_value);
                    numberOnly = Bg_prop_value.replaceAll("[^0-9]", "");
                    //System.out.println("total_bg_img_limit---------"+numberOnly);



                    String SQl_getOtherimagesizelimit = "select prop_value from property_table where prop_name='portal_other_image' ";
                    PreparedStatement pst_otherimgsizelimit = con.prepareStatement(SQl_getOtherimagesizelimit);
                    ResultSet rs_other_img_size_lim = pst_otherimgsizelimit.executeQuery();
                    rs_other_img_size_lim.next();
                    String other_prop_value = rs_other_img_size_lim.getString(1);
                    numberOnly2 = other_prop_value.replaceAll("[^0-9]", "");

                } catch (Exception exp) {
                    numberOnly = "0";
                    numberOnly2 = "0";

                    System.out.println("Exception in last try-----" + exp);

                }

                sessionMap.put("bg_limit", numberOnly);
                sessionMap.put("other_limit", numberOnly2);
                sessionMap.put("list_app_details", list_app_details);
                sessionMap.put("list_user_role", list_user_role);
                sessionMap.put("list_categorization", list_categorization);
                sessionMap.put("list_app_name", list_app_name);
                sessionMap.put("list_category", list_category);

                return "success";


            } // String sql_app_details = "select distinct (select count (distinct(acs.categorization)) from appify_categories acs)  ,(select count (distinct(acs.categori)) from appify_categories acs),(select count (distinct (apv.vedeo_id)) from appify_video_link apv),apm.app_id,apm.app_name,apm.app_status,date(apm.date_created),date(apm.date_modified) from appify_app_master apm,appify_categories acs,appify_video_link apv where apm.app_name =acs.app_name and apv.categorization= acs.categorization  ";
            else {
                return "error";
            }
        } catch (Exception e) {
            System.out.println("Exception---------------" + e);
            return "error";
        }finally {
            try {
                if (null != con) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setSession(Map map) {
        sessionMap = (SessionMap) map;

    }

    @Override
    public void validate() {
      
        System.out.println("username---" + username);
        PreparedStatement pst_userc = null;
          PreparedStatement pst_check= null;
        ResultSet rs_check_userc = null;
         ResultSet rs_check = null;
              if (username == null) {
                HttpSession session = ServletActionContext.getRequest().getSession();
                password = (String) session.getAttribute("uPass");
                username = (String) session.getAttribute("uName");
            }
              
       try{
        try {
            con = com.appify.vidstream.portal.util.DataConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    String SQL_Check_Username="SELECT user_name FROM portal_user where user_name='"+username+"'";
        try {
          pst_check =  con.prepareStatement(SQL_Check_Username);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs_check =pst_check.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(rs_check.next()){
         sql_login_try = "select password FROM portal_user where user_name=?";
        try {

            pstmt_login_try = con.prepareStatement(sql_login_try);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pstmt_login_try.setString(1, username);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs_login_try = pstmt_login_try.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs_login_try.next();
        } catch (SQLException ex) {
            Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Check_password = rs_login_try.getString(1);
        } catch (SQLException ex) {
            Check_password="NA";
            Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql_get_user = "select user_name from portal_user where password='" + Check_password + "' ";
        try {
            pst_userc = con.prepareStatement(sql_get_user);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs_check_userc = pst_userc.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs_check_userc.next();
        } catch (SQLException ex) {
            Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Check_userName = rs_check_userc.getString(1).toString();
        } catch (SQLException ex) {
            Check_userName ="NA";
            Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            }else{
             Check_userName ="NA";
             Check_password="NA";
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        if (Check_password.equals(password) && Check_userName.equals(username)) {

            addActionMessage("You are valid user!");
        } else {
            addActionError(" Invalid Username/Password!");
        }
       }finally {
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
