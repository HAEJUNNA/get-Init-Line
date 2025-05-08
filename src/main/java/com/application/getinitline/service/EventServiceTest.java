package com.application.getinitline.service;

import com.application.getinitline.constant.EventStatus;
import com.application.getinitline.dto.EventDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @packageName    : com.application.getinitline.service
 * @fileName       : EventServiceTest
 * @author         :  MINGO
 * @date           : 2025-03-24
 * @description    : 이벤트 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-24        NAHAEJUN              최초생성
 */

public interface EventServiceTest {
    /**
     * 검색어들을 통한 이벤트 리스트를 반환
     * 
     * @param placeId 장소 ID
     * @param name 이벤트 이름
     * @param eventStatus 이벤트 상태
     * @param eventStartDateTime 이벤트 시작 시간
     * @param eventEndDateTime 이벤트 종료 시간
     *
     * */
    List<EventDTO> findEvents(
            Long placeId,
            String name,
            EventStatus eventStatus,
            LocalDateTime eventStartDateTime,
            LocalDateTime eventEndDateTime
    );
}
