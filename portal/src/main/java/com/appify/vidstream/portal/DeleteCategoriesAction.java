/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Nileh Diore
 */
public class DeleteCategoriesAction extends ActionSupport {
    private Connection con;
        private String hidden_category_id,hidden_category_name;

    public String getHidden_category_id() {
        return hidden_category_id;
    }

    public void setHidden_category_id(String hidden_category_id) {
        this.hidden_category_id = hidden_category_id;
    }

    public String getHidden_category_name() {
        return hidden_category_name;
    }

    public void setHidden_category_name(String hidden_category_name) {
        this.hidden_category_name = hidden_category_name;
    }

  
        
    public DeleteCategoriesAction() {
    }
    
    public String execute() throws Exception {
         try {
            con = null;




            con = com.appify.vidstream.portal.util.DataConnection.getConnection();

        String CATID =getHidden_category_id();
        String CATNAME =getHidden_category_name();


            String sql = "DELETE FROM category WHERE id ='" + Integer.parseInt(CATID) + "' and name='"+CATNAME+"'";
            System.out.println("sql_for delete:::"+sql);
            Statement createStatement = con.createStatement();
            createStatement.execute(sql);
            System.out.println("Delete Successfully Category id:::"+CATID);
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return ERROR;
        } finally {
            try {
                

                con.close();
            } catch (Exception e) {
                try {
                    if (null != con) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        }
    }
}