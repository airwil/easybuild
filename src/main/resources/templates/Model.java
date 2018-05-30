package [[${packages}]];

/**
 * 描述：[[${notes}]]
 * 
 * 创建时间：[[${creatTime}]]
 */
public class [[${className}]]{
	[# th:each="field : ${fields}"]
	/**
	 * [[${field.fieldRemark}]]
	 */
	private	[[${field.javaType}]] [[${field.fieldName}]];
	
	[/]
	[# th:each="field : ${fields}"]
	public [[${field.javaType}]] get[[${field.fieldNameUP}]](){
		return [[${field.fieldName}]];
	}
	
	public void set[[${field.fieldNameUP}]]([[${field.javaType}]] [[${field.fieldName}]]){
		this.[[${field.fieldName}]]=[[${field.fieldName}]];
	}
	[/]
	
}
