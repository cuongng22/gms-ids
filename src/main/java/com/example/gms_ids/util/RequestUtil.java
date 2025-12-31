package com.example.gms_ids.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtils.hasText(ip) && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip != null ? ip : "unknown";
    }

    public static String getUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return userAgent != null ? userAgent : "";
    }

    public static String getUserId(HttpServletRequest request) {
        String userId = request.getHeader("X-User-Id");
        if (!StringUtils.hasText(userId)) {
            userId = request.getHeader("User-Id");
        }
        return userId != null ? userId : "SYSTEM";
    }

    public static String getUserName(HttpServletRequest request) {
        String userName = request.getHeader("X-User-Name");
        if (!StringUtils.hasText(userName)) {
            userName = request.getHeader("User-Name");
        }
        return userName != null ? userName : "SYSTEM";
    }

    public static String getQueryParamsAsString(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            params.put(paramName, paramValue);
        }
        try {
            return params.isEmpty() ? "" : objectMapper.writeValueAsString(params);
        } catch (Exception e) {
            return params.toString();
        }
    }

    public static String getRequestBodyAsString(Object requestBody) {
        if (requestBody == null) {
            return "";
        }
        try {
            return objectMapper.writeValueAsString(requestBody);
        } catch (Exception e) {
            return requestBody.toString();
        }
    }
}



