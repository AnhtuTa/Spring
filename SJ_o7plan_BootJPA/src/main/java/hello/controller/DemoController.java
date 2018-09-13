package hello.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hello.entity.Comment;
import hello.entity.Post;
import hello.entity.School;
import hello.entity.Student;
import hello.service.CommentService;
import hello.service.PostService;
import hello.service.SchoolService;
import hello.service.StudentService;

@RestController
public class DemoController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private SchoolService schoolService;

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/rest/students", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	// produces = "application/json;charset=UTF-8" cũng đc!
	public List<Student> getAllStudents() {
		List<Student> stList = studentService.getAllStudents();
		// for(Student st : stList) {
		// System.out.println(st.getId() + " - " + st.getName());
		// System.out.println("\t" + st.getSchool());
		// }

		return stList;
	}

	@RequestMapping(value = "/rest/student/by-name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Student> getStudentsByName(@PathVariable String name) {
		List<Student> stList = studentService.getStudentsByName(name);
		return stList;
	}

	@RequestMapping(value = "/rest/schools", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<School> getAllSchools() {
		List<School> scList = schoolService.getAllSchools();
		return scList;
	}

	@RequestMapping(value = "/rest/posts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Post> getAllPosts() {
		return postService.getAllPosts();
	}

	@RequestMapping(value = "/rest/post/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Post getPost(@PathVariable int id) {
		return postService.getPostById(id);
	}

	/* ---------------- CREATE NEW post ------------------------ */
	@RequestMapping(value = "/rest/post", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> createPost(@RequestBody Post post) {
		int insertedId = postService.savePost(post);
		return new ResponseEntity<String>("New post was created with id = " + insertedId, HttpStatus.CREATED);
	}

	/*
	 * Khi insert new comment thì field ko set đc data cho cột post_id của bảng
	 * comment Mặc dù đã ghi rõ data trong body = {"content":
	 * "This is another demo comment", "post_id":2} WHY?? CHƯA TÌM ĐC CÁCH KHẮC
	 * PHỤC!
	 */
	/* ---------------- CREATE NEW comment ------------------------ */
	@RequestMapping(value = "/rest/comment", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> createComment(@RequestBody Comment comment) {
		Post p = comment.getPost();
		if (p != null)
			System.out.println("p = ");
		else
			System.out.println("p == null");
		
		//Chắc chắn p sẽ = null, do đó không insert được comment!
		int insertedId = commentService.saveComment(comment);
		return new ResponseEntity<String>("New comment was created with id = " + insertedId, HttpStatus.CREATED);
	}
	
	/**
	 * Insert comment
	 * @param payload chuỗi JSON chứa data của comment cần insert, nó có dạng: 
	 * {"content":"content value...", "post_id":a_number_here}
	 * @return Kết quả insert (thành công hay thất bại)
	 * @throws JSONException Nếu payload không đúng cú pháp JSON
	 */
	@RequestMapping(value = "/rest/comment2", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> createComment2(@RequestBody String payload) throws JSONException {
		System.out.println("[DemoController] payload = " + payload);
		JSONObject json = new JSONObject(payload);
		
		Comment c = new Comment();
		
		if(json.has("content")) {
			c.setContent(json.getString("content"));
		} else {
			return new ResponseEntity<String>("content is required!", HttpStatus.NOT_ACCEPTABLE);
		}
		
		if(json.has("post_id")) {
			int post_id = json.getInt("post_id");
			Post p = postService.getPostById(post_id);
			c.setPost(p);
		} else {
			return new ResponseEntity<String>("post_id is required!", HttpStatus.NOT_ACCEPTABLE);
		}
		
		int insertedId = commentService.saveComment(c);
		return new ResponseEntity<String>("New comment was created with id = " + insertedId, HttpStatus.CREATED);
	}
	
//	public static void main(String[] args) throws JSONException {
//		String payload = "{\"content\": \"Trung Anh\", \"post_id\":\"2\"}";
//		JSONObject json = new JSONObject(payload);
//		String content = json.getString("content");
//		int post_id = json.getInt("post_id");
//		
//		System.out.println(json.has("content"));
//		System.out.println(json.has("demo"));
//		//String demo = json.getString("demo");
//		
//		System.out.println("content = " + content);
//		System.out.println("post_id = " + post_id);
//		//System.out.println("demo = " + demo);
//	}
}
