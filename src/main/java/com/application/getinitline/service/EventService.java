package com.application.getinitline.service;

import com.application.getinitline.constant.EventStatus;
import com.application.getinitline.dto.EventDTO;
import com.application.getinitline.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.application.getinitline.service
 * fileName       : EventService
 * @author         : NAHAEJUN
 * date           : 2025-03-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-24        NAHAEJUN              최초생성
 */
@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

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
    public List<EventDTO> getEvents(
            Long placeId,
            String name,
            EventStatus eventStatus,
            LocalDateTime eventStartDateTime,
            LocalDateTime eventEndDateTime) {
        return List.of();
    }
    public Optional<EventDTO> getEvent(Long eventId) {
        return Optional.empty();
    }

    public boolean crateEvent(EventDTO eventDTO) {
        return true;
    }

    public boolean updateEvent(EventDTO eventDTO,Long eventId) {
        return true;
    }

    public boolean deleteEvent(Long eventId) {
        return true;
    }
}
