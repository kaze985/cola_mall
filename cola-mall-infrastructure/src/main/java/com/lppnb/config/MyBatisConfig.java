package com.lppnb.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * @author kaze
 */
@Configuration
@MapperScan({"com.lppnb.generator.mapper","com.lppnb.dao"})
public class MyBatisConfig {
}
