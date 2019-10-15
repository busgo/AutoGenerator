<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${daoPackage}.${tableInfo.modelName}Dao">
    <!--Create by Auto Generator -->
    <resultMap type="${modelPackage}.${tableInfo.modelName}" id="${tableInfo.modelName}Map">
    <#list  tableInfo.fieldInfoList as field >
        <result column="${field.columnName}" property="${field.fieldName}"/>
    </#list>
    </resultMap>


    <sql id="_field_list">
 <#list  tableInfo.fieldInfoList as field >
    <#if (tableInfo.fieldInfoList?size -1) ==  field_index>
         `${field.columnName}`
    <#else >
         `${field.columnName}`,
    </#if>
 </#list>
    </sql>

    <sql id="_value_list">
 <#list  tableInfo.fieldInfoList as field >
 <#if (tableInfo.fieldInfoList?size -1) ==  field_index>
     <#noparse>#</#noparse>{${field.fieldName}}
 <#else >
     <#noparse>#</#noparse>{${field.fieldName}},
 </#if>
 </#list>
    </sql>

    <sql id="_batch_value_list">
 <#list  tableInfo.fieldInfoList as field >
 <#if (tableInfo.fieldInfoList?size -1) ==  field_index>
     <#noparse>#</#noparse>{item.${field.fieldName}}
 <#else >
     <#noparse>#</#noparse>{item.${field.fieldName}},
 </#if>
 </#list>
    </sql>

    <sql id="_common_where">
 <#list  tableInfo.fieldInfoList as field >
     <if test="${field.fieldName} !=null">AND `${field.columnName}`=<#noparse>#</#noparse>{${field.fieldName}}</if>
 </#list>
     <if test="include${tableInfo.pkFieldName?cap_first}List !=null">
         AND `${tableInfo.pkColumnName}` IN
         <foreach collection="include${tableInfo.pkFieldName?cap_first}List" item="item" open="(" close=")" separator=",">
            <#noparse>#</#noparse>{item}
         </foreach>
     </if>
        <if test="exclude${tableInfo.pkFieldName?cap_first}List !=null">
            AND `${tableInfo.pkColumnName}` NOT IN
            <foreach collection="exclude${tableInfo.pkFieldName?cap_first}List" item="item" open="(" close=")" separator=",">
                <#noparse>#</#noparse>{item}
            </foreach>
        </if>
    </sql>

    <sql id="_common_sort">
        <if test="sorts !=null">
            ORDER BY
            <foreach collection="sorts" item="sort" separator=",">
            <#noparse>${sort.columnName} ${sort.sortMode.mode}</#noparse>
            </foreach>
        </if>
    </sql>

    <sql id="_page_field">
        <if test="offset != null and rows !=null">
           <#noparse>LIMIT #{offset},#{rows}</#noparse>
        </if>
    </sql>

    <insert id="insert" useGeneratedKeys="true" keyProperty="${tableInfo.pkFieldName}">
        INSERT INTO `${tableInfo.tableName}`
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <include refid="_field_list"/>
        </trim>
        VALUES
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <include refid="_value_list"/>
        </trim>
    </insert>

    <insert id="batchInsert" useGeneratedKeys="true" keyProperty="${tableInfo.pkFieldName}">
        INSERT INTO `${tableInfo.tableName}`
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <include refid="_field_list"/>
        </trim>
        VALUES
        <foreach collection="list" item="item" separator=",">
            (<include refid="_batch_value_list"/>)
        </foreach>
    </insert>

    <select id="selectById" parameterType="java.lang.${tableInfo.pkFieldType}" resultMap="${tableInfo.modelName}Map">
        SELECT
        <include refid="_field_list"/>
        FROM `${tableInfo.tableName}`
        WHERE `${tableInfo.pkColumnName}` = <#noparse>#</#noparse>{${tableInfo.pkFieldName}} limit 1
    </select>

    <select id="selectByIdForUpdate" parameterType="java.lang.${tableInfo.pkFieldType}" resultMap="${tableInfo.modelName}Map">
        SELECT
        <include refid="_field_list"/>
        FROM `${tableInfo.tableName}`
        WHERE `${tableInfo.pkColumnName}` = <#noparse>#</#noparse>{${tableInfo.pkFieldName}} limit 1 FOR UPDATE
    </select>

    <delete id="deleteById" parameterType="java.lang.${tableInfo.pkFieldType}">
        DELETE FROM `${tableInfo.tableName}` WHERE `${tableInfo.pkColumnName}` = <#noparse>#</#noparse>{${tableInfo.pkFieldName}} limit 1
    </delete>

    <select id="deleteByParam" parameterType="map">
        DELETE
        FROM `${tableInfo.tableName}`
        <where>
            <include refid="_common_where"/>
        </where>
        <include refid="_page_field"/>
    </select>

    <update id="updateById">
        UPDATE `${tableInfo.tableName}`
        <trim prefix="SET" suffixOverrides="," prefixOverrides=",">
<#list  tableInfo.fieldInfoList as field >
        <#if field.columnName !=tableInfo.pkColumnName >
            <if test="${field.fieldName} !=null">`${field.columnName}`=<#noparse>#</#noparse>{${field.fieldName}},</if>
        </#if>
    </#list>
        </trim>
        WHERE `${tableInfo.pkColumnName}`=<#noparse>#</#noparse>{${tableInfo.pkFieldName}} LIMIT 1
    </update>

    <update id="updateByQuery">
        UPDATE `${tableInfo.tableName}`
        <trim prefix="SET" suffixOverrides="," prefixOverrides=",">
<#list  tableInfo.fieldInfoList as field >
        <#if field.columnName !=tableInfo.pkColumnName >
            <if test="po.${field.fieldName} !=null">`${field.columnName}`=<#noparse>#</#noparse>{po.${field.fieldName}},</if>
        </#if>
</#list>
        </trim>
        <where>
        <#list  tableInfo.fieldInfoList as field >
            <if test="query.${field.fieldName} !=null">AND `${field.columnName}`=<#noparse>#</#noparse>{query.${field.fieldName}}</if>
        </#list>
        </where>
    </update>

    <select id="queryListByParam" parameterType="map" resultMap="${tableInfo.modelName}Map">
        SELECT
        <include refid="_field_list"/>
        FROM `${tableInfo.tableName}`
        <where>
            <include refid="_common_where"/>
        </where>
        <include refid="_common_sort"/>
        <include refid="_page_field"/>
    </select>

    <select id="queryCountByParam" parameterType="map" resultType="java.lang.Integer">
        SELECT
        COUNT(*)
        FROM `${tableInfo.tableName}`
        <where>
            <include refid="_common_where"/>
        </where>
    </select>

    <select id="queryPkListByParam" parameterType="map" resultType="java.lang.${tableInfo.pkFieldType}">
        SELECT
        `${tableInfo.pkColumnName}`
        FROM `${tableInfo.tableName}`
        <where>
            <include refid="_common_where"/>
        </where>
        <include refid="_common_sort"/>
        <include refid="_page_field"/>
    </select>

</mapper>