package demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RSSController {
	@RequestMapping("/")
	public ModelAndView getFeedInRss() {
		List<RSSMessage> items = new ArrayList<>();
		
		RSSMessage content = new RSSMessage();
		content.setTitle("Spring MVC");
		content.setUrl("https://stackjava.com/spring/spring-mvc-rss-feed.html");
		content.setSummary("RSS feed demo");
		content.setCreatedDate(new Date());
		
		RSSMessage content2 = new RSSMessage();
	    content2.setTitle("Hibernate");
	    content2.setUrl("https://stackjava.com/category/hibernate");
	    content2.setSummary("Hibernate tutorial");
	    content2.setCreatedDate(new Date());
	    
	    items.add(content);
	    items.add(content2);

	    ModelAndView mav = new ModelAndView();
	    //ta trả về view có name là “myRssViewer”, view đó sẽ được map 
	    //với bean “myRssViewer” (trong file config .xml) để trả về RSS Feed
	    mav.setViewName("myRssViewer");
	    mav.addObject(RSSFeedViewer.FEED_CONTENT, items);
	    
	    return mav;
	}
}
