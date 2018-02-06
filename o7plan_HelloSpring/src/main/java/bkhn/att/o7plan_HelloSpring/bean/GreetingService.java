package bkhn.att.o7plan_HelloSpring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bkhn.att.o7plan_HelloSpring.lang.Language;

/*
 * @Service là một annotation, nó được sử dụng để chú thích trên
 * một class để nói với Spring rằng class đó là một Spring BEAN. 
 * @Autowired được chú thích trên một trường (field) để nói với
 * Spring rằng hãy tiêm (inject) giá trị vào cho trường đó.
 * Chú ý: Từ tiêm ở đây có ý giống với gán giá trị cho trường đó. 
 */
@Service
public class GreetingService {
	@Autowired
	private Language language;	//tên này phải trùng với tên bean trong file config thì mới autowire đc!
	
	public GreetingService() {}
	
	public void sayGreeting() {
		System.out.println("Greeting from service: " + language.getGreeting());
	}
}
