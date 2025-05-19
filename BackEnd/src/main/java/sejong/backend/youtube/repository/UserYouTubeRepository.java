package sejong.backend.youtube.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sejong.backend.youtube.entity.UserYoutube;
import sejong.backend.youtube.entity.UserYoutubeId;

public interface UserYouTubeRepository extends JpaRepository<UserYoutube, UserYoutubeId> {

}
