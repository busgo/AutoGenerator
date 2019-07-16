package com.busgo.generator.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/***
 *  通用工具类
 * @author busgo
 *
 */
public class GeneratorUtils {

    /***
     * 下划线转驼峰
     * @param str 内容
     * @return
     */
    public static String toHump(String str) {


        StringBuilder sb = new StringBuilder();

        //  内容不为空
        if (StringUtils.isNoneBlank(str)) {

            boolean hit = false;
            for (int i = 0; i < str.length(); i++) {

                //  设置
                char c = str.charAt(i);
                if ("_".charAt(0) == c) {
                    hit = true;
                } else {
                    //  命中
                    if (hit) {
                        sb.append(Character.toUpperCase(c));
                        hit = false;

                    } else {
                        sb.append(c);
                    }

                }
            }
        }
        return sb.toString();
    }


    /***
     * 将jdbcType转成javaType
     * @param jdbcType jdbc 类型
     * @return
     */
    public static String toJavaType(String jdbcType) {


        if (jdbcType.equals("CHAR") || jdbcType.equals("VARCHAR") || jdbcType.equals("LONGVARCHAR") || jdbcType.equals("TEXT")||jdbcType.equals("LONGTEXT")|| jdbcType.equals("VARBINARY")) {
            return "String";
        } else if (jdbcType.equals("NUMERIC") || jdbcType.equals("DECIMAL")) {
            return "BigDecimal";
        } else if (jdbcType.equals("BIT") || jdbcType.equals("BOOLEAN")) {
            return "Boolean";
        } else if (jdbcType.equals("TINYINT") || jdbcType.equals("SMALLINT") || jdbcType.equals("INTEGER") || jdbcType.equals("INT") || jdbcType.equals("INT UNSIGNED") || jdbcType.equals("SMALLINT UNSIGNED") ||jdbcType.equals("TINYINT UNSIGNED")) {
            return "Integer";
        } else if (jdbcType.equals("BIGINT") || jdbcType.equals("BIGINT UNSIGNED")) {
            return "Long";
        } else if (jdbcType.equals("DATE") || jdbcType.equals("DATETIME")) {
            return "Date";
        } else if (jdbcType.equals("TIME")) {
            return "Time";
        } else if (jdbcType.equals("TIMESTAMP")) {
            return "Timestamp";
        } else if (jdbcType.equals("CLOB")|| jdbcType.equalsIgnoreCase("LONGCLOB")) {
            return "byte[]";
        } else if (jdbcType.equals("BLOB")|| jdbcType.equalsIgnoreCase("LONGBLOB")) {
            return "byte[]";
        } else if (jdbcType.equals("FLOAT")) {
            return "Float";
        }else if (jdbcType.equalsIgnoreCase("DOUBLE")){
            return "Double";
        }


        return jdbcType;

    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String toFirstUpper(String str) {

        if (StringUtils.isBlank(str)) return "";

        if (Character.isUpperCase(str.charAt(0))) return str;

        return new StringBuilder().append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String toFirstLower(String str) {

        if (StringUtils.isBlank(str)) return "";

        if (Character.isLowerCase(str.charAt(0))) return str;

        return new StringBuilder().append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
    }


    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String toLower(String str) {

        if (StringUtils.isBlank(str)) return "";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {

            if (Character.isUpperCase(str.charAt(i))) {

                sb.append(Character.toLowerCase(str.charAt(i)));
            } else {

                sb.append(str.charAt(i));
            }
        }


        return sb.toString();
    }


    /**
     * 是否为Date 类型
     * @param javaType java 类型
     * @return
     */
    public static boolean isDateType(String javaType){

        return  "Date".equalsIgnoreCase(javaType);
    }


    /**
     * 是否为 BigDecimal 类型
     * @param javaType java 类型
     * @return
     */
    public static boolean isBigDecimalType(String javaType){

        return "BigDecimal".equalsIgnoreCase(javaType);
    }

    /**
     * 递归创建目录
     * @param path 路径
     */
    public static void createDirNotExist(String path) {

        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
