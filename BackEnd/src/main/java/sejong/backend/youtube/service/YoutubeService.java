package sejong.backend.youtube.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sejong.backend.youtube.dto.req.addYoutubeReqDto;
import sejong.backend.youtube.dto.res.addYoutubeResDto;
import sejong.backend.youtube.entity.Youtube;
import sejong.backend.youtube.repository.YoutubeRepository;

@Service
public class YoutubeService {
    
    private final YoutubeRepository youtubeRepository;
    
    public YoutubeService(YoutubeRepository youtubeRepository) {
        this.youtubeRepository = youtubeRepository;
    }
    
    public addYoutubeResDto addYoutubeToUser(addYoutubeReqDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        
        System.out.println(email);
        
        Youtube youtube = new Youtube();
        youtube.setYoutubeId(dto.getYoutubeId());
        
        youtubeRepository.save(youtube);
        
        addYoutubeResDto resDto = new addYoutubeResDto();
        resDto.setYoutubeId(dto.getYoutubeId());
        
        return resDto;
    }
}
