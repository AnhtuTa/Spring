package hello;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.MongoClient;

import hello.entity.Student;

/*
 * Ví dụ này đéo liên quan đến App.java, nó chả cần config gì cả, chỉ cần
 * mỗi thằng Student.java thôi! Nói cách khác: nó KHÔNG liên quan đến Spring,
 * chỉ là Java bình thường
 */
public class App2 {
	public static final String DB_NAME = "anhtudb";
	public static final String PERSON_COLLECTION = "student";
	public static final String MONGO_HOST = "localhost";
	public static final int MONGO_PORT = 27017;

	public static void main(String[] args) {
		try {
			MongoClient mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
			MongoTemplate mongo = new MongoTemplate(mongoClient, DB_NAME);
			
			// get all documents
			Query query = new Query();
			List<Student> stList = mongo.find(query, Student.class);
			for(Student st :  stList) {
				st.printInfo();
			}
			
//			Student st2 = new Student("Frodo", "HUST", "Hobbit", 1050);
//			mongo.insert(st2);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("done!");
	}
}
