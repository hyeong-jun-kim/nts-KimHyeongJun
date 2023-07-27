package com.ntech.board.config.response;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    /**
     * 2000 : Request 오류
     */
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    PASSWORD_LENGTH_ERROR(false, 2001, "비밀번호 길이가 올바르지 않습니다."),
    WRITER_LENGTH_ERROR(false, 2002, "작성자의 길이가 올바르지 않습니다."),
    TITLE_LENGTH_ERROR(false, 2003, "제목의 길이가 올바르지 않습니다."),
    CONTENT_LENGTH_ERROR(false, 2004, "게시글 본문의 길이가 올바르지 않습니다."),
    NOT_EXIST_POST(false, 2005, "존재하지 않는 게시글입니다."),

    /**
     * 3000 : Response 오류
     */


    /**
     * 4000 : Database, Server 오류
     */
    INTERNAL_SERVER_ERROR(false,4000,"서버 오류입니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message){
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
