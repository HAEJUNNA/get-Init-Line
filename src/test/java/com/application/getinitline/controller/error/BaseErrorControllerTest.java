package com.application.getinitline.controller.error;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * packageName    : com.application.getinitline.controller.error
 * fileName       : BaseErrorControllerTest
 * author         : NAHAEJUN
 * date           : 2025-03-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-12        NAHAEJUN              최초생성
 */
@WebMvcTest
class BaseErrorControllerTest {

    private final MockMvc mockMvc;

    public BaseErrorControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @DisplayName("[view][GET] 기본 페이지 요청")
    @Test
    void testRoot() throws Exception {


        mockMvc.perform(get("/asdasd")) // 일부러 에러 발생, 그렇기때문에 하위 테스트는 굳이 필요 x
                .andExpect(status().isNotFound()) //httpStatus검사를 해준다
                .andDo(print()); // 잃반적으로 에러발생해야지만 출력
    }
}