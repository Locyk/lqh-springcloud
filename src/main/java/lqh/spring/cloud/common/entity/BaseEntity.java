package lqh.spring.cloud.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @description: 实体基础类
 * @author: Locyk
 * @time: 2021/7/13 0013 下午 12:38
 */
public class BaseEntity implements Serializable {
    @JsonSerialize(
            using = ToStringSerializer.class
    )
    @ApiModelProperty("主键id")
    @TableId(
            value = "id",
            type = IdType.ASSIGN_ID
    )
    private Long id;
    @JsonSerialize(
            using = ToStringSerializer.class
    )
    @ApiModelProperty("创建人")
    private Long createUser;
    @JsonSerialize(
            using = ToStringSerializer.class
    )
    @ApiModelProperty("创建部门")
    private Long createDept;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @ApiModelProperty("创建时间")
    private Date createTime;
    @JsonSerialize(
            using = ToStringSerializer.class
    )
    @ApiModelProperty("更新人")
    private Long updateUser;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("业务状态")
    private Integer status;
    @TableLogic
    @ApiModelProperty("是否已删除")
    private Integer isDeleted;

    public BaseEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public Long getCreateUser() {
        return this.createUser;
    }

    public Long getCreateDept() {
        return this.createDept;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Long getUpdateUser() {
        return this.updateUser;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Integer getIsDeleted() {
        return this.isDeleted;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setCreateUser(final Long createUser) {
        this.createUser = createUser;
    }

    public void setCreateDept(final Long createDept) {
        this.createDept = createDept;
    }

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateUser(final Long updateUser) {
        this.updateUser = updateUser;
    }

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public void setIsDeleted(final Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseEntity)) {
            return false;
        } else {
            BaseEntity other = (BaseEntity)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label107: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label107;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label107;
                    }

                    return false;
                }

                Object this$createUser = this.getCreateUser();
                Object other$createUser = other.getCreateUser();
                if (this$createUser == null) {
                    if (other$createUser != null) {
                        return false;
                    }
                } else if (!this$createUser.equals(other$createUser)) {
                    return false;
                }

                Object this$createDept = this.getCreateDept();
                Object other$createDept = other.getCreateDept();
                if (this$createDept == null) {
                    if (other$createDept != null) {
                        return false;
                    }
                } else if (!this$createDept.equals(other$createDept)) {
                    return false;
                }

                label86: {
                    Object this$updateUser = this.getUpdateUser();
                    Object other$updateUser = other.getUpdateUser();
                    if (this$updateUser == null) {
                        if (other$updateUser == null) {
                            break label86;
                        }
                    } else if (this$updateUser.equals(other$updateUser)) {
                        break label86;
                    }

                    return false;
                }

                label79: {
                    Object this$status = this.getStatus();
                    Object other$status = other.getStatus();
                    if (this$status == null) {
                        if (other$status == null) {
                            break label79;
                        }
                    } else if (this$status.equals(other$status)) {
                        break label79;
                    }

                    return false;
                }

                label72: {
                    Object this$isDeleted = this.getIsDeleted();
                    Object other$isDeleted = other.getIsDeleted();
                    if (this$isDeleted == null) {
                        if (other$isDeleted == null) {
                            break label72;
                        }
                    } else if (this$isDeleted.equals(other$isDeleted)) {
                        break label72;
                    }

                    return false;
                }

                Object this$createTime = this.getCreateTime();
                Object other$createTime = other.getCreateTime();
                if (this$createTime == null) {
                    if (other$createTime != null) {
                        return false;
                    }
                } else if (!this$createTime.equals(other$createTime)) {
                    return false;
                }

                Object this$updateTime = this.getUpdateTime();
                Object other$updateTime = other.getUpdateTime();
                if (this$updateTime == null) {
                    if (other$updateTime != null) {
                        return false;
                    }
                } else if (!this$updateTime.equals(other$updateTime)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BaseEntity;
    }

    public int hashCode() {
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $createUser = this.getCreateUser();
        result = result * 59 + ($createUser == null ? 43 : $createUser.hashCode());
        Object $createDept = this.getCreateDept();
        result = result * 59 + ($createDept == null ? 43 : $createDept.hashCode());
        Object $updateUser = this.getUpdateUser();
        result = result * 59 + ($updateUser == null ? 43 : $updateUser.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $isDeleted = this.getIsDeleted();
        result = result * 59 + ($isDeleted == null ? 43 : $isDeleted.hashCode());
        Object $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
        Object $updateTime = this.getUpdateTime();
        result = result * 59 + ($updateTime == null ? 43 : $updateTime.hashCode());
        return result;
    }

    public String toString() {
        return "BaseEntity(id=" + this.getId() + ", createUser=" + this.getCreateUser() + ", createDept=" + this.getCreateDept() + ", createTime=" + this.getCreateTime() + ", updateUser=" + this.getUpdateUser() + ", updateTime=" + this.getUpdateTime() + ", status=" + this.getStatus() + ", isDeleted=" + this.getIsDeleted() + ")";
    }
}
