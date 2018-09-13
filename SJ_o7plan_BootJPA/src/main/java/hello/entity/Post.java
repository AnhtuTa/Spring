package hello.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "post")
@Getter
@Setter
public class Post implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1491825794365023669L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "content", nullable=false)
	private String content;

	// field time trong db sẽ ko cần chỉ rõ giá trị, vì nó sẽ lấy timestamp hiện tại
	@Column(name = "time", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	/*
	 * Nếu ko có @JsonManagedReference thì sẽ bị lỗi lặp vô tận
	 * Tham khảo: https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue/18288939#18288939
	 */
	@OneToMany(cascade = CascadeType.PERSIST, //
			fetch = FetchType.LAZY, //
			mappedBy = "post")
	@JsonManagedReference
	private List<Comment> comments;
}
