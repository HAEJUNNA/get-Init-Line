package com.application.getinitline.service;

import com.application.getinitline.constant.EventStatus;
import com.application.getinitline.dto.EventDTO;
import com.application.getinitline.repository.EventRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * packageName    : com.application.getinitline.service
 * fileName       : EventServiceTest
 * author         : NAHAEJUN
 * date           : 2025-05-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-05-11        NAHAEJUN              최초생성
 */
@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @InjectMocks
    private EventService sut;
    @Mock
    private EventRepository eventRepository;

    @Test
    @DisplayName("검색 조건 없이 이벤트 검색화면 , 전체 결과를 출력하여 보여준다.")
    void givenNothing_whenSearchingEvents_thenReturnsEntireEventList(){
        // Given
        BDDMockito.given(eventRepository.findEvents(null,null,null,null,null))
                .willReturn(List.of(
                        createEventDto(1L,"오전 운동",true)
                        , createEventDto(1L,"오후 운동",false)
                ));
        // When
        List<EventDTO> list = sut.getEvents(null,null,null,null,null);

        // Then
        assertThat(list).hasSize(2);
        BDDMockito.then(eventRepository).should().findEvents(null,null,null,null,null);
        Mockito.verify(eventRepository).findEvents(null,null,null,null,null);
    }

    @Test
    @DisplayName("검색 조건과 함께 이벤트 검색하면, 검색된 결과를 출력하여 보여준다.")
    void givenSearchParams_whenSearchingEvents_thenReturnsEventList(){
        // Given
        long placeId = 1L;
        String eventName = "오전 운동";
        EventStatus eventStatus = EventStatus.OPENED;
        LocalDateTime eventStartDataTime = LocalDateTime.of(2025,1,1,0,0,0);
        LocalDateTime eventEndDataTime = LocalDateTime.of(2025,1,2,0,0,0);

        BDDMockito.given(eventRepository.findEvents(placeId,eventName,eventStatus,eventStartDataTime,eventEndDataTime))
                .willReturn(List.of(
                        createEventDto(1L,"오전 운동",eventStatus,eventStartDataTime,eventEndDataTime)
                ));

        // When
        List<EventDTO> list = sut.getEvents(placeId,eventName,eventStatus,eventStartDataTime,eventEndDataTime);


        // Then
        assertThat(list)
                .hasSize(1)
                .allSatisfy(event->{ //결과가 n개가 나올거라 예상하고, 각1개씩 검사
                    assertThat(event)
                            .hasFieldOrPropertyWithValue("placeId", placeId)
                            .hasFieldOrPropertyWithValue("eventName", eventName)
                            .hasFieldOrPropertyWithValue("eventStatus", eventStatus);
                    assertThat(event.eventStartDateTime()).isAfterOrEqualTo(eventStartDataTime);
                    assertThat(event.eventStartDateTime()).isBeforeOrEqualTo(eventStartDataTime);

                });
        BDDMockito.then(eventRepository).should().findEvents(placeId,eventName,eventStatus,eventStartDataTime,eventEndDataTime);
    }

    @Test
    @DisplayName("이벤트 ID에 존재하는 이벤트를 출력하여 보여준다")
    void givenEventId_whenSearchingExistEvent_thenReturnsEvent(){
        // Given
        long eventId = 1L;
        EventDTO eventDTO = createEventDto(1L,"오전 운동",true);
        BDDMockito.given(eventRepository.findEvent(eventId)).willReturn(Optional.of(eventDTO));

        // When
        Optional<EventDTO> result = sut.getEvent(eventId);


        // Then
        assertThat(result).hasValue(eventDTO);
        BDDMockito.then(eventRepository).should().findEvent(eventId);

    }

    @Test
    @DisplayName("이벤트 정보를 주면, 이벤트를 생성하고 결과를 true로 반환해준다.")
    void givenEvent_whenCreating_thenReturnsTrue(){
        // Given
        EventDTO eventDTO = createEventDto(1L,"오후 운동", false);
        BDDMockito.given(eventRepository.insertEvent(eventDTO)).willReturn(true);
        // When
        boolean result = sut.createEvent(eventDTO);

        // Then
        assertThat(result).isTrue();
        BDDMockito.then(eventRepository).should().insertEvent(eventDTO);
    }

    @Test
    @DisplayName("이벤트 ID와 정보를 주면, 이벤트 정보를 변경하고 결과물 true 로 보여 준다.")
    void givenEventIdAndItsInfo_whenModifying_thenModifiesEventAndReturnsTrue(){
        // Given
        long eventId = 1L;
        EventDTO dto = createEventDto(1L, "오후 운동", false);
        BDDMockito.given(eventRepository.updateEvent(eventId, dto)).willReturn(true);
        // When
        boolean result = sut.modifyEvent(eventId, dto);

        // Then
        assertThat(result).isTrue();
        BDDMockito.then(eventRepository).should().updateEvent(eventId, dto);
    }

    @Test
    @DisplayName("이벤트 ID만 주지않고 정보만 주는경우, 이벤트 정보 변경을 중단 하고, 결과물 false 로 보여 준다.")
    void givenNoEventId_whenModifying_thenAbortModifyingAndReturnsFalse(){
        // Given
        EventDTO dto = createEventDto(1L, "오후 운동", false);
        BDDMockito.given(eventRepository.updateEvent(null, dto)).willReturn(false);
        // When
        boolean result = sut.modifyEvent(null, dto);

        // Then
        assertThat(result).isFalse();
        BDDMockito.then(eventRepository).should().updateEvent(null, dto);
    }
    @Test
    @DisplayName("이벤트 ID만 주고 정보를 주지 않으면, 이벤트 정보 변경을 중단 하고, 결과물 false 로 보여 준다.")
    void givenNoEventIdOnly_whenModifying_thenAbortModifyingAndReturnsFalse(){
        // Given
        long eventId = 1L;
        BDDMockito.given(eventRepository.updateEvent(eventId, null)).willReturn(false);

        // When
        boolean result = sut.modifyEvent(eventId, null);

        // Then
        assertThat(result).isFalse();
        BDDMockito.then(eventRepository).should().updateEvent(eventId, null);

    }

    @Test
    @DisplayName("이벤트 ID를 주고 ,이벤트를 삭제하고 결과물 true 로 보여 준다.")
    void givenEventIdOnly_whenDeleting_thenDeleteEventAndReturnsFalse(){
        // Given
        long eventId = 1L;
        BDDMockito.given(eventRepository.removeEvent(eventId)).willReturn(true);
        // When
        boolean result = sut.removeEvent(eventId);

        // Then
        assertThat(result).isTrue();
        BDDMockito.then(eventRepository).should().removeEvent(eventId);
    }
    @Test
    @DisplayName("이벤트 ID를 주지않고 , 삭제 중단한고 ,결과물 false 로 보여 준다.")
    void givenNotEventId_whenDeleting_thenAbortDeleteEventAndReturnsFalse(){
        // Given

        BDDMockito.given(eventRepository.removeEvent(null)).willReturn(false);

        // When
        boolean result = sut.removeEvent(null);

        // Then
        assertThat(result).isFalse();
        BDDMockito.then(eventRepository).should().removeEvent(null);
    }




    private EventDTO createEventDto(
            long placeId
            , String eventName
            , boolean isMorning) {
        String hourStart = isMorning ? "09" : "13";
        String hourEnd = isMorning ? "12" : "16";
        return createEventDto(
                placeId
                , eventName
                , EventStatus.OPENED
                , LocalDateTime.parse("2025-01-01T%s:00:00".formatted(hourStart))
                , LocalDateTime.parse("2025-01-02T%s:00:00".formatted(hourEnd))
        );
    }

    //테스트 더미 데이터 DTO를 따로 생성
    private EventDTO createEventDto(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDateTime,
            LocalDateTime eventEndDateTime
    ){
        return EventDTO.of(placeId
                , eventName,eventStatus
                , eventStartDateTime
                , eventEndDateTime
                , 0
                , 24
                , "마스크 착용하세요"
                , LocalDateTime.now()
                , LocalDateTime.now());
    }


}