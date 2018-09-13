package hello.entity;

import java.util.Date;

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
@Table(name = "message")
@Getter
@Setter
public class Message {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "content")
	private String content;

	// field time trong db sẽ ko cần chỉ rõ giá trị, vì nó sẽ lấy timestamp hiện tại
	@Column(name = "time", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;
	
	
	@Column(name = "content_type")
	private int contentType;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference
	private Room room;

	public Message() {}
	
	public Message(String content, int contentType) {
		this.content = content;
		this.contentType = contentType;
	}
}
