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
public class getAppTopLevelcategoryServlet extends HttpServlet {

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
    private String sql_app_catories, categorization_id;
    private PreparedStatement stmt_app_catories, stmt_system_integrator_name;
    private ResultSet rs_app_catories, rs_system_integrator_name;
    private List  <Integer>parent_category_list, child_category_list;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        parent_category_list  = new <Integer> ArrayList();
        child_category_list = new ArrayList();
        con = null;
        sql_app_catories = "";
        stmt_app_catories = null;
        rs_app_catories = null;

        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            categorization_id = request.getParameter("categorization_id");
            System.out.println("categorization_id::::" + categorization_id);

            con = conn.appify.vidstream.portal.DataConnection.getConnection();

          /*  String SQl_parent_category_list = "select distinct parent_category_id from parent_child_category_mappings order by parent_category_id asc";
            PreparedStatement pst_parent_category_list = con.prepareStatement(SQl_parent_category_list);
            ResultSet rs_parent_category_list = pst_parent_category_list.executeQuery();
            while (rs_parent_category_list.next()) {
                parent_category_list.add(rs_parent_category_list.getInt(1));
            }*/
             String SQl_child_category_list = "select distinct child_category_id from parent_child_category_mappings order by child_category_id asc";
             PreparedStatement pst_child_category_list = con.prepareStatement(SQl_child_category_list);
             ResultSet rs_child_category_list = pst_child_category_list.executeQuery();
             while (rs_child_category_list.next()) {
             parent_category_list.add(rs_child_category_list.getInt(1));
             }
           

            sql_app_catories = "select id,name from category where categorization_id='" + categorization_id + "'  ORDER BY name asc";

            System.out.println("sql_app_catories::::" + sql_app_catories);
            stmt_app_catories = con.prepareStatement(sql_app_catories);

            rs_app_catories = stmt_app_catories.executeQuery();
            out.print("<option value='Select'>" + "Select" + "</option>");
            while (rs_app_catories.next()) {
                boolean match_found = true;
                int catid = rs_app_catories.getInt(1);
                for (int k = 0; k < parent_category_list.size(); k++) {

                    if (parent_category_list.get(k) == catid) {
                        match_found = false;
                        System.out.println("match_found ::::::::::-----" + parent_category_list.get(k));
                    }
                }

                if (match_found == true) {




                    System.out.println("match_found true::::::::::-----" + catid);
                    System.out.println("data" + rs_app_catories.getString(2));
                    //str=str+"<option>"+rs.getString(1)+"</option>";
                    //out.print("<option>" + rs.getString(1) + "</option>");
                    out.print("<option value='" + rs_app_catories.getString(2) + "'>" + rs_app_catories.getString(2) + "</option>");
                } else {
                }


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
