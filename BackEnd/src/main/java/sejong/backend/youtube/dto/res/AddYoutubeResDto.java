package sejong.backend.youtube.dto.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddYoutubeResDto {

    private String reqUseremail;
    private String YoutubeUUID;
    
    public AddYoutubeResDto(String reqUseremail, String YoutubeUUID) {
        this.reqUseremail = reqUseremail;
        this.YoutubeUUID = YoutubeUUID;
    }
}
