package com.application.getinitline.controller.error;

import com.application.getinitline.constant.ErrorCode;
import com.application.getinitline.dto.APIErrorResponse;
import com.application.getinitline.exception.GeneralException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
// 우리가 흔히 하는 예외 (uncheckedException 말고 SpringBoot에서 발생하는 예외를 처리해줘야한다.
// 그걸 간단하게 처리해줄수 있는게 ResponseEntityExceptionHandler 이다.
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
    // RestController 한정 - GeneralException 터지면 이곳으로 옴
//    @ExceptionHandler
//    public ResponseEntity<APIErrorResponse> general(GeneralException e){
//        ErrorCode errorCode = e.getErrorCode();
//        HttpStatus status = errorCode.isClientSideError() ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;
//        return ResponseEntity.status(status).body(
//                APIErrorResponse.of(false,errorCode,errorCode.getMessage(e))
//        );
//    }
    // 이미 상속받아서 안쓰기는 아깝고, 해당 메서드에서도 500에러가 발생할수 있으니 통일성을 위해 같이 처리하는방식으로 진행
    @ExceptionHandler
    public ResponseEntity<Object> general(GeneralException e , WebRequest req){
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.isClientSideError() ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(e // 이렇게 재정의함으로서ResponseEntityExceptionHandler 의 모든메서드는 영향을 받는다.
                , APIErrorResponse.of(false,errorCode.getCode(),errorCode.getMessage(e))
                , HttpHeaders.EMPTY
                , status
                , req);
    }


    @ExceptionHandler
    public ResponseEntity<Object> general(ConstraintViolationException e , WebRequest req){
        ErrorCode errorCode =ErrorCode.VALIDATION_ERROR;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return super.handleExceptionInternal(
                e // 이렇게 재정의함으로서ResponseEntityExceptionHandler 의 모든메서드는 영향을 받는다.
                , APIErrorResponse.of(false,errorCode.getCode(),errorCode.getMessage(e))
                , HttpHeaders.EMPTY
                , status
                , req);
    }


    // 우리가 알지못하는 에러들을 다루기 위한 메서드
    // RestController 한정
    // Exception 터졌을경우 이곳으로 오게된다.
//    @ExceptionHandler
//    public ResponseEntity<APIErrorResponse> exception(Exception e){
//        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
//        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//        return ResponseEntity
//                .status(status)
//                .body(APIErrorResponse.of(false,errorCode,errorCode.getMessage(e))
//        );
//    }
    @ExceptionHandler
    public ResponseEntity<Object> exception(Exception e ,WebRequest req){
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(e // 이렇게 재정의함으로서ResponseEntityExceptionHandler 의 모든메서드는 영향을 받는다.
                , APIErrorResponse.of(false,errorCode.getCode(),errorCode.getMessage(e))
                , HttpHeaders.EMPTY
                , status
                , req);
    }
    /*
    * SpringWep 예외를 처리할수있는 방법은 , 2가지다.
    * ResponseEntityExceptionHandler클래스의 protected 메서드를 오버라이딩 하거나,
    * (protected 접근제어자라는건 이미 외부에서 접근하라는 일종의 계약을 한거나 다름없다.)
    * Internal 메서드 하나를 오버라이드 하거나,
    * Internal 메서드 하나를 오버라이드 하는게 좀더 효율적이며, ResponseEntityExceptionHandler 클래스 구조를 보면
    * 모든 메서드의 body는 전부 null로 넣어진다. 그렇기때문에 우리가 만든 API 형태로 응답하기 위해 따로 body를 넣어주는 작업을 한다.
    * */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        // 똑같이 동작하되, body만 넣어줌 super.handleExceptionInternal 이걸 호출
        ErrorCode errorCode = statusCode.is4xxClientError() ? ErrorCode.SPRING_BAD_REQUEST : ErrorCode.SPRING_INTERNAL_ERROR;
        /*
        * 	if (statusCode.equals(HttpStatus.INTERNAL_SERVER_ERROR) && body == null) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);}
        *  요 기능을 사용하기 위해 handleExceptionInternal 호출
        *
        * 스프링 웹에서 예외발생시 ResponseEntityExceptionHandler의 모든 handleExceptionInternal()메서드는 이제 여기서 새로
        * 오버라이드 한 handleExceptionInternal을 타게된다. 그래서 없던 커스텀한 body를 가지게된다.
        * */
        return handleExceptionInternal(ex // 이렇게 재정의함으로서ResponseEntityExceptionHandler 의 모든메서드는 영향을 받는다.
                , APIErrorResponse.of(false,errorCode.getCode(),errorCode.getMessage(ex))
                , headers
                , statusCode
                , request);
    }
}
