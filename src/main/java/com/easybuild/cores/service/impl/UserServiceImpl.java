package com.easybuild.cores.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easybuild.cores.model.User;
import com.easybuild.cores.dao.UserDao;
import com.easybuild.cores.service.UserService;


/**
 * 描述：用户表Service实现
 * 
 * 创建时间：2018/07/28 19:56
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	/**
	 * 插入
	 */
	@Override
	public int insert(User record) {
		return userDao.insert(record);
	}
    
	
	/**
	 * 根据主键删除
	 */
	@Override
	public int deleteByPrimaryKey(int id) {
		return userDao.deleteByPrimaryKey(id);
	}
	 
	
	/**
	 * 根据主键更新
	 */
	@Override
	public int updateByPrimaryKey(User record) {
		return userDao.updateByPrimaryKey(record);
	}

	
	/**
	 * 根据主键查询
	 */
	@Override
	public User selectByPrimaryKey(int id){
		return userDao.selectByPrimaryKey(id);
	}

	
	/**
	 * 分页、条件、排序查询
	 */
	@Override
	public List<User> select(Map<String,Object> map){
		return userDao.select(map);
	}
	
	
	/**
	 * 根据条件计算总数据量
	 */
	@Override
	public int count(Map<String,Object> map) {
		return userDao.count(map);
	}
	
}
