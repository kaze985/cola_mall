package com.lppnb.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan({"com.lppnb.mbg.mapper","com.lppnb.dao"})
public class MyBatisConfig {
}
