package org.example;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ComandServer {



    //Class ci = Class.forName("");


    public ComandServer() throws ClassNotFoundException {
        ArrayList<String> Fields = null;

        for (String className : Fields) {
            Class<?> c = null;
            c.getDeclaredFields();
            try {c = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


            Method[] myMethods = c.getMethods();

            for (Method m : myMethods) {
                if (m.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping annotation = m.getAnnotation(RequestMapping.class);
                    String file = annotation.value();
                }
            }

       }
    }





}
