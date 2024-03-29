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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
public class AddMultipleVideoActionServlet extends HttpServlet {

    private static final String SUCCESS = "success";
    private final static String FAILURE = "failure";
    private Connection con, connection;
    private String sql_app_catzion, Application_Name, Categorization_Name, Category_Name, vidlist;
    private PreparedStatement stmt_app_catzion, stmt_system_integrator_name;
    private ResultSet rs_app_catzion, rs_system_integrator_name;
    private List list_device_name;
    private String App_Id, categorization_id;
    private String explore_video_view_count,Explore_video_id,explore_video_title,explore_video_published_date,ExpYTupdate_Application_Name,ExpYTube_Categorization;

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
        list_device_name = new ArrayList();
        con = null;
        sql_app_catzion = "";
        stmt_app_catzion = null;
        rs_app_catzion = null;

        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
           
            Explore_video_id =request.getParameter("Explore_video_id");
             explore_video_title =request.getParameter("explore_video_title");
              explore_video_view_count =request.getParameter("explore_video_view_count");
              explore_video_published_date =request.getParameter("explore_video_published_date");
              ExpYTupdate_Application_Name =request.getParameter("explore_video_published_date");
              ExpYTube_Categorization =request.getParameter("ExpYTube_Categorization");
              
              
              System.out.println("Explore_video_id------------------------"+Explore_video_id);
              System.out.println("explore_video_title------------------------"+explore_video_title);
              System.out.println("explore_video_view_count------------------------"+explore_video_view_count);
              System.out.println("ExpYTupdate_Application_Name------------------------"+ExpYTupdate_Application_Name);
              System.out.println("ExpYTube_Categorization------------------------"+ExpYTube_Categorization);
     
    }

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
            Logger.getLogger(AddMultipleVideoActionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddMultipleVideoActionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
