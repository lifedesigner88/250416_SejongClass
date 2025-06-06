package sejong.backend.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sejong.backend.user.dto.res.UserInfoResDto;
import sejong.backend.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    
    private final UserService userService;
    
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("list")
    public ResponseEntity<List<UserInfoResDto>> getUserList() {
        List<UserInfoResDto> resDtos = userService.getAllUserInfo();
        return ResponseEntity.ok(resDtos);
    }
    
}