package com.example.SpringBootJPA.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



/**
 * @author Kranti
 *
 *Goal of this annotation is validate given object by providing unique keys
 */
@Constraint(validatedBy=UniqueNameValidator.class)
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD, TYPE })
public @interface UniqueChild {
	String message() default "";
	String[] uniqueKeys() default {};
	String primaryKey() default "";
	
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	
}
