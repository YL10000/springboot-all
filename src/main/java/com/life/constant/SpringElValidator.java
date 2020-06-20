package com.life.constant;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * TODO
 *
 * @author liufangfang
 * @version 1.0
 * @date 2020/5/9 16:25
 * @since 1.0
 */
public class SpringElValidator implements ConstraintValidator<SpringEl, Object> {

  private String[] conditions = {};
  private String beanAlias = "";

  @Override
  public void initialize(SpringEl constraintAnnotation) {
    conditions = constraintAnnotation.conditions();
    beanAlias = constraintAnnotation.beanAlias();
  }

  @SneakyThrows
  @Override
  public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
    EvaluationContext context = new StandardEvaluationContext();
    ExpressionParser parser = new SpelExpressionParser();
    String beanName=StrUtil.isNotEmpty(beanAlias)?beanAlias:value.getClass().getSimpleName();
    context.setVariable(beanName,value);
    if (ObjectUtil.isNotEmpty(conditions)){
      for (int i=0,len=conditions.length;i<len;i++){
        String conditionStr=conditions[i];
        if (StrUtil.isNotEmpty(conditionStr)){
          String[] condition=conditions[i].split(",");
          String expression = condition[0];
          String message = condition[1];
          Boolean result = parser.parseExpression(expression).getValue(context,Boolean.class);
          if (!result){
            BindException bindException = new BindException(value,beanName);
            bindException.getBindingResult().addError(new ObjectError(beanName,message));
            throw bindException;
          }
        }
      }
    }
    return true;
  }

}
