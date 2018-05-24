package [[${packages}]]
[# th:each="item : ${imports}"]
import [(${item})];\n
[/]

/**
 * [[${notes}]]
 * 
 */
public interface [[${className}]]Dao extends BaseDao<[[${className}]]>{

}
