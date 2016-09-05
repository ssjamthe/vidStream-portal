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
public class PreListallApplicationPropertyAction extends ActionSupport implements SessionAware {

    private String Sql_List_Property;
    PreparedStatement pst_list_property;
    ResultSet rs_list_property;
    private List list_all_property;
    private Connection con;
     private SessionMap<String, Object> sessionMap;
    HttpSession session = ServletActionContext.getRequest().getSession(false);

    public PreListallApplicationPropertyAction() {
    }

    @Override
    public String execute() throws Exception {
        list_all_property = new ArrayList();
        try {
            con = com.appify.vidstream.portal.util.DataConnection.getConnection();
            Sql_List_Property = "SELECT prop_name, prop_value FROM property_table;";
            pst_list_property = con.prepareStatement(Sql_List_Property);
            rs_list_property = pst_list_property.executeQuery();
            while (rs_list_property.next()) {
             
                 list_all_property.add(rs_list_property.getString(1));
                 list_all_property.add(rs_list_property.getString(2));

            }

            sessionMap.put("list_all_property", list_all_property);
        } catch (Exception exp) {
            
            System.out.println("Exception in try ---------"+exp);
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