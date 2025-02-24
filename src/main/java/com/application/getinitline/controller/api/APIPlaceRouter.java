package com.application.getinitline.controller.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

import static org.springframework.web.servlet.function.RequestPredicates.*;
import static org.springframework.web.servlet.function.RouterFunctions.*;

/**
 * packageName    : com.application.getinitline.controller.api
 * fileName       : APIPlaceRouter
 * author         : NAHAEJUN
 * date           : 2025-02-22
 * description    :
 *  함수형 엔드포인트 작성
 *  1. WebNvc.fn
 *  2. routing, request Handling
 *  3. 불변성을 고려하여 설제,
 *  4. 기존  DispactherServlert 위에서 동작
 *  5. 어노테이션 스타일과 함께 사용가능
 *
 *  주요키워드 : 클래스
 *      ● HandlerFunction (인터페이스) == @RequestMapping
 *        - 입력 : ServerRequest
 *        - 출력 : ServerResponse
 *      ● RouterFunction (인터페이스) == @RequestMapping
 *        - 입력 : ServerRequest
 *        - 출력 : Optional<HandlerFunction>
 *
 *  둘은 @RequestMapping 의 대응 된다면 둘의 차이는 뭐지?
 *  HandlerFunction 의 결과 : data (data의 집중한 function 이다.)
 *  RouterFunction 의 결과 :  data + behavior (행위) (ex : url mapping)
 *  RouterFunction 의 경우는 결과를 데이터 뿐 아니라 행위도 같이 돌려준다.
 *  HandlerFunction 을 RouterFunction 안에 넣고 HandlerFunction 에서 비즈니스
 *  로직을 태워서 데이터를 가지고 와주면 , RouterFunction 은 그걸 사용자의 요청과
 *  맵핑해서 자용자에게 돌려주는 방식으로 작동을 한다.
 *  - 기타 세부 키워드
 *  1. RequestPredicates
 *  2. RequestFunction.route().nest()
 *  3. RequestFunction.route().before()
 *  4. RequestFunction.route().after()
 *  5. RequestFunction.Builder.onError()
 *  6. RequestFunction.Builder.filter()
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-22        NAHAEJUN              최초생성
 */
@Configuration
public class APIPlaceRouter {
    /*
     * RouterFunction 은 우선 configuration 으로 등록해서 사용해야 한다.
     * @Controller 라는 어노테이션을 사용하지 않고,configuration을 이용해서 사용한다.
     *
     *
     * configuration클래스에 bean을 사용시 bean으로 등록된 메소드의 입력인자(매개변수)는 전부 bean이여야 한다.
     *
     */
    @Bean
    public RouterFunction<ServerResponse> placeRouter(APIPlaceHandler handler){
        //임포트 시킨후, 해당 클래스에가서 alt +엔터 치면 스태틱으로 등록가능
        /*
        *  GET("/api/places",req -> ServerResponse.ok().body(List.of("placel","place2"))) 요 한줄이
        * 
        *  //아래메서드를 나탄냄
        *  @GetMapping("/places")
        *  public List<String> getplaces() {return List.of("placel","place2");}
        *
        * // nest()를 이용해 한번더 함수형태로 묶는데, 이경우가 컨트롤러 전체에 RequestMapping 을
        * // 전체적으로 생성할때 쓴다 ex) @RequestMapping("/api/places") 요 어노테이션 방식을 아래와 함께 사용
        *    nest(path("/api/places") , buider -> builder.GET(..).POST(..).build();
        * 함수형 방식에서는 경로가 없다해서 /(root패스)를 그냥 넣어주면 오류가 발생한다. 즉필용벗는 패스는 제거한다.
        * */
//        return route().nest(path("/api/places") , builder -> builder
//                        .GET("",req -> ServerResponse.ok().body(List.of("placel","place2")))
//                .POST("",req -> ServerResponse.ok().body(true))
//                .GET("/{placesId}",req -> ServerResponse.ok().body("places " + req.pathVariable("placesId")))
//                .PUT("/{placesId}",req -> ServerResponse.ok().body(true))
//                .DELETE("/{placesId}",req -> ServerResponse.ok().body(true)))
//                .build();
        /* FunctionHandler를 이용한 분리 -> APIPlaceHandler클래스 참조 */
        return route().nest(path("/api/places") , builder -> builder
                        .GET("",handler::getPlaces)
                .POST("",handler::createPlaces)
                .GET("/{placesId}",handler::getPlace)
                .PUT("/{placesId}",handler::modifyPlace)
                .DELETE("/{placesId}",handler::removePlace))
                .build();

    }
}
