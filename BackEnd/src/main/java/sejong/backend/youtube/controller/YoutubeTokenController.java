package sejong.backend.youtube.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sejong.backend.youtube.service.YoutubeService;

@RestController
@RequestMapping("/youtube/token")
public class YoutubeTokenController {
    
    
    private final YoutubeService youtubeService;
    
    public YoutubeTokenController(YoutubeService youtubeService) {
        this.youtubeService = youtubeService;
    }
    
    @DeleteMapping("delete/{youtubeId}")
    public ResponseEntity<String> deleteYoutubeFromToken(@PathVariable Long youtubeId) {
        youtubeService.deleteYoutubeFromToken(youtubeId);
        return ResponseEntity.ok("토큰을 이용환 영상 삭제 성공");
    }
    
}
