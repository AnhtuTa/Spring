package hello.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gif")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gif {
	public static final String GIF_DESPICABLE_ME = "DESPICABLE_ME";
	public static final String GIF_DRAGONBALL = "DRAGONBALL";
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "url")
	private String url;

	@Column(name = "title")
	private String title;

	@Column(name = "type")
	private String type;
}
