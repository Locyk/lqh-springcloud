package lqh.spring.cloud.modules.demo_admin_system.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @description:  用户登录参数
 * @author: Locyk
 * @time: 2021/7/13 0013 上午 10:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AdminLoginParam {

    @NotEmpty
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @NotEmpty
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
