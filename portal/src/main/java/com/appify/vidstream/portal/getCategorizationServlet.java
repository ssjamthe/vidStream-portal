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
public class getCategorizationServlet extends HttpServlet {

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
    private String categorization_id;
    private String Sql_get_categorization_ref_id, categorization_name;
    private PreparedStatement pst_ref_categorization;
    private ResultSet rs_ref_categorization;
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
       
        con = null;
        Sql_get_categorization_ref_id = "";
        pst_ref_categorization = null;
        rs_ref_categorization = null;

        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            categorization_name = request.getParameter("categorization_name");
            System.out.println("categorization_name::::" + categorization_name);

            con = com.appify.vidstream.portal.util.DataConnection.getConnection();
            Sql_get_categorization_ref_id = "select id  from categorization where name='" + categorization_name + "'";
            pst_ref_categorization = con.prepareStatement(Sql_get_categorization_ref_id);
            rs_ref_categorization = pst_ref_categorization.executeQuery();
            rs_ref_categorization.next();
           categorization_id = rs_ref_categorization.getString(1);
            System.out.println("categorization_id::::"+categorization_id);
            ArrayList ar = new ArrayList();
            Map<String, String> options = new LinkedHashMap<String, String>();

            options.put("categorization_id", categorization_id);
                

            ar.add(options);
            System.out.println("connection closed");
            String json = new Gson().toJson(ar);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);


        } catch (SQLException e) {
            System.out.println("excep::" + e);


        } finally {
            try {
                pst_ref_categorization.close();
            } catch (Exception e) {
            }
            try {
                rs_ref_categorization.close();
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
