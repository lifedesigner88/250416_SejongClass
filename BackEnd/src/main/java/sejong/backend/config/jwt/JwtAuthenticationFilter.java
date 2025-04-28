package sejong.backend.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtTokenUtil jwtTokenUtil;
    
    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {

            // 토큰 파싱.
            String jwt = null;
            String bearerToken = request.getHeader("Authorization");
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
                jwt = bearerToken.substring(7);
            
            // 인증정보 Security 정보에 저장
            if (StringUtils.hasText(jwt) && jwtTokenUtil.validateToken(jwt))
                SecurityContextHolder.getContext()
                        .setAuthentication(jwtTokenUtil.getAuthentication(jwt));
            

        } catch (Exception ex) {
            System.out.println("Could not set user authentication in security context");
        }

        filterChain.doFilter(request, response);
    }
}
