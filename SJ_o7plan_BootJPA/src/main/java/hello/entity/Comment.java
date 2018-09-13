package hello.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment implements Serializable {

	private static final long serialVersionUID = -7345639529459246736L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="content",nullable=false)
	private String content;

	// field time trong db sẽ ko cần chỉ rõ giá trị, vì nó sẽ lấy timestamp hiện tại
	@Column(name = "time", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;
	
	/*
	 * Nếu ko có @JsonBackReference thì sẽ bị lỗi lặp vô tận
	 * Tham khảo: https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue/18288939#18288939
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference
	private Post post;
}
