package io.github.takuiash.template.core.utils.storage;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	String name() default "";
	ColumnType type();
	int size() default 32;
	int decimal() default 2;
	boolean unique() default false;
	boolean nullable() default true;
	boolean primary() default false;
}
