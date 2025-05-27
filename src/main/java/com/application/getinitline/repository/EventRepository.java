package com.application.getinitline.repository;

import com.application.getinitline.constant.EventStatus;
import com.application.getinitline.domain.Event;
import com.application.getinitline.domain.Place;
import com.application.getinitline.dto.EventDTO;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.awt.print.Pageable;
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
public interface EventRepository extends
    JpaRepository<Event, Long>,
    QuerydslPredicateExecutor<Event>{

//    Page<Event> findByPlace(Place place, Pageable pageable);
//
//    @Override
//    default void customize(QuerydslBindings bindings, QEvent root) {
//        bindings.excludeUnlistedProperties(true);
//        bindings.including(root.place.placeName, root.eventName, root.eventStatus, root.eventStartDatetime, root.eventEndDatetime);
//        bindings.bind(root.place.placeName).as("placeName").first(StringExpression::containsIgnoreCase);
//        bindings.bind(root.eventName).first(StringExpression::containsIgnoreCase);
//        bindings.bind(root.eventStartDatetime).first(ComparableExpression::goe);
//        bindings.bind(root.eventEndDatetime).first(ComparableExpression::loe);
//    }
}
