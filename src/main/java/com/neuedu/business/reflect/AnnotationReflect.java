package com.neuedu.business.reflect;

import com.neuedu.business.annotation.Description;
import com.neuedu.business.pojo.User;

public class AnnotationReflect {
    public static void main(String[] args) {
        Class<User> c=User.class;
        boolean result=c.isAnnotationPresent(Description.class);
        if(result){
            //user类上有description注解
            Description des=(Description) c.getAnnotation(Description.class);
            System.out.println(des.age());
            System.out.println(des.author());
            System.out.println(des.desc());
        }
    }
}
