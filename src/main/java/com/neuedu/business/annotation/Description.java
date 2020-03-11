package com.neuedu.business.annotation;


import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Description {

    String desc() default "";
    String author() default "";
    int age() default 0;


}
