package com.ntech.board.config.advice;

import com.ntech.board.config.response.BaseException;
import com.ntech.board.config.response.BaseResponse;
import com.ntech.board.config.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(BaseException.class)
    public BaseResponse<BaseResponseStatus> baseException(BaseException e) {
        log.error("Handle CommonException: {}", e.getMessage());
        return new BaseResponse<>(e.getStatus());
    }
}
