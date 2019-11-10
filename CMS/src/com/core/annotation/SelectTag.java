package com.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SelectTag {//<select name=""><option value="" selected></option>11</select>
	public String label();
	public String name();
	public String keys();
	public String values(); 
	
}
