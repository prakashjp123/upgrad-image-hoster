package ImageHoster.service;

import ImageHoster.model.AccessibleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AccessibleEntityService<U> {

    @Autowired
    private HttpSession httpSession;

    public boolean isAccessible(AccessibleEntity<U> entity) {
        U user = (U) httpSession.getAttribute("loggeduser");
        return entity.getUser().equals(user);
    }
}
