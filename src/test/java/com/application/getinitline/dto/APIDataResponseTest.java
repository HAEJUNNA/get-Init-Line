package com.application.getinitline.dto;

import com.application.getinitline.constant.ErrorCode;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.application.getinitline.dto
 * fileName       : APIDataResponseTest
 * author         : NAHAEJUN
 * date           : 2025-05-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-05-09        NAHAEJUN              최초생성
 */
class APIDataResponseTest {

    @Test
    @DisplayName("문자열 데이터가 주어지면, 표준 성공 응답을 생성한다.")
    void givenStringData_whenCreatingResponse_thenReturnSuccessFulResponse() {
        // Given
        String data = "test data";
        // When
        APIDataResponse<String> response = APIDataResponse.of(data);
        // Then
        // assertj.core.api.Assertions.assertThat 사용
        assertThat(response)
//                .isNotNull()// hasFieldOrPropertyWithValue("success",true)검사와 동일한 테스트
                .hasFieldOrPropertyWithValue("success",true) //response가 null이면 여기서부터 실패
                .hasFieldOrPropertyWithValue("errorCode",ErrorCode.OK.getCode()) //
                .hasFieldOrPropertyWithValue("message",ErrorCode.OK.getMessage())
                .hasFieldOrPropertyWithValue("data",data);
    }
    @Test
    @DisplayName("문자열 데이터가 없을때, 비어있는 표준 성공 응답을 생성한다.")
    void givenNothing_whenCreatingResponse_thenReturnEmptySuccessFulResponse() {
        // Given
        String data = "test data";
        // When
        APIDataResponse<String> response = APIDataResponse.empty();
        // Then
        // assertj.core.api.Assertions.assertThat 사용
        assertThat(response)
//                .isNotNull()// hasFieldOrPropertyWithValue("success",true)검사와 동일한 테스트
                .hasFieldOrPropertyWithValue("success",true) //response가 null이면 여기서부터 실패
                .hasFieldOrPropertyWithValue("errorCode",ErrorCode.OK.getCode()) //
                .hasFieldOrPropertyWithValue("message",ErrorCode.OK.getMessage())
                .hasFieldOrPropertyWithValue("data",null);
    }
}