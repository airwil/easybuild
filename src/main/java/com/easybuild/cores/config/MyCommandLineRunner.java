package com.easybuild.cores.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class MyCommandLineRunner implements CommandLineRunner{
	@Value("${server.port}")
	private String port;
	
    @Override
    public void run(String... var1) throws Exception{
    	System.err.println("项目启动完成 \n前台：http://localhost:"+port+"  后台：http://localhost:"+port+"/admin/index.html");
    }
}