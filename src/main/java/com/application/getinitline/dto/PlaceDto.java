package com.application.getinitline.dto;

import com.application.getinitline.constant.PlaceType;

/**
 * packageName    : com.application.getinitline.dto
 * fileName       : PlaceDto
 * author         : NAHAEJUN
 * date           : 2025-03-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-13        NAHAEJUN              최초생성
 */
public record PlaceDto(
        PlaceType placeType,
        String placeName,
        String address,
        String phoneNumber,
        Integer capacity,
        String memo
) {
    //생성자 단축키 클릭시 Compact 는 record에서 지원, Canonical은 평소 우리가 쓰는 생성자
    //Canonical 생성자
    public static PlaceDto of(
            PlaceType placeType,
            String placeName,
            String address,
            String phoneNumber,
            Integer capacity,
            String memo
    ) {
        return new PlaceDto(placeType, placeName, address, phoneNumber, capacity, memo);
    }
}
