package sejong.backend.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sejong.backend.config.Secret;
import sejong.backend.user.dto.req.CreateUserReqDto;
import sejong.backend.user.dto.res.CreateUserResDto;
import sejong.backend.user.entity.User;
import sejong.backend.user.repository.UserDAO;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final Secret secret;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDAO userDAO, Secret secret, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.secret = secret;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${SECRET_ENV}")
    private String secretEnv;

    public CreateUserResDto createUser(CreateUserReqDto dto) {
        User user = dto.makeReqDtoToUser();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        System.out.println(secret.getKakao());
        System.out.println(secretEnv);
        return new CreateUserResDto(userDAO.createUser(user));
    }
}