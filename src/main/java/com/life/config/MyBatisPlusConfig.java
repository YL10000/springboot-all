package com.life.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * TODO
 *
 * @author liufangfang
 * @version 1.0
 * @date 2020/5/8 16:49
 * @since TODO
 */

@Configuration
@MapperScan("com.life.mapper")
@EnableTransactionManagement
@Slf4j
public class MyBatisPlusConfig {

  @Bean
  public PaginationInterceptor paginationInterceptor(){
    return new PaginationInterceptor();
  }
}

