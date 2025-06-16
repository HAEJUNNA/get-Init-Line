package com.application.getinitline.dto;

import com.application.getinitline.constant.EventStatus;
import com.application.getinitline.domain.Event;
import com.application.getinitline.domain.Place;

import java.time.LocalDateTime;

/**
 * packageName    : com.application.getinitline.dto
 * fileName       : EventDTO
 * author         : NAHAEJUN
 * date           : 2025-03-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-24        NAHAEJUN              최초생성
 */
public record EventDTO (
        Long id,
        PlaceDto placeDto,
        String eventName,
        EventStatus eventStatus,
        LocalDateTime eventStartDatetime,
        LocalDateTime eventEndDatetime,
        Integer currentNumberOfPeople,
        Integer capacity,
        String memo,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static EventDTO of(
        Long id,
        PlaceDto placeDto,
        String eventName,
        EventStatus eventStatus,
        LocalDateTime eventStartDatetime,
        LocalDateTime eventEndDatetime,
        Integer currentNumberOfPeople,
        Integer capacity,
        String memo,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
    ) {
        return new EventDTO (
            id,
            placeDto,
            eventName,
            eventStatus,
            eventStartDatetime,
            eventEndDatetime,
            currentNumberOfPeople,
            capacity,
            memo,
            createdAt,
            modifiedAt
        );
    }

    public static EventDTO of(Event event) {
        return new EventDTO(
            event.getId(),
            PlaceDto.of(event.getPlace()),
            event.getEventName(),
            event.getEventStatus(),
            event.getEventStartDatetime(),
            event.getEventEndDatetime(),
            event.getCurrentNumberOfPeople(),
            event.getCapacity(),
            event.getMemo(),
            event.getCreatedAt(),
            event.getModifiedAt()
        );
    }

    public Event toEntity(Place place) {
        return Event.of(
            place,
            eventName,
            eventStatus,
            eventStartDatetime,
            eventEndDatetime,
            currentNumberOfPeople,
            capacity,
            memo
        );
    }

    public Event updateEntity(Event event) {
        if (eventName != null) { event.setEventName(eventName); }
        if (eventStatus != null) { event.setEventStatus(eventStatus); }
        if (eventStartDatetime != null) { event.setEventStartDatetime(eventStartDatetime); }
        if (eventEndDatetime != null) { event.setEventEndDatetime(eventEndDatetime); }
        if (currentNumberOfPeople != null) { event.setCurrentNumberOfPeople(currentNumberOfPeople); }
        if (capacity != null) { event.setCapacity(capacity); }
        if (memo != null) { event.setMemo(memo); }

        return event;
    }
}
