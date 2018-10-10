package com.qpp.adminmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
//@MapperScan("com.qpp.adminmanage.dao")
@EnableScheduling
@EnableSwagger2
public class AdminManageApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    public static void main(String[] args) {
       SpringApplication.run(AdminManageApplication.class, args);
//        SpringApplication sa=new SpringApplication(AdminManageApplication.class);
//        // 禁用devTools热部署
//        //System.setProperty("spring.devtools.restart.enabled", "false");
//        // 禁用命令行更改application.properties属性
//        sa.setAddCommandLineProperties(false);
//        sa.run(args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(AdminManageApplication.class);
    }
}
