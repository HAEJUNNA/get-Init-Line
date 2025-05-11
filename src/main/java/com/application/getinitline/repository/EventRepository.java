package com.application.getinitline.repository;

import com.application.getinitline.constant.EventStatus;
import com.application.getinitline.dto.EventDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.application.getinitline.repository
 * fileName       : EventRepository
 * author         : NAHAEJUN
 * date           : 2025-03-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-26        NAHAEJUN              최초생성
 */
public interface EventRepository {
    // TODO , 인스턴스 생성시 편의를 위함
    default List<EventDTO> findEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartTime,
            LocalDateTime EndStartTime
    ){return List.of();}

    default Optional<EventDTO> findEvent(Long eventId){return Optional.empty();}
    default boolean insertEvent(EventDTO eventDTO){return true;}
    default boolean updateEvent(Long eventId, EventDTO dto){return true;}
    default boolean removeEvent(Long eventId){return true;}
}
