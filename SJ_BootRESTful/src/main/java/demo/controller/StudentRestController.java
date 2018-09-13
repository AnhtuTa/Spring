package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.dao.StudentDAO;
import demo.model.Student;

@RestController
public class StudentRestController {
	@Autowired
	StudentDAO studentDAO;

	/* ---------------- GET Student ------------------------ */
	@RequestMapping(value = { "/students", "/stds" }, method = RequestMethod.GET)
	public ResponseEntity<List<Student>> getAllStudents() {
		return new ResponseEntity<List<Student>>(studentDAO.getAllStudents(), HttpStatus.OK);
	}
	
	/* ---------------- GET Student, hàm này sẽ coi như thực hiện 1 công việc
	 * tốn rất nhiều thời gian (giả sử 10 giây, sau đó mới return kq ------------------------ */
	@RequestMapping(value = { "/students-wait" }, method = RequestMethod.GET)
	public ResponseEntity<List<Student>> getAllStudentsAndWaiting() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<List<Student>>(studentDAO.getAllStudents(), HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/students-wait30" }, method = RequestMethod.GET)
	public ResponseEntity<List<Student>> getAllStudentsAndWaiting30() {
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<List<Student>>(studentDAO.getAllStudents(), HttpStatus.OK);
	}

	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	public ResponseEntity<Student> getStudent(@PathVariable int id) {
		return new ResponseEntity<Student>(studentDAO.getStudent(id), HttpStatus.OK);
	}

	/* ---------------- CREATE NEW Student ------------------------ */
	@RequestMapping(value = "/student", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> createStudent(@RequestBody Student student) {
		// if (studentDAO.containStudent(student.getId()) != 0) {
		// return new ResponseEntity<String>("User Already Exist!",
		// HttpStatus.CONFLICT);
		// }

		int insertedId = studentDAO.insertStudent(student);
		return new ResponseEntity<String>("New student was created with id = " + insertedId, HttpStatus.CREATED);
	}

	/* ---------------- UPDATE Student ------------------------ */
	@RequestMapping(value = "/student", method = RequestMethod.PUT, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> updateStudent(@RequestBody Student st) {
		int rows = studentDAO.updateStudent(st);
		if (rows > 0)
			return new ResponseEntity<String>("Update successful!", HttpStatus.CREATED);
		else
			return new ResponseEntity<String>("Update failed!", HttpStatus.CREATED);
	}

	/* ---------------- DELETE Student ------------------------ */
	@RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteStudent(@PathVariable int id) {
		int kq = studentDAO.containStudent(id);
		if (kq == 0) {
			return new ResponseEntity<String>("Not Found Student", HttpStatus.OK);
		}

		int rows = studentDAO.deleteStudent(id);
		if (rows > 0)
			return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Delete failed!", HttpStatus.CREATED);
	}
}
