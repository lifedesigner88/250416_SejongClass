package sejong.backend.youtube.dto.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class addYoutubeResDto {

    private String reqUseremail;
    private String YoutubeUUID;
    
    public addYoutubeResDto(String reqUseremail, String YoutubeUUID) {
        this.reqUseremail = reqUseremail;
        this.YoutubeUUID = YoutubeUUID;
    }
}
