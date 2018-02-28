package hello.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hello.model.Song;
import hello.model.Student;

@Controller
public class StudentController {
	private static List<Student> students = new ArrayList<>();
	
	@Value("${error.message}")
	private String errorMessage;

	static {
		List<Song> songs1 = new ArrayList<>();
		songs1.add(new Song(1, "See you again", "Charlie Puth", 2013));
		songs1.add(new Song(2, "One call away", "Charlie Puth", 2014));
		songs1.add(new Song(3, "Noi nay co anh", "Son Tung MTP", 2016));
		songs1.add(new Song(4, "Only love", "Trademark", 2002));
		
		List<Song> songs2 = new ArrayList<>();
		songs2.add(new Song(1, "See you again", "Charlie Puth", 2013));
		songs2.add(new Song(5, "Chung ta ko thuoc ve nhau", "Son Tung MTP", 2017));
		songs2.add(new Song(3, "Noi nay co anh", "Son Tung MTP", 2016));
		
		List<Song> songs3 = new ArrayList<>();
		songs3.add(new Song(6, "Bad day", "Unknown", 2012));
		songs3.add(new Song(7, "Lac Troi", "Son Tung MTP", 2016));
		
		students.add(new Student(20134509, "Anhtu", "Male", "Hanoi", "SET", songs1));
		students.add(new Student(20134510, "Huy ga", "Male", "Hanoi", "SET", songs2));
		students.add(new Student(20134511, "Toan nood", "Male", "Bac Giang", "SET", songs3));
		
	}
	
	@RequestMapping(value = { "/student-list" }, method = RequestMethod.GET)
	public String studentList(Model model) {
		model.addAttribute("students", students);
		return "student_list";
	}

	@RequestMapping(value = { "/add-student" }, method = RequestMethod.GET)
	public String addStudent(Model model) {
		model.addAttribute("songs", getSongListDemo());
		model.addAttribute("newStudent", new Student());
		return "add_student";
	}
	
	@RequestMapping(value = { "/add-student" }, method = RequestMethod.POST)
	public String saveStudent(Model model, @ModelAttribute("newStudent") Student newStudent) {
		if(newStudent.getId() != 0 && !"".equals(newStudent.getName())) {
			List<Integer> songIdList = newStudent.getSongIdList();
			newStudent.setFavoriteSongs(getSongListByIdFromDatabase(songIdList));
			students.add(newStudent);
			return "redirect:student-list";
		} else {
			model.addAttribute("songs", getSongListDemo());
			model.addAttribute("errorMessage", errorMessage);
			return "add_student";
		}
	}
	
	public List<Song> getSongListDemo() {
		List<Song> songs = new ArrayList<>();
		songs.add(new Song(1, "See you again", "Charlie Puth", 2013));
		songs.add(new Song(2, "One call away", "Charlie Puth", 2014));
		songs.add(new Song(3, "Noi nay co anh", "Son Tung MTP", 2016));
		songs.add(new Song(4, "Only love", "Trademark", 2002));
		songs.add(new Song(5, "Chung ta ko thuoc ve nhau", "Son Tung MTP", 2017));
		songs.add(new Song(6, "Bad day", "Unknown", 2012));
		songs.add(new Song(7, "Lac Troi", "Son Tung MTP", 2016));
		
		return songs;
	}
	
	public List<Song> getSongListByIdFromDatabase(List<Integer> songIdList) {
		List<Song> songs = new ArrayList<>();
		
		for(Integer id : songIdList) {
			if(id == 1) songs.add(new Song(1, "See you again", "Charlie Puth", 2013));
			if(id == 2) songs.add(new Song(2, "One call away", "Charlie Puth", 2014));
			if(id == 3) songs.add(new Song(3, "Noi nay co anh", "Son Tung MTP", 2016));
			if(id == 4) songs.add(new Song(4, "Only love", "Trademark", 2002));
			if(id == 5) songs.add(new Song(5, "Chung ta ko thuoc ve nhau", "Son Tung MTP", 2017));
			if(id == 6) songs.add(new Song(6, "Bad day", "Unknown", 2012));
			if(id == 7) songs.add(new Song(7, "Lac Troi", "Son Tung MTP", 2016));
		}
		return songs;
	}
}
