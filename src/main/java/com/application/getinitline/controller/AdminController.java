package com.application.getinitline.controller;

import com.application.getinitline.constant.PlaceType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.application.getinitline.controller
 * fileName       : AdminController
 * author         : NAHAEJUN
 * date           : 2025-02-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-22        NAHAEJUN              최초생성
 */
@RequestMapping(value = "/admin")
@Controller
public class AdminController {

    @GetMapping("/places")
    public ModelAndView adminPlaces(PlaceType placeType, String placeName , String address) { // TODO - 추후 인증 작업 추가
        // 컨버터 개념
        // 예를들어 Enum 타입으로 내가 아는 타입형태로 클래스를 만들었는데,
        // 파라미터 타입을 해당 클래슬 했을때 유저가 어떻게알고? 맞춰서 응답 받는가?
        // Enum.name을 토대로 우선은 직렬화 비직렬화 시도를 하고 컨버터가 동작할때 오토컨픽으로 잡혀있는 jackson이 동작한다.

        /*
        * 요청으로 @RequestParam 이 있는것과 생략하는거에 동작방식차이
        * 기본적으로 @RequestParam 을 매개변수에서 생략해도 queryParam 은 받을수 있다.
        * 단, 동작방식이 달라진다.
        * @RequestParam 경우 속성값으로 required 속성이 있는데 default 는 true 이다.(옵셔널 체크 여부다.)
        * @RequestParam은 기본값이 true, 생략할경우는 false 가 기본값이다.
        *
        * */
        Map<String, Object> map = new HashMap<>();
        map.put("placeType", placeType);
        map.put("placeName", placeName);
        map.put("address", address);

        return new ModelAndView("admin/place",map);
    }
    @GetMapping("/places/{placeId}")
    public String adminPlaceDetail(@PathVariable Integer id) {
        return "admin/place-detail";
    }
    @GetMapping(value = "/events")
    public String adminEvents() {return "admin/events";}

    @GetMapping(value = "/events/{eventId}")
    public String adminEventsDetail(@PathVariable Integer id) {return "admin/event-detail";}

}
