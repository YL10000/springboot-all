package com.life.constant;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.life.modal.UserEntity;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * TODO
 *
 * @author liufangfang
 * @version 1.0
 * @date 2020/5/9 16:25
 * @since TODO
 */
public class SpringElValidator implements ConstraintValidator<SpringEl, UserEntity> {

  public static ThreadLocal<String> messageThreadLocal=new ThreadLocal<>();
  private String[] conditions = {};
  private String beanAlias = "";
  public static  ThreadLocal<String> userReferLocal = new ThreadLocal<>();

  @Override
  public void initialize(SpringEl constraintAnnotation) {
    conditions = constraintAnnotation.conditions();
    beanAlias = constraintAnnotation.beanAlias();
  }

  @Override
  public boolean isValid(UserEntity value, ConstraintValidatorContext constraintValidatorContext) {
    EvaluationContext context = new StandardEvaluationContext();
    ExpressionParser parser = new SpelExpressionParser();
    context.setVariable(StrUtil.isNotEmpty(beanAlias)?beanAlias:value.getClass().getSimpleName(),value);

    if (ObjectUtil.isNotEmpty(conditions)){
      for (int i=0,len=conditions.length;i<len;i++){
        String conditionStr=conditions[i];
        if (StrUtil.isNotEmpty(conditionStr)){
          String[] condition=conditions[i].split(",");
          String expression = condition[0];
          String message = condition[1];
          Boolean result = parser.parseExpression(expression).getValue(context,Boolean.class);
          if (!result){
            messageThreadLocal.set(message);
            return true;
          }
        }
      }
    }
    return true;
  }

}
