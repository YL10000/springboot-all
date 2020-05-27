package com.life.constant;

import cn.hutool.core.util.ObjectUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * TODO
 *
 * @author liufangfang
 * @version 1.0
 * @date 2020/5/9 16:25
 * @since TODO
 */

public class InListValidator implements ConstraintValidator<InList,Object> {

  String[] list={"0","1"};

  @Override
  public void initialize(InList constraintAnnotation) {
    list=constraintAnnotation.list();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
    if (ObjectUtil.isNotEmpty(value)){
      return Arrays.asList(list).contains(value.toString());
    }
    return true;
  }
}

