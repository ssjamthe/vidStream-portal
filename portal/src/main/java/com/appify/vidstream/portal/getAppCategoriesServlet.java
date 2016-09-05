/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class getAppCategoriesServlet extends HttpServlet {

    
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
        Connection con = null;
        String sql_app_catories,  categorization_id;
        PreparedStatement stmt_app_catories;
        ResultSet rs_app_catories;
        sql_app_catories = "";
        stmt_app_catories = null;
        rs_app_catories = null;

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            categorization_id = request.getParameter("categorization_id");
            System.out.println("categorization_id::::"+categorization_id);
           
            con = com.appify.vidstream.portal.util.DataConnection.getConnection();

            sql_app_catories= "select name from category where categorization_id='" + categorization_id + "'  ORDER BY name asc";

            System.out.println("sql_app_catories::::"+sql_app_catories);
            stmt_app_catories = con.prepareStatement(sql_app_catories);

            rs_app_catories = stmt_app_catories.executeQuery();
            out.print("<option value='Select'>" + "Select" + "</option>");
            while (rs_app_catories.next()) {
                System.out.println("data" + rs_app_catories.getString(1));
                //str=str+"<option>"+rs.getString(1)+"</option>";
                //out.print("<option>" + rs.getString(1) + "</option>");
                out.print("<option value='" + rs_app_catories.getString(1) + "'>" + rs_app_catories.getString(1) + "</option>");

            }

        } catch (SQLException e) {
            System.out.println("excep::" + e);


        } finally {
            try {
                stmt_app_catories.close();
            } catch (Exception e) {
            }
            try {
                rs_app_catories.close();
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
            Logger.getLogger(getAppCategoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(getAppCategoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
