package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    // Comment text is posted to the url '/image/{imageId}/comments' by the user.
    // Adds new comment to the image.
    @RequestMapping(value = "/image/{imageId}/comments", method = RequestMethod.POST)
    public String addComment(@PathVariable("imageId") Integer imageId, @RequestParam("comment") String commentText,
                             HttpSession session) {
        Image image = imageService.getImage(imageId);
        User user = (User) session.getAttribute("loggeduser");

        Comment comment = new Comment();
        comment.setText(commentText);
        comment.setCreatedDate(new Date());
        comment.setImage(image);
        comment.setUser(user);
        commentService.createComment(comment);

        return "redirect:/images/" + imageId;
    }
}
