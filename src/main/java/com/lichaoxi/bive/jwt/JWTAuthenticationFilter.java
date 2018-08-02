package com.lichaoxi.bive.jwt;

import com.lichaoxi.bive.entity.User;
import com.lichaoxi.bive.repository.UserRepository;
import com.lichaoxi.bive.security.CustomUserDetails;
import com.lichaoxi.bive.security.CustomUserDetailsService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private CustomUserDetailsService customUserDetailsService;

    //此处通过传递 CustomUserDetailsService 解决 filter 中无法使用 @Autowired 注入的问题
    public JWTAuthenticationFilter(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication((usernamePasswordAuthenticationToken));

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        if (token != null) {
            // parse the token.
            String username = Jwts.parser()
                .setSigningKey("MyJwtSecret")
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody()
                .getSubject();

            CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(username);

            if (username != null) {
                return new UsernamePasswordAuthenticationToken(customUserDetails.getUsername(), null, customUserDetails.getAuthorities());
            }
            return null;
        }
        return null;
    }

}
