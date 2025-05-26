package sejong.backend.youtube.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sejong.backend.user.entity.User;
import sejong.backend.youtube.entity.UserYoutube;
import sejong.backend.youtube.entity.UserYoutubeId;

import java.util.List;

public interface UserYoutubeRepository extends JpaRepository<UserYoutube, UserYoutubeId> {
    List<UserYoutube> findByUser(User user);

}
