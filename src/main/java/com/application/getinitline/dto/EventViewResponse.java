package com.application.getinitline.dto;

import com.application.getinitline.constant.EventStatus;

import java.time.LocalDateTime;

/**
 * MethodName     :
 * description    :
 *
 * @param :
 * @return :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-06-16        NAHAEJUN       최초 생성
 */
public record EventViewResponse(
    Long id,
    String placeName,
    String eventName,
    EventStatus eventStatus,
    LocalDateTime eventStartDateTime,
    LocalDateTime eventEndDateTime,
    Integer currentNumberOfPeople,
    Integer capacity,
    String memo
) {
    public EventViewResponse(Long id
        , String placeName
        , String eventName
        , EventStatus eventStatus
        , LocalDateTime eventStartDateTime
        , LocalDateTime eventEndDateTime
        , Integer currentNumberOfPeople
        , Integer capacity
        , String memo) {
        this.id = id;
        this.placeName = placeName;
        this.eventName = eventName;
        this.eventStatus = eventStatus;
        this.eventStartDateTime = eventStartDateTime;
        this.eventEndDateTime = eventEndDateTime;
        this.currentNumberOfPeople = currentNumberOfPeople;
        this.capacity = capacity;
        this.memo = memo;
    }

    public static EventViewResponse of(
        Long id
        , String placeName
        , String eventName
        , EventStatus eventStatus
        , LocalDateTime eventStartDateTime
        , LocalDateTime eventEndDateTime
        , Integer currentNumberOfPeople
        , Integer capacity
        , String memo
    ) {
        return new EventViewResponse(id, placeName, eventName, eventStatus, eventStartDateTime, eventEndDateTime, currentNumberOfPeople, capacity, memo);
    }

    public static EventViewResponse from(EventDTO eventDTO) {
        if (eventDTO == null) {return null;}
        return EventViewResponse.of(
            eventDTO.id()
            , eventDTO.PlaceName()
            , eventDTO.getEventName()
            , eventDTO.getEventStatus()
            , eventDTO.getEventStartDateTime()
            , eventDTO.getEventEndDateTime()
            , eventDTO.getCurrentNumberOfPeople()
            , eventDTO.getCapacity()
            , eventDTO.getMemo())
    }
}
