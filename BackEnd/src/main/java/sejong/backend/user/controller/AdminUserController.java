package sejong.backend.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println("토큰 사용자 이름");
        System.out.println(email);
        
        List<UserInfoResDto> resDtos = userService.getAllUserInfo();
        
        return ResponseEntity.ok(resDtos);
    }
    
}