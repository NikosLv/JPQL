package L6_7.first.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.*;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	
	@ManyToMany
	@JoinTable(name="post_tag", joinColumns=@JoinColumn(name="post_id"), inverseJoinColumns=@JoinColumn(name="tag_id"))
	private List<Tag> tags =new ArrayList<>();
	

	@Override
	public String toString() {
		return "Post [title=" + title + ", content=" + content + ", status=" + status + ", getId()=" + getId() + "]";
	}
	
	
	
	
	
	
	
}
