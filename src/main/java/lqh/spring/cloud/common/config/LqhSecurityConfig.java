package lqh.spring.cloud.common.config;


import lqh.spring.cloud.common.component.DynamicSecurityService;
import lqh.spring.cloud.modules.demo_admin_system.entity.AdminResource;
import lqh.spring.cloud.modules.demo_admin_system.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: Lqh的SpringSecurity的配置
 * @author: Locyk
 * @time: 2021/7/13 0013 上午 11:15
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LqhSecurityConfig extends SecurityConfig{

    @Autowired
    private AdminUserService adminService;


    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> adminService.loadUserByUsername(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
//                List<AdminResource> resourceList = resourceService.listAll();
//                for (AdminResource resource : resourceList) {
//                    map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
//                }
                return map;
            }
        };
    }
}
