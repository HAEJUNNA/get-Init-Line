package com.application.getinitline.dto;

import com.application.getinitline.constant.ErrorCode;
import lombok.*;

/**
 * packageName    : com.application.getinitline.dto
 * fileName       : APIErrorResponse
 * author         : NAHAEJUN
 * date           : 2025-03-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-03        NAHAEJUN              최초생성
 */
@Getter
@ToString
@EqualsAndHashCode
// 상속에 열려있어야 하기 때문에 PROTECTED 지정 , 바깥에서도 쓸수없고, 상속받은 APIDataResponse 클래스느 사용할수있게 제한 두는것
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class APIErrorResponse {

    private final Boolean success;
    private final Integer errorCode;
    private final String message;

    /* 수동입력 각각 필드들을 내 입맛대로 넣고싶을때 */
    public static APIErrorResponse of(Boolean success, Integer errorCode, String message) {
        return new APIErrorResponse(success, errorCode, message);
    }
    /* 에러코드를 대응 하는곳 */
    public static APIErrorResponse of(Boolean success, ErrorCode errorCode) {
        return new APIErrorResponse(success, errorCode.getCode(), errorCode.getMessage());
    }
    /* 에러메세지를 다른곳에서 넣고싶은 경우 */
    public static APIErrorResponse of(Boolean success, ErrorCode errorCode, Exception e) {
        return new APIErrorResponse(success, errorCode.getCode(), errorCode.getMessage(e));
    }

    public static APIErrorResponse of(Boolean success, ErrorCode errorCode, String message) {
        return new APIErrorResponse(success, errorCode.getCode(), errorCode.getMessage(message));
    }

}
