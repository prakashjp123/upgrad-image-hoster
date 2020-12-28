package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    //The method calls the createComment() method in the Repository and passes the comment to be persisted in the database
    public void createComment(Comment comment) {
        commentRepository.createComment(comment);
    }

    //The method calls the getImageByImage() method in the Repository and passes the image to be fetch the associated comments
    public List<Comment> getCommentsByImage(Image image) {
        return commentRepository.getCommentsByImage(image);
    }
}
