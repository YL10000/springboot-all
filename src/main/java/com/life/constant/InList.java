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
@Constraint(validatedBy = {InListValidator.class})
public @interface InList {
  boolean required() default true;

  String message() default "不在指定的列表中";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String[] list() default {"0","1"};

  @Documented
  public @interface List {
    InList[] value();
  }
}
