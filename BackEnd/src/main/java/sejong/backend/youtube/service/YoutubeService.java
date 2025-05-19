package sejong.backend.youtube.service;

import org.springframework.stereotype.Service;
import sejong.backend.config.security.SecurityUtils;
import sejong.backend.exception.InvalidYoutubeIdException;
import sejong.backend.user.entity.User;
import sejong.backend.youtube.dto.req.addYoutubeReqDto;
import sejong.backend.youtube.dto.res.addYoutubeResDto;
import sejong.backend.youtube.entity.UserYoutube;
import sejong.backend.youtube.entity.Youtube;
import sejong.backend.youtube.repository.UserYoutubeRepository;
import sejong.backend.youtube.repository.YoutubeRepository;

import java.util.regex.Pattern;

@Service
public class YoutubeService {
    
    private static final Pattern YOUTUBE_ID_PATTERN = Pattern.compile("^[A-Za-z0-9_-]{11}$");
    private final YoutubeRepository youtubeRepository;
    private final UserYoutubeRepository userYoutubeRepository;
    private final SecurityUtils securityUtils;
    
    public YoutubeService(YoutubeRepository youtubeRepository, SecurityUtils securityUtils,
                          UserYoutubeRepository userYoutubeRepository) {
        this.youtubeRepository = youtubeRepository;
        this.securityUtils = securityUtils;
        this.userYoutubeRepository = userYoutubeRepository;
    }
    
    public addYoutubeResDto addYoutubeToUser(addYoutubeReqDto dto) throws InvalidYoutubeIdException {
        
        if (!validateYoutubeId(dto.getYoutubeId()))
            throw new InvalidYoutubeIdException("Invalid YouTube ID format");
        
        User reqUser = securityUtils.getUserByAuthentication();
        
        if (youtubeRepository.existsByYoutubeUUID(dto.getYoutubeId())){
            Youtube youtube = youtubeRepository.findByYoutubeUUID(dto.getYoutubeId());
            UserYoutube userYoutube = new UserYoutube(reqUser, youtube);
            userYoutubeRepository.save(userYoutube);
        }
        
        Youtube youtube = new Youtube();
        youtube.setYoutubeUUID(dto.getYoutubeId());
        
        youtubeRepository.save(youtube);
        
        addYoutubeResDto resDto = new addYoutubeResDto();
        resDto.setYoutubeId(dto.getYoutubeId());
        
        return resDto;
    }
    
    private boolean validateYoutubeId(String youtubeId) {
        return youtubeId != null && YOUTUBE_ID_PATTERN.matcher(youtubeId).matches();
    }
}
