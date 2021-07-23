package lqh.spring.cloud.modules.demo_admin_system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lqh.spring.cloud.common.entity.BaseEntity;

import java.util.Date;

/**
 * @description:
 * @author: Locyk
 * @time: 2021/7/12 0012 上午 8:34
 */
@Data
@TableName("gyt_contract_down_entry")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractDownEntry对象", description = "ContractDownEntry对象")
public class AdminUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "备注信息")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最后登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
    private Integer status;


}
