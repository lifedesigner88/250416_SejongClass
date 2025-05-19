package sejong.backend.youtube.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sejong.backend.user.entity.User;

@Entity
@Getter
@IdClass(UserYoutubeId.class)
@NoArgsConstructor
public class UserYoutube {
    
    
    @ManyToOne
    @JoinColumn(nullable = false)
    @Id private User user;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    @Id private Youtube youtube;
    
    @Builder
    public UserYoutube(User user, Youtube youtube) {
        this.user = user;
        this.youtube = youtube;
    }

}
