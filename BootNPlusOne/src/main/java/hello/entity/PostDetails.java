package hello.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "post_details")
@Getter
@Setter
public class PostDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "post_id", nullable = false)
    private Integer id;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "content")
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @MapsId
    @JsonBackReference
    private Post post;
}
