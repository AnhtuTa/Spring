package tutorialspoint;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

//ApplicationContext automatically detects any beans that are defined with the
//implementation of the BeanPostProcessor 
public class MyPostProcessor implements BeanPostProcessor {

	// khi nào 1 bean bất kỳ đc khởi tạo thì sẽ gọi hàm này TRƯỚC khi khởi tạo bean đó
	// nghĩa là có bao nhiêu bean trong Beans.xml thì có bấy nhiêu lần gọi hàm này
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		//System.out.println("This is before Initialization, beanName = " + beanName);
		return bean;	//or you can return anything you want
	}
	
	// khi nào 1 bean bất kỳ đc khởi tạo thì sẽ gọi hàm này SAU khi khởi tạo bean đó
	// nghĩa là có bao nhiêu bean trong Beans.xml thì có bấy nhiêu lần gọi hàm này
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		//System.out.println("This is after Initialization, beanName = " + beanName);
		return bean;	//or you can return anything you want
	}


}
