package com.easybuild.cores.utils.generator;

import java.util.ArrayList;
import java.util.List;

public class AutoBuildStarter {
	/**model包名*/
	private static String MODEL_PACKAGE="com.easybuild.cores.model";
	
	/**DAO包名*/
	private static String DAO_PACKAGE="com.easybuild.cores.dao";
	
	/**BaseDao包名*/
	private static String BASEDAO_PACKAGE="com.easybuild.cores.dao";
	
	/**Service包名*/
	private static String SERVICE_PACKAGE="com.easybuild.cores.service";
	
	/**ServiceImpl包名*/
	private static String SERVICE_IMPL_PACKAGE="com.easybuild.cores.service.impl";
	
	/**Controller包名*/
	private static String CONTROLLER_PACKAGE="com.easybuild.cores.controller";
	
	/**Util工具类包名*/
	private static String UTILS_PACKAGE="com.easybuild.cores.utils";
	
	/**配置不需要创建的表名*/
	private static final String[] NO_CREATE_TABLES=null;
	
	/**配置指定生成的表名*/
	private static final String[] APPOINT_TABLES=null;
	
	/**配置搜索参数*/
	private static final  String[] SEARCHPARAMS=null;
	
	public static void main(String[] args) {
//		settingPackage("com.easybuild.cores");//包名前缀一致时使用
		start();
	}

	/**
	 * 配置包前缀，在包前缀一致的情况下使用
	 */
	public static void settingPackage(String prefix) {
		AutoBuildStarter.MODEL_PACKAGE=prefix+".model";
		AutoBuildStarter.DAO_PACKAGE=prefix+".dao";
		AutoBuildStarter.BASEDAO_PACKAGE=prefix+".dao";
		AutoBuildStarter.SERVICE_PACKAGE=prefix+".service";
		AutoBuildStarter.SERVICE_IMPL_PACKAGE=prefix+".service.impl";
		AutoBuildStarter.CONTROLLER_PACKAGE=prefix+".controller";
		AutoBuildStarter.UTILS_PACKAGE=prefix+".utils";
	}


	public static void start() {
		TemplateUtil templateUtil=new TemplateUtil();
		PropertiesUtil propertiesUtil=new PropertiesUtil("src/main/resources/application.properties");
		String url=propertiesUtil.getValue("spring.datasource.url");
		String username=propertiesUtil.getValue("spring.datasource.username");
		String password=propertiesUtil.getValue("spring.datasource.password");
		String dbName = url.substring(url.lastIndexOf("/")+1, url.indexOf("?"));
		ConnDB connDB=new ConnDB(url, username,
				password, dbName);
		List<String> tableList = new ArrayList<>();
		//判断是否有指定的表
		if(APPOINT_TABLES!=null&&APPOINT_TABLES.length>0){
			for (String t : APPOINT_TABLES) {
				tableList.add(t);
			}
		}else{
			tableList=AutoBuildStarter.selectedTables(connDB.getTables(), NO_CREATE_TABLES);
		}
		List<TableInfo> tableInfoList=new ArrayList<>();
		for (String table : tableList) {
			TableInfo tableInfo=new TableInfo();
			tableInfo.setTableName(table);
			tableInfo.setNotes(connDB.getTableRemark(table));
			tableInfo.setFieldList(connDB.getFieldsByTableName(table));
			//生成model
			templateUtil.createModel(MODEL_PACKAGE, tableInfo);
			//生成DAO
			templateUtil.createDao(DAO_PACKAGE, tableInfo, BASEDAO_PACKAGE, MODEL_PACKAGE);
			//生成Service
			templateUtil.createIService(SERVICE_PACKAGE, tableInfo, MODEL_PACKAGE);
			//生成ServiceImpl
			templateUtil.createServiceImpl(SERVICE_IMPL_PACKAGE, tableInfo, 
					MODEL_PACKAGE, DAO_PACKAGE, SERVICE_PACKAGE);
			//生成Controller
			templateUtil.createController(CONTROLLER_PACKAGE, tableInfo,
					MODEL_PACKAGE, SERVICE_PACKAGE, UTILS_PACKAGE, SEARCHPARAMS, "id");
			//生成Mapper
			templateUtil.createMapperXML(tableInfo.getTableNameUP()+"Mapper", tableInfo,MODEL_PACKAGE,DAO_PACKAGE,SEARCHPARAMS);
			tableInfoList.add(tableInfo);
		}
	}

	
	/**
	 * 去除不用生成的表
	 * @param allTableList
	 * @param noCreateTables
	 * @return
	 */
	public static List<String> selectedTables(List<String> allTableList,String[] noCreateTables){
		if(noCreateTables==null||noCreateTables.length==0){
			return allTableList;
		}
		for (String s : noCreateTables) {
			allTableList.remove(s.toLowerCase());
		}
		return allTableList;
	}
}
