package auto_scan_components;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
Thông thường, chúng ta khai báo tất cả các bean hoặc component trong 
file XML để Spring container có thể tìm và quản lý các bean.
Thực tế, Spring có khả năng tự động tìm, dò và tạo thể hiện của bean từ 
các định nghĩa ban đầu ở package, class mà không cần phải khai báo chúng trong file XML.
(Cách này thường dùng với các class không lưu trạng thái – không có 
thuộc tính, mà chỉ chứa các method)
 */

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("auto_scan.xml");
		UserService userService = (UserService) context.getBean("service_what_the_hell");
		userService.findUser(123);
		((ConfigurableApplicationContext)context).close();
	}
}
