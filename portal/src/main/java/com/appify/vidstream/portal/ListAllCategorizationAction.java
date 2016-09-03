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
public class ListAllCategorizationAction extends ActionSupport implements SessionAware {

    private Connection con;
    private PreparedStatement stmt, pst_getimg, pst_category, pst_video;
    private ResultSet rs, rs_get_img, rs_category, rs_video;
    private String SQL, app_name;
    String encodedImage, encodedImage1;
    private List categorization_list, nested_categorization;
    private SessionMap<String, Object> sessionMap;
    private int app_id;
    private String apnam;
     String apnamid ;
    HttpSession session = ServletActionContext.getRequest().getSession(false);
  

    public ListAllCategorizationAction() {
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public String execute() throws Exception {
        try {
           apnamid = getApp_name();
              app_id = Integer.parseInt(apnamid);
            con = conn.appify.vidstream.portal.DataConnection.getConnection();
            String sql_id = "select name from application where id='" + app_id + "'";
          
            PreparedStatement pst_id = con.prepareStatement(sql_id);
            ResultSet rs_id = pst_id.executeQuery();
            rs_id.next();
          apnam = rs_id.getString(1);
           // int app_id = Integer.parseInt(id);
            categorization_list = new ArrayList();
            nested_categorization = new ArrayList();

            SQL = "SELECT id,name,image,date(date_created),date(date_modified)FROM categorization where app_id='" + app_id + "' order by (date_modified) desc;";
            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                categorization_list.add(apnamid);
                categorization_list.add(rs.getString("name"));
                 categorization_list.add(rs.getInt("id"));
                
                // String SQL_get_img = "select contents from images where id='" + rs.getString("image") + "' and name='" + rs.getString("name") + "'";
                String SQL_get_img = "select contents from images where id='" + rs.getString("image") + "' ";
                pst_getimg = con.prepareStatement(SQL_get_img);
                rs_get_img = pst_getimg.executeQuery();
                while (rs_get_img.next()) {
                    byte[] rb = rs_get_img.getBytes("contents");
                    encodedImage = Base64.encode(rb);
                    categorization_list.add(encodedImage);

                }
                int counter_category = 0;
                int counter_video = 0;
                //int cate_id = Integer.parseInt(rs.getString("id"));
                String sql_category_id = "select count(id) from category where categorization_id ='" + Integer.parseInt(rs.getString("id")) + "' ";
                pst_category = con.prepareStatement(sql_category_id);
                rs_category = pst_category.executeQuery();
                boolean empty = true;
                boolean videoflag = true;
                while (rs_category.next()) {

                    // ResultSet processing here
                    counter_category = counter_category + Integer.parseInt(rs_category.getString(1));

                    // int category_id = Integer.parseInt(rs_category.getString(2));
                    String SQL_get_id = "select id from category where categorization_id='" + Integer.parseInt(rs.getString("id")) + "'";
                    System.out.println("SQL_get_id:::" + SQL_get_id);
                    PreparedStatement prepareStatement = con.prepareStatement(SQL_get_id);
                    ResultSet executeQuery = prepareStatement.executeQuery();
                    empty = false;
                    int category_id_foryoutube;
                    // executeQuery.next();
                    if (!executeQuery.next()) {
                      
                        category_id_foryoutube = 0;
                        counter_video=0;
                    } else {

                        do {
                             //counter_video=0;
                            category_id_foryoutube = Integer.parseInt(executeQuery.getString(1));
                            System.out.println("category_id_foryoutube :::::--------" + category_id_foryoutube);
                            System.out.println("categorization_id_foryoutube :::::--------" + Integer.parseInt(rs.getString("id")));
                            String SQL_video = "select count(*) from youtube_video_category_mapping where category_id='" + category_id_foryoutube + "'";
                            System.out.println("SQL_video:::" + SQL_video);
                            pst_video = con.prepareStatement(SQL_video);
                            rs_video = pst_video.executeQuery();

                            while (rs_video.next()) {
                                counter_video = counter_video + Integer.parseInt(rs_video.getString(1));

                                videoflag = false;
                            }
                            if (videoflag) {
                                categorization_list.add("0");
                            }
                        } while (executeQuery.next());




                    }











                }
                categorization_list.add(String.valueOf(counter_category));
                categorization_list.add(String.valueOf(counter_video));

                String convert_create_date = "", convert_modified_date = "";

                try {
                    String create_date = rs.getString(4);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date tempcreate_date = simpleDateFormat.parse(create_date);
                    SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-YYYY");
                    convert_create_date = outputDateFormat.format(tempcreate_date);
                    //  System.out.println("Output date is = " + outputDateFormat.format(tempcreate_date));
                } catch (ParseException ex) {
                    System.out.println("Parse Exception");
                }

                categorization_list.add(convert_create_date);



                try {
                    String modifiedDate = rs.getString(5);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date tempmodified_Date = simpleDateFormat.parse(modifiedDate);
                    SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-YYYY");
                    convert_modified_date = outputDateFormat.format(tempmodified_Date);
                    //System.out.println("Output date is = " + outputDateFormat.format(tempmodified_Date));
                } catch (ParseException ex) {
                    System.out.println("Parse Exception");
                }
                categorization_list.add(convert_modified_date);
                if (empty) {
                    // Empty result set
                    categorization_list.add("0");

                }

            }

            session.removeAttribute("nested_list");
            session.removeAttribute("category_name");
            sessionMap.put("categorization_list", categorization_list);
            sessionMap.put("nested_categorization", nested_categorization);
            sessionMap.put("app_id", apnamid);
           // System.out.println("sessionMap   app_id--------------------------------------"+app_id);
            sessionMap.put("App_Name", apnam);
           //  System.out.println("sessionMap App_Name--------------------------------------"+apnam);
            sessionMap.put("map_vido_app_id", apnamid);



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