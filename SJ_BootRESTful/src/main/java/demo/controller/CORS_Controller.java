package demo.controller;

import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CORS_Controller {
	
	/*
	 * Enabling CORS: So that the RESTful web service will include CORS 
	 * access control headers in its response, you just have to add a 
	 * @CrossOrigin annotation to the handler method
	 * 
	 * it is also possible to add this annotation at controller class 
	 * level as well, in order to enable CORS on all handler methods 
	 * of this class
	 * 
	 * If we didn’t set any configuration for the @CrossOrigin annotation, 
	 * so the default configuration takes place:
		- All origins are allowed
		- The HTTP methods allowed are those specified in the @RequestMapping 
		  annotation (for this example is GET)
		- The time that the preflight response is cached (maxAge) is 30 minutes'
	 * 
	 * There are several ways to config CORS in Controller Method in Spring boot app
	 * See more: https://www.baeldung.com/spring-cors
	 */
	@CrossOrigin(origins = "http://localhost:8084", methods=RequestMethod.GET, maxAge=30*60)
	@RequestMapping(value="/cors/demo_cors", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String demoCORS() {
		// Giả sử lấy được từ database list Student, chuyển thành JSON thì
		// nó trông như sau:
		String studentJSONString = "[{\"id\":1,\"name\":\"anhtu\",\"school\":\"HUST\"},"
				+ "{\"id\":2,\"name\":\"Longporn\",\"school\":\"HUST\"},"
				+ "{\"id\":4,\"name\":\"Nguyen Bka\",\"school\":\"HUST\"},"
				+ "{\"id\":5,\"name\":\"Huy ga\",\"school\":\"HUST\"},"
				+ "{\"id\":6,\"name\":\"Toan\",\"school\":\"HUST\"},"
				+ "{\"id\":7,\"name\":\"Trung Anh\",\"school\":\"Unknown\"},"
				+ "{\"id\":8,\"name\":\"long\",\"school\":\"demo\"},"
				+ "{\"id\":9,\"name\":\"Tuan anh\",\"school\":\"demo\"},"
				+ "{\"id\":10,\"name\":\"Phanh Lee\",\"school\":\"demo\"},"
				+ "{\"id\":11,\"name\":\"Huyen\",\"school\":\"demo\"}]";
		
		return studentJSONString;
	}
	
	@CrossOrigin(origins = "http://localhost:8084", methods=RequestMethod.POST, maxAge=30*60)
	@RequestMapping(value="/cors/insert_student", method = RequestMethod.POST, //
		produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ReturnMessage demoCORS_CreateStudent(@RequestBody String payload) throws JSONException {
		System.out.println("[DemoController] payload = " + payload);
		JSONObject json = new JSONObject(payload);
		String name, school;
		
		if(json.has("name")) {
			name = json.getString("name");
		} else {
			return new ReturnMessage("failed", "'name' is required!");
		}
		
		if(json.has("school")) {
			school = json.getString("school");
		} else {
			return new ReturnMessage("failed", "'school' is required!");
		}
		
		int insertedId = insertStudentToDb(name, school);
		return new ReturnMessage("Success! New comment was created with id = " + insertedId, null);
	}
	
	private int insertStudentToDb(String name, String school) {
		// Giả sử hàm này thực hiện insert student, và return 
		// id của student vừa được insert
		return new Random().nextInt(1000);
	}
	
	@CrossOrigin(origins = "http://localhost:8084", methods=RequestMethod.DELETE, maxAge=30*60)
	@RequestMapping(value="/cors/delete_student", method = RequestMethod.DELETE, //
		produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ReturnMessage deleteStudent(@RequestBody String payload) {
		System.out.println("[DemoController] payload = " + payload);
		JSONObject json = new JSONObject(payload);
		int id;
		
		if(json.has("id")) {
			id = Integer.valueOf(json.getString("id"));
		} else {
			return new ReturnMessage("failed", "'id' is required!");
		}
		
		boolean kq = deleteStudentToDb(id);
		if(kq) {
			return new ReturnMessage("Success! Student with id = " + id + " has been deleted!", null);
		} else {
			return new ReturnMessage("failed", "Student not found!");
		}
	}
	
	private boolean deleteStudentToDb(int id) {
		// Giả sử hàm này thực hiện delete student, và return 
		// kq delete student
		return new Random().nextInt(100) > 40 ? true : false;
	}
}
