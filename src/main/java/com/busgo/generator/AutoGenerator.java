package com.busgo.generator;

import com.busgo.generator.meta.MetaDataHandler;
import com.busgo.generator.model.ConfigInfo;
import com.busgo.generator.model.TableInfo;
import com.busgo.generator.util.GeneratorUtils;
import com.busgo.generator.model.DataBaseInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 *
 * @author busgo
 *
 */
public class AutoGenerator {


    private final static String BASE_DAO_TEMPLATE = "BaseDao.ftl";
    private final static String DAO_TEMPLATE = "Dao.ftl";
    private final static String BASE_SERVICE_TEMPLATE = "BaseService.ftl";
    private final static String SERVICE_TEMPLATE = "Service.ftl";
    private final static String BASE_QUERY_TEMPLATE = "BaseQuery.ftl";
    private final static String QUERY_TEMPLATE = "Query.ftl";
    private final static String MODEL_TEMPLATE = "Model.ftl";
    private final static String SORT_MODE_TEMPLATE = "SortMode.ftl";
    private final static String SORT_MODE_ENUM_TEMPLATE = "SortModeEnum.ftl";
    private final static String XML_TEMPLATE = "Xml.ftl";


    private static Configuration configuration;

    private static List<TableInfo> tableInfoList;

    private static ConfigInfo configInfo;

    static {

        configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(AutoGenerator.class.getClass(), "/template");
    }

    public static void main(String[] args) throws Exception {

        // 数据库配置信息
        String url = "jdbc:mysql://127.0.0.1:3306/ball?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
        String userName = "root";
        String password = "123456";
        String driverClass = "com.mysql.jdbc.Driver";

        DataBaseInfo dataBaseInfo = new DataBaseInfo(url, userName, password, driverClass);


        List<String> tables = new ArrayList<String>();
        tables.add("user_entrust_order");
        // 代码生成配置
        configInfo = new ConfigInfo()
                .daoPackage("com.busgo.ball.inner.dao")
                .servicePackage("com.busgo.ball.inner.service")
                .modelPackage("com.busgo.ball.inner.model")
                .queryPackage("com.busgo.ball.inner.query").dataBaseInfo(dataBaseInfo).saveDir("ball").tables(tables);


        MetaDataHandler handler = new MetaDataHandler(dataBaseInfo.getConnection(), configInfo.getTables());
        // 加载元数据
        tableInfoList = handler.loadMetaData();


        String daoPackage = configInfo.getDaoPackage();
        String modelPackage = configInfo.getModelPackage();
        String queryPackage = configInfo.getQueryPackage();
        String servicePackage = configInfo.getServicePackage();

        Map<String, Object> root = new HashMap<String, Object>();

        root.put("daoPackage", daoPackage);
        root.put("modelPackage", modelPackage);
        root.put("queryPackage", queryPackage);
        root.put("servicePackage", servicePackage);


        String basePath = configInfo.getSaveDir();

        String daoPath = basePath.concat("/").concat(daoPackage.replaceAll("\\.", "/"));
        String modelPath = basePath.concat("/").concat(modelPackage.replaceAll("\\.", "/"));
        String queryPath = basePath.concat("/").concat(queryPackage.replaceAll("\\.", "/"));
        String servicePath = basePath.concat("/").concat(servicePackage.replaceAll("\\.", "/"));
        System.err.println("daoPath=" + daoPath);
        System.err.println("modelPath=" + modelPath);
        System.err.println("queryPath=" + queryPath);
        System.err.println("servicePath=" + servicePath);

        String xmlPath = modelPath.concat("/xml");
        GeneratorUtils.createDirNotExist(modelPath);
        GeneratorUtils.createDirNotExist(queryPath);
        GeneratorUtils.createDirNotExist(daoPath);
        GeneratorUtils.createDirNotExist(servicePath);
        GeneratorUtils.createDirNotExist(xmlPath);

        // 生成基本代码
        generateCode(root, BASE_DAO_TEMPLATE, daoPath, "BaseDao.java");
        generateCode(root, BASE_SERVICE_TEMPLATE, servicePath, "BaseService.java");
        generateCode(root, SORT_MODE_ENUM_TEMPLATE, queryPath, "SortModeEnum.java");
        generateCode(root, SORT_MODE_TEMPLATE, queryPath, "SortMode.java");
        generateCode(root, BASE_QUERY_TEMPLATE, queryPath, "BaseQuery.java");


        for (TableInfo table : tableInfoList) {

            // 生成 dao service  xml 文件
            root.put("tableInfo", table);
            generateCode(root, MODEL_TEMPLATE, modelPath, String.format("%s.java", table.getModelName()));
            generateCode(root, QUERY_TEMPLATE, queryPath, String.format("%sQuery.java", table.getModelName()));

            generateCode(root, DAO_TEMPLATE, daoPath, String.format("%sDao.java", table.getModelName()));
            generateCode(root, SERVICE_TEMPLATE, servicePath, String.format("%sService.java", table.getModelName()));

            generateCode(root, XML_TEMPLATE, xmlPath, String.format("%s-sqlmap.xml", table.getTableName()));
        }


    }

    private static void generateCode(Map<String, Object> root, String templateName, String path, String fileName) throws IOException, TemplateException {


        Template template = configuration.getTemplate(templateName);
        fileName = path.concat("/").concat(fileName);
        System.err.println(fileName);
        template.process(root, new FileWriter(fileName));
    }


}
