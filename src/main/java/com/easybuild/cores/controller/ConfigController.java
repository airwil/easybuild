package com.easybuild.cores.controller;

import com.easybuild.cores.utils.Result;
import com.easybuild.cores.utils.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.easybuild.cores.model.Database;
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

	/**
	 * 配置数据库信息
	 * @param database  数据库IP及端口，数据库名称
	 */
	@PostMapping(value="/core/config/database/write")
	public Result<String> cofigureDatabase(@RequestBody Database database) {
		PropertiesUtil propertiesUtil=new PropertiesUtil("src/main/resources/application.properties");
		propertiesUtil.write("spring.datasource.url",
				"jdbc:mysql://"+database.getUrl()+"/"+database.getDatabaseName(),
				null);
		return ResultGenerator.genSuccessResult();
	}

	/**
	 * 查询数据库信息
	 */
	@GetMapping(value = "/core/config/database/read")
	public Result<Database> readDatabaseConfig(){
		PropertiesUtil propertiesUtil=new PropertiesUtil("src/main/resources/application.properties");
		String s=propertiesUtil.getValue("spring.datasource.url");
		String[] s1 = s.split("//");
		String[] s2 = s1[1].split("/");
		String url=s2[0];
		String databaseName = s2[1];
		String username=propertiesUtil.getValue("spring.datasource.username");
		String password=propertiesUtil.getValue("spring.datasource.password");
		Database database=new Database();
		database.setUrl(url);
		database.setDatabaseName(databaseName);
		database.setUsername(username);
		database.setPasswrod(password);
		return ResultGenerator.genSuccessResult(database);
	}
}
