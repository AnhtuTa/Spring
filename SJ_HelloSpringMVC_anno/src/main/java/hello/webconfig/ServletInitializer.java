package hello.webconfig;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Tạo một class ServletInitializer bằng cách extending 
//AbstractAnnotationConfigDispatcherServletInitializer, Class này có 
//chức năng load các file cấu hình và chạy nó. 
//Nó tương tự với việc khai báo file xml config trong web.xml
public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
