package com.application.getinitline.service;

import ch.qos.logback.core.boolex.EventEvaluatorBase;
import com.application.getinitline.constant.EventStatus;
import com.application.getinitline.dto.EventDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.array;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.application.getinitline.service
 * fileName       : EventServiceTest
 * author         : NAHAEJUN
 * date           : 2025-03-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-24        NAHAEJUN              최초생성
 */
class EventServiceTest {

    // sut : System Under Test , 즐겨사용하는 관례적인 단어이다.
    private EventService sut;

    @DisplayName("검색 조건 없이 이벤트 검색 하면, 전체 결과를 출력 하여 보여 준다.")
    @Test
    void givenNothing_WhenSearchingEvents_thenReturnEntireEventList() {
        // Given

        // When
        List<EventDTO> list = sut.getEvents(null,null,null,null,null);
        // Then
        assertThat(list).isNotNull();
        assertThat(list).hasSize(2);
    }

    void givenSearchParams_WhenSearchingEvents_thenReturnEventList() {
        // Given
        Long placeId = 1L;
        String name = "오전 운ㄷ동";
        EventStatus eventStatus = EventStatus.OPENED;
        LocalDateTime eventStartTime = LocalDateTime.of(2025, 3, 24, 0, 0);
        LocalDateTime eventEndTime = LocalDateTime.of(2025, 3, 24, 1, 0);
        // When
        List<EventDTO> list = sut.getEvents(placeId,name,eventStatus,eventStartTime,eventEndTime);

        // Then
        // 여러개가 뽑힐수도 있다는 가정하에 테스트
        // 결과가 1 이상 n개 나왔을때 하나하나 엘리먼트 이벤트가 되기때문에 하나하나 테스트
        assertThat(list).hasSize(1)
                .allSatisfy(event -> {
                    assertThat(event)
                            .hasFieldOrPropertyWithValue("placeId",placeId) // 필드면은 문자로, 값은 그대로
                            .hasFieldOrPropertyWithValue("eventName",name)
                            .hasFieldOrPropertyWithValue("eventStatus",eventStatus);
                    assertThat(event.eventStartDateTime()).isAfterOrEqualTo(eventStartTime); // 검색한 내용의 시작시간이 내검색어의 시작 시간보다 더 뒤에있거나, 혹은 같을떄
                    assertThat(event.eventStartDateTime()).isBeforeOrEqualTo(eventStartTime);
                });
    }

    private EventDTO createEventDTO(long placeId, String eventName, boolean isMorning) {
        String hourStart = isMorning ? "09" : "13";
        String hourEnd = isMorning ? "12" : "16";

        return createEventDTO(
                placeId,
                eventName,
                EventStatus.OPENED,
                LocalDateTime.parse("2025-03-01T%s:00:00", formatted);
        );
    }



    /*
    * 데이터를 계속해서 세팅하게 되면, DTO사이즈가 커진다. 그렇기 떄문에여기다 따로 빼놓는다.
    *
    * */
    private EventDTO createEventDTO(
        long placeId,
        String name,
        EventStatus eventStatus,
        LocalDateTime eventStartTime,
        LocalDateTime eventEndTime
        ){
        return EventDTO.of(
                placeId,
                name,
                eventStatus,
                eventStartTime,
                eventEndTime,
                currentNumberOfPeople,
                capacity,
                memo,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}