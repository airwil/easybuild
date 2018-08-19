package com.easybuild.cores.utils.generator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 连接数据库
 *
 */
public class ConnDB {
	/**
	 * url
	 */
	private String url;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 数据库名称
	 */
	private String dbName;

	public ConnDB(String url, String username, String password, String dbName) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
		this.dbName = dbName;
	}

	/**
	 * 获取数据库表
	 */
	public List<String> getTables() {
		List<String> list = new ArrayList<String>();
		Connection connection = null;
		ResultSet rs = null;
		try {
			// 注册 JDBC 驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取连接
			connection = DriverManager.getConnection(url, username, password);
			DatabaseMetaData metaData = connection.getMetaData();
			rs = metaData.getTables(dbName, "%", "%", new String[] { "TABLE" });
			System.out.println("数据库\"" + dbName + "\"存在的表如下:");
			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				list.add(tableName);
				System.out.println(tableName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	
	
	/**
	 * 根据表名获取表的结构：字段名、类型、注释
	 */
	public List<FieldInfo> getFieldsByTableName(String tableName) {
		List<FieldInfo> list = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		try {
			// 注册 JDBC 驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取连接
			connection = DriverManager.getConnection(url, username, password);
			DatabaseMetaData metaData = connection.getMetaData();
			rs=metaData.getColumns(null,"%", tableName,"%");
			System.out.println("表："+tableName+" 的结构如下");
			while (rs.next()) {
				String fieldName=rs.getString("COLUMN_NAME");
				String fieldType=rs.getString("TYPE_NAME");
				String fieldRemark=rs.getString("REMARKS");
				FieldInfo fieldInfo=new FieldInfo();
				fieldInfo.setFieldName(fieldName);
				fieldInfo.setFieldType(fieldType);
				fieldInfo.setFieldRemark(fieldRemark);
				list.add(fieldInfo);
				System.out.println(fieldName+" "+fieldType+" "+fieldRemark);
			}
			System.out.println("-----------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 根据表名获取表的注释
	 */
	public String getTableRemark(String tableName){
		Connection connection = null;
		ResultSet rs = null;
		String comment="";
		try {
			// 注册 JDBC 驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取连接
			connection = DriverManager.getConnection(url, username, password);
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery("SHOW CREATE TABLE " + tableName);
			if(rs!=null&&rs.next()){
				String createSql=rs.getString(2);
				int index = createSql.indexOf("COMMENT='");
		        if (index < 0) {
		            return "";
		        }
		        comment = createSql.substring(index + 9);
		        comment = comment.substring(0, comment.length() - 1);
		        comment = new String(comment.getBytes("iso-8859-1"),"utf-8");
		        System.out.println(comment);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return comment;
	}
	
	public static void main(String[] args) {
		ConnDB cdb = new ConnDB("jdbc:mysql://localhost:3306/springboot?useUnicode=true&characterEncoding=utf8", "root",
				"root", "springboot");
		cdb.getFieldsByTableName("users");
	}

}
