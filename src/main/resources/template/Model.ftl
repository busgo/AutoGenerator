package   ${modelPackage};

import    lombok.AllArgsConstructor;
import    lombok.Builder;
import    lombok.Data;
import    lombok.NoArgsConstructor;
import    lombok.ToString;

import    java.io.Serializable;

<#if tableInfo.includeDateType>
import    java.util.Date;
</#if>
<#if tableInfo.includeDecimal>
import    java.math.BigDecimal;
</#if>
/***
 *
 * @author Create By AutoGenerator
 */
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${tableInfo.modelName} implements Serializable {



<#list  tableInfo.fieldInfoList as field>
	// ${field.remark}
	private ${field.fieldType} ${field.fieldName};

</#list>

}
