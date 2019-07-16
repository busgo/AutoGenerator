package com.busgo.generator.model;

import lombok.Getter;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/***
 *
 * @author busgo
 *
 */
@Getter
public class DataBaseInfo implements Serializable {


    //  jdbc url
    private String url;

    // user name
    private String userName;

    // password
    private String password;

    // 驱动类
    private String driverClass;

    // 数据库连接
    private Connection connection;

    public DataBaseInfo(String url, String userName, String password, String driverClass) throws Exception {
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.driverClass = driverClass;

        this.initConnection();

    }

    /***
     * 初始化连接
     */
    private void initConnection() throws Exception {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        this.connection = DriverManager.getConnection(this.url, userName, password);

    }


}
