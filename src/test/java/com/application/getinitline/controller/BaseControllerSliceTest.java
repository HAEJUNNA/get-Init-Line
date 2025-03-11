package com.application.getinitline.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
 * @SpringBootTest를 slice 테스트로 전환
 * 
 * AutoConfigureMockMvc , @SpringBootTest
 * 없이도  @WebMvcTest이용해 테스트 가능
 * 
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-09        NAHAEJUN              최초생성
 */
//@WebMvcTest //WebMvcTest 가 모든 컨트롤러를 다읽는다.
@WebMvcTest(BaseControllerSliceTest.class) //단, 클래스만 지정해서도 가능, 이렇게 되면 로딩시 지정한 컨트롤러만 테스트 가능
class BaseControllerSliceTest {


    private final MockMvc mockMvc;

    public BaseControllerSliceTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }


    @DisplayName("[view][GET] 기본 페이지 요청")
    @Test
    void testRoot() throws Exception {
        // Given

        // When
        ResultActions resultActions = mockMvc.perform(get("/"));

        // Then
        resultActions.andExpect(status().isOk())
                .andExpect(content().string(containsString("this page!")))
                .andExpect(view().name("index"))
                .andDo(print());


    }
}