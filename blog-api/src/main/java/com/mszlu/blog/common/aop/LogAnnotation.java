package com.mszlu.blog.common.aop;

import java.lang.annotation.*;

/**
 * @author sfChampion
 * @date 2023/2/9 17:17
 */
// TYPE 代表可以放在类上，METHOD 代表可以放在方法上
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String module() default "";
    String operator() default "";
}
