package lqh.spring.cloud.common.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @description: Redis配置类
 * @author: Locyk
 * @time: 2021/7/12 0012 下午 3:44
 */
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig{
}
