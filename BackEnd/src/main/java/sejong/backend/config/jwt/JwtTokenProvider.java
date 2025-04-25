//package sejong.backend.config.jwt;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.nio.charset.StandardCharsets;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Date;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Component
//public class JwtTokenProvider {
//
//    private final SecretKey key;
//    private final long tokenValidityInMilliseconds;
//
//    public JwtTokenProvider(
//            @Value("${jwt.secret}") String secret,
//            @Value("${jwt.token-validity-in-milliseconds}") long tokenValidityInMilliseconds) {
//        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds * 1000;
//    }
//
//    // 토큰 생성
//    public String createToken(Authentication authentication) {
//        String authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//
//        long now = (new Date()).getTime();
//        Date validity = new Date(now + this.tokenValidityInMilliseconds);
//
//        return Jwts.builder()
//                .setSubject(authentication.getName())
//                .claim("auth", authorities)
//                .setIssuedAt(new Date(now))
//                .setExpiration(validity)
//                .signWith(key, SignatureAlgorithm.HS512)
//                .compact();
//    }
//
//
//    // 토큰에서 인증 정보 추출
//    public Authentication getAuthentication(String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        Collection<? extends GrantedAuthority> authorities =
//                Arrays.stream(claims.get("auth").toString().split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());
//
//        User principal = new User(claims.getSubject(), "", authorities);
//
//        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
//    }
//
//    // 토큰 유효성 검증
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            log.error("Invalid JWT token: {}", e.getMessage());
//            return false;
//        }
//    }
//
//
//}
