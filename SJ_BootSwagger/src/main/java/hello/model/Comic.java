package hello.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comic {
	// Annotation @ApiModelProperty dùng để giải thích các field của model trên swagger
	@ApiModelProperty(notes = "Id auto increase upon create new")
	private int id;
	
	@ApiModelProperty(notes = "Đây là tên của cuốn truyện tranh")
	private String title;
	
	private String author;
}
