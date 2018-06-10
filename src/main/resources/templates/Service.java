package [[${packages}]];

import java.util.List;
import java.util.Map;
import [[${modelImport}]];

/**
 * [[${notes}]]
 * 
 */
public interface [[${className}]]Service {
	/**
	 * 插入
	 */
	int insert([[${className}]] record);
    
	
	/**
	 * 根据主键删除
	 */
	int deleteByPrimaryKey(int id);
	 
	
	/**
	 * 根据主键更新
	 */
	int updateByPrimaryKey([[${className}]] record);

	
	/**
	 * 根据主键查询
	 */
	[[${className}]] selectByPrimaryKey(int id);

	
	/**
	 * 分页、条件、排序查询
	 */
	List<[[${className}]]> select(Map<String,Object> map);
	
	
	/**
	 * 根据条件计算总数据量
	 */
	int count(Map<String,Object> map);
	
}
