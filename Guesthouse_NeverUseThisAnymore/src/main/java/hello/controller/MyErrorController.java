//package hello.controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.web.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class MyErrorController implements ErrorController {
//
//	@Autowired
//	HttpServletRequest request;
//	
//	@RequestMapping(value = {"/error", "/{locale:en|vi}/error"})
//	public String error(HttpServletRequest request) {
//		return "redirect:/page_not_found";
//	}
//
//	@Override
//	public String getErrorPath() {
//		return "/error";
//	}
//
//	@RequestMapping(value = {"/{locale:en|vi}/page_not_found", "/page_not_found"})
//	public String pageNotFound(HttpServletRequest request) {
//		return "404Page";
//	}
//}
