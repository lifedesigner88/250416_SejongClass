package sejong.backend.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sejong.backend.config.jwt.JwtTokenUtil;
import sejong.backend.exception.EmailDuplicateException;
import sejong.backend.user.dto.req.CreateUserReqDto;
import sejong.backend.user.dto.req.LoginReqDto;
import sejong.backend.user.dto.res.CreateUserResDto;
import sejong.backend.user.dto.res.LoginResDto;
import sejong.backend.user.dto.res.UserInfoResDto;
import sejong.backend.user.entity.User;
import sejong.backend.user.repository.UserDAO;
import sejong.backend.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public UserService(UserDAO userDAO, UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenUtil jwtTokenUtil) {
        this.userDAO = userDAO;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public CreateUserResDto createUser(CreateUserReqDto dto) {
        User user = dto.makeReqDtoToUser();
        if (userRepository.existsByEmail(user.getEmail())) {
            System.out.println("12345123124123");
            throw new EmailDuplicateException("이미 존재하는 이메일입니다.");
        }
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

    public List<UserInfoResDto> getAllUserInfo() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserInfoResDto::new)
                .toList();
    }
}