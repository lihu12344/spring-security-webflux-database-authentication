package com.example.demo.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenerator {
    public static void main(String[] args){

        AutoGenerator autoGenerator=new AutoGenerator();

        GlobalConfig globalConfig=new GlobalConfig();

        globalConfig
                .setIdType(IdType.AUTO)
                .setActiveRecord(true)
                .setEnableCache(false)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setOutputDir("src"+ File.separator +"main"+File.separator+"java")
                .setFileOverride(true)
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
                .setXmlName("%sMapper")
                .setMapperName("%sMapper")
                .setAuthor("lihu")
                .setOpen(false);
        autoGenerator.setGlobalConfig(globalConfig);

        DataSourceConfig dataSourceConfig=new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUsername("root")
                .setUrl("jdbc:mysql://localhost:3306/webflux?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8")
                .setPassword("123456")
                .setDriverName("com.mysql.cj.jdbc.Driver");
        autoGenerator.setDataSource(dataSourceConfig);

        StrategyConfig strategyConfig=new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setInclude("user")
                .setNaming(NamingStrategy.underline_to_camel);
        autoGenerator.setStrategy(strategyConfig);

        PackageConfig packageConfig=new PackageConfig();
        packageConfig
                .setParent("com.example.demo")
                .setEntity("pojo")
                .setMapper("dao")
                .setService("service")
                .setServiceImpl("serviceImpl")
                .setController("controller");
        autoGenerator.setPackageInfo(packageConfig);

        //下面一段为重新指定mapper.xml文件生成的位置到"src/main/resources/mapper"
        TemplateConfig templateConfig=new TemplateConfig();
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);

        InjectionConfig injectionConfig=new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-rb");
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        injectionConfig.setFileOutConfigList(focList);
        autoGenerator.setCfg(injectionConfig);

        autoGenerator.execute();
    }
}
