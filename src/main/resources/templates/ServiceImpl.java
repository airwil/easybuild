package [[${packages}]];

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
[# th:each="item : ${imports}"]
import [[${item}]];
[/]

/**
 * [[${notes}]]
 * 
 */
@Service
public class [[${className}]]ServiceImpl {
	@Autowired
	private [[${className}]]Dao [[${className2}]]Dao;
	
	/**
	 * 插入
	 */
	public int insert([[${className}]] record) {
		return [[${className2}]]Dao.insert(record);
	}
    
	
	/**
	 * 根据主键删除
	 */
	public int deleteByPrimaryKey(int id) {
		return [[${className2}]]Dao.deleteByPrimaryKey(id);
	}
	 
	
	/**
	 * 根据主键更新
	 */
	public int updateByPrimaryKey([[${className}]] record) {
		return [[${className2}]]Dao.updateByPrimaryKey(record);
	}

	
	/**
	 * 根据主键查询
	 */
	public [[${className}]] selectByPrimaryKey(int id){
		return [[${className2}]]Dao.selectByPrimaryKey(id);
	}

	
	/**
	 * 分页、条件、排序查询
	 */
	public List<[[${className}]]> select(Map<String,Object> map){
		return [[${className2}]]Dao.select(map);
	}
	
	
	/**
	 * 根据条件计算总数据量
	 */
	public int count(Map<String,Object> map) {
		return [[${className2}]]Dao.count(map);
	}
	
}
