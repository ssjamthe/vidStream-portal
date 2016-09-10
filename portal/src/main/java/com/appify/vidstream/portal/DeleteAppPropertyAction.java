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
public class DeleteAppPropertyAction extends ActionSupport {
     private Connection con;
    private String hidden_del_prop_name;
    public DeleteAppPropertyAction() {
    }

    public String getHidden_del_prop_name() {
        return hidden_del_prop_name;
    }

    public void setHidden_del_prop_name(String hidden_del_prop_name) {
        this.hidden_del_prop_name = hidden_del_prop_name;
    }
    
     /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public String execute() throws Exception {
      try {
            con = null;

            con = com.appify.vidstream.portal.util.DataConnection.getConnection();

            String Propname= getHidden_del_prop_name();
            String sql = "DELETE FROM property_table WHERE prop_name='" + Propname + "'";
            System.out.println("sql_for delete property:::" + sql);
            Statement createStatement = con.createStatement();
            createStatement.execute(sql);
              System.out.println("Delete Property  Successfully");
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