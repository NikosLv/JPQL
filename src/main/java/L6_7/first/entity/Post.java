package L6_7.first.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import L6_7.first.entity.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="post")
@NoArgsConstructor
@Getter @Setter
public class Post extends BaseEntity {

	
	@Column(name="title", length=100)
	private String title;
	
	@Column(name="content", length=1000)
	private String content;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(mappedBy="post")
	private List<Comment> comments =new ArrayList<>();
	
	@OneToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Product product;
	
	
	@ManyToMany
	@JoinTable(name="post_tag", joinColumns=@JoinColumn(name="post_id"), inverseJoinColumns=@JoinColumn(name="tag_id"))
	private List<Tag> tags =new ArrayList<>();
	

	@Override
	public String toString() {
		return "Post [title=" + title + ", content=" + content + ", status=" + status + ", getId()=" + getId() + "]";
	}
	
	
	
	
	
	
	
}
