package lqh.spring.cloud.modules.demo_admin_system.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
/**
 * @description: 修改用户名密码参数
 * @author: Locyk
 * @time: 2021/7/12 0012 下午 4:20
 */
@Getter
@Setter
public class UpdateAdminPasswordParam {
    @NotEmpty
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @NotEmpty
    @ApiModelProperty(value = "旧密码", required = true)
    private String oldPassword;
    @NotEmpty
    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;
}
