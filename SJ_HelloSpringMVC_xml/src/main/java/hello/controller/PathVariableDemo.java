package hello.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@PathVariable: Annotation PathVariable được sử dụng để
//xử lý những URI động, có một hoặc nhiều paramter bên trong URI.

//Annotation @RequestParam: Khi submit method GET, trên URL 
//sẽ chứa các giá trị của các ô input được submit. Sử dụng 
//Annotation RequestParam giúp chúng ta lấy được giá trị đó.

@Controller
public class PathVariableDemo {
	@RequestMapping("/test21/{postId}")
	public String test21(@PathVariable("postId") int postid, Model model) {
		model.addAttribute("postId", postid);
		return "test21";
	}
	
	@RequestMapping("/test22/{groupName}/{postId}/{commentId}")
	public String test22(@PathVariable("groupName") String groupName,
			@PathVariable("postId") int postId,
			@PathVariable("commentId") int commentId, Model model) {
		model.addAttribute("groupName", groupName);
		model.addAttribute("postId", postId);
		model.addAttribute("commentId", commentId);
		
		return "test22";
	}
	
	@RequestMapping("/test23")
	public String test23(@RequestParam("name") String name,
			@RequestParam("age") int age, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "test23";
	}
	
	@RequestMapping("/test24")
	public String test24(
			@RequestParam("name") String name,
			@RequestParam("food") List<String> foodList,
			
			Model model) {
		model.addAttribute("name", name);
		model.addAttribute("foodList", foodList);
		return "test24";
	}
}
