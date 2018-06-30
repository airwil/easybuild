package com.easybuild.cores.service;

import java.util.List;
import java.util.Map;
import com.easybuild.cores.model.Test;

/**
 * 测试
 * 
 */
public interface TestService {
	/**
	 * 插入
	 */
	int insert(Test record);
    
	
	/**
	 * 根据主键删除
	 */
	int deleteByPrimaryKey(int id);
	 
	
	/**
	 * 根据主键更新
	 */
	int updateByPrimaryKey(Test record);

	
	/**
	 * 根据主键查询
	 */
	Test selectByPrimaryKey(int id);

	
	/**
	 * 分页、条件、排序查询
	 */
	List<Test> select(Map<String,Object> map);
	
	
	/**
	 * 根据条件计算总数据量
	 */
	int count(Map<String,Object> map);
	
}
