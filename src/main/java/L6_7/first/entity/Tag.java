package L6_7.first.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tag")
@NoArgsConstructor
@Getter @Setter
public class Tag extends BaseEntity {
	
	
	private String name;

	@ManyToMany(mappedBy="tags")
	private List<Post> posts =new ArrayList<>();
	
	
	@Override
	public String toString() {
		return "Tag [name=" + name + ", getId()=" + getId() + "]";
	}

	
}
