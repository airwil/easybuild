package com.easybuild.cores.config;

/**
 * 数据库配置信息
 */
public class Database {
	/**
	 * 数据库IP地址+端口  例如：127.0.0.1:3306
	 */
	private String url;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String passwrod;
	
	/**
	 * 数据库名称
	 */
	private String databaseName;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswrod() {
		return passwrod;
	}
	public void setPasswrod(String passwrod) {
		this.passwrod = passwrod;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	
	
}
