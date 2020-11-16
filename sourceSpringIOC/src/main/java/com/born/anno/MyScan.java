package com.born.anno;

import com.born.Import.MyImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 模拟@MapperScan
 */
@Import(MyImportBeanDefinitionRegistrar.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyScan {
}
