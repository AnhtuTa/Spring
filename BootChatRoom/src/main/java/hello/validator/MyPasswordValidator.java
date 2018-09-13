package hello.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
 * mật khẩu phải có độ dài lớn hơn 4 ký tự, và phải chứa ít nhất 1 tự đặc biệt và số
 * Nếu thêm điều kiện: password length < 20 thì không save được vào
 * database, vì, password sau khi mã hóa có length > 20
 */
public class MyPasswordValidator implements ConstraintValidator<MyPassword, String> {

	@Override
	public void initialize(MyPassword arg0) {
		//do nothing
	}

	// Trong ví dụ này chỉ check length thôi!
	@Override
	public boolean isValid(String pass, ConstraintValidatorContext arg1) {
		// check length
		if(pass.length() < 4) return false;
		
//		// check contains special character
//		String specialChar [] = {"@", "!", "$", "%", "^", "&", "*", "<", ">", "?", "/", "\\", "+", "="};
//		int numOfSpecialChar = 0;
//		for(String c : specialChar) {
//			if(pass.contains(c)) {
//				numOfSpecialChar++;
//				break;
//			}
//		}
//		if(numOfSpecialChar == 0) return false;
//		
//		// check contains digit
//		int numOfDigit = 0;
//		for (int i = 0; i < 10; i++) {
//			if(pass.contains("" + i)) {
//				numOfDigit++;
//				break;
//			}
//		}
//		if(numOfDigit == 0) return false;
		
		return true;
	}

}
