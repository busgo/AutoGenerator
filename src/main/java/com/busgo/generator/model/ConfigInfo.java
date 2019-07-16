package com.busgo.generator.model;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/***
 *
 * @author  busgo
 *
 */
@Getter
public class ConfigInfo implements Serializable {


    // as: dao
    private String daoPackage;

    // as: service
    private String servicePackage;

    // model
    private String modelPackage;

    //  query model package
    private String queryPackage;


    // spec table name
    private List<String> tables;

    // database info
    private DataBaseInfo dataBaseInfo;

    // 保存目录
    private String saveDir;




    public ConfigInfo daoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
        return this;
    }

    public ConfigInfo servicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
        return this;
    }

    public ConfigInfo modelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
        return this;
    }

    public ConfigInfo queryPackage(String queryPackage) {
        this.queryPackage = queryPackage;
        return this;
    }

    public ConfigInfo tables(List<String> tables) {
        this.tables = tables;
        return this;
    }

    public ConfigInfo dataBaseInfo(DataBaseInfo dataBaseInfo) {
        this.dataBaseInfo = dataBaseInfo;
        return this;
    }


    public ConfigInfo saveDir(String saveDir){
        this.saveDir = saveDir;
        return this;
    }

}
