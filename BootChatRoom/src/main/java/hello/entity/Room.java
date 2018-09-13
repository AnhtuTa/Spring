package hello.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "room")
@Getter
@Setter
@NoArgsConstructor
public class Room {
	public static final String PRIVACY_PUBLIC = "PUBLIC";	//public room: anyone can join
	public static final String PRIVACY_PRIVATE = "PRIVATE";	//private room: anyone can request to join
	public static final String PRIVACY_CLOSED = "CLOSED";	//close room: nobody can request to join
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="name")
	private String name;
	
	@Column(name="privacy")
	private String privacy;
	
	@OneToMany(cascade = CascadeType.PERSIST, //
			fetch = FetchType.LAZY, //
			mappedBy = "room")
	@JsonManagedReference
	private List<Message> messages;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="rooms")
    private Set<User> users;
	
	public Room(long id, String name, String privacy) {
		this.id = id;
		this.name = name;
		this.privacy = privacy;
	}
}
