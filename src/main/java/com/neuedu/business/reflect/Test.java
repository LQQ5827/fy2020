package com.neuedu.business.reflect;

import com.neuedu.business.pojo.Product;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) {

      Class<Product> c=   Product.class;
      Product p=new Product();
     Class c2= p.getClass();
        Class c3=null;
        try {
             c3=Class.forName("com.neuedu.pojo.Product");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

       // System.out.println(c);
       // System.out.println(c2);
       // System.out.println(c3);

        //构造类的示例
        Product product=null;
        try {
             product=c.newInstance();
            System.out.println(product);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //访问product对象的方法

       Method[] methods=c.getDeclaredMethods();
        for(Method m:methods){
           // System.out.println(m.getName());
        }

       //访问product对象的属性
        Field[] fileds=c.getDeclaredFields();
        for(Field f:fileds){
            //System.out.println(f.getName());
            try {
                f.setAccessible(true);
                System.out.println(f.get(product));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        //访问一个方法
        try {
            Method method=c.getMethod("setName",String.class);
            method.invoke(product,"neuedu") ;

            Object o= c.getMethod("getName",null).invoke(product,null);
           // System.out.println(o);

            //调用一个私有方法
           Method m2= c.getDeclaredMethod("testProduct",null);
           m2.setAccessible(true);//绕开java语言对private的访问限制
           m2.invoke(product,null);



        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }






}
