package com.ntech.board.utils.encrypt;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 비밀번호 암호화 클래스
@Component
public class SHA256 {
    public String encrypt(String password){
        MessageDigest md;
        String result = null;

        {
            try {
                md = MessageDigest.getInstance("SHA-256");
                byte[] hashBytes = md.digest(password.getBytes());

                // 암호화된 바이트 배열 16진수 문자열로 반환
                StringBuilder sb = new StringBuilder();
                for(byte b : hashBytes)
                    sb.append(String.format("%02x", b));

                result = sb.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
