package com.siman.assestment.common.logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.siman.assestment.repository.SearchLogRepository;
import com.siman.assestment.repository.entity.SearchLog;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoggingApiDB {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${api.filter.excluded.endpoints}")
    private String excludedEndpoints;

    @Value("${api.filter.allowed.endpoints}")
    private String allowedEndpoints;

    private final SearchLogRepository loggingRepository;

    public SearchLog insertRequestLog(HttpServletRequest request, String username) {
        String requestURI = request.getRequestURI();
        if (!isExcludedEndpoint(requestURI)) {
        	SearchLog searchLog = createSearchLog(request, username);
        	loggingRepository.save(searchLog);
            return searchLog;
        }
        return null;
    }

    public void updateResponseLog(SearchLog logEntity, HttpServletResponse response, String username) {
        if (logEntity != null) {
            LocalDateTime responseDate = LocalDateTime.now(ZoneOffset.UTC);
            logEntity.setResponseDate(responseDate);
            logEntity.setStatus(String.valueOf(response.getStatus()));

            if(logEntity.getUsername()==null)
            	logEntity.setUsername(username);
            loggingRepository.save(logEntity);
        }
    }

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

    private SearchLog createSearchLog(HttpServletRequest request, String username) {
    	LocalDateTime requestDate = LocalDateTime.now(ZoneOffset.UTC);
    	SearchLog logEntity = SearchLog.builder()
    			.username(username)
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
    	
    	return logEntity;
    }
}
