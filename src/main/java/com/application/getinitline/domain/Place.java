package com.application.getinitline.domain;

import com.application.getinitline.constant.PlaceType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * packageName    : com.application.getinitline.domain
 * fileName       : Place
 * author         : NAHAEJUN
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        NAHAEJUN              최초생성
 */
@Data
public class Place {
    private Long id;

    private PlaceType placeType;
    private String placeName;
    private String address;
    private String phoneNumber;

    private Integer capacity;
    private String memo;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
