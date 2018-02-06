package bkhn.att.o7plan_HelloSpring.bean;

import java.util.Date;

import org.springframework.stereotype.Repository;

/*
 * @Repository là một annotation, nó được sử dụng để chú
 * thích trên một class để nói với Spring rằng class
 * này là một Spring BEAN. 
 */
@Repository
public class MyRepository {
	public String getAppName() {
		return "Hello Spring App";
	}

	public Date getSystemDateTime() {
		return new Date();
	}
}
