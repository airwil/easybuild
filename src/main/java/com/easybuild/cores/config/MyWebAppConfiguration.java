package com.easybuild.cores.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.easybuild.cores.interceptor.MyInterceptor;

/**
 * SpringBoot个性化配置
 */
@SuppressWarnings("deprecation")
@Configuration
public class MyWebAppConfiguration extends WebMvcConfigurerAdapter{
	//读取配置文件
	@Value(value = "${custom.uploadPath}")
	private String uploadPath;
	
	/**
	 * 设置SpringBoot读取本地文件，相当于虚拟一个服务器
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//指定“/pic/**”定向到本地路径“F:/testUpload/”
		registry.addResourceHandler("/pic/**").addResourceLocations("file:"+uploadPath);
		super.addResourceHandlers(registry);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//注册自定义拦截器，添加拦截路径和排除拦截路径  
		registry.addInterceptor(new MyInterceptor()).excludePathPatterns("/admin/index*","/admin/login*",
				"/portal/login*","/portal/register*","/index.html","/error");
	}
}