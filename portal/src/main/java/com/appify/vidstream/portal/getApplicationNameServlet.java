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
public class getApplicationNameServlet extends HttpServlet {
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
    private String sql_app_name,  app_name;
    private PreparedStatement stmt_app_name, stmt_system_integrator_name;
    private ResultSet rs_app_name, rs_system_integrator_name;
    private List list_device_name;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        list_device_name = new ArrayList();
        con = null;
        sql_app_name = "";
        stmt_app_name = null;
        rs_app_name = null;

        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            app_name = request.getParameter("app_name");
            System.out.println("app_name::::"+app_name);
           
            con = conn.appify.vidstream.portal.DataConnection.getConnection();
          

           




            sql_app_name= "select id from application where name='" + app_name + "'  ORDER BY id";

            stmt_app_name = con.prepareStatement(sql_app_name);

            rs_app_name = stmt_app_name.executeQuery();
            //out.print("<option value='Select'>" + "Select" + "</option>");
            while (rs_app_name.next()) {
                System.out.println("data-------" + rs_app_name.getInt(1));
                //str=str+"<option>"+rs.getString(1)+"</option>";
                //out.print("<option>" + rs.getString(1) + "</option>");
                int appID=rs_app_name.getInt(1);
                out.print(appID);
               // out.print("<option value='" + rs_app_name.getInt(1) + "'>" + rs_app_name.getInt(1) + "</option>");

            }



        } catch (SQLException e) {
            System.out.println("excep::" + e);


        } finally {
            try {
                stmt_app_name.close();
            } catch (Exception e) {
            }
            try {
                rs_app_name.close();
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
