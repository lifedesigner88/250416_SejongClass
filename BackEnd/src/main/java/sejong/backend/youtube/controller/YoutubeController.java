package sejong.backend.youtube.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sejong.backend.youtube.dto.req.addYoutubeReqDto;
import sejong.backend.youtube.dto.res.addYoutubeResDto;
import sejong.backend.youtube.service.YoutubeService;

@RestController
@RequestMapping("/youtube")
public class YoutubeController {
    
    private final YoutubeService youtubeService;
    
    public YoutubeController(YoutubeService youtubeService) {
        this.youtubeService = youtubeService;
    }
    
    @PostMapping("add")
    public ResponseEntity<addYoutubeResDto> addYoutubeToUser(@RequestBody addYoutubeReqDto dto){
        
        addYoutubeResDto resDto = youtubeService.addYoutubeToUser(dto);
        
        return ResponseEntity.ok(resDto);
    }
    
    

}