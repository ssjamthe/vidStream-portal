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
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Nileh Diore
 */
public class PreListAllFeedbackAction extends ActionSupport implements SessionAware {

    private String Sql_List_user_feedback, convert_comment_Date;
    PreparedStatement pst_list_user_feedback;
    ResultSet rs_list_user_feedback;
    private List list_all_user_feedback;
    private Connection con;
    private SessionMap<String, Object> sessionMap;
    HttpSession session = ServletActionContext.getRequest().getSession(false);

    public PreListAllFeedbackAction() {
    }

    @Override
    public String execute() throws Exception {

        list_all_user_feedback = new ArrayList();
        try {

            con = com.appify.vidstream.portal.util.DataConnection.getConnection();
            Sql_List_user_feedback = "SELECT app_id, device_id, user_comment, comment_date FROM feedback order by comment_date desc;";
            pst_list_user_feedback = con.prepareStatement(Sql_List_user_feedback);
            rs_list_user_feedback = pst_list_user_feedback.executeQuery();
            while (rs_list_user_feedback.next()) {
                list_all_user_feedback.add(rs_list_user_feedback.getInt(1));
                
                String SQL_getAPP_NAME="select name from application where id='"+rs_list_user_feedback.getInt(1)+"'";
                PreparedStatement pst_getAPP_NAME = con.prepareStatement(SQL_getAPP_NAME);
                ResultSet rs_app_name = pst_getAPP_NAME.executeQuery();
                rs_app_name.next();
                 list_all_user_feedback.add(rs_app_name.getString(1));
                list_all_user_feedback.add(rs_list_user_feedback.getString(2));
                list_all_user_feedback.add(rs_list_user_feedback.getString(3));


                try {
                    String commentDate = rs_list_user_feedback.getString(4);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date tempcreateDate = simpleDateFormat.parse(commentDate);
                    SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-YYYY");
                    convert_comment_Date = outputDateFormat.format(tempcreateDate);
                } catch (ParseException ex) {
                    System.out.println("Parse Exception" + ex);
                }
                list_all_user_feedback.add(convert_comment_Date);
            }
            sessionMap.put("list_all_user_feedback", list_all_user_feedback);
            System.out.println("in action classs---"+list_all_user_feedback);

        } catch (Exception exp) {

            System.out.println("inside PreListAllFeedbackAction Exception in Try " + exp);
            return ERROR;

        }finally {
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
        sessionMap = (SessionMap) map; //To change body of generated methods, choose Tools | Templates.
    }
}