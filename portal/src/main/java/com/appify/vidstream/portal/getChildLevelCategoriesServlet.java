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
public class getChildLevelCategoriesServlet extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        ArrayList ar = new ArrayList();
        Connection con = null;
        int top_level_parent_category_id;
        String Sql_get_child_category_id, top_level_parent_category = "";
        PreparedStatement pst_child_category_id = null;
        ResultSet rs_child_category_id = null;

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            top_level_parent_category = request.getParameter("top_level_parent_category");
            System.out.println("top_level_parent_category::::" + top_level_parent_category);
            con = com.appify.vidstream.portal.util.DataConnection.getConnection();

            String query_parent_catid = "select id from category where name='" + top_level_parent_category + "'";
            PreparedStatement pst_parent_catid = con.prepareStatement(query_parent_catid);
            ResultSet rs_parent_ctegoryid = pst_parent_catid.executeQuery();
            System.out.println("query_parent_catid::::" + query_parent_catid);
            rs_parent_ctegoryid.next();
            top_level_parent_category_id = rs_parent_ctegoryid.getInt(1);


            Sql_get_child_category_id = "select distinct child_category_id  from parent_child_category_mappings where parent_category_id='" + top_level_parent_category_id + "'";
            pst_child_category_id = con.prepareStatement(Sql_get_child_category_id);
            rs_child_category_id = pst_child_category_id.executeQuery();
            System.out.println("Sql_get_child_category_id::::" + Sql_get_child_category_id);


            while (rs_child_category_id.next()) {

                Map<String, String> options = new LinkedHashMap<String, String>();
                String get_cat_name = "select name from category where id='" + rs_child_category_id.getInt(1) + "' ";
                PreparedStatement pst_cat_Name = con.prepareStatement(get_cat_name);
                ResultSet rs_CAT_Name = pst_cat_Name.executeQuery();
                rs_CAT_Name.next();


                options.put("child_category_id", String.valueOf(rs_child_category_id.getInt(1)));
                
                options.put("child_category_name", String.valueOf(rs_CAT_Name.getString(1)));



                ar.add(options);

                System.out.println("get_cat_name::::" + rs_child_category_id.getInt(1));
            }


            String json = new Gson().toJson(ar);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            ar.clear();



        } catch (SQLException e) {
            System.out.println("excep::" + e);


        } finally {
            try {
                pst_child_category_id.close();
            } catch (Exception e) {
            }
            try {
                rs_child_category_id.close();
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
