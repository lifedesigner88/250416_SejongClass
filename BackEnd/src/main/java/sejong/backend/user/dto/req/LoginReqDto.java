package sejong.backend.user.dto.req;

import lombok.Getter;

@Getter
public class LoginReqDto {
    private String email;
    private String password;
}
