package lqh.spring.cloud.common.exception;

import lqh.spring.cloud.common.api.IErrorCode;

/**
 * @description: 自定义API异常
 * @author: Locyk
 * @time: 2021/7/12 0012 下午 3:22
 */
public class ApiException extends RuntimeException{
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
