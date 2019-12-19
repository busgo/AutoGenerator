package    ${queryPackage};

import    lombok.Data;
import    lombok.ToString;

<#if tableInfo.includeDateType>
import    java.util.Date;
</#if>
<#if tableInfo.includeDecimal>
import    java.math.BigDecimal;
</#if>
import    java.util.List;
/***
 *
 * @author Create By AutoGenerator
 */
@Data
@ToString(callSuper = true)
public class ${tableInfo.modelName}Query extends BaseQuery {


<#list  tableInfo.fieldInfoList as field>
	// ${field.remark}
	private ${field.fieldType} ${field.fieldName};

</#list>
<#list  tableInfo.fieldInfoList as field>
	// include ${field.remark}
	private List<${field.fieldType}}> include${field.fieldName?cap_first}List;

    // exclude ${field.remark}
    private List<${field.fieldType}> exclude${field.fieldName?cap_first}List;

</#list>

}
