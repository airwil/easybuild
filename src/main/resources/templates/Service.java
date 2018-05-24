package [[${packages}]]
[# th:each="item : ${imports}"]
import [(${item})];\n
[/]

/**
 * [[${notes}]]
 * 
 */
public interface [[${className}]]Service extends BaseDao<[[${className}]]>{

}
