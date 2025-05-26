package sejong.backend.youtube.dto.res;

import lombok.Getter;
import sejong.backend.youtube.entity.Youtube;

@Getter
public class YoutubeResDto {

    private final Long youtubeId;
    private final String youtubeUUID;
    
    public YoutubeResDto(Youtube youtube){
        this.youtubeId = youtube.getYoutubeId();
        this.youtubeUUID = youtube.getYoutubeUUID();
    }

}
