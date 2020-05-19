package com.life.advice;

import cn.hutool.core.util.StrUtil;
import net.hasor.dataql.fx.web.WebUdfSource;
import net.hasor.dataway.spi.ApiInfo;
import net.hasor.dataway.spi.PreExecuteChainSpi;
import net.hasor.dataway.spi.StatusMessageException;
import net.hasor.utils.future.BasicFuture;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * hasor 配置的api权限拦截
 *
 * @author yanglin
 * @version 1.0
 * @date 2020/5/19 17:56
 * @since 1.0
 */
@Component
public class HasorApiPreAdvice implements PreExecuteChainSpi {
  @Override
  public void preExecute(ApiInfo apiInfo, BasicFuture<Object> basicFuture) {
    Map<String, Object> parameterMap = apiInfo.getParameterMap();
    String token = WebUdfSource.getHeader("token");
    if (StrUtil.isEmpty(token)){
      basicFuture.failed(new StatusMessageException(401,"not power"));
    }
  }
}

