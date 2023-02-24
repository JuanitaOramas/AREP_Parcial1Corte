package org.example;

import java.lang.annotation.Annotation;

public class RequestMapping implements Annotation {
    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    public String value() {
        return "get()";
    }
}
