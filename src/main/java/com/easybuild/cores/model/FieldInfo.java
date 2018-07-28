package com.easybuild.cores.model;

/**
 * 表字段信息
 *
 */
public class FieldInfo {
	//字段名
	private String fieldName;
	//字段名首字母大写
	private String fieldNameUP;
	//类型
	private String fieldType;
	//Java类型
	private String javaType;
	//注释
	private String fieldRemark;
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
		
		char[] cs=fieldName.toCharArray();
        cs[0]-=32;
        this.fieldNameUP=String.valueOf(cs);
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
		
		switch (fieldType) {
			case "INTEGER":
				this.javaType="Integer";
				break;
			case "INT":
				this.javaType="Integer";
				this.fieldType="INTEGER";
				break;
			case "VARCHAR":
				this.javaType="String";
				break;
			case "DATE":
				this.javaType="java.util.Date";
				break;
			case "TIMESTAMP":
				this.javaType="java.sql.Timestamp";
				break;
			case "DECIMAL":
				this.javaType="java.math.BigDecimal";
				break;
			}
	}
	public String getFieldRemark() {
		return fieldRemark;
	}
	public void setFieldRemark(String fieldRemark) {
		this.fieldRemark = fieldRemark;
	}
	public String getFieldNameUP() {
		return fieldNameUP;
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
}
