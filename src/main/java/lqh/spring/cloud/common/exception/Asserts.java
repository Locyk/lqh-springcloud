package lqh.spring.cloud.common.exception;

import lqh.spring.cloud.common.api.IErrorCode;

/**
 * @description: 断言处理类，用于抛出各种API异常
 * @author: Locyk
 * @time: 2021/7/12 0012 下午 3:22
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
