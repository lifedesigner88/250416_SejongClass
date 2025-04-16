package sejong.backend.user.service;

import org.springframework.beans.factory.annotation.Value;
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

    public UserService(UserDAO userDAO, Secret secret) {
        this.userDAO = userDAO;
        this.secret = secret;
    }

    @Value("${SECRET_ENV}")
    private String secretEnv;

    public CreateUserResDto createUser(CreateUserReqDto dto) {
        User user = dto.makeReqDtoToUser();
        user.setPassword(dto.getPassword());
        System.out.println(secret.getKakao());
        System.out.println(secretEnv);
        return new CreateUserResDto(userDAO.createUser(user));
    }
}