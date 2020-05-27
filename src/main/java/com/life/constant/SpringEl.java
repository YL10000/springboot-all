package com.life.constant;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * TODO
 *
 * @author liufangfang
 * @version 1.0
 * @date 2020/5/9 16:27
 * @since TODO
 */

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {SpringElValidator.class})
public @interface SpringEl {
  boolean required() default true;

  String message() default "spring el 表达式验证错误";

  Class<?>[] groups() default {};

  String[] conditions() default {};

  Class<? extends Payload>[] payload() default {};

  String beanAlias() default "";

  @Documented
  public @interface List {
    SpringEl[] value();
  }
}
