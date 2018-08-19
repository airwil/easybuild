package com.easybuild.cores.config;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(1)
public class MyCommandLineRunner implements CommandLineRunner{
	@Value("${server.port}")
	private String port;
	@Value("${uploadPath}")
	private String uploadPath;
	private static final Logger logger=LoggerFactory.getLogger(MyCommandLineRunner.class);
    @Override
    public void run(String... var1) throws Exception{
    	System.err.println("项目启动完成 \n前台：http://localhost:"+port+"  后台：http://localhost:"+port+"/admin/index.html");
    	//检查本地上传文件夹是否建立
    	File filepath=new File(uploadPath);
		if(!filepath.exists()){
			filepath.mkdirs();
			logger.info("上传文件夹建立---[{}]",uploadPath);
		}else{
			logger.info("上传文件夹已存在");
		}
    }
}