package com.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InputTag{//<input type="" name=""  value="">
	public String label();
	public String type();
	public String name();
	public String value();
	
}
