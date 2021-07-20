package com.example.velogclonebe.config.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.velogclonebe.config.auth.PrincipalDetails;
import com.example.velogclonebe.domain.entity.User;
import com.example.velogclonebe.domain.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager , UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    // 인증, 권한이 필요한 요청시 타는 필터
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        // System.out.println("인증이나 권한이 필요한 주소가 요청됨");
        String jwtHeader = request.getHeader("Authorization");
        // System.out.println("jwtHeader ::::::: " + jwtHeader);

        if (jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        // jwt 토큰 검증
        String jwtToken = request.getHeader("Authorization").replace(JwtProperties.TOKEN_PREFIX, "");
        String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken).getClaim("username").asString();

        // 서명 정상
        if (username != null) {
            User userEntity = userRepository.findByUsername(username);
            PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            // 시큐리티 세션에 접근해 Authentication 객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        }

    }
}
