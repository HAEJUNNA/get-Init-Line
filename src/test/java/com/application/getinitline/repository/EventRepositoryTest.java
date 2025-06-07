package com.application.getinitline.repository;

import com.application.getinitline.constant.EventStatus;
import com.application.getinitline.constant.PlaceType;
import com.application.getinitline.domain.Event;
import com.application.getinitline.domain.Place;
import com.querydsl.core.BooleanBuilder;
import org.aspectj.util.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * MethodName     :
 * description    :
 *
 * @param :
 * @return :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-06-01        NAHAEJUN       최초 생성
 */
@DataJpaTest // 영속성 레이어 테스트 부분
class EventRepositoryTest {

    private final EventRepository sut;
    private final TestEntityManager testEntityManager;

    public EventRepositoryTest(
          @Autowired EventRepository sut
        , @Autowired TestEntityManager testEntityManager
    ) {
        this.sut = sut;
        this.testEntityManager = testEntityManager;
    }

    @DisplayName("[repository][GET] EventRepository")
    @Test
    void givenEventRepository_whenGetEvent_thenReturnEvent() {

        // Given
        Place place = createPlace();
        Event event = createEvent(place);

        testEntityManager.persist(place);
        testEntityManager.persist(event);

        // When
        Iterable<Event> events =  sut.findAll(new BooleanBuilder());

        // Then
//        assertThat(events).contains();
        assertThat(events).hasSize(7);

    }

    private Event createEvent(Place place) {
        return createEvent(
            place
            ,"test event"
            , EventStatus.ABORTED
            , LocalDateTime.now()
            , LocalDateTime.now());
    }


    private Event createEvent(
//        long id
//        , long placeId
        Place place
        , String eventName
        , EventStatus eventStatus
        , LocalDateTime eventStartDatetime
        , LocalDateTime eventEndDatetime) {

        //ReflectionTestUtils 사용 안감
//        Event event = Event.of( place
//            , eventName
//            , eventStatus
//            , eventStartDatetime
//            , eventEndDatetime
//            ,0
//            ,24
//            ,"마스크 착용하세요");
//        ReflectionTestUtils.setField(event, "id", id);
        return Event.of(place, eventName, eventStatus, eventStartDatetime, eventEndDatetime,0,24,"마스크 착용하세요");
    }


    private Place createPlace() {
        Place place = Place.of(PlaceType.COMMON,"test place", "test address", "010-1234-5678", 100, "test memo");
        ReflectionTestUtils.setField(place, "id", 1L);
        return place;
    }


}