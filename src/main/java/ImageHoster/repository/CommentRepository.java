package ImageHoster.repository;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

//The annotation is a special type of @Component annotation which describes that the class defines a data repository
@Repository
public class CommentRepository {

    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    //The method receives the Comment object to be persisted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
    public Comment createComment(Comment comment) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(comment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        return comment;
    }

    //The method creates an instance of EntityManager
    //Executes JPQL query to fetch the comments from the database with corresponding image
    //Returns the comments in case the comments are found in the database
    //Returns null if no comment is found in the database
    public List<Comment> getCommentsByImage(Image image) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c WHERE c.image = :image", Comment.class);
            query.setParameter("image", image);
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
