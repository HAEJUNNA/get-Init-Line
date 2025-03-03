package com.application.getinitline.controller.error;

import com.application.getinitline.constant.ErrorCode;
import com.application.getinitline.dto.APIErrorResponse;
import com.application.getinitline.exception.GeneralException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * packageName    : com.application.getinitline.controller.error
 * fileName       : BaseExceptionHandler
 * author         : NAHAEJUN
 * date           : 2025-03-03
 * description    :

 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-03        NAHAEJUN              최초생성
 */
@RestControllerAdvice(annotations = RestController.class)
public class APIExceptionHandler {
    @ExceptionHandler
    // 범위는 요 APIEventController 클래스 안에 정의된 핸들러 메소드들이
    // GeneralException이 터졌을경우 이곳으로 오게된다. (APIEventController클래스 범위 한정)
    public ResponseEntity<APIErrorResponse> general(GeneralException e){
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.isClientSideError() ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(
                APIErrorResponse.of(false,errorCode,errorCode.getMessage(e))
        );
    }
    // 우리가 알지못하는 에러들을 다루기 위한 메서드
    // 범위는 요 APIEventController 클래스 안에 정의된 핸들러 메소드들이
    // GeneralException이 터졌을경우 이곳으로 오게된다. (APIEventController클래스 범위 한정)
    @ExceptionHandler
    public ResponseEntity<APIErrorResponse> exception(Exception e){
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(status)
                .body(APIErrorResponse.of(false,errorCode,errorCode.getMessage(e))
        );
    }
}
