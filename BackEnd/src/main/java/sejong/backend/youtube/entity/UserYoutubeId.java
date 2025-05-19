package sejong.backend.youtube.entity;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sejong.backend.user.entity.User;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class UserYoutubeId implements Serializable {
    
    private User user;
    private Youtube youtube;
    
    @Builder
    public UserYoutubeId(User user, Youtube youtube) {
        this.user = user;
        this.youtube = youtube;
    }
}
