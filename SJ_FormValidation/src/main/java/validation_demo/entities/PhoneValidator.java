package validation_demo.entities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
	public void initialize(Phone paramA) {
	}

	public boolean isValid(String phoneNo, ConstraintValidatorContext ctx) {
		if (phoneNo == null) {
			return false;
		}
		return phoneNo.matches("(?:\\d{3}|\\(\\d{3}\\))([-\\/\\.]|)\\d{3}\\1\\d{4}");
		//regex = (?:\d{3}|\(\d{3}\))([-\/\.]|)\d{3}\1\d{4}
		//VD: các số phone sau có thể match regex trên:
//		0983-123-4563
//		(0983)-123-4563
//		098/894/9333
//		093.292.9929
	}
}
