package   ${modelPackage};

import    lombok.Data;
import    lombok.ToString;

import    java.io.Serializable;

<#if tableInfo.includeDateType>
import    java.util.Date;
</#if>
<#if tableInfo.includeDecimal>
import    java.match.BigDecimal;
</#if>
/***
 *
 * @author Create By AutoGenerator
 */
@Data
@ToString(callSuper = true)
public class ${tableInfo.modelName} implements Serializable {



<#list  tableInfo.fieldInfoList as field>
	// ${field.remark}
	private ${field.fieldType} ${field.fieldName};

</#list>

}
