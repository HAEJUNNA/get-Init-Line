package com.application.getinitline.controller.api;

import com.application.getinitline.constant.ErrorCode;
import com.application.getinitline.constant.PlaceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * packageName    : com.application.getinitline.controller.api
 * fileName       : APIPlaceControllerTest
 * author         : NAHAEJUN
 * date           : 2025-03-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-13        NAHAEJUN              최초생성
 */
@WebMvcTest(APIPlaceController.class)
class APIPlaceControllerTest {
    
    private final MockMvc mockMvc;

    public APIPlaceControllerTest(@Autowired MockMvc mvc){this.mockMvc = mvc;}

    @DisplayName("[API] [GET] 장소 리스트 조회")
    @Test
    void givenNothing_whenRequestingPlaces_thenReturnsListOfPlaces() throws Exception {
        // Given

        // When & Then
        //장소를 요청하면 장소에 리스트를 전달한다.
        mockMvc.perform(get("/api/places"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].placeType").value(PlaceType.COMMON.name()))
                .andExpect(jsonPath("$.data[0].placeName").value("랄라배드민턴장"))
                .andExpect(jsonPath("$.data[0].address").value("서울시 강남구 강남대로"))
                .andExpect(jsonPath("$.data[0].phoneNumber").value("010-1234-5678"))
                .andExpect(jsonPath("$.data[0].capacity").value(30))
                .andExpect(jsonPath("$.data[0].memo").value("신장 개업"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));


    }


}