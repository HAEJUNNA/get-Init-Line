package com.application.getinitline.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * packageName    : com.application.getinitline.domain
 * fileName       : AdminPlaceMap
 * author         : NAHAEJUN
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        NAHAEJUN              최초생성
 */
@Data
public class AdminPlaceMap {
    private Long id;
    private Long adminId;
    private Long PlaceId;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
