package kr.or.ddit.validate.constraints;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileNotEmptyValidator implements ConstraintValidator<FileNotEmpty, MultipartFile>{

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		if(value == null) return true;
		return !value.isEmpty();
		
	}

}
