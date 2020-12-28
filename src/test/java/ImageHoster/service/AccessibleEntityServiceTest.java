package ImageHoster.service;

import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.model.UserProfile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;

@RunWith(SpringRunner.class)
public class AccessibleEntityServiceTest {

    @TestConfiguration
    static class AccessibleEntityServiceTestContextConfiguration {

        @Bean
        public AccessibleEntityService<User> accessibleEntityService() {
            return new AccessibleEntityService<User>();
        }

        @Bean
        public HttpSession httpSession() {
            return new MockHttpSession();
        }
    }

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private AccessibleEntityService<User> accessibleEntityService;

    // This test checks the isAccessible method to find whether the entity is accessible.
    @Test
    public void isAccessibleTrue() throws Exception {
        User user = new User();
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1);
        userProfile.setEmailAddress("a@gmail.com");
        userProfile.setFullName("Abhi Mahajan");
        userProfile.setMobileNumber("9876543210");
        user.setProfile(userProfile);
        user.setId(1);
        user.setUsername("Abhi");
        user.setPassword("password1@");

        Image image = new Image();
        image.setId(1);
        image.setTitle("new");
        image.setDescription("This image is for testing purpose");
        image.setUser(user);

        httpSession.setAttribute("loggeduser", user);
        Assert.assertEquals(accessibleEntityService.isAccessible(image), true);
    }

    // This test checks the isAccessible method to find whether the entity is not accessible.
    @Test
    public void isAccessibleFalse() throws Exception {
        User user = new User();
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1);
        userProfile.setEmailAddress("a@gmail.com");
        userProfile.setFullName("Abhi Mahajan");
        userProfile.setMobileNumber("9876543210");
        user.setProfile(userProfile);
        user.setId(1);
        user.setUsername("Abhi");
        user.setPassword("password1@");

        User user1 = new User();
        UserProfile userProfile1 = new UserProfile();
        userProfile.setId(2);
        userProfile.setEmailAddress("p@gmail.com");
        userProfile.setFullName("Prerna");
        userProfile.setMobileNumber("9876543210");
        user1.setProfile(userProfile1);
        user1.setId(2);
        user1.setUsername("Prerna");
        user1.setPassword("password1@@");

        Image image = new Image();
        image.setId(1);
        image.setTitle("new");
        image.setDescription("This image is for testing purpose");
        image.setUser(user1);

        httpSession.setAttribute("loggeduser", user);
        Assert.assertEquals(accessibleEntityService.isAccessible(image), false);
    }
}
