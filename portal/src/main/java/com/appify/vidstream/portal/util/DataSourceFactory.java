/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appify.vidstream.portal.util;

import java.util.Properties;


import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.apache.commons.dbcp2.BasicDataSource;
/**
 *
 * @author Nileh Diore
 */
public class DataSourceFactory {
     private static DataSource dataSource;
	 private static Logger log=Logger.getLogger(DataSourceFactory.class.getName());
	 
	 /**
		 * @return the dataSource
		 */
		public static DataSource getDataSource() {
			if(dataSource==null){
				loadDataSource();
			}
			return dataSource;
		}

		
		public static synchronized void loadDataSource() {
			if(dataSource==null){
				log.debug("DataSource is getting initialized...");
                                System.out.println("Inside DataSource Factory-----");
				BasicDataSource ds=new BasicDataSource();
				Properties conf=ResourceHelper.getResource();
                             
				ds.setUrl(conf.getProperty("url"));
				ds.setDriverClassName(conf.getProperty("driverClassName"));
				ds.setUsername(conf.getProperty("username"));
				ds.setPassword(conf.getProperty("password"));
				ds.setMaxTotal(Integer.parseInt(conf.getProperty("maxConnections")));
				ds.setMaxIdle(Integer.parseInt(conf.getProperty("maxConnections")));
				dataSource=ds;
                                System.out.println("dataSource---"+dataSource);
				log.debug("DataSource successfully created with total Connections..."+ds.getMaxTotal());
			}
			
		}

		/**
		 * @param dataSource the dataSource to set
		 */
		public static void setDataSource(DataSource dataSource) {
			DataSourceFactory.dataSource = dataSource;
		}
}
