package sejong.backend.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sejong.backend.user.dto.res.UserInfoResDto;
import sejong.backend.user.service.UserService;

@RestController
@RequestMapping("/user/token")
public class UserTokenController {

    private final UserService userService;
    
    public UserTokenController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("myinfo")
    public ResponseEntity<UserInfoResDto> getMyInfoFromToken() {
        return ResponseEntity.ok(userService.getMyInfoFromToken());
    }
    
}
