package bkhn.att.o7plan_HelloSpring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * @Component là một annotation, nó được chú thích trên một class
 * để nói với Spring rằng class này là một Spring BEAN. 
 * @Autowired được chú thích trên một trường (field) để nói với
 * Spring rằng hãy tiêm (inject) giá trị vào cho trường đó.
 * Chú ý: Từ tiêm ở đây có ý giống với gán giá trị cho trường đó.
 */
@Component
public class MyComponent {
	@Autowired
	private MyRepository repository;

	public void showAppInfo() {
		System.out.println("Now is: " + repository.getSystemDateTime());
		System.out.println("App Name: " + repository.getAppName());
	}
}
/*
Không có sự khác biệt về cách sử dụng của @Service, @Component 
và @Repository, bạn sử dụng để chú thích trên các class của bạn 
nên phù hợp với ý nghĩa và ngữ cảnh trong ứng dụng.
*/