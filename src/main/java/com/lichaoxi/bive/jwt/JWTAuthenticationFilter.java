package com.lichaoxi.bive.jwt;

import com.lichaoxi.bive.security.CustomUserDetails;
import com.lichaoxi.bive.security.CustomUserDetailsService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }


        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
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

            if (username != null) {
                return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }

}
