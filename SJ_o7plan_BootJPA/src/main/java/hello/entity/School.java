package hello.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "School")
@Table(name = "school")
@Getter
@Setter
public class School {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="abbr")
	private String abbr;

	@Column(name="name")
	private String name;

	@Column(name="address")
	private String address;

	@Override
	public String toString() {
		return abbr + " - " + name + " - " + address;
	}
}
