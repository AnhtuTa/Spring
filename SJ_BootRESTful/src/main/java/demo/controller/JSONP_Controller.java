package demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JSONP_Controller {

	/*
	 truy cập vào địa chỉ http://localhost:8080/demo_jsonp thì nó trả về kq như sau:
	 clientFunction([{"id":1,"name":"anhtu","school":"HUST"},{"id":2,
	 "name":"Longporn","school":"HUST"},{"id":4,"name":"Nguyen Bka",
	 "school":"HUST"},{"id":5,"name":"Huy ga","school":"HUST"},{"id":6,
	 "name":"Toan","school":"HUST"},{"id":7,"name":"Trung Anh","school":
	 "Unknown"},{"id":8,"name":"long","school":"demo"},{"id":9,"name":"Tuan anh",
	 "school":"demo"},{"id":10,"name":"Phanh Lee","school":"demo"},{"id":11,
	 "name":"Huyen","school":"demo"}])
	 */
	@RequestMapping("/demo_jsonp")
	@ResponseBody
	public String demoJSONP() {
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
		
		return "clientFunction(" + studentJSONString + ")";
	}
	
	/*
	 * Đôi khi bên client sẽ cung cấp các tham số và cả tên hàm callback
	 * để client gọi sau khi server trả về kq.
	 */
	@RequestMapping("/demo_jsonp_dynamic")
	@ResponseBody
	public String demoJSONPDynamic(HttpServletRequest request) {

		String callbackName = request.getParameter("callback");
		int num_records = Integer.valueOf(request.getParameter("num_records"));
		
		return callbackName + "(" + getStudentJSONFromDb(num_records) + ")";
	}
	
	/**
	 * Lấy các student từ database
	 * @param numRecords số lượng student cần lấy
	 * @return
	 */
	private String getStudentJSONFromDb(int numRecords) {
		String[] arr = {"{\"id\":1,\"name\":\"anhtu\",\"school\":\"HUST\"}",
				"{\"id\":2,\"name\":\"Longporn\",\"school\":\"HUST\"}",
				"{\"id\":4,\"name\":\"Nguyen Bka\",\"school\":\"HUST\"}",
				"{\"id\":5,\"name\":\"Huy ga\",\"school\":\"HUST\"}",
				"{\"id\":6,\"name\":\"Toan\",\"school\":\"HUST\"}",
				"{\"id\":7,\"name\":\"Trung Anh\",\"school\":\"Unknown\"}",
				"{\"id\":8,\"name\":\"long\",\"school\":\"demo\"}",
				"{\"id\":9,\"name\":\"Tuan anh\",\"school\":\"demo\"}",
				"{\"id\":10,\"name\":\"Phanh Lee\",\"school\":\"demo\"}",
				"{\"id\":11,\"name\":\"Huyen\",\"school\":\"demo\"}"};
		String kq = "[";
		
		if(numRecords > arr.length) {
			numRecords = arr.length;
		}
		for(int i=0; i < numRecords; i++) {
			kq += arr[i] + ",";
		}
		kq = kq.substring(0, kq.length()-1);	//bỏ dấu , cuối cùng	
		kq += "]";
		
		System.out.println(kq);
		
		return kq;
	}
}
