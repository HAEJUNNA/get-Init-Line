package com.application.getinitline.controller.api;

import com.application.getinitline.constant.PlaceType;
import com.application.getinitline.dto.APIDataResponse;
import com.application.getinitline.dto.PlaceDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.application.getinitline.controller.api
 * fileName       : APIPlaceController
 * author         : NAHAEJUN
 * date           : 2025-02-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-22        NAHAEJUN              최초생성
 */
@RequestMapping("/api")
@RestController
public class APIPlaceController {

    //헨들러 메소드.... - SpringWeb에서 사용자의 요청을 받아 응답을 리턴하는 메소드
    // @RequestMapping 필터의 역할을 한다.
//    @RequestMapping(
//            name = "IamNma", // 뷰템플릿에서 해당 메서드를 부를수 있는 이름
//            //name ="" 을 빈값으로 두면 getplaces() 메소드 기준 "AC#getplaces" 가 자동으로 기본이름이된다.
//            value = "/uriPATH", // 내 URI , ENDPOINT 경로
//            path = "/places", // value와 동일
//            method = RequestMethod.GET, // Http method 어떤방식으로할지
//            params = "test=true", // GET방식으로 요청올때, query-Param에서 test=true 파라미터가 있는 요청만 받겟다. (파라미터 검사)
//            headers = "header-auth=stupidPassword", // header-auth 헤더값 있는 요청만.(헤더 검사)
//            consumes = MediaType.APPLICATION_JSON_VALUE, // json으로 데이터 주는 요청만 (헤더의 Content-Type 검사)
//            // json으로 데이터 받을수있니? (헤더의 Accept 검사), 서버가 클라이언트에게 묻는거다.
//            // 받을수 있다고 , 받겠다고 해당 값을 헤더에 담아 넘기면 서버는 그 형태로 리턴값을 던져주는거다.
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
    @GetMapping("/places") //맵핑정보
    public List<String> getplaces() { // 요청
        /*
        * 응답 요청으로 @ResponseBody , ResponseEntity는 정확히 동일한 동작을 한다.
        * ResponseEntity 를 return타입으로 정했을때는 responseBody를 명시하지 않아도 된다.
        * */
        return List.of("placel","place2"); // 응답
    }
    // APIPlaceControllerTest 테스트용
    @GetMapping("/placesTest")
    public APIDataResponse<List<PlaceDto>> getTestPlaces() {

        return APIDataResponse.of(List.of(PlaceDto.of(
                PlaceType.COMMON,
                "랄라배드민턴장",
                "서울시 강남구 강남대로",
                "010-1234-5678",
                30,
                "신장 개업"
        )));
    }

    @PostMapping("/places")
    public Boolean createPlaces(){
        return true;
    }

    @GetMapping("/places/{placesId}")
    public String getPlaces(@PathVariable String placesId){
        return "places " + placesId;
    }
    // 단일 , 주소 테스트
    @GetMapping("/placeTest/{placesId}")
    public APIDataResponse<PlaceDto> getTestPlace(@PathVariable String placesId){

        if (placesId.equals(2)) {
            return APIDataResponse.of(null);
        }

        return APIDataResponse.of( PlaceDto.of(
                PlaceType.COMMON,
                "랄라배드민턴장",
                "서울시 강남구 강남대로",
                "010-1234-5678",
                30,
                "신장 개업"
        ));
    }

    @PutMapping("/places/{placeId}")
    public Boolean modifyPlace(@PathVariable String placeId){
        return true;
    }

    @DeleteMapping("/places/{placeId}")
    public Boolean deletePlace(@PathVariable String placeId){
        return true;
    }

}
