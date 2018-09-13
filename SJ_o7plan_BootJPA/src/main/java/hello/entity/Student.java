package hello.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name="Student")
@Table(name="student")
@Getter
@Setter
public class Student {
	@Id
	@Column(name="id")
	// To use a MySQL AUTO_INCREMENT column, you are supposed to use an IDENTITY strategy
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	private School school;

}
