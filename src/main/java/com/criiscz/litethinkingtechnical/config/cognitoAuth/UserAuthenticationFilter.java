package com.criiscz.litethinkingtechnical.config.cognitoAuth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.Writer;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
// Here we are for sure, context object holds a valid and decoded JWT token.
        SecurityContext context = SecurityContextHolder.getContext();
        try {
            if (context.getAuthentication() instanceof JwtAuthenticationToken) {
                JwtAuthenticationToken auth = (JwtAuthenticationToken) context.getAuthentication();
                String cognitoSub = auth.getName();
            }
        } catch (UsernameNotFoundException ex) {
//            log.error("Encountered error while finding user with current authentication token", ex);
            sendUnAuthorized(response, ex);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void sendUnAuthorized(HttpServletResponse response, UsernameNotFoundException ex) {
        response.setStatus(HttpStatus.FORBIDDEN.value());

        if (ex != null) {
            try (Writer writer = response.getWriter()) {
                writer.write(ex.getMessage());
            } catch (IOException ioEx) {
                throw new RuntimeException(ioEx);
            }
        }
    }
}
