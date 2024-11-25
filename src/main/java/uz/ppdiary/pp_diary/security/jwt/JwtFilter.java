package uz.ppdiary.pp_diary.security.jwt;

import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.ppdiary.pp_diary.dto.response.UserDto;
import uz.ppdiary.pp_diary.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@NonNullApi
@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().startsWith("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader(AUTHORIZATION);
        if (token != null && token.startsWith(BEARER)) {
            token = token.substring(BEARER.length());
            try {
                Claims claims = jwtTokenProvider.parseAllClaims(token);
                String subject = claims.getSubject();
                if (subject != null) {
                    Long userId = Long.parseLong(subject);
                    UserDto user = userService.getById(userId);
                    if (user != null) {
                        List<String> roles = claims.get("roles", List.class);
                        List<GrantedAuthority> authorities = roles.stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());

                        SecurityContextHolder.getContext().setAuthentication(
                                new UsernamePasswordAuthenticationToken(user.getId(), null, authorities)
                        );
                    }
                }
            } catch (Exception e) {
                logger.error("Error processing JWT token", e);
            }
        }

        filterChain.doFilter(request, response);
    }
}

