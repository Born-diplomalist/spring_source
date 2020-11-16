package com.born.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)//默认是编译期间生效，编译完成后在class中被丢弃
public @interface Luban {
    String value();
}
