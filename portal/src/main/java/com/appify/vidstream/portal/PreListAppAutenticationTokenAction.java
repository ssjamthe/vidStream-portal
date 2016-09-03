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
public class PreListAppAutenticationTokenAction extends ActionSupport implements SessionAware {

    private String Sql_List_Auth_Token;
    PreparedStatement pst_list_auth_token;
    ResultSet rs_list_auth_token;
    private List list_all_auth_token, list_auth_application_id;
    private Connection con;
    private SessionMap<String, Object> sessionMap;
    HttpSession session = ServletActionContext.getRequest().getSession(false);

    public PreListAppAutenticationTokenAction() {
    }

    @Override
    public String execute() throws Exception {
        list_all_auth_token = new ArrayList();
        list_auth_application_id = new ArrayList();
        try {
            con = conn.appify.vidstream.portal.DataConnection.getConnection();
            Sql_List_Auth_Token = "SELECT id,app_id,token, description FROM auth_token order by app_id asc;";
            pst_list_auth_token = con.prepareStatement(Sql_List_Auth_Token);
            rs_list_auth_token = pst_list_auth_token.executeQuery();
            while (rs_list_auth_token.next()) {
                String SQl_app_NAme = "select name from application where id='" + rs_list_auth_token.getString(2) + "'";
                PreparedStatement pst_app_name = con.prepareStatement(SQl_app_NAme);
                ResultSet rs_app_name = pst_app_name.executeQuery();
                rs_app_name.next();
                list_all_auth_token.add(rs_list_auth_token.getString(1));
                list_all_auth_token.add(rs_list_auth_token.getString(2));
                list_all_auth_token.add(rs_app_name.getString(1));
                list_all_auth_token.add(rs_list_auth_token.getString(3));
                list_all_auth_token.add(rs_list_auth_token.getString(4));

            }

            String Sql_appnamelist = "select distinct (name) from application order by name asc";
            System.out.println("Sql_appnamelist:::" + Sql_appnamelist);
            PreparedStatement pst_appname = con.prepareStatement(Sql_appnamelist);
            ResultSet rs_appname = pst_appname.executeQuery();
            while (rs_appname.next()) {
                list_auth_application_id.add(rs_appname.getString(1));
            }

            sessionMap.put("list_all_auth_token", list_all_auth_token);
            sessionMap.put("list_auth_application_id", list_auth_application_id);
        } catch (Exception exp) {

            System.out.println("Exception in try ---------" + exp);
            return ERROR;
        }

        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap) map; //To change body of generated methods, choose Tools | Templates.
    }
}