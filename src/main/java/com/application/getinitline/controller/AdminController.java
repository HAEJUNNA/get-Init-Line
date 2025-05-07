package com.application.getinitline.controller;

import com.application.getinitline.constant.PlaceType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    // RequestMapping
    // 클래스의 대문자 를 따서, 메소드 이름을 붙이면 이게 기본 name을 작성하지 않았을떄 기본값이
    // AC#adminPlaces , 따로 지정없을시 이렇게 기본값으로 셋팅된다.
    // name옵션은 뷰템플릿에서 식별할때 사용한다.
    // params : 파라미터 검사 ex) test=true ,test라는 쿼리 파라미터가 존재하고 true라는 값을 가질때만 허용
    // headers : 헤더검사 ex) header-auth=stupidPassword , 해당 헤더 정보를 넣을대만 허용
    // consumes : 헤더의 content-type 검사, ex) consumes = MediaType.APPLICATION_JSON_VALUE =>json으로만 주는 요청만 허용
    // produces = 헤더의 Accepth 검사 ex) produces = MediaType.APPLICATION_JSON_VALUE => json으로 데이터 받을수 있는지 여부체크

    @GetMapping(value = "/places")
    public ModelAndView adminPlaces(@RequestParam(required = false) PlaceType placeType, String placeName , String address) { // TODO - 추후 인증 작업 추가
        // 컨버터 개념
        // 예를들어 Enum 타입으로 내가 아는 타입형태로 클래스를 만들었는데,
        // 파라미터 타입을 해당 클래스를 했을때 유저가 어떻게알고? 맞춰서 응답 받는가? (심지어 스프링도 enum타입은 내가 정의하였기때문에 그걸 모를텐데;)
        // Enum.name을 토대로 우선은 직렬화 비직렬화 시도를 하고 컨버터가 동작할때 오토컨픽으로 잡혀있는 jackson이 동작한다.

        /*
        * 요청으로 @RequestParam 이 있는것과 생략하는거에 동작방식차이
        * 기본적으로 @RequestParam 을 매개변수에서 생략해도 queryParam 은 받을수 있다.
        * collection data가 아닌 단일 객체의 경우는 request Parameter로 collection data ,map,
        * 단, 동작방식이 달라진다.
        * @RequestParam 경우 속성값으로 required 속성이 있는데 default 는 true 이다.(옵셔널 체크 여부다.)
        * @RequestParam은 기본값이 true, 생략할경우는 false 가 기본값이다.
        * 기술하고 싶을경우 예시 ) @RequestParam(required = false) PlaceType placeType
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


    @PostMapping(value = "/events/exception")
    public String exceptionDetail() throws HttpRequestMethodNotSupportedException {
        throw new HttpRequestMethodNotSupportedException("런타임 예외 테스트");
    }

}
