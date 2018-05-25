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
}
