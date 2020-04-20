package com.life.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.life.exception.APIException;
import com.life.vo.ResultVO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一返回结果，在方法返回数据之前进行增强
 */
@RestControllerAdvice(basePackages = "com.life.controller")
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 哪些数据类型返回前需要增强
     * @param returnType
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        return !returnType.getGenericParameterType().equals(ResultVO.class);
    }

    /**
     * 具体怎么增强
     * @param data
     * @param returnType
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //String类不能直接包装，所以要进行特别的处理
        if (returnType.getGenericParameterType().equals(String.class)){
            ObjectMapper objectMapper=new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(new ResultVO<>(data));
            }catch (Exception e){
                throw  new APIException(e.getMessage());
            }
        }
        return new ResultVO<>(data);
    }
}
