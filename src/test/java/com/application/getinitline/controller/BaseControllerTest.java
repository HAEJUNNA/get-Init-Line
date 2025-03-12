package com.application.getinitline.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * packageName    : com.application.getinitline.controller
 * fileName       : BaseControllerTest
 * author         : NAHAEJUN
 * date           : 2025-03-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-09        NAHAEJUN              최초생성
 */
// TestConstructor.AutowireMode.ALL 사용시
// 생성자에 있는 모든 메서드 파라미터는 전부 다 Spring 컨테이너가 주도권을 갖는다.
// 하지만 내가 의도하지도 않은 것도 스프링 컨테이너의 위임되기때문에 , 다른 파라미터 리졸브 피처를 쓸수없다.
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureMockMvc// 설정시 MockMvc를 AutoWired로 바로 주입 받을수있다.
// WebEnvironment.NONE (서버 필요x) , WebEnvironment.MOCK(서버필요) , 진짜환경 WebEnvironment.DFINED_PORT,RANDOM_PORT
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE) // @ExtendWith 이 추가되어 실제로 생성자 주입이 가능해짐
class BaseControllerTest {

//    @Autowired
//    private MockMvc mvc;

    /*
    * 생성자 주입 방식 Junuit5부터 생김
    * 5 이전까지는 생성자주입이 없이 직접 주입 해서 사용또는, 매개변수로 입력받아
    * 입력 인자를 통해서만 참조가 가능햇음.
    * 5부터는 ParameterResolver 틀 통해서 가능
    * @SpringBootTest -> @ExtendWith({SpringExtension.class}) 통해서 가능
    * */
    
    private final MockMvc mockMvc;

    // 입력인자, 즉 주입받고자 하는 매개변수에 @Autowired를 꼭 붙여야 한다.
    public BaseControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    /**
     * packageName    : com.application.getinitline.controller
     * fileName       : BaseControllerTest
     * author         : NAHAEJUN
     * date           : 2025-03-09
     * description    :
     *  GetMapping 으로 "/" root 경로로 올때 정상작동을 하는지 체크
     * ===========================================================
     * DATE              AUTHOR             NOTE
     * -----------------------------------------------------------
     * 2025-03-09        NAHAEJUN              최초생성
     */
    @DisplayName("[view][GET] 기본 페이지 요청")
    @Test
    // 필드주입 받을때 입력인자, 즉 주입받고자 하는 매개변수에 @Autowired를 꼭 붙여야 한다
//    void testRoot(@Autowired MockMvc mvc) throws Exception { // @Autowired 꼭 붙여야함
    void testRoot() throws Exception {
        // Given

        // When
        ResultActions resultActions = mockMvc.perform(get("/"));

        // Then
        resultActions.andExpect(status().isOk()) //httpStatus검사를 해준다
//                .andExpect(content().contentType(MediaType.TEXT_HTML)) //contentType검사
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) // contentTypeCompatibleWith 은 해당 타입 하나라도 속하면
                .andExpect(content().string(containsString("this page!"))) //내용 body를 검사한다. string()을 쓸경우 완벽히 일치해야한다
                .andExpect(view().name("index")) // 맵핑 테스트, 즉 view 네임
                .andDo(print()); // 잃반적으로 에러발생해야지만 출력하는데 , andDo쓸경우 에러, 성공 상관없이 출력
        // When &Then
//        mvc.perform(get("/")).andExpect(status().isOk()) //httpStatus검사를 해준다
////                .andExpect(content().contentType(MediaType.TEXT_HTML)) //contentType검사
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) // contentTypeCompatibleWith 은 해당 타입 하나라도 속하면
//                .andExpect(content().string(containsString("WebMvcAutoConfiguration.class 덕분에 resource/static 경로에 있기만 하면\\n" +
//                        "루트페이지로 인식!!!"))) //내용 body를 검사한다. string()을 쓸경우 완벽히 일치해야한다
//                .andExpect(view().name("index")) // 맵핑 테스트, 즉 view 네임
//                .andDo(print()); // 잃반적으로 에러발생해야지만 출력하는데 , andDo쓸경우 에러, 성공 상관없이 출력
    }
}