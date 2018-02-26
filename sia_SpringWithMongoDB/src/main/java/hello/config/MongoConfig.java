package hello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import hello.repo.StudentRepository;

/*
 * you’ll need to configure a MongoClient bean to be able to 
 * access the MongoDB database.
 * You’ll also need a MongoTemplate bean to be able to perform 
 * template-based data access against the database.
 * Optionally, but desirably, you’ll want to enable Spring Data MongoDB’s 
 * automatic repository generation.
 * 
 * By default, @EnableMongoRepositories will scan the current package for any 
 * interfaces that extend one of Spring Data’s repository interfaces. Use it’s 
 * basePackageClasses=StudentRepository.class to safely tell Spring Data MongoDB to 
 * scan a different root package by type if your project layout has multiple 
 * projects and its not finding your repositories.
 */

@Configuration
@EnableMongoRepositories(basePackageClasses=StudentRepository.class,
	repositoryImplementationPostfix="Impl")		//tự động sinh các mongo repository
// bằng việc scan package hello.repo
// Chú ý: repositoryImplementationPostfix mặc định = "Impl", do đó ko cần dòng này cũng đc
public class MongoConfig extends AbstractMongoConfiguration {
//	@Bean
//	public MongoFactoryBean mongo() {
//		MongoFactoryBean mongo = new MongoFactoryBean();
//		mongo.setHost("localhost");
//		return mongo;
//	}
//
//	@Bean
//	public MongoOperations mongoTemplate(Mongo mongo) {
//		return new MongoTemplate(mongo, "OrdersDB");
//	}

	// Rather than declare these above beans directly, the configuration class could
	// extend AbstractMongoConfiguration and override its getDatabaseName() and mongo() methods.
	@Override
	protected String getDatabaseName() {
		return "anhtudb"; // Specify database name
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient("localhost", 27017); // Create a Mongo client
	}

	/*
	 * The most noticeable difference is that this configuration doesn’t directly
	 * declare a MongoTemplate bean, although one is implicitly created. Instead,
	 * you override getDatabaseName() to provide the name of the database. The
	 * mongo() method still creates an instance of MongoClient, but because it
	 * throws Exception, you can work with MongoClient directly without working with
	 * MongoFactoryBean.
	 */
}
