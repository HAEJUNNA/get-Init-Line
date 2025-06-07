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
 * author         : NAHAEJUN
 * date           : 2025-05-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-05-11        NAHAEJUN              최초생성
 */
@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

//    public List<EventDTO> getEvents(
//            Long placeId ,
//            String eventName ,
//            EventStatus eventStatus ,
//            LocalDateTime eventStartDateTime ,
//            LocalDateTime eventEndDateTime
//    ) {
//        return eventRepository.findEvents(placeId, eventName, eventStatus, eventStartDateTime, eventEndDateTime);
//    }
//
//    public Optional<EventDTO> getEvent(Long eventId) {
//        return eventRepository.findEvent(eventId);
//    }
//
//    public boolean createEvent(EventDTO eventDTO) {
//        return eventRepository.insertEvent(eventDTO);
//    }
//
//    public boolean modifyEvent(Long eventId, EventDTO eventDTO ) {
//        return eventRepository.updateEvent(eventId, eventDTO);
//    }
//
//    public boolean removeEvent(Long eventId) {
//        return eventRepository.removeEvent(eventId);
//    }
}
