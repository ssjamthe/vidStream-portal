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
public class getCategoryIdServlet extends HttpServlet {
private  String category_name,category_id;
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
    	String temp_category_id = null,Sql_get_category_ref_id ,Parent_category_ID="";
    	PreparedStatement pst_ref_category = null;
    	ResultSet rs_ref_category = null;
        category_id="";
   

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            category_name = request.getParameter("category_name");
            Parent_category_ID =  request.getParameter("Parent_category_ID");
            System.out.println("category_name::::" + category_name);
            System.out.println("Parent_category_ID--------::::" + Parent_category_ID);

            con = com.appify.vidstream.portal.util.DataConnection.getConnection();
            Sql_get_category_ref_id = "select child_category_id  from parent_child_category_mappings where parent_category_id='" + Integer.parseInt(Parent_category_ID) + "'";
            System.out.println("Sql_get_category_ref_id----"+Sql_get_category_ref_id);
            pst_ref_category = con.prepareStatement(Sql_get_category_ref_id);
            rs_ref_category = pst_ref_category.executeQuery();
            while(rs_ref_category.next()){
              temp_category_id = rs_ref_category.getString(1);
            System.out.println("category_id::::"+temp_category_id);
            String check_catgory="select name from category where id='"+Integer.parseInt(temp_category_id)+"' ";
                PreparedStatement pst_check_cat = con.prepareStatement(check_catgory);
                ResultSet rs_check_cat = pst_check_cat.executeQuery();
                rs_check_cat.next();
                String result_cat=rs_check_cat.getString(1);
                if(category_name.equals(result_cat.toString())){
                   category_id = temp_category_id;
                }
                else{
                    
                }
            
            }
            
         
            ArrayList ar = new ArrayList();
            Map<String, String> options = new LinkedHashMap<String, String>();

            options.put("category_id", category_id);
                

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
                pst_ref_category.close();
            } catch (Exception e) {
            }
            try {
                rs_ref_category.close();
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
