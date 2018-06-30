package com.easybuild.cores.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easybuild.cores.model.Test;
import com.easybuild.cores.dao.TestDao;
import com.easybuild.cores.service.TestService;


/**
 * 测试
 * 
 */
@Service
public class TestServiceImpl implements TestService{
	@Autowired
	private TestDao testDao;
	
	/**
	 * 插入
	 */
	@Override
	public int insert(Test record) {
		return testDao.insert(record);
	}
    
	
	/**
	 * 根据主键删除
	 */
	@Override
	public int deleteByPrimaryKey(int id) {
		return testDao.deleteByPrimaryKey(id);
	}
	 
	
	/**
	 * 根据主键更新
	 */
	@Override
	public int updateByPrimaryKey(Test record) {
		return testDao.updateByPrimaryKey(record);
	}

	
	/**
	 * 根据主键查询
	 */
	@Override
	public Test selectByPrimaryKey(int id){
		return testDao.selectByPrimaryKey(id);
	}

	
	/**
	 * 分页、条件、排序查询
	 */
	@Override
	public List<Test> select(Map<String,Object> map){
		return testDao.select(map);
	}
	
	
	/**
	 * 根据条件计算总数据量
	 */
	@Override
	public int count(Map<String,Object> map) {
		return testDao.count(map);
	}
	
}
