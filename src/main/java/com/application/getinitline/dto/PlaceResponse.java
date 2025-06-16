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
    Long id,
    PlaceType placeType,
    String placeName,
    String address,
    String phoneNumber,
    Integer capacity,
    String memo
) {

    public static PlaceResponse of(
        Long id,
        PlaceType placeType,
        String placeName,
        String address,
        String phoneNumber,
        Integer capacity,
        String memo
    ) {
        return new PlaceResponse(id, placeType, placeName, address, phoneNumber, capacity, memo);
    }

    public static PlaceResponse from(PlaceDto placeDto) {
        if (placeDto == null) { return null; }
        return PlaceResponse.of(
            placeDto.id(),
            placeDto.placeType(),
            placeDto.placeName(),
            placeDto.address(),
            placeDto.phoneNumber(),
            placeDto.capacity(),
            placeDto.memo()
        );
    }

}