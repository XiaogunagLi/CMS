package com.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TextAreaTag {//<textArea name="", cols="", rows="">value</textArea>
	public String label();
	public String name();
	public String cols();
	public String rows();
	public String value();
	
}
