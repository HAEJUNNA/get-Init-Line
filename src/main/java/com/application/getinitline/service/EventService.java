package com.application.getinitline.service;

import com.application.getinitline.constant.EventStatus;
import com.application.getinitline.dto.EventDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.application.getinitline.service
 * fileName       : EventService
 * author         : NAHAEJUN
 * date           : 2025-05-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-05-11        NAHAEJUN              최초생성
 */
@Service
public class EventService {

    public List<EventDTO> getEvents(
            Long placeId ,
            String eventName ,
            EventStatus eventStatus ,
            LocalDateTime eventStartDateTime ,
            LocalDateTime eventEndDateTime
    ) {
        return null;
    }

    public Optional<EventDTO> getEvent(Long eventId) {
        return Optional.empty();
    }

    public boolean createEvent(EventDTO eventDTO) {
        return true;
    }

    public boolean modifyEvent(Long eventId, EventDTO eventDTO ) {
        return true;
    }

    public boolean removeEvent(Long eventId) {
        return true;
    }
}
