package com.ntech.board.utils.ip;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

public class IPUtils {
    // HttpServletReqeust의 IP를 가져오는 메서드 (클라이언트 IP 얻는 메서드)
    public static String getClientIpAddress(HttpServletRequest request){
        // 프록시 서버, 로드 밸런서를 통해 요청이 전달되었을 때를 고려해 X-Forwarded-For 헤더 먼저 탐색
        String ipAddress = request.getHeader("X-Forwarded-For");

        // X-Forwarded-For 헤더가 존재하지 않거나 비어있는 경우, 직접 추출
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr(); // 마지막 수단으로 클라이언트의 IP 주소 가져오기
        }

        // 만약 여러 IP 주소가 콤마로 구분되어 있을 경우, 첫 번째 IP를 추출 (보통 실제 클라이언트 IP가 첫 번째로 오게 됨)
        int commaIndex = ipAddress.indexOf(',');
        if (commaIndex != -1) {
            ipAddress = ipAddress.substring(0, commaIndex);
        }

        return ipAddress;
    }
}
