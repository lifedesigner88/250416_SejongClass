package sejong.backend.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sejong.backend.config.Secret;
import sejong.backend.user.dto.req.CreateUserReqDto;
import sejong.backend.user.dto.req.LoginReqDto;
import sejong.backend.user.dto.res.CreateUserResDto;
import sejong.backend.user.dto.res.LoginResDto;
import sejong.backend.user.entity.User;
import sejong.backend.user.repository.UserDAO;

import java.util.Optional;

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

    public LoginResDto login(LoginReqDto dto) {
        Optional<User> user = userDAO.findUserByEmail(dto.getEmail());
        if (user.isPresent()) {
            if (passwordEncoder.matches(dto.getPassword(), user.get().getPassword())) {
                // 토큰 발급 코드 삽입
                return new LoginResDto(user.get(), "로그인 성공");
            }
            return new LoginResDto(null, "비밀번호 불일치");
        }
        return new LoginResDto(null, "존재하지 않는 이메일 입니다.");
    }
}