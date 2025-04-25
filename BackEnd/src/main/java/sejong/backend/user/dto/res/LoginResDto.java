package sejong.backend.user.dto.res;

import lombok.Getter;
import lombok.Setter;
import sejong.backend.user.entity.User;

@Getter
public class LoginResDto {

    @Setter
    private String accessToken;

    private final Long userId;
    private final String email;
    private final String name;
    private final String role;
    private final String message;

    public LoginResDto(User user, String message) {
        if (user == null) { // 이메일 존재 하지 않을 경우.
            this.userId = null;
            this.email = null;
            this.name = null;
            this.role = null;
        } else {
            this.userId = user.getUserId();
            this.email = user.getEmail();
            this.name = user.getName();
            this.role = user.getRole().name();
        }
        this.message = message;
    }
}
