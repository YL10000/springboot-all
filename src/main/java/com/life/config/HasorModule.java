package com.life.config;

import com.life.advice.HasorApiPreAdvice;
import net.hasor.core.ApiBinder;
import net.hasor.core.DimModule;
import net.hasor.dataway.spi.PreExecuteChainSpi;
import net.hasor.db.JdbcModule;
import net.hasor.db.Level;
import net.hasor.spring.SpringModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 引入hasor模块，将其交给spring容器管理
 *
 * @author yanglin
 * @version 1.0
 * @date 2020/5/19 15:13
 * @since 1.0
 */
@Component
@DimModule
public class HasorModule implements SpringModule {

  @Autowired
  private DataSource dataSource;

  @Autowired
  private HasorApiPreAdvice hasorApiPreAdvice;

  @Override
  public void loadModule(ApiBinder apiBinder) throws Throwable {
    apiBinder.installModule(new JdbcModule(Level.Full,this.dataSource));
    //加入api拦截
    apiBinder.bindSpiListener(PreExecuteChainSpi.class,hasorApiPreAdvice);
  }

}
