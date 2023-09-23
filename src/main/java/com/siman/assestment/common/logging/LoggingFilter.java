package com.siman.assestment.common.logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.siman.assestment.repository.SearchLogRepository;
import com.siman.assestment.repository.entity.SearchLog;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoggingFilter extends OncePerRequestFilter {

    @Value("${spring.application.name}")
    private String appName;
    
    @Value("${api.filter.excluded.endpoints}")
    private String excludedEndpoints;
    
    @Value("${api.filter.allowed.endpoints}")
    private String allowedEndpoints;
    
    private final SearchLogRepository searchLogRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        LocalDateTime requestDate = LocalDateTime.now(ZoneOffset.UTC);

        String requestURI = request.getRequestURI();
        
        if (isExcludedEndpoint(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (isAllowedEndpoint(requestURI)) {
        	SearchLog search = SearchLog.builder()
        			.username(getUser())
        			.requestDate(requestDate)
        			.responseDate(null)
        			.appName(appName)
        			.remoteHost(request.getRemoteAddr())
        			.remotePort(String.valueOf(request.getRemotePort()))
        			.httpMethod(request.getMethod())
        			.url(request.getRequestURL().toString())
        			.uri(request.getRequestURI())
        			.request(getRequestBody(request))
        			.response(null)
        			.token(getAuthToken(request))
        			.build();

            searchLogRepository.save(search);

            try {
                filterChain.doFilter(request, response);
            } finally {
                LocalDateTime responseDate = LocalDateTime.now(ZoneOffset.UTC);
                search.setResponseDate(responseDate);
                search.setStatus(Integer.toString(response.getStatus()));
                
                search.setUsername(getUser());
                
                searchLogRepository.save(search);
            }
        } else {
           
            filterChain.doFilter(request, response);
        }
    }
    
    /*
    private boolean issAllowedEndpoint(String requestURI) {
        String[] patterns = allowedEndpoints.split(",");
        for (String pattern : patterns) {
        	// Reemplazar "**" con ".*" para que funcione como un comodín en una expresión regular
        	String regexPattern = pattern.replace("**", ".*");
        	// Agregar "^" al principio y "$" al final para asegurarse de que coincida con toda la cadena
        	regexPattern = "^" + regexPattern + "$";
           if (requestURI.matches(regexPattern)){
        	   return true;
           }
        }
        return false;
    }
    */
    
    private boolean isExcludedEndpoint(String requestURI) {
        return Arrays.stream(excludedEndpoints.split(","))
                .anyMatch(pattern -> requestURI.matches(toRegexPattern(pattern)));
    }
    
    private boolean isAllowedEndpoint(String requestURI) {
        return Arrays.stream(allowedEndpoints.split(","))
                .anyMatch(pattern -> requestURI.matches(toRegexPattern(pattern)));
    }

    private String toRegexPattern(String pattern) {
        pattern = pattern.replace("**", ".*");
        return "^" + pattern + "$";
    }

    private String getRequestBody(HttpServletRequest request) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString().isEmpty() ? "NO-REQUEST-BODY" : sb.toString();
        } catch (IOException e) {
            return "NO-REQUEST-BODY";
        }
    }

    private String getAuthToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
    
    private String getUser() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication != null && authentication.isAuthenticated()) ? 
        		authentication.getName() : "NO-AUTH";
    }
}