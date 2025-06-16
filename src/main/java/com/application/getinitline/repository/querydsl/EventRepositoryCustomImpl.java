package com.application.getinitline.repository.querydsl;

import com.application.getinitline.constant.EventStatus;
import com.application.getinitline.domain.Event;
import com.application.getinitline.domain.QEvent;
import com.application.getinitline.dto.EventViewResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

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
public class EventRepositoryCustomImpl extends QuerydslRepositorySupport implements EventRepositoryCustom{

    public EventRepositoryCustomImpl() {
        super(Event.class); // 엔티티를 넣어주면된다(테이블)
    }

    @Override
    public Page<EventViewResponse> findEventViewPageBySearchParams(String placeName
        , String eventName
        , EventStatus eventStatus
        , LocalDateTime eventStartDateTime
        , LocalDateTime eventEndDateTime
        , Pageable pageable) {
        QEvent event = QEvent.event;

        JPQLQuery<EventViewResponse> query = from(event).select(Projections.constructor(EventViewResponse.class
            , event.id
            , event.place.placeName
            , event.eventName
            , event.eventStatus
            , event.eventStartDatetime
            , event.eventEndDatetime
            , event.currentNumberOfPeople
            , event.capacity
            , event.memo));

        //다이나믹 쿼리 처리 (독립적으로 각각 시행)
        if (placeName != null && !placeName.isBlank()) {
            query.where(event.place.placeName.containsIgnoreCase(placeName));
        }
        if (eventName != null && !eventName.isBlank()) {
            query.where(event.eventName.containsIgnoreCase(eventName));
        }
        if (eventStatus != null) {
            query.where(event.eventStatus.eq(eventStatus));
        }
        if (eventStartDateTime != null) {
            query.where(event.eventStartDatetime.goe(eventStartDateTime));
        }
        if (eventEndDateTime != null) {
            query.where(event.eventEndDatetime.loe(eventEndDateTime));
        }

        return null;
    }
}
