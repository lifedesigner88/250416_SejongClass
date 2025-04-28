package sejong.backend.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import sejong.backend.config.Secret;
import sejong.backend.user.entity.User;
import sejong.backend.user.entity.UserRole;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    private final Secret secret;

    public JwtTokenUtil(Secret secret) {
        this.secret = secret;
    }

    private Key getSigningKey() {
        byte[] keyBytes = secret.getJwt().getSecret().getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(User user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("role", user.getRole().name());

        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + secret.getJwt().getExpiration()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
    public boolean validateToken(String jwt) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public UsernamePasswordAuthenticationToken getAuthentication(String jwt) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        
        Long userId = claims.get("userId", Long.class);
        String role = claims.get("role", String.class);
        String email = claims.getSubject();
        
        User user = User.builder()
                .userId(userId)
                .email(email)
                .role(UserRole.valueOf(role))
                .build();
        
        return new UsernamePasswordAuthenticationToken(user, jwt, user.getAuthorities());
    }
}
