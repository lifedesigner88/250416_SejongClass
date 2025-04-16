package sejong.backend.user.repository;

import org.springframework.stereotype.Component;
import sejong.backend.user.entity.User;

@Component
public class UserDAO {

    private final UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser (User user) {
        return userRepository.save(user);
    }

}