package L6_7.first;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PostLoad;

import L6_7.first.entity.Comment;
import L6_7.first.entity.Post;
import L6_7.first.entity.Tag;
import L6_7.first.entity.enums.Status;


public class App 
{
    public static void main( String[] args )   {
    	
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
    	EntityManager em=factory.createEntityManager();
    	
    	addTags(em);
    	//addPost(em);
    	//addComment(em);
    	
    	em.getTransaction().commit();
    	factory.close();
    }
    
    private static void addTags(EntityManager em)   {
    	List <String>tags =new ArrayList<>();
    	tags.add("Java");
    	tags.add("JDBC");
    	tags.add("ORM");
    	tags.add("JPA");
    	tags.add("STS");
    	
    	for(int i=0; i<tags.size(); i++) {
    		Tag tag =new Tag();
    		tag.setName(tags.get(i));
    		
    		em.persist(tag);
    	}
    	
    }
    
    private static void addPost(EntityManager em) {
    	for(int i=1; i<=100; i++) {
    		Post post =new Post();
    		post.setTitle("Post title#" +i);
    		post.setContent("Long post content#"+i);
    		
    		if(i%2==0) post.setStatus(Status.DRAFT);
    		if(i%2==1) post.setStatus(Status.PUBLISH);
    		
    		em.persist(post);
    		List<Tag> tags=em.createQuery("SELECT t FROM TAG t", Tag.class).getResultList();
    		post.setTags(tags);
    	}
    }
    
    private static void addComment(EntityManager em) {
    	for(int i=1; i<=100; i++) {
    		Post post =em.createQuery("SELECT p FROM p WHERE p.id=:id", Post.class)
    				.setParameter("id", i).getSingleResult();
    		
    		Comment comment = new Comment();
    		comment.setAuthor("Author#"+i);
    		comment.setComment("The best comment#"+i);
    		comment.setPost(post);
    		
    		em.persist(comment);
    		
    	}
    }
}
