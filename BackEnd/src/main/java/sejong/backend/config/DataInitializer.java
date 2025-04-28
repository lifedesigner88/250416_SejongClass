package sejong.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sejong.backend.user.entity.User;
import sejong.backend.user.entity.UserRole;
import sejong.backend.user.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private final Secret secret;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public DataInitializer(Secret secret, UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.secret = secret;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("DataInitializer");
        if (!userRepository.existsByUserId(1L)) {
            User admin = User.builder()
                    .name("관리자")
                    .email(secret.getAdmin().getAdminId())
                    .role(UserRole.ROLE_ADMIN)
                    .build();
            admin.setPassword(passwordEncoder.encode(
                    secret.getAdmin().getAdminPass()
            ));
            userRepository.save(admin);
            System.out.println("Admin Created");
        }
    }
}
