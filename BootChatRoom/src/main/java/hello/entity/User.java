package hello.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import hello.validator.MyPassword;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Size(min=4, max=20, message="{Size.User.username}")
	@Column(name="username",nullable=false)
	private String username;

	@MyPassword(message="{MyPassword.User.password}")
	@Column(name="password",nullable=false)
	private String password;

	@NotBlank(message="{NotBlank.User.fullname}")
	@Column(name="fullname")
	private String fullname;

	@Column(name="single_id", nullable=true, columnDefinition = "String default null")
	private String singleId;	// ID của nhân viên tại Samsung
	
	@Column(name="enabled", insertable = false)
	private int enabled;
	
	// field không có trong database
	// Use @Transient annotation for field you are not going to store in DB
	// If you annotate a field with @Transient it will not be persisted
	@Transient
	private String confirmPassword;
	/*
	 * Nếu ko có @JsonBackReference thì sẽ bị lỗi lặp vô tận
	 * Tham khảo: https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue/18288939#18288939
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JsonBackReference
    @JoinTable(
        name="user_role",
        joinColumns=@JoinColumn(name="user_id"),
        inverseJoinColumns=@JoinColumn(name="role_id")
    )
    private Set<Role> roles;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JsonBackReference
    @JoinTable(
        name="user_room",
        joinColumns=@JoinColumn(name="user_id"),
        inverseJoinColumns=@JoinColumn(name="room_id")
    )
    private Set<Room> rooms;

	// Constructors
	public User() {}
	
	public User(String username, String fullname) {
		this.username = username;
		this.fullname = fullname;
	}
}
