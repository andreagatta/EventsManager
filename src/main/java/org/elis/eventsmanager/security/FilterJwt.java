package org.elis.eventsmanager.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.elis.eventsmanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class FilterJwt extends OncePerRequestFilter {

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authCode = httpServletRequest.getHeader(("Authorization"));
        if(authCode!=null&&authCode.startsWith("Bearer")){
            String token = authCode.substring(7);
            User user = tokenUtil.getUserFromToken(token);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           /* String ruolo = tokenUtil.getRole(token);
            httpServletResponse.addHeader("ruolo", ruolo);*/
            System.out.println(httpServletRequest.getContextPath());
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
