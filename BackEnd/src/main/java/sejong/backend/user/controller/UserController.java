package sejong.backend.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sejong.backend.user.dto.req.CreateUserReqDto;
import sejong.backend.user.dto.res.CreateUserResDto;
import sejong.backend.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("create")
    public ResponseEntity<CreateUserResDto> createUser (@RequestBody CreateUserReqDto dto){
        CreateUserResDto resDto = userService.createUser(dto);
        return ResponseEntity.ok(resDto);
    }

}