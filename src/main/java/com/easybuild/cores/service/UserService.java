package com.easybuild.cores.service;

import java.util.List;
import java.util.Map;
import com.easybuild.cores.model.User;

/**
 * 描述：用户表
 * 
 * 创建时间：2018/07/28 19:56
 */
public interface UserService {
	/**
	 * 插入
	 */
	int insert(User record);
    
	
	/**
	 * 根据主键删除
	 */
	int deleteByPrimaryKey(int id);
	 
	
	/**
	 * 根据主键更新
	 */
	int updateByPrimaryKey(User record);

	
	/**
	 * 根据主键查询
	 */
	User selectByPrimaryKey(int id);

	
	/**
	 * 分页、条件、排序查询
	 */
	List<User> select(Map<String,Object> map);
	
	
	/**
	 * 根据条件计算总数据量
	 */
	int count(Map<String,Object> map);
	
}
