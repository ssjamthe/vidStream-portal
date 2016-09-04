/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Ankit Kumar
 */
public class DataConnection {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
    	return DataSourceFactory.getDataSource().getConnection();
    }
}
