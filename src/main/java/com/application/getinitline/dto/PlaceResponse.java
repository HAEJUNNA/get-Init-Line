package com.application.getinitline.dto;

import com.application.getinitline.constant.PlaceType;

/**
 * packageName    : com.application.getinitline.dto
 * fileName       : PlaceResponse
 * author         : NAHAEJUN
 * date           : 2025-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-05-10        NAHAEJUN              최초생성
 */
public record PlaceResponse(
        PlaceType placeType,
        String placeName,
        String address,
        String phoneNumber,
        Integer capacity,
        String memo

) {
    public static PlaceResponse of(
        PlaceType placeType,
        String placeName,
        String address,
        String phoneNumber,
        Integer capacity,
        String memo
    ) {
       return new PlaceResponse(placeType, placeName, address, phoneNumber, capacity, memo);
    }


}
