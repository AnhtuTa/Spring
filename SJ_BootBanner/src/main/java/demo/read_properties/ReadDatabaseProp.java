package demo.read_properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// Sử dụng annotation @PropertySource để đọc những file .properites tùy chọn.
@Controller
@PropertySource(value="classpath:database.properties")
public class ReadDatabaseProp {
	@Value("${className}")
	private String className;
	
	@Value("${url}")
	private String url;	
	
	@ResponseBody
	@RequestMapping(value="/read-db-prop")
	public String readDbProp() {
		String str = className + " - " + url;
		return str;
	}
}
