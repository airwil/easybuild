package com.easybuild.cores.utils;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * 根据模板生成Html页面
 */
public class TemplateUtil {
	/**
	 * @param template 模板名称
	 * @param dir HTML输出路径及名称
	 * @param data 模板数据
	 */
	public static void createHtml(String template,String dir,Map<String, Object> data) {
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
	 * @param template 模板名称
	 * @param data 模板数据
	 */
	public static void createHtml(String template,Map<String, Object> data) {
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
}