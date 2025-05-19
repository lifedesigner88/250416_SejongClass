package sejong.backend.youtube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sejong.backend.youtube.entity.Youtube;


@Repository
public interface YoutubeRepository extends JpaRepository<Youtube, Long> {
    boolean existsByYoutubeUUID(String uuid);
    Youtube findByYoutubeUUID(String uuid);
}
