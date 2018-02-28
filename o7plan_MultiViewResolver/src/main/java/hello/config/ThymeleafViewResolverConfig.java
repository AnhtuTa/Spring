package hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeleafViewResolverConfig {
	@Bean
	public ViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(thymeleafTemplateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		
		// Trong ứng dụng này chúng ta cấu hình Thymeleaf ViewResolver 
		// có độ ưu tiên cao nhất (order = 0).
		viewResolver.setOrder(0);

		/*
		 * Thymeleaf ViewResolver sẽ ném ra ngoại lệ khi không tìm 
		 * thấy "View Name" cần thiết (Một tập tin html cần thiết).
		 * Nó trái ngược với mong muốn của bạn là ViewResolver có 
		 * độ ưu tiên kế tiếp sẽ được sử dụng. Vì vậy bạn cần thiết
		 * lập quy tắc cho các "View Name" sẽ được phục vụ bởi 
		 * Thymeleaf ViewResolver.
		 */
		// Important!!
		// th_page1.html, th_page2.html, ...
		viewResolver.setViewNames(new String[] { "th_*" });

		return viewResolver;
	}

	// Thymeleaf template engine with Spring integration
	@Bean
	public SpringTemplateEngine thymeleafTemplateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(thymeleafTemplateResolver());
		templateEngine.setEnableSpringELCompiler(true);

		return templateEngine;
	}

	@Bean
	public SpringResourceTemplateResolver springResourceTemplateResolver() {
		return new SpringResourceTemplateResolver();
	}

	// Thymeleaf template resolver serving HTML 5
	@Bean
	public ITemplateResolver thymeleafTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

		templateResolver.setPrefix("templates/");
		templateResolver.setCacheable(false);
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding("UTF-8");

		return templateResolver;
	}

}
