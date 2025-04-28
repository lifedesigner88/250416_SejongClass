package sejong.backend.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sejong.backend.user.dto.req.CreateUserReqDto;
import sejong.backend.user.dto.req.LoginReqDto;
import sejong.backend.user.dto.res.CreateUserResDto;
import sejong.backend.user.dto.res.LoginResDto;
import sejong.backend.user.dto.res.UserInfoResDto;
import sejong.backend.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("create")
    public ResponseEntity<CreateUserResDto> createUser(@RequestBody CreateUserReqDto dto) {
        CreateUserResDto resDto = userService.createUser(dto);
        return ResponseEntity.ok(resDto);
    }
    
    @PostMapping("login")
    public ResponseEntity<LoginResDto> login(@RequestBody LoginReqDto dto) {
        LoginResDto resDto = userService.login(dto);
        return ResponseEntity.ok(resDto);
    }
    
    @GetMapping("list")
    public ResponseEntity<List<UserInfoResDto>> getUserList() {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println(email);
        
        List<UserInfoResDto> resDtos = userService.getAllUserInfo();
        
        return ResponseEntity.ok(resDtos);
    }
    
}