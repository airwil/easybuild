package com.easybuild.cores.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class MyWebAppConfiguration extends WebMvcConfigurerAdapter{
	//读取配置文件
//	@Value(value = "${localFile}")
//	private String localFile;
	
	/**
	 * 设置SpringBoot读取本地文件，相当于虚拟一个服务器
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//指定“/pic/**”定向到本地路径“F:/testUpload/”
		registry.addResourceHandler("/pic/**").addResourceLocations("file:F:/testUpload/");
		super.addResourceHandlers(registry);
	}
}