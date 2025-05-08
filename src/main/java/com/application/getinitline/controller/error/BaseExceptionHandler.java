package com.application.getinitline.controller.error;

import com.application.getinitline.constant.ErrorCode;
import com.application.getinitline.dto.APIErrorResponse;
import com.application.getinitline.exception.GeneralException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * packageName    : com.application.getinitline.controller.error
 * fileName       : BaseExceptionHandler
 * author         : NAHAEJUN
 * date           : 2025-03-03
 * description    :
 *  produces = MediaType.TEXT_HTML_VALUE를 위한 에러 Exception 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-03        NAHAEJUN              최초생성
 */
@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler
    public ModelAndView general(GeneralException e,HttpServletResponse res) {
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.isClientSideError() ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;


        return new ModelAndView("error"
                , Map.of (
                "statusCode" ,status.value() ,
                "errorCode", errorCode,
                "message",errorCode.getMessage(status.getReasonPhrase()) // getReasonPhrase() 일어난 이유를 담고있음
        ),status);
    }
    // 우리가 알지못하는 에러들을 다루기 위한 메서드
    @ExceptionHandler
    public ModelAndView general(Exception e) {
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;


        return new ModelAndView("error"
                , Map.of (
                "statusCode" ,status.value() ,
                "errorCode", errorCode,
                "message",errorCode.getMessage(status.getReasonPhrase()) // getReasonPhrase() 일어난 이유를 담고있음
        ),status);
    }
}
