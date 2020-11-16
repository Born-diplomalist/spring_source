package com.born.anno;

import com.born.Import.MyImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Import(value = MyImportSelector.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableEureka {
}
