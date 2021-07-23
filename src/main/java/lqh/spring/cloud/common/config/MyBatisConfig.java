package lqh.spring.cloud.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description:
 * @author: Locyk
 * @time: 2021/7/13 0013 下午 4:14
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"lqh.spring.cloud.modules.demo_admin_system.mapper"})
public class MyBatisConfig {
}
