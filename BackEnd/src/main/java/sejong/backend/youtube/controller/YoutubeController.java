package sejong.backend.youtube.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sejong.backend.exception.InvalidYoutubeIdException;
import sejong.backend.youtube.dto.req.AddYoutubeReqDto;
import sejong.backend.youtube.dto.res.AddYoutubeResDto;
import sejong.backend.youtube.service.YoutubeService;

@RestController
@RequestMapping("/youtube")
public class YoutubeController {
    
    private final YoutubeService youtubeService;
    
    public YoutubeController(YoutubeService youtubeService) {
        this.youtubeService = youtubeService;
    }
    
    @PostMapping("add")
    public ResponseEntity<AddYoutubeResDto> addYoutubeToUser(@RequestBody AddYoutubeReqDto dto) throws InvalidYoutubeIdException {
        
        AddYoutubeResDto resDto = youtubeService.linkYoutubeToUser(dto);
        
        return ResponseEntity.ok(resDto);
    }
    
    

}