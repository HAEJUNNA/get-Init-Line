package com.application.getinitline.controller.api;

import com.application.getinitline.constant.ErrorCode;
import com.application.getinitline.constant.PlaceType;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper mapper;




    public APIPlaceControllerTest(
            @Autowired MockMvc mvc,
            @Autowired ObjectMapper mapper) {
        this.mockMvc = mvc;
        this.mapper = mapper;
    }

    /**
     * 
     * MethodName     : TDD 스타일 개발 시작 
     * description    : 장소조회  컨트롤러 개발을 위한 TDD작성
     * @param         : 
     * @return        :
     * ===========================================================
     * DATE              AUTHOR             NOTE
     * -----------------------------------------------------------
     * 2025-05-10        NAHAEJUN       최초 생성
     */
    @DisplayName("[API] [GET] 장소 리스트 조회 - 장소 리스트 데이터를 담은 표준 API. 출력")
    @Test
    void givenNothing_whenRequestingPlaces_thenReturnsPlaceInStandardResponse() throws Exception {
        // Given



        // When & Then
        mockMvc.perform(get("/api/places"))
                .andExpect(status().isOk()) //200이 떨어져야함

                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray()) // jsonPath 는 json을 검사해준다.
                .andExpect(jsonPath("$.data[0].placeType").value(PlaceType.COMMON.name()))//순서대로 데이터 잘넘어왔는지 체크
                .andExpect(jsonPath("$.data[0].placeName").value("랄라배드민턴장"))
                .andExpect(jsonPath("$.data[0].address").value("서울시 강남구 강남대로"))
                .andExpect(jsonPath("$.data[0].phoneNumber").value("010-1234-5678"))

                .andExpect(jsonPath("$.data[0].capacity").value(30))
                .andExpect(jsonPath("$.data[0].memo").value("신장 개업"))
                .andExpect(jsonPath("$.success").value(true))

                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));

    }


    @DisplayName("[API] [GET] 단일 장소 조회 있는곳 [비정상 작동 검증]")
    @Test
    void givenPlaceAndItsId_whenRequestingPlaces_thenReturnsPlaceInStandardResponse() throws Exception {
        // Given - 데이터가 있는경우
        // 테스트 데이터
        int placeId = 1;

        // When & Then
        //장소를 요청하면 장소에 리스트를 전달한다.
        mockMvc.perform(get("/api/placeTest/" + placeId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isMap()) // jsonPath 는 json을 검사해준다.
                .andExpect(jsonPath("$.data.placeType").value(PlaceType.COMMON.name()))//순서대로 데이터 잘넘어왔는지 체크
                .andExpect(jsonPath("$.data.placeName").value("랄라배드민턴장"))
                .andExpect(jsonPath("$.data.address").value("서울시 강남구 강남대로"))
                .andExpect(jsonPath("$.data.phoneNumber").value("010-1234-5678"))
                .andExpect(jsonPath("$.data.capacity").value(30))
                .andExpect(jsonPath("$.data.memo").value("신장 개업"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode())) //에러코드여부
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage())); // 에러메세지여부


    }

    @DisplayName("[API] [GET] 단일 장소 조회 없는경우 [오류 검증]")
    @Test
    void givenPlaceId_whenRequestingPlaces_thenReturnsEmptyInStandardResponse() throws Exception {
        // Given - 데이터가 있는경우

        int placeId = 2;

        // When & Then
        //장소를 요청하면 장소에 리스트를 전달한다.
        mockMvc.perform(get("/api/placeTest/" + placeId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isEmpty()) // 비어있는경우
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode())) //에러코드여부
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage())); // 에러메세지여부


    }


}