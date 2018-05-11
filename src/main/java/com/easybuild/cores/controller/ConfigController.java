package com.easybuild.cores.controller;

import com.easybuild.cores.utils.Result;
import com.easybuild.cores.utils.ResultGenerator;
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

	/**
	 * 配置数据库信息
	 * @param database  数据库IP及端口，数据库名称
	 */
	@PostMapping(value="/core/config/database")
	public Result<String> cofigureDatabase(@RequestBody Database database) {
		PropertiesUtil propertiesUtil=new PropertiesUtil("src/main/resources/application.properties");
		propertiesUtil.write("spring.datasource.url",
				"jdbc:mysql://"+database.getUrl()+"/"+database.getDatabaseName(),
				null);
		return ResultGenerator.genSuccessResult();
	}
}
