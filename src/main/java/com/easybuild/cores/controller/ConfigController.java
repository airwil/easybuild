package com.easybuild.cores.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.easybuild.cores.config.Database;
import com.easybuild.cores.utils.PropertiesUtil;

/**
 * <p>
 *  项目数据库配置
 * </p>
 *
 *
 */
@RestController
public class ConfigController {
	
	@PostMapping(value="/core/config/database")
	public void cofigureDatabase(@RequestBody Database database) {
		PropertiesUtil propertiesUtil=new PropertiesUtil("src/main/resources/application2.properties");
//		propertiesUtil.write("test", "3", null);
		boolean exist = propertiesUtil.exist("test");
		System.out.println(exist);
	}
}
