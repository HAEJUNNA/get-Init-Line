package com.application.getinitline.repository;

import com.application.getinitline.domain.Event;
import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

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
    testEntityManager.persist()

        // When
        Iterable<Event> events =  sut.findAll(new BooleanBuilder());

        // Then
        assertThat(events).contains();

    }


}