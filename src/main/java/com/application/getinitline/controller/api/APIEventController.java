package com.application.getinitline.controller.api;

import com.application.getinitline.constant.ErrorCode;
import com.application.getinitline.dto.APIErrorResponse;
import com.application.getinitline.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.application.getinitline.controller.api
 * fileName       : APIEventController
 * author         : NAHAEJUN
 * date           : 2025-03-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-03        NAHAEJUN              최초생성
 */
@RequestMapping("/api")
@RestController
public class APIEventController {
    @GetMapping("/events")
    public List<String> getEvents(){
        return List.of("event1","event2");
    }

    @PostMapping("/events")
    public Boolean createEvent(){
        return true;
    }

    @GetMapping("/events/{eventId}")
    public String getEvent(@PathVariable Integer eventId) {
        return "event "+ eventId;
    }

    @PutMapping
    public Boolean modifyEvent(@PathVariable Integer eventId) {
        return true;
    }

    @DeleteMapping("/events/{eventId}")
    public Boolean removeEvent(@PathVariable Integer eventId) {
        return true;
    }

    // 범위는 요 APIEventController 클래스 안에 정의된 핸들러 메소드들이
    // GeneralException이 터졌을경우 이곳으로 오게된다. (APIEventController클래스 범위 한정)
//    @ExceptionHandler
//    public ResponseEntity<APIErrorResponse> general(GeneralException e){
//        ErrorCode errorCode = e.getErrorCode();
//        HttpStatus status = errorCode.isClientSideError() ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;
//        return ResponseEntity.status(status).body(
//                APIErrorResponse.of(false,errorCode,errorCode.getMessage(e))
//        );
//    }
}
