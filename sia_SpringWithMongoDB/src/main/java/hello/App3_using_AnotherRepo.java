package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import hello.config.MongoConfig;
import hello.entity.Song;
import hello.entity.Student;
import hello.repo.AnotherStRepository;

/*
 * Class này dùng interface AnotherStRepository để thực hiện CRUD
 * class AnotherStRepository chỉ extends thằng MongoRepository
 * chứ ko thêm 1 phương thức mở rộng nào 
 */
public class App3_using_AnotherRepo {
	public static void main(String[] args) {
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(MongoConfig.class);
		
		AnotherStRepository asr = (AnotherStRepository) context.getBean("anotherStRepository");
		MongoTemplate mongoTemplate = (MongoTemplate) context.getBean("mongoTemplate");

		System.out.println("\n============= Save and insert ============= ");
		Student st1 = new Student("Demo", "FTU", "Bac Giang", 3230);
		List<Song> songs = new ArrayList<>();
		songs.add(new Song("Lạc trôi", "Sơn Tùng MTP", 2012));
		songs.add(new Song("One call away", "Charlie", 2011));
		st1.setSongs(songs);
		//asr.save(st1);	success!!!
		//asr.insert(st1);	//it works the same as the save operation
		
		// KHÔNG thể dùng asr để find record vì id làm sao mà nhớ đc!
		// Student st2 = asr.findOne(id);
		// Phải dùng thằng mongoTemplate
		System.out.println("\n============= update ============= ");
		Query query = Query.query(Criteria.where("name").is("Demo"));	//tìm 1 record có name = Demo
		Student st2 = mongoTemplate.findOne(query, Student.class);
		if(st2 != null) {
			st2.setName("Demo2");
			//asr.save(st2);	//update success!
		}
		
		System.out.println("\n============= delete ============= ");
		query = Query.query(Criteria.where("name").is("Demo2"));
		st2 = mongoTemplate.findOne(query, Student.class);
		if(st2 != null) {
			//asr.delete(st2);	//delete success!
		}
		
		System.out.println("\n============= find ==============");
		System.out.println(asr.findAll());
		
		System.out.println("\n============= find and sort ==============");
		List<Student> stList = asr.findAll(new Sort(Sort.Direction.ASC, "name"));
		for(Student st : stList) {
			System.out.println(st);
		}
		

		System.out.println("\n============= FindAll with Pageable ==============");
		Pageable pageableRequest = new PageRequest(3, 2);
		Page<Student> stPage = asr.findAll(pageableRequest);
		stList = stPage.getContent();
		for(Student st : stList) {
			System.out.println(st);
		}
		((ConfigurableApplicationContext)context).close();
	}

}
