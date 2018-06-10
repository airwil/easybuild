package com.easybuild.cores.dao;
import java.util.List;
import java.util.Map;

/**
 * 基类Dao：所有的Dao都要继承此接口
 * 
 */
public interface BaseDao<T> {
	/**
	 * 插入
	 */
	int insert(T record);
    
	
	/**
	 * 根据主键删除
	 */
	int deleteByPrimaryKey(int id);
	 
	
	/**
	 * 根据主键更新
	 */
	int updateByPrimaryKey(T record);

	
	/**
	 * 根据主键查询
	 */
	T selectByPrimaryKey(int id);

	
	/**
	 * 分页、条件、排序查询
	 */
	List<T> select(Map<String,Object> map);
	
	
	/**
	 * 根据条件计算总数据量
	 */
	int count(Map<String,Object> map);
	
}
