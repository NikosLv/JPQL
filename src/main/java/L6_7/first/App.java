package L6_7.first;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import L6_7.first.entity.Comment;
import L6_7.first.entity.Post;
import L6_7.first.entity.Tag;
import L6_7.first.entity.enums.Status;

public class App {
	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		/**
		 * command to select data from the list comments
		 */
		/*
		  List<Comment> comments=em.createQuery("SELECT c FROM Comment c",
		  Comment.class).getResultList(); //
		  comments.forEach(c->System.out.println(c)); // print list comments
		  Comment commentById=em.createQuery("SELECT c FROM Comment c WHERE c.id=:comment_id",
		  Comment.class) .setParameter("comment_id", 88).getSingleResult();
		  System.out.println(commentById); // print selected comment with id=88
		 */
		/**
		 * command print data from the list comments where id>50
		 */

		/*
		  List<Post> posts = em.createQuery("SELECT p FROM Post p WHERE p.id>:post_id", Post.class)
		 				.setParameter("post_id", 50).getResultList();
		  posts.forEach(p -> System.out.println(p));
		*/
		/**
		 * command print data from the list comments where id=2,56,35,57,78,45.
		 * all data is displayed in the list
		 */
		
		/* 
		   List<Post> posts =em.createQuery("SELECT p FROM Post p WHERE p.id IN (:ids)",Post.class)
		                 .setParameter("ids",Arrays.asList(2,56,35,57,78,45)).getResultList();
		  posts.forEach(p->System.out.println(p));
		 */
		
		/**
		 * command print data from the list comments where id begins with the number 8
		 * all data is displayed in the list
		 */
		 /*	
		  	List<Post> posts =em.createQuery("SELECT p FROM Post p WHERE p.title LIKE:post_title", Post.class)
				 		.setParameter("post_title","%8_").getResultList();
		 	posts.forEach(p->System.out.println(p));
		 */
		
		/**
		 * command print data from the list comments where id is between 76 and 85
		 * all data is displayed in the list
		 */
		 /*
		  	List<Post> posts =em.createQuery("SELECT p FROM Post p WHERE p.id BETWEEN :first AND :last",Post.class)
		  		 		.setParameter("first",76).setParameter("last",85).getResultList();
		 	posts.forEach(p->System.out.println(p));
		  */
		
		// AgrFunc

		// Long count = em.createQuery("SELECT count(c.id) FROM Comment c", Long.class).getSingleResult();
		// System.out.println("Count:"+count);

		// Long sum = em.createQuery("SELECT sum(c.id) FROM Comment c", Long.class).getSingleResult();
		// System.out.println("Sum:"+sum);
		
		 Double avg=em.createQuery("SELECT avg(c.id) FROM Comment c", Double.class).getSingleResult();
		 System.out.println("Avg:"+avg);
		//
		// Integer max=em.createQuery("SELECT max(c.id) FROM Comment c", Integer.class).getSingleResult();
		// System.out.println("Max:"+max);
		//
		// Integer min=em.createQuery("SELECT min(c.id) FROM Comment c", Integer.class).getSingleResult();
		// System.out.println("Min:"+min);

		// addTags(em);
		// addPost(em);
		//addComment(em);

		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	/**
	 * 
	 * This method adding Tags
	 */
	private static void addTags(EntityManager em) {
		List<String> tags = new ArrayList<>();
		tags.add("Java");
		tags.add("JDBC");
		tags.add("ORM");
		tags.add("JPA");
		tags.add("STS");

		for (int i = 0; i < tags.size(); i++) {
			Tag tag = new Tag();
			tag.setName(tags.get(i));

			em.persist(tag);
		}

	}

	/**
	 * 
	 * This method adding all posts
	 */
	private static void addPost(EntityManager em) {
		for (int i = 1; i <= 100; i++) {
			Post post = new Post();
			post.setTitle("Post title#" + i);
			post.setContent("Long post content#" + i);

			if (i % 2 == 0)
				post.setStatus(Status.DRAFT);
			if (i % 2 == 1)
				post.setStatus(Status.PUBLISH);

			em.persist(post);

			List<Tag> tags = em.createQuery("SELECT t FROM Tag t", Tag.class).getResultList();
			post.setTags(tags);
		}
	}

	/**
	 * 
	 * This method adding all comments
	 */
	private static void addComment(EntityManager em) {
		for (int i = 2; i <= 101; i++) {
			Post post = em.createQuery("SELECT p FROM Post p WHERE p.id = :id", Post.class).setParameter("id", i)
					.getSingleResult();

			Comment comment = new Comment();
			comment.setAuthor("Author #" + i);
			comment.setComment("The best comment #" + i);
			comment.setPost(post);

			em.persist(comment);
		}
	}

}
