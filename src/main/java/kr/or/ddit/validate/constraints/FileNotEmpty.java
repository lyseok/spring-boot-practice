package kr.or.ddit.validate.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = FileNotEmptyValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FileNotEmpty {
	String message() default "파일이 비어있음";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
