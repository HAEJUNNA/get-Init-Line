package com.application.getinitline.domain;

import com.application.getinitline.constant.EventStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * packageName    : com.application.getinitline.domain
 * fileName       : Event
 * author         : NAHAEJUN
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        NAHAEJUN              최초생성
 */
@Data
public class Event {
    private Long id;

    private Long placeId;
    private String eventName;
    private EventStatus eventStatus;
    private LocalDateTime eventStartDatetime;
    private LocalDateTime eventEndDatetime;
    private Integer currentNumberOfPeople;
    private Integer capacity;
    private String memo;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
