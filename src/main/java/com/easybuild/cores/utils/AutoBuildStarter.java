package com.easybuild.cores.utils;

import java.util.ArrayList;
import java.util.List;

import com.easybuild.cores.model.TableInfo;

public class AutoBuildStarter {
	
	public static void main(String[] args) {
		TemplateUtil templateUtil=new TemplateUtil();
		PropertiesUtil propertiesUtil=new PropertiesUtil("src/main/resources/application.properties");
		String url=propertiesUtil.getValue("spring.datasource.url");
		String username=propertiesUtil.getValue("spring.datasource.username");
		String password=propertiesUtil.getValue("spring.datasource.password");
		String dbName = url.substring(url.lastIndexOf("/")+1, url.indexOf("?"));
		ConnDB connDB=new ConnDB(url, username,
				password, dbName);
		List<String> tableList = connDB.getTables();
		List<TableInfo> tableInfoList=new ArrayList<>();
		for (String table : tableList) {
			TableInfo tableInfo=new TableInfo();
			tableInfo.setTableName(table);
			tableInfo.setFieldList(connDB.getFieldsByTableName(table));
			templateUtil.createModel("com.easybuild.cores.model", tableInfo);
			tableInfoList.add(tableInfo);
		}
		//生成model
		
	}

}
