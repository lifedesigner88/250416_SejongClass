package sejong.backend.user.dto.req;

import lombok.Getter;
import lombok.Setter;
import sejong.backend.user.entity.User;

@Setter
@Getter
public class CreateUserReqDto {

    public String name;
    public String email;
    public String password;

    public User makeReqDtoToUser() {
        return User.builder()
                .name(name)
                .email(email)
                .build();
    }
}
