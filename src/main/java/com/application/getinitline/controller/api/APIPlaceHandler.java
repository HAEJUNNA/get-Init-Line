package com.application.getinitline.controller.api;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.function.ServerResponse.*;

/**
 * packageName    : com.application.getinitline.controller.api
 * fileName       : APIPlaceHandler
 * author         : NAHAEJUN
 * date           : 2025-02-25
 * description    :
 * HandlerFunction은 빈으로 등록하여 사용한다.
 * Handler를 전부 router로직 (APIPlaceRouter클래스)에서 분리
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-25        NAHAEJUN              최초생성
 */
@Component
public class APIPlaceHandler {
    public ServerResponse getPlaces(ServerRequest request) throws Exception {
        return ok().body(List.of("placel","place2"));
    }
    public ServerResponse createPlaces(ServerRequest request) throws Exception {
        // Https.ok() 말고 Https.creat()를 사용. creat는 반드시 URI 인자값을 넣어줘야한다.
        return created(URI.create("/api/palces/1")).body(true); //TODO
    }
    public ServerResponse getPlace(ServerRequest req) throws Exception {
        return ok().body("places " + req.pathVariable("placesId"));
    }
    public ServerResponse modifyPlace(ServerRequest request) throws Exception {
        return ok().body(true);
    }
    public ServerResponse removePlace(ServerRequest request) throws Exception {
        return ok().body(true);
    }
}
