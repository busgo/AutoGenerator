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
    private List<${tableInfo.pkFieldType?cap_first}> include${tableInfo.pkFieldName?cap_first}List;

    private List<${tableInfo.pkFieldType?cap_first}> exclude${tableInfo.pkFieldName?cap_first}List;

}
