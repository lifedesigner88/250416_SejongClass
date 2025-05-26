package sejong.backend.youtube.service;

import org.springframework.stereotype.Service;
import sejong.backend.config.security.SecurityUtils;
import sejong.backend.exception.InvalidYoutubeIdException;
import sejong.backend.user.entity.User;
import sejong.backend.youtube.dto.req.AddYoutubeReqDto;
import sejong.backend.youtube.dto.res.AddYoutubeResDto;
import sejong.backend.youtube.dto.res.YoutubeResDto;
import sejong.backend.youtube.entity.UserYoutube;
import sejong.backend.youtube.entity.UserYoutubeId;
import sejong.backend.youtube.entity.Youtube;
import sejong.backend.youtube.repository.UserYoutubeRepository;
import sejong.backend.youtube.repository.YoutubeRepository;

import java.util.List;
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
    
    public AddYoutubeResDto linkYoutubeToUser(AddYoutubeReqDto dto) throws InvalidYoutubeIdException {
        String youtubeUUID = dto.getYoutubeUUID();
        validateYoutubeId(youtubeUUID);
        
        User currentUser = securityUtils.getUserByAuthentication();
        Youtube youtube = findOrCreateYoutube(youtubeUUID);
        linkUserToYoutubeIfNotExists(currentUser, youtube);
        
        return new AddYoutubeResDto(currentUser.getEmail(), youtube.getYoutubeUUID());
    }
    
    private void validateYoutubeId(String youtubeUUID) throws InvalidYoutubeIdException {
        if (youtubeUUID == null || !YOUTUBE_ID_PATTERN.matcher(youtubeUUID).matches()) {
            throw new InvalidYoutubeIdException("Invalid YouTube ID format");
        }
    }
    
    private Youtube findOrCreateYoutube(String youtubeUUID) {
        Youtube youtube = youtubeRepository.findByYoutubeUUID(youtubeUUID);
        if (youtube == null) {
            youtube = youtubeRepository.save(new Youtube(youtubeUUID));
        }
        return youtube;
    }
    
    private void linkUserToYoutubeIfNotExists(User user, Youtube youtube) {
        UserYoutubeId id = new UserYoutubeId(user, youtube);
        if (!userYoutubeRepository.existsById(id)) {
            UserYoutube userYoutube = new UserYoutube(user, youtube);
            userYoutubeRepository.save(userYoutube);
        }
    }
    
    public List<YoutubeResDto> getMyYoutubeList() {
        User currentUser = securityUtils.getUserByAuthentication();
        return userYoutubeRepository.findByUser(currentUser)
                .stream()
                .map(UserYoutube::getYoutube)
                .map(YoutubeResDto::new)
                .toList();
    }
}
