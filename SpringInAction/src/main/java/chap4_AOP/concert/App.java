package chap4_AOP.concert;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * Nhiều người không thực sự hiểu AOP là gì và cho rằng AOP là sự 
 * thay thế cho ngôn ngữ lập trình hướng đối tượng (OOP), chính vì 
 * vậy mà chúng tôi cần giải thích sâu. Khái niệm này rõ ràng hoàn 
 * toàn sai: AOP dựa trên OOP. Nó tập trung vào các khái niệm cross-cutting 
 * hoặc các khía cạnh –phần mã chung cho các đối tượng khác nhau. 
 * Sử dụng một ngôn ngữ AOP (như AspectJ) hoặc các thư viện (như Spring), 
 * những người lập trình có thể viết mã cho chức năng này, sau đó 
 * định nghĩa vị trí đan kết nó vào trong các đối tượng đang tồn tại
 * 
 * Để thực hiện AOP trong Java ta có thể sử dụng các cài đặt cung cấp bởi
	AspectJ
	Spring AOP
	JBoss AOP
 * 	
 * the two most popular AOP frameworks for Java: Spring AOP and AspectJ
 * Loạt bài ví dụ này dùng AspectJ
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext context = 
			new AnnotationConfigApplicationContext(ConcertConfig.class);
		
		Performance p1 = (Performance) context.getBean("emCuaNgayHomQua");
		try {
			p1.perform();
		} catch (Exception e) {}
		
		Performance p2 = (Performance) context.getBean("emGaiMua");
		try {
			p2.perform();
		} catch (Exception e) {
			System.out.println("There's something wrong about Huong Tram...???");
			System.out.println(e.getMessage());
		}
		
		((ConfigurableApplicationContext) context).close();
	}
}
