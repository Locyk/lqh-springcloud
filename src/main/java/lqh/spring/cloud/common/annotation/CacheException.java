package lqh.spring.cloud.common.annotation;

import java.lang.annotation.*;

/**
 * @description: 自定义注解，有该注解的缓存方法会抛出异常
 * @author: Locyk
 * @time: 2021/7/12 0012 下午 5:11
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}
