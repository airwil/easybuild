package com.easybuild.cores.utils.generator;

import java.util.List;

/**
 * 表信息
 */
public class TableInfo {
	/**
	 * 表名
	 */
	private String tableName;
	
	/**
	 * 表名首字母大写
	 */
	private String tableNameUP;
	
	/**
	 * 字段信息
	 */
	private List<FieldInfo> fieldList;
	
	/**
	 * 表注释
	 */
	private String notes;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
		
		char[] cs=tableName.toCharArray();
        cs[0]-=32;
        this.tableNameUP=String.valueOf(cs);
	}

	public List<FieldInfo> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<FieldInfo> fieldList) {
		this.fieldList = fieldList;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public String getTableNameUP() {
		return tableNameUP;
	}
}
