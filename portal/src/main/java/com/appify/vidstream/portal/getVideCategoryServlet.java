/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nileh Diore
 */
public class getVideCategoryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String SUCCESS = "success";
    private final static String FAILURE = "failure";
    private Connection con, connection;
    private int category_id;
    private String Sql_get_video_ref_id, video_id, ref_video_id, SQL_category_id, SQl_category_name, Category_Name;
    private PreparedStatement pst_ref_vid, pst_cat_id, pst_cat_name;
    private ResultSet rs_ref_vid, rs_cat_id, rs_cat_name;
    private List list_device_name;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        list_device_name = new ArrayList();
        con = null;
        Sql_get_video_ref_id = "";
        pst_ref_vid = null;
        rs_ref_vid = null;

        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            video_id = request.getParameter("videoid");
            System.out.println("video_id::::" + video_id);

            con = conn.appify.vidstream.portal.DataConnection.getConnection();
           /* Sql_get_video_ref_id = "select id  from youtube_video where youtube_id='" + video_id + "'";
            pst_ref_vid = con.prepareStatement(Sql_get_video_ref_id);
            rs_ref_vid = pst_ref_vid.executeQuery();
            rs_ref_vid.next();
            ref_video_id = rs_ref_vid.getString(1);*/
            SQL_category_id = "select category_id from youtube_video_category_mapping where video_id='" + video_id + "'";
            pst_cat_id = con.prepareStatement(SQL_category_id);
            rs_cat_id = pst_cat_id.executeQuery();
            rs_cat_id.next();
            category_id = rs_cat_id.getInt(1);
            System.out.println("category_id::" + category_id);

            SQl_category_name = "Select name from category where id='" + category_id + "'";
            pst_cat_name = con.prepareStatement(SQl_category_name);
            rs_cat_name = pst_cat_name.executeQuery();
            rs_cat_name.next();
            Category_Name = rs_cat_name.getString(1);
            System.out.println("Category_Name::" + Category_Name);
            
            String sql_categorization="select categorization_id from category where name='"+Category_Name+"'";
            PreparedStatement prepareStatement = con.prepareStatement(sql_categorization);
            ResultSet executeQuery = prepareStatement.executeQuery();
            executeQuery.next();
            int categorization_id  = executeQuery.getInt(1);
            
            String SQL_get_appid="select app_id from categorization where id='"+categorization_id+"'";
            PreparedStatement pst_get_appid = con.prepareStatement(SQL_get_appid);
            ResultSet rs_getappid = pst_get_appid.executeQuery();
            rs_getappid.next();
            int app_id =rs_getappid.getInt(1);
            
            String SQL_getAppname="select name from application where id ='"+app_id+"'";
            PreparedStatement pst_app_name = con.prepareStatement(SQL_getAppname);
            ResultSet rs_app_name = pst_app_name.executeQuery();
            rs_app_name.next();
            String APP_Name=rs_app_name.getString(1);
            System.out.println("APP_Name:::"+APP_Name);
            ArrayList ar = new ArrayList();
            Map<String, String> options = new LinkedHashMap<String, String>();

            options.put("Category_Name", Category_Name);
                  options.put("APP_Name", APP_Name);

            ar.add(options);
            System.out.println("connection closed");
            String json = new Gson().toJson(ar);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

            /*      sql_app_name = "select id from application where name='" + app_name + "'  ORDER BY id";

             stmt_app_name = con.prepareStatement(sql_app_name);

             rs_app_name = stmt_app_name.executeQuery();
             //out.print("<option value='Select'>" + "Select" + "</option>");
             while (rs_app_name.next()) {
             System.out.println("data" + rs_app_name.getString(1));
             //str=str+"<option>"+rs.getString(1)+"</option>";
             //out.print("<option>" + rs.getString(1) + "</option>");
             out.print("<option value='" + rs_app_name.getString(1) + "'>" + rs_app_name.getString(1) + "</option>");

             }*/



        } catch (SQLException e) {
            System.out.println("excep::" + e);


        } finally {
            try {
                pst_cat_id.close();
            } catch (Exception e) {
            }
            try {
                rs_cat_id.close();
            } catch (Exception e) {
            }
            try {
                if (null != con) {
                    con.close();
                    out.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
    /* TODO output your page here. You may use following sample code. */

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(getApplicationNameServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(getApplicationNameServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
