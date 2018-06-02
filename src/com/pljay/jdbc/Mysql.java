package com.pljay.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public final class Mysql
{
  private Logger log = Logger.getLogger(Mysql.class);
  private static Mysql instance;
  private static ComboPooledDataSource dataSource;
  public Connection conn = null;
  public PreparedStatement pst = null;
  public static String url = "";
  public static String user = "";
  public static String pass = "";
  public static String ip = "";
  public static int port;
  public static String CORPID = "";
  public static String SECRET = "";

private Mysql()
    throws SQLException, PropertyVetoException
  {
    File f = new File(getClass().getResource("/").getPath());
    String a = f+"";
    
    new XMLReaderTest(a);
    dataSource = new ComboPooledDataSource();
    dataSource.setUser(user);
    dataSource.setPassword(pass);
    dataSource.setJdbcUrl(url);
    dataSource.setDriverClass("com.mysql.jdbc.Driver");
 /*   dataSource.setInitialPoolSize(10);*/
    dataSource.setMinPoolSize(2);
    dataSource.setMaxPoolSize(100);
    dataSource.setMaxStatements(0);
    dataSource.setMaxStatementsPerConnection(0);
    dataSource.setMaxIdleTime(60);
    dataSource.setIdleConnectionTestPeriod(2000);
  }
  
  public static final Mysql getInstance()
  {
    if (instance == null) {
      try
      {
        instance = new Mysql();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return instance;
  }
  
  public final synchronized Connection getConnection()
  {
    Connection conn = null;
    try
    {
      conn = dataSource.getConnection();
      String className = java.lang.Thread.currentThread().getStackTrace()[2].getClassName();
      String methodName = java.lang.Thread.currentThread().getStackTrace()[2].getMethodName();
      int lineNumber = java.lang.Thread.currentThread().getStackTrace()[2].getLineNumber();
      this.log.info(className);
      this.log.info(methodName);
      this.log.info(String.valueOf(lineNumber));
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return conn;
  }
  
  public Mysql(String sql)
  {
    try
    {
      this.conn = getInstance().getConnection();
      this.pst = this.conn.prepareStatement(sql);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void close()
  {
    try
    {
      this.pst.close();
      this.conn.close();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  
}
