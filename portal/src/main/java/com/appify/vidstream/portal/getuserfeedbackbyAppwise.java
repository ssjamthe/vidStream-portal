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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class getuserfeedbackbyAppwise extends HttpServlet {

    private static final String SUCCESS = "success";
    private final static String FAILURE = "failure";
    private Connection con;
    private String sql_getfeedback_appwise, app_id;
    private PreparedStatement stmt_app_feedback;
    private ResultSet rs_app_feedback;
    private String convert_comment_Date;
    List ar;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        con = null;
        sql_getfeedback_appwise = "";
        stmt_app_feedback = null;
        rs_app_feedback = null;

        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            ar = new ArrayList();
            app_id = request.getParameter("app_id");
            System.out.println("categorization_id::::" + app_id);
            con = com.appify.vidstream.portal.util.DataConnection.getConnection();


            if (app_id.equals("Select")) {
                sql_getfeedback_appwise = "SELECT  app_id, device_id, user_comment, date(comment_date) FROM feedback  order by app_id asc";

            } else {
                sql_getfeedback_appwise = "SELECT  app_id, device_id, user_comment, date(comment_date) FROM feedback where app_id='" + app_id + "' order by app_id asc";
                
            }


            System.out.println("sql_app_catories::::" + sql_getfeedback_appwise);
            stmt_app_feedback = con.prepareStatement(sql_getfeedback_appwise);
            rs_app_feedback = stmt_app_feedback.executeQuery();
            // out.print("<option value='Select'>" + "Select" + "</option>");
            while (rs_app_feedback.next()) {
                Map<String, String> options = new LinkedHashMap<String, String>();
                options.put("app_id", Integer.toString(rs_app_feedback.getInt(1)));
                String SQL_getAPP_NAME = "select name from application where id='" + rs_app_feedback.getInt(1) + "'";
                PreparedStatement pst_getAPP_NAME = con.prepareStatement(SQL_getAPP_NAME);
                ResultSet rs_app_name = pst_getAPP_NAME.executeQuery();
                rs_app_name.next();
                options.put("app_name", rs_app_name.getString(1));
                options.put("device_id", rs_app_feedback.getString(2));
                options.put("user_comment", rs_app_feedback.getString(3));
                try {
                    String commentDate = rs_app_feedback.getString(4);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date tempcreateDate = simpleDateFormat.parse(commentDate);
                    SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-YYYY");
                    convert_comment_Date = outputDateFormat.format(tempcreateDate);
                } catch (ParseException ex) {
                    System.out.println("Parse Exception" + ex);
                }
                options.put("comment_date", convert_comment_Date);
                ar.add(options);
                // list_feedback_app_wise.add(convert_comment_Date);
            }
            System.out.println("connection closed");
            String json = new Gson().toJson(ar);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (SQLException e) {
            System.out.println("excep::" + e);

        } finally {
            try {
                stmt_app_feedback.close();
            } catch (Exception e) {
            }
            try {
                rs_app_feedback.close();
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
