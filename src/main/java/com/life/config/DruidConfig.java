package com.life.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * druid数据监控配置类
 *
 * @author yanglin
 * @version 1.0
 * @date 2020/8/25 14:06
 * @since 1.0
 */
@Configuration
public class DruidConfig {

  @Bean
  public ServletRegistrationBean<StatViewServlet> StatViewServlet(){
    ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
    //控制台用户
    servletRegistrationBean.addInitParameter("loginUsername", "yanglin");
    servletRegistrationBean.addInitParameter("loginPassword", "life");
    //是否能够重置数据
    servletRegistrationBean.addInitParameter("resetEnable", "true");
    return servletRegistrationBean;
  }

  @Bean
  public FilterRegistrationBean<WebStatFilter> statFilter(){
    FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
    //添加过滤规则
    filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
    return filterRegistrationBean;
  }
}

