package sejong.backend.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import sejong.backend.user.entity.User;
import sejong.backend.user.repository.UserRepository;

@Component
public class SecurityUtils {
    
    private final UserRepository userRepository;
    
    public SecurityUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User getUserByAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User reqUser = (User) authentication.getPrincipal();
        return userRepository.findById(reqUser.getUserId()).orElse(null);
    }
}
