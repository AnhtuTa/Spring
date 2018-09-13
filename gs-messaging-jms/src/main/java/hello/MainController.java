package hello;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	@Autowired
	JmsTemplate jmsTemplate;

	@RequestMapping(value = "/")
	public String home(HttpServletResponse response) {
		response.setContentType("text/html");
		return "<a href=\"/send-message?to=anhtu51195@gmail.com&body=what%20the%20fuck%20is%20JMS\">" + //
				"Demo</a>";
	}

	@RequestMapping(value = "/send-message")
	public String sendMessage(HttpServletRequest request) {
		String to = request.getParameter("to"); // receiver
		String body = request.getParameter("body"); // content

		System.out.println("Sending an email message.");
		jmsTemplate.convertAndSend("mailbox", new Email(to, body));

		return "send!";
	}
}
