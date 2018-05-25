package [[${packages}]];

/**
 * [[${notes}]]
 * 
 */
public class [[${className}]]{
	[# th:each="field : ${fields}"]
	/**
	 * [[${field.fieldRemark}]]
	 */
	private	[[${field.fieldType}]] [[${field.fieldName}]];
	
	[/]
	[# th:each="field : ${fields}"]
	public [[${field.fieldType}]] get[[${field.fieldNameUP}]](){
		return [[${field.fieldName}]];
	}
	
	public void set[[${field.fieldNameUP}]]([[${field.fieldType}]] [[${field.fieldName}]]){
		this.[[${field.fieldName}]]=[[${field.fieldName}]];
	}
	[/]
	
}
