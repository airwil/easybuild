package com.easybuild.cores.utils;
import com.easybuild.cores.model.FieldInfo;
import com.easybuild.cores.model.TableInfo;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 根据模板生成Html或者Java
 */
@Component
public class TemplateUtil {
	/**
	 * 生成HTML
	 * @param template 模板名称
	 * @param dir HTML输出路径及名称
	 * @param data 模板数据
	 */
	public  void createHtml(String template,String dir,Map<String, Object> data) {
		//默认将HTML存入resources/static/
		if(null==dir) {
			String path=System.getProperty("user.dir")+"/src/main/resources/static/";
			dir=path+template+".html";
		}
		
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setTemplateMode("HTML");
		templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        Context context=new Context();
        context.setVariables(data);
		try {
			FileWriter writer = new FileWriter(dir);
			templateEngine.process(template, context, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 生成HTML (默认存放路径：/src/main/resources/static/)
	 * @param template 模板名称
	 * @param data 模板数据
	 */
	public  void createHtml(String template,Map<String, Object> data) {
		//默认将HTML存入resources/static/
		String path=System.getProperty("user.dir")+"/src/main/resources/static/";
		String dir=path+template+".html";
		
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setTemplateMode("HTML");
		templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        Context context=new Context();
        context.setVariables(data);
		try {
			FileWriter writer = new FileWriter(dir);
			templateEngine.process(template, context, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成Java类
	 */
	public void createJava(String template,String dir,Map<String, Object> data) {
		//生成时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm");
        data.put("creatTime",sdf.format(new Date()));
        
        //创建模板生成类
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setTemplateMode("TEXT");
		templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".java");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        Context context=new Context();
        context.setVariables(data);
        
        //判断文件夹是否存在
        int lastIndexOf = dir.lastIndexOf("/");
        String s= dir.substring(0, lastIndexOf);
        File f=new File(s);
        if(!f.exists()) {
        	System.err.println("文件夹:"+s+" 不存在，正在创建...");
        	f.mkdirs();
        }
        
        
        
        //生成模板
		try {
			FileWriter writer = new FileWriter(dir);
			templateEngine.process(template, context, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 生成Model类
	 */
	public void createModel(String packages,TableInfo tableInfo) {
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("packages", packages);
		data.put("notes", tableInfo.getNotes());
		data.put("fields", tableInfo.getFieldList());
		data.put("className", tableInfo.getTableNameUP());
		String s=packages.replace(".", "/");
		String dir=System.getProperty("user.dir")+"/src/main/java/"+s+"/"+tableInfo.getTableNameUP()+".java";
		this.createJava("Model", dir, data);
		System.err.println("生成Model类:"+tableInfo.getTableNameUP());
		System.err.println("路径:"+dir);
	}
	
	
	/** 
	 * 生成DAO接口
	 * @param packages DAO包位置
	 * @param tableInfo 表信息
	 * @param baseDaoImport BaseDao的位置
	 * @param modelImport Model的位置
	 */
	public void createDao(String packages,TableInfo tableInfo,String baseDaoImport,String modelImport) {
		Map<String,Object> data=new HashMap<>();
		data.put("packages", packages);
		data.put("notes", tableInfo.getNotes());
		data.put("className", tableInfo.getTableNameUP());
		data.put("baseDaoImport", baseDaoImport+".BaseDao");
		data.put("modelImport", modelImport+"."+tableInfo.getTableNameUP());
		String s=packages.replace(".", "/");
		String dir=System.getProperty("user.dir")+"/src/main/java/"+s+"/"+tableInfo.getTableNameUP()+"Dao.java";
		this.createJava("Dao", dir, data);
	}

	/**
	 * 生成Service接口
	 */
	public void createIService(String packages,TableInfo tableInfo,String modelImport){
		Map<String,Object> data=new HashMap<>();
		data.put("packages", packages);
		data.put("notes", tableInfo.getNotes());
		data.put("className", tableInfo.getTableNameUP());
		data.put("modelImport", modelImport+"."+tableInfo.getTableNameUP());
		String s=packages.replace(".", "/");
		String dir=System.getProperty("user.dir")+"/src/main/java/"+s+"/"+tableInfo.getTableNameUP()+"Service.java";
		this.createJava("Service", dir, data);
	}

	/**
	 * 生成Service实现类
	 */
	public void createServiceImpl(String packages,TableInfo tableInfo,
								  String modelImport,String daoImport,String serviceImport) {
		Map<String,Object> data=new HashMap<>();
		data.put("packages", packages);
		data.put("notes", tableInfo.getNotes());
		data.put("className", tableInfo.getTableNameUP());
		data.put("className2",tableInfo.getTableName());
		List<String> imports = new ArrayList<String>();
		imports.add(modelImport+"."+tableInfo.getTableNameUP());
		imports.add(daoImport+"."+tableInfo.getTableNameUP()+"Dao");
		imports.add(serviceImport+"."+tableInfo.getTableNameUP()+"Service");
		data.put("imports", imports);
		data.put("daoImport",daoImport+"."+tableInfo.getTableNameUP());
		String s=packages.replace(".", "/");
		String dir=System.getProperty("user.dir")+"/src/main/java/"+s+"/"+tableInfo.getTableNameUP()+"ServiceImpl.java";
		this.createJava("ServiceImpl", dir, data);
	}

	/**
	 * 生成Controller
	 */
	public void createController(String packages,TableInfo tableInfo,
			  String modelImport,String serviceImport,String utilImport,
			  String[] searchParams,String sort) {
		Map<String,Object> data=new HashMap<>();
		data.put("packages", packages);
		data.put("notes", tableInfo.getNotes());
		data.put("className", tableInfo.getTableNameUP());
		data.put("className2",tableInfo.getTableName());
		List<String> imports = new ArrayList<String>();
		imports.add(modelImport+"."+tableInfo.getTableNameUP());
		imports.add(serviceImport+"."+tableInfo.getTableNameUP()+"Service");
		imports.add(utilImport+".*");
		data.put("imports", imports);
		if(searchParams==null||searchParams.length<1){
			data.put("isSearch", null);	
		}else{
			data.put("isSearch", "true");
		}
		data.put("searchParams", searchParams);
		data.put("sort", sort);
		String s=packages.replace(".", "/");
		String dir=System.getProperty("user.dir")+"/src/main/java/"+s+"/"+tableInfo.getTableNameUP()+"Controller.java";
		this.createJava("Controller", dir, data);
	}

	/**
	 * 生成MapperXML
	 */
	public void createMapperXML(String xmlname,TableInfo tableInfo) {
		String path=System.getProperty("user.dir")+"/src/main/resources/mappers/";
		String dir=path+xmlname+".xml";
		
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setTemplateMode("TEXT");
		templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".txt");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("daoImport", "com.easybuild.cores.dao.TestDao");
        map.put("modelImport", "com.easybuild.cores.model.Test");
        map.put("fields", tableInfo.getFieldList());
        map.put("tableInfo", tableInfo);
        String searchParams[]={"id","name"};
        map.put("searchParams", searchParams);
        Context context=new Context();
        context.setVariables(map);
		try {
			FileWriter writer = new FileWriter(dir);
			templateEngine.process("Mapper", context, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		TemplateUtil templateUtil=new TemplateUtil();
		TableInfo tableInfo=new TableInfo();
		tableInfo.setTableName("test");
		tableInfo.setNotes("测试");
		List<FieldInfo> list=new ArrayList<>();
		FieldInfo info=new FieldInfo();
		info.setFieldName("username");
		info.setFieldType("VARCHAR");
		info.setFieldRemark("用户名");
		FieldInfo info2=new FieldInfo();
		info2.setFieldName("age");
		info2.setFieldType("INTEGER");
		info2.setFieldRemark("年龄");
		list.add(info);
		list.add(info2);
		tableInfo.setFieldList(list);
//		templateUtil.createModel("com.easybuild.cores.model", tableInfo);
//		templateUtil.createDao("com.easybuild.cores.dao", tableInfo,"com.easybuild.cores.dao","com.easybuild.cores.model");
//		templateUtil.createIService("com.easybuild.cores.service", tableInfo,"com.easybuild.cores.model");
//		templateUtil.createServiceImpl("com.easybuild.cores.service.impl", tableInfo,
//				"com.easybuild.cores.model","com.easybuild.cores.dao",
//				"com.easybuild.cores.service");
//		templateUtil.createController("com.easybuild.cores.controller", tableInfo, "com.easybuild.cores.model",
//				"com.easybuild.cores.service", "com.easybuild.cores.utils", null, "id");
		templateUtil.createMapperXML("TestMapper",tableInfo);
	}
}