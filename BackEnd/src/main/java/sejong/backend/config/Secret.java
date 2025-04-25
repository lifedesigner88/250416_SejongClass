package sejong.backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "secret")
@Getter @Setter
public class Secret {

    @Getter @Setter
    public static class Jwt {
        private String secret;
        private long expiration;
    }

    @Getter @Setter
    public static class Admin {
        private String adminId;
        private String adminPass;
    }

    private Jwt jwt = new Jwt();
    private Admin admin = new Admin();

}
