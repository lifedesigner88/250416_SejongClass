package sejong.backend.youtube.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sejong.backend.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Youtube extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long youtubeId;
    
    private String youtubeUUID;
    
    @OneToMany(mappedBy = "youtube", cascade = CascadeType.ALL)
    private final List<UserYoutube> userYoutubes = new ArrayList<>();
    
    
}
