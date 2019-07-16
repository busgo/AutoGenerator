package	${servicePackage};

import	${modelPackage}.${tableInfo.modelName};
import	${queryPackage}.${tableInfo.modelName}Query;
import	${daoPackage}.${tableInfo.modelName}Dao;
import	org.springframework.stereotype.Service;


import	javax.annotation.Resource;
import	java.util.List;
/***
 *
 * @author Create By AutoGenerator
 */
@Service
public class ${tableInfo.modelName}Service implements BaseService<${tableInfo.pkFieldType},${tableInfo.modelName},${tableInfo.modelName}Query> {


	@Resource
	private	${tableInfo.modelName}Dao	${tableInfo.modelName?uncap_first}Dao;


	/**
     * 插入记录
     * @param ${tableInfo.modelName?uncap_first} 实体
     * @return
     */
	public int insert(${tableInfo.modelName} ${tableInfo.modelName?uncap_first}) {
		return this.${tableInfo.modelName?uncap_first}Dao.insert(${tableInfo.modelName?uncap_first});
	}


    /**
     * 插入记录
     * @param list 待插入集合
     * @return
     */
	public int batchInsert(List<${tableInfo.modelName}> list){
   		return this.${tableInfo.modelName?uncap_first}Dao.batchInsert(list);
    }


	/**
     * 根据主键更新记录
     * @param ${tableInfo.modelName?uncap_first} 实体
     * @return
     */
	public int updateById(${tableInfo.modelName} ${tableInfo.modelName?uncap_first}) {
		return this.${tableInfo.modelName?uncap_first}Dao.updateById(${tableInfo.modelName?uncap_first});
	}



    /**
     * 根据条件更新对象
     * @param ${tableInfo.modelName?uncap_first} 对象
     * @param query 条件
     * @return
     */
    public int updateByQuery(${tableInfo.modelName} ${tableInfo.modelName?uncap_first},${tableInfo.modelName}Query query){
        return this.${tableInfo.modelName?uncap_first}Dao.updateByQuery(${tableInfo.modelName?uncap_first},query);
    }

	/**
     * 根据主键查询记录详情
     * @param ${tableInfo.pkFieldName} 主键
     * @return
     */
	public ${tableInfo.modelName} selectById(${tableInfo.pkFieldType} ${tableInfo.pkFieldName}) {
		return this.${tableInfo.modelName?uncap_first}Dao.selectById(${tableInfo.pkFieldName});
	}


	/**
     * 带有行锁根据主键查询记录详情
     * @param ${tableInfo.pkFieldName} 主键
     * @return
     */
	public ${tableInfo.modelName} selectByIdForUpdate(${tableInfo.pkFieldType} ${tableInfo.pkFieldName}) {
		return this.${tableInfo.modelName?uncap_first}Dao.selectByIdForUpdate(${tableInfo.pkFieldName});
	}


	/**
     * 根据主键删除记录
     * @param ${tableInfo.pkFieldName} 主键
     * @return
     */
	public int deleteById(${tableInfo.pkFieldType} ${tableInfo.pkFieldName}) {
		return this.${tableInfo.modelName?uncap_first}Dao.deleteById(${tableInfo.pkFieldName});
	}


	/**
     * 根据参数分页查询记录列表
     * @param query 查询条件
     * @return
     */
	public List<${tableInfo.modelName}> queryListByParam(${tableInfo.modelName}Query query) {
        return this.${tableInfo.modelName?uncap_first}Dao.queryListByParam(query);
    }


	/**
     * 根据查询条件查询记录总是
     * @param query 查询条件
     * @return
     */
    public int queryCountByParam(${tableInfo.modelName}Query query) {
        return this.${tableInfo.modelName?uncap_first}Dao.queryCountByParam(query);
    }


    /***
     * 根据查询条件查询主键列表
     * @param query 查询条件
     * @return
     */
    public List<${tableInfo.pkFieldType}> queryPkListByParam(${tableInfo.modelName}Query query){
           return this.${tableInfo.modelName?uncap_first}Dao.queryPkListByParam(query);
    }

}
