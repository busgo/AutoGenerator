package com.busgo.generator.meta;

import com.busgo.generator.model.FieldInfo;
import com.busgo.generator.model.TableInfo;
import com.busgo.generator.util.GeneratorUtils;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/***
 *  元数据信息处理
 * @author busgo
 *
 */
public class MetaDataHandler {

    private Connection connection;

    private List<String> tableNames;

    private List<TableInfo> tableInfoList;

    public MetaDataHandler(Connection connection, List<String> tableNames) {
        this.connection = connection;
        this.tableNames = tableNames;
    }


    /**
     * 加载元数据
     *
     * @return
     * @throws Exception
     */
    public List<TableInfo> loadMetaData() throws Exception {

        if (this.tableInfoList != null && !this.tableInfoList.isEmpty()) {
            return this.tableInfoList;
        }
        //
        if (this.tableNames == null || this.tableNames.isEmpty()) {

            this.tableNames = this.loadAllTableNameList();
        }

        if (this.tableNames == null || this.tableNames.isEmpty()) {

            throw new IllegalArgumentException("has no table for load error");
        }
        //   加载表元数据列表
        this.loadAllTableInfo();

        return this.tableInfoList;
    }


    /**
     * 加载当前数据库中所有表名列表
     *
     * @return
     * @throws SQLException
     */
    private List<String> loadAllTableNameList() throws SQLException {


        ResultSet tableResult = this.connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
        List<String> tableNameList = new ArrayList<String>();
        while (tableResult.next()) {

            String tableName = tableResult.getString("TABLE_NAME");
            System.out.println(String.format("load table name 【%s】 success!", tableName));
            System.out.println("--------------------------------------------------------");
            tableNameList.add(tableName);
        }

        return tableNameList;
    }


    /***
     * 加载所有指定的表数据信息
     * @throws Exception
     */
    private void loadAllTableInfo() throws Exception {

        this.tableInfoList = new ArrayList<TableInfo>();

        System.out.println("---------------Start load all table info-------------------------");
        for (String tableName : tableNames) {

            // 根据表名加载表数据信息
            TableInfo tableInfo = this.loadTableInfoByTableName(tableName);
            System.out.println("load table info is :" + tableInfo);
            this.tableInfoList.add(tableInfo);

        }
        System.out.println("---------------End load all table info success-------------------------");


    }


    /***
     * 根据表名获取表信息
     * @param tableName 表名
     * @return
     */
    private TableInfo loadTableInfoByTableName(String tableName) throws Exception {

        ResultSet columnsResult = this.connection.getMetaData().getColumns(null, null, tableName, null);


        boolean includeDateType = false;

        boolean includeDecimalType = false;
        List<FieldInfo> fieldInfoList = new ArrayList<FieldInfo>();
        //  循环迭代获取所有的表中的列明和类型集合
        while (columnsResult.next()) {

            String column = columnsResult.getString("COLUMN_NAME");
            String typeName = columnsResult.getString("TYPE_NAME");
            String remark = columnsResult.getString("REMARKS");
            System.out.println("load column name >>>>>>>>>" + column);
            System.out.println("load column type name >>>>>>>>" + typeName);
            System.out.println("load column remark >>>>>>>>" + remark);
            String fieldType = GeneratorUtils.toJavaType(typeName);

            if (!includeDateType){
                includeDateType = GeneratorUtils.isDateType(fieldType);
            }
            if (!includeDecimalType){
                includeDecimalType = GeneratorUtils.isBigDecimalType(fieldType);
            }

            String filedName = GeneratorUtils.toFirstLower(GeneratorUtils.toHump(GeneratorUtils.toLower(column)));
            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.setColumnName(GeneratorUtils.toLower(column));
            fieldInfo.setFieldName(filedName);
            fieldInfo.setRemark(remark);


            fieldInfo.setFieldType(fieldType);
            fieldInfo.setColumnType(typeName);
            fieldInfoList.add(fieldInfo);

        }

        // 关闭
        columnsResult.close();

        //  加载主键信息
        Pair<String, Integer> pkInfoResult = this.loadPkInfoByTableName(tableName);

        if (pkInfoResult == null)
            throw new IllegalArgumentException(String.format("table %s is no primary key ", tableName));


        String pkFieldName = GeneratorUtils.toFirstLower(GeneratorUtils.toHump(GeneratorUtils.toLower(pkInfoResult.getKey())));
        pkInfoResult.getValue();
        //  构造表实体
        String modelName = GeneratorUtils.toFirstUpper(GeneratorUtils.toHump(GeneratorUtils.toLower(tableName)));
        TableInfo tableInfo = new TableInfo();
        tableInfo.setTableName(tableName);
        tableInfo.setModelName(modelName);
        tableInfo.setFieldInfoList(fieldInfoList);
        tableInfo.setPkFieldName(pkFieldName);
        tableInfo.setPkColumnName(pkInfoResult.getKey());
        tableInfo.setPkSeq(pkInfoResult.getValue());
        tableInfo.setIncludeDateType(includeDateType);
        tableInfo.setIncludeDecimal(includeDecimalType);
        tableInfo.setPkColumnType(tableInfo.getFieldInfoList().get(tableInfo.getPkSeq() - 1).getColumnType());
        tableInfo.setPkFieldType(tableInfo.getFieldInfoList().get(tableInfo.getPkSeq() - 1).getFieldType());
        return tableInfo;
    }

    /***
     *  根据表名加载主键元数据信息
     * @param tableName 表名
     * @return
     */
    private Pair<String, Integer> loadPkInfoByTableName(String tableName) throws Exception {

        ResultSet primaryKeysResult = this.connection.getMetaData().getPrimaryKeys(null, null, tableName);

        if (primaryKeysResult.next()) {

            String pkName = primaryKeysResult.getString("COLUMN_NAME");
            int pkSeq = primaryKeysResult.getInt("KEY_SEQ");
            primaryKeysResult.close();
            return new Pair<String, Integer>(pkName, pkSeq);
        }

        return null;
    }


}
