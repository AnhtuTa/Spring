package demo.read_properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//Annotation @EnableAutoConfiguration sẽ tự động mapping thuộc tính của bean 
//với các giá trị trong file .properties
//Yêu cầu: Cần cỏ đủ các getter và setter cho các field
@Controller
@ConfigurationProperties
@PropertySource(value="classpath:database.properties")
public class ReadDatabaseProp2 {
	private String className;
	
	private String url;
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@ResponseBody
	@RequestMapping(value="/read-db-prop2")
	public String readDbProp() {
		String str = className + " - " + url;
		return str;
	}
}
