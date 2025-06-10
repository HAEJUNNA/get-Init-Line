package com.application.getinitline.repository;

import com.application.getinitline.domain.Event;
import com.application.getinitline.domain.Place;
import com.application.getinitline.domain.QEvent;
import com.querydsl.core.types.dsl.ComparableExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.awt.print.Pageable;

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

    Page<Event> findByPlace(Place place, Pageable pageable);
    default void customize(QuerydslBindings bindings, QEvent root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.place.placeName, root.eventName, root.eventStatus, root.eventStartDatetime, root.eventEndDatetime);
        bindings.bind(root.place.placeName).as("placeName").first(StringExpression::containsIgnoreCase);
        bindings.bind(root.eventName).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.eventStartDatetime).first(ComparableExpression::goe);
        bindings.bind(root.eventEndDatetime).first(ComparableExpression::loe);
    }
}
