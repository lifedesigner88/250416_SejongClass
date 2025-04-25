package sejong.backend.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sejong.backend.config.jwt.JwtTokenUtil;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder,
                       JwtTokenUtil jwtTokenUtil) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public CreateUserResDto createUser(CreateUserReqDto dto) {
        User user = dto.makeReqDtoToUser();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return new CreateUserResDto(userDAO.createUser(user));
    }

    public LoginResDto login(LoginReqDto dto) {
        Optional<User> optionalUser = userDAO.findUserByEmail(dto.getEmail());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
                String token = jwtTokenUtil.generateToken(user);
                LoginResDto loginResDto = new LoginResDto(user, "로그인 성공");
                loginResDto.setAccessToken(token);
                return loginResDto;
            }
            return new LoginResDto(null, "비밀번호 불일치");
        }
        return new LoginResDto(null, "존재하지 않는 이메일 입니다.");
    }
}