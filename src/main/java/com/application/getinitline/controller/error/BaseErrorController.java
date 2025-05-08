package com.application.getinitline.controller.error;

import com.application.getinitline.constant.ErrorCode;
import com.application.getinitline.dto.APIErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.Banner;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * packageName    : com.application.getinitline.controller.error
 * fileName       : BaseErrorController
 * author         : NAHAEJUN
 * date           : 2025-03-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-03        NAHAEJUN              최초생성
 */
@Controller
public class BaseErrorController implements ErrorController {

    // 에러페이지 매핑
    // produces = MediaType.TEXT_HTML_VALUE 헤더 Accept 형태가 TEXT_HTML 향테로 받겠다고 지정했을경우
    @RequestMapping(value = "/error",produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHTML(HttpServletResponse res) {
        /*
        * HttpServletResponse -> status코드가 존재한다.
        * */
        HttpStatus status = HttpStatus.valueOf(res.getStatus());
        /*
        * is4xxClientError -> 4xx 에러일경우
        * */
        ErrorCode errorCode = status.is4xxClientError() ? ErrorCode.BAD_REQUEST : ErrorCode.INTERNAL_ERROR;


        return new ModelAndView("error"
                , Map.of (
                "statusCode" ,status.value() ,
                "errorCode", errorCode,
                "message",errorCode.getMessage(status.getReasonPhrase()) // getReasonPhrase() 일어난 이유를 담고있음
        ),status);
    }
    // API용, JSON형태로 보여주기위한 에러페이지
    @RequestMapping("/error")
    public ResponseEntity<APIErrorResponse> error(HttpServletResponse res) {
        /*
         * HttpServletResponse -> status코드가 존재한다.
         * */
        HttpStatus status = HttpStatus.valueOf(res.getStatus());
        /*
         * is4xxClientError -> 4xx 에러일경우
         * */
        ErrorCode errorCode = status.is4xxClientError() ? ErrorCode.BAD_REQUEST : ErrorCode.INTERNAL_ERROR;


        return ResponseEntity.status(status).body(APIErrorResponse.of(false,errorCode));
    }

}
