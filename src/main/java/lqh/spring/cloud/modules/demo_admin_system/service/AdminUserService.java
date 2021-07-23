package lqh.spring.cloud.modules.demo_admin_system.service;

import lqh.spring.cloud.modules.demo_admin_system.dto.AdminUserParam;
import lqh.spring.cloud.modules.demo_admin_system.dto.UpdateAdminPasswordParam;
import lqh.spring.cloud.modules.demo_admin_system.entity.AdminResource;
import lqh.spring.cloud.modules.demo_admin_system.entity.AdminRole;
import lqh.spring.cloud.modules.demo_admin_system.entity.AdminUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminUserService {

    /**
     * 根据用户名获取后台管理员
     */
    AdminUser getAdminByUsername(String username);

    /**
     * 注册功能
     */
    AdminUser register(AdminUserParam adminUserParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

//    /**
//     * 根据用户id获取用户
//     */
//    AdminUser getItem(Long id);
//
//    /**
//     * 根据用户名或昵称分页查询用户
//     */
//    List<AdminUser> list(String keyword, Integer pageSize, Integer pageNum);
//
//    /**
//     * 修改指定用户信息
//     */
//    int update(Long id, AdminUser admin);
//
//    /**
//     * 删除指定用户
//     */
//    int delete(Long id);
//
//    /**
//     * 修改用户角色关系
//     */
//    @Transactional
//    int updateRole(Long adminId, List<Long> roleIds);
//
//    /**
//     * 获取用户对应角色
//     */
//    List<AdminRole> getRoleList(Long adminId);
//
//    /**
//     * 获取指定用户的可访问资源
//     */
//    List<AdminResource> getResourceList(Long adminId);
//
//    /**
//     * 修改密码
//     */
//    int updatePassword(UpdateAdminPasswordParam updatePasswordParam);
//
    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);
}
