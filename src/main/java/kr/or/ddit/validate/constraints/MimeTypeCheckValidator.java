package kr.or.ddit.validate.constraints;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MimeTypeCheckValidator implements ConstraintValidator<MimeTypeCheck, MultipartFile>{
	private String mainType;
	
	@Override
	public void initialize(MimeTypeCheck constraintAnnotation) {
		this.mainType = constraintAnnotation.mainType();
	}
	
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		if(value == null || value.isEmpty()) return true;
		
		String mime = value.getContentType();
		return mime!=null && mime.startsWith(mainType);
	}
	
}
