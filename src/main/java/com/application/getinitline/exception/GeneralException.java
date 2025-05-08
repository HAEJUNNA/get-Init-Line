package com.application.getinitline.exception;

import com.application.getinitline.constant.ErrorCode;
import lombok.Getter;

/**
 * packageName    : com.application.getinitline.exception
 * fileName       : GeneralException
 * author         : NAHAEJUN
 * date           : 2025-03-03
 * description    :
 *
 * 우리가 예상하고 처리할수있는 예외와, 우리가 예상할수 없는 예외를 분리하고자 작성
 *
 *
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-03        NAHAEJUN              최초생성
 */
@Getter
public class GeneralException extends RuntimeException{
    //RuntimeException 을 상속받아 해당 클래스 안에 메소스들을 재구현햇다.
    //오버라이딩 - 실질적으로 재구성이아닌, 몇가지를 제외 그냥 필요 시그니처를 따른것뿐이다.
    private final ErrorCode errorCode;

    public GeneralException() {
        super();
        this.errorCode = ErrorCode.INTERNAL_ERROR;
    }

    public GeneralException(String message) {
        super(message);
        this.errorCode = ErrorCode.INTERNAL_ERROR;
    }

    public GeneralException(String message , Throwable cause) {
        super(message, cause);
        this.errorCode = ErrorCode.INTERNAL_ERROR;
    }

    public GeneralException(Throwable cause) {
        super(cause);
        this.errorCode = ErrorCode.INTERNAL_ERROR;
    }

    public GeneralException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = ErrorCode.INTERNAL_ERROR;
    }

    public GeneralException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public GeneralException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    public GeneralException(ErrorCode errorCode, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(errorCode.getMessage(), cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode.INTERNAL_ERROR;
    }
}
