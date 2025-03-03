package com.application.getinitline.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * packageName    : com.application.getinitline.constant
 * fileName       : ErrorCode
 * author         : NAHAEJUN
 * date           : 2025-03-03
 * description    :
 *
 * 필요도 없을수도 있지만, 우리는 대내외로 커뮤니케이션하는데 유용하게 쓰인다.
 * 이렇게 Error 를 정의해서, 외부, 또는 다른 부서와 협력할때 상정하고
 * 작업하는 것이다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-03        NAHAEJUN              최초생성
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    OK(0,ErrorCategory.NORMAL,"OK"),

    BAD_REQUEST(10000,ErrorCategory.CLIENT_SIDE,"bad request"),
    SPRING_BAD_REQUEST(10001,ErrorCategory.CLIENT_SIDE,"Spring-detected bad request"),

    INTERNAL_ERROR(20000,ErrorCategory.SERVER_SIDE,"Internal error"),
    SPRING_INTERNAL_ERROR(20001,ErrorCategory.SERVER_SIDE,"Spring-detected Internal error");

    private final Integer code;
    /* 분류를 위한 카테코리 설정 */
    private final ErrorCategory errorCategory;
    private final String message;


    /*
    * 에러메세지를 default 로 넣어뒀지만 사용자가 직접 넣는것까지 고려하여
    * 오버로딩을 통해 2개의 getMessage() 를 작성
    * 예외를 넣거나, 직접 메세지를 넣으면 그것을 보여주고 그게 아니라면
    * 기본메세지 출력
    * */
    public String getMessage(Exception e) {
        return getMessage(e.getMessage());
    }
    /*
     * 예외를 넣거나, 직접 메세지를 넣으면 그것을 보여주고 그게 아니라면
     * 기본메세지 출력
    * */
    public String getMessage(String msg) {
        return Optional.ofNullable(msg)
                .filter(Predicate.not(String::isBlank))
                .orElse(getMessage()); // 기본메세지 @Getter 를 통해 만들어진 getMessage() 출력
    }

    public boolean isClientSideError(){return this.getErrorCategory() == ErrorCategory.CLIENT_SIDE;}

    public boolean isServerSideError(){return this.getErrorCategory() == ErrorCategory.SERVER_SIDE;}

    public String toString(){
        return String.format("%s: (%d)", name(), this.getCode());
    }

    // 이안에서만 사용할거니 여기서만 작성 nested enum
    public enum ErrorCategory {
        NORMAL,CLIENT_SIDE,SERVER_SIDE;
    }
}
