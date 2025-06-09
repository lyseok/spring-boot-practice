package kr.or.ddit.validate.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MimeTypeCheckValidator.class)
public @interface MimeTypeCheck {
	String mainType();
	
	String message() default "파일 MIME 의 메인 타입 확인";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
