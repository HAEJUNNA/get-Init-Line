package com.application.getinitline.dto;

import com.application.getinitline.constant.ErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * packageName    : com.application.getinitline.dto
 * fileName       : APIDataResponse
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
// callSuper=false 옵션을 해둠으로서 equals hex 코드 할때 상속받은 APIErrorResponse 안에있는
// equals hex 코드도 불러서 사용할수 있게 끝 안에있는 필드들도 동일한지 검사하를 하기위함
// 이건 즉, 추이성을 지키기 위한 옵션이라고 생각하면된다.
@EqualsAndHashCode(callSuper=false)
public class APIDataResponse<T> extends APIErrorResponse{

    private final T data;

    private APIDataResponse(T data) {
        super(true
                , ErrorCode.OK.getCode()
                , ErrorCode.OK.getMessage() );
        this.data = data;
    }
    /*
    *  type safe feature를 충분히 쓸수가 없기 떄문에, 해당 dto를 사용
    * */
    public static <T> APIDataResponse<T> of(T data) {
        return new APIDataResponse<>(data);
    }
    public static <T> APIDataResponse<T> empty() {
        return new APIDataResponse<>(null);
    }
}

