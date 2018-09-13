package demo.read_properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Sử dụng annotation @Value để inject giá trị trong file application.properites vào thuộc tính của các Spring Bean.
@RestController
public class ReadPropertiesController {
	@Value("${website}")
	private String website;
	
	@RequestMapping(value="/read-prop")
	public String readProp() {
		return "website = " + website;
	}
}
