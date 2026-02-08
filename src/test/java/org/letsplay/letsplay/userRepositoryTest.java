package org.letsplay.letsplay;


import com.mongodb.internal.VisibleForTesting;
import org.junit.jupiter.api.Test;
import org.letsplay.letsplay.model.User;
import org.letsplay.letsplay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class userRepositoryTest {
    private UserRepository userRepository;


    public void testCreateUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setUsername("test");
        user.setPassword("Test@test");

        User saved = userRepository.save(user);

        assertThat(saved.getUuid()).isNotNull();
        assertThat(saved.getEmail()).isEqualTo("test@example.com");
    }

}