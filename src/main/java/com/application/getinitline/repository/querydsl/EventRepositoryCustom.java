package com.application.getinitline.repository.querydsl;

import com.application.getinitline.constant.EventStatus;
import com.application.getinitline.dto.EventViewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
//JPA와 다르게 구현체를 만들어줘야함
public interface EventRepositoryCustom {

    Page<EventViewResponse> findEventViewPageBySearchParams(
        String placeName,
        String eventName,
        EventStatus eventStatus,
        LocalDateTime eventStartDateTime,
        LocalDateTime eventEndDateTime,
        Pageable pageable
    );
}
