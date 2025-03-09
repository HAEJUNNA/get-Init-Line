package com.application.getinitline.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
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
@AutoConfigureMockMvc// 설정시 MockMvc를 AutoWired로 바로 주입 받을수있다.
@SpringBootTest
class BaseControllerTest {

    @Autowired
    private MockMvc mvc;

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
    void testRoot() throws Exception {
        // Given

        // When
        ResultActions resultActions = mvc.perform(get("/"));

        // Then
        resultActions.andExpect(status().isOk()) //httpStatus검사를 해준다
//                .andExpect(content().contentType(MediaType.TEXT_HTML)) //contentType검사
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) // contentTypeCompatibleWith 은 해당 타입 하나라도 속하면
                .andExpect(content().string(containsString("WebMvcAutoConfiguration.class 덕분에 resource/static 경로에 있기만 하면\\n" +
                        "루트페이지로 인식!!!"))) //내용 body를 검사한다. string()을 쓸경우 완벽히 일치해야한다
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