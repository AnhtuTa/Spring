package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import hello.config.MongoConfig;
import hello.entity.Song;
import hello.entity.Student;

public class App4_using_mongoTemplate {
	// KHÔNG DÙNG ĐC THẰNG SAU
	@Autowired
	MongoTemplate mongoTemplate;
	//Note: MongoTemplate bean already defined in AbstractMongoConfiguration
	
	

	public static void main(String[] args) {
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(MongoConfig.class);
		
//		App4 app = new App4();
//		app.mongoTemplate.findAll(Student.class);	app.mongoTemplate = null
		
		MongoTemplate mongoTemplate = (MongoTemplate) context.getBean("mongoTemplate");
//		List<Student> stList = mongoTemplate.findAll(Student.class);
//		System.out.println(stList);

		System.out.println("\n============= insert ============= ");
		Student st1 = new Student("Demo3", "FTUwtf", "Bac Giang", 3230);
		List<Song> songs = new ArrayList<>();
		songs.add(new Song("Lạc trôi", "Sơn Tùng MTP", 2012));
		songs.add(new Song("One call away", "Charlie", 2011));
		st1.setSongs(songs);
		//mongoTemplate.insert(st1);	//success!
		
		System.out.println("\n============= update (updateFirst, updateMulti: see google)  ============= ");
		Query query = Query.query(Criteria.where("name").is("Demo3"));
		Student st2 = mongoTemplate.findOne(query, Student.class);
		if(st2 != null) st2.setName("Demo3_updated");
		//mongoTemplate.save(st2);	//success!
		
		System.out.println("\n============= upsert ============= ");
		query = Query.query(Criteria.where("name").is("TooHot"));
		Update update = new Update();
		update.set("name", "TooHot_update");
		//mongoTemplate.upsert(query, update, Student.class);	//this new student will be created
		
		query = Query.query(Criteria.where("name").is("TooHot_update"));
		Student st3 = mongoTemplate.findOne(query, Student.class);
		if(st3 != null) mongoTemplate.remove(st3);	//success
		
		((ConfigurableApplicationContext)context).close();
	}

}
