package com.application.getinitline.service;

import com.application.getinitline.constant.EventStatus;
import com.application.getinitline.dto.EventDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * packageName    : com.application.getinitline.service
 * fileName       : AdminService
 * author         : NAHAEJUN
 * date           : 2025-03-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-23        NAHAEJUN              최초생성
 */
@Service
public class EventServiceTestImpl implements EventServiceTest {

    @Override
    public List<EventDTO> findEvents(Long placeId
            , String name
            , EventStatus eventStatus
            , LocalDateTime eventStartDateTime
            , LocalDateTime eventEndDateTime) {
        return List.of();
    }
}
