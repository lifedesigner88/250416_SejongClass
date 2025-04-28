package sejong.backend.user.dto.res;

import lombok.Getter;
import sejong.backend.user.entity.User;

@Getter
public class UserInfoResDto {
    private final Long userId;
    private final String name;
    private final String email;
    private final String role;
    
    public UserInfoResDto(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.role = String.valueOf(user.getRole());
    }
}
