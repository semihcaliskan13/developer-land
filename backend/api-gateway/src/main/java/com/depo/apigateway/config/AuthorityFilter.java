package com.depo.apigateway.config;

import com.depo.apigateway.dto.response.codebase.CodebaseGetByIdResponse;
import com.depo.apigateway.service.CodebaseService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthorityFilter extends OncePerRequestFilter {

    private final CodebaseService codebaseService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        String method = request.getMethod();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && path.contains("codebases/")) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            if (path.split("/").length - 1 > 3) {
                int startIndex = path.indexOf("codebases/") + "codebases/".length();
                int endIndex;
                if (path.contains("?")){
                    endIndex = path.indexOf("?", startIndex);
                }
                else {
                    endIndex = path.indexOf("/",startIndex);
                }

                if (endIndex == -1) {
                    endIndex = path.length();
                }
                String id = path.substring(startIndex, endIndex);
                CodebaseGetByIdResponse codebase = codebaseService.getCodebaseById(id);
                String authority = codebase.getName().toUpperCase() + "." + method;
                boolean authorized = userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .anyMatch(auth -> auth.contains(authority) || auth.contains("SA"));
                if (authorized) {
                    filterChain.doFilter(request, response);
                }
                else {
                    response.setStatus(403);
                }
            }

        } else {
            filterChain.doFilter(request, response);
        }

    }
}
