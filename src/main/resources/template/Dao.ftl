package	${daoPackage};

import ${modelPackage}.${tableInfo.modelName};
import ${queryPackage}.${tableInfo.modelName}Query;

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
public interface ${tableInfo.modelName}Dao extends BaseDao<${tableInfo.pkFieldType},${tableInfo.modelName},${tableInfo.modelName}Query> {


<#list  tableInfo.fieldInfoList as field >
     /***
     * 根据查询条件查询${field.remark}列表
     * @param query 查询条件
     * @return
     */
     List<${field.fieldType}> query${field.fieldName?cap_first}ListByParam(${tableInfo.modelName}Query query);

</#list>

<#list  tableInfo.fieldInfoList as field >
     /***
     * 根据查询条件查询${field.remark}总数
     * @param query 查询条件
     * @return
     */
    public List<${field.fieldType}> query${field.fieldName?cap_first}CountByParam(${tableInfo.modelName}Query query);

</#list>

}
