package lqh.spring.cloud.modules.demo_admin_system.service.impl;

import lqh.spring.cloud.common.exception.Asserts;
import lqh.spring.cloud.common.utils.JwtTokenUtil;
import lqh.spring.cloud.modules.demo_admin_system.dto.AdminUserParam;
import lqh.spring.cloud.modules.demo_admin_system.entity.*;
import lqh.spring.cloud.modules.demo_admin_system.mapper.AdminUserMapper;
import lqh.spring.cloud.modules.demo_admin_system.service.AdminUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: Locyk
 * @time: 2021/7/12 0012 下午 2:42
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserServiceImpl.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private  AdminUserMapper adminMapper;


    @Override
    public AdminUser getAdminByUsername(String username) {
        AdminUser admin = null;
        if(admin!=null) return  admin;
        AdminUserExample example = new AdminUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<AdminUser> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            return admin;
        }
        return null;
    }

    @Override
    public AdminUser register(AdminUserParam umsAdminParam) {
        AdminUser umsAdmin = new AdminUser();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        AdminUserExample example = new AdminUserExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<AdminUser> umsAdminList = adminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                Asserts.fail("密码不正确");
            }
            if(!userDetails.isEnabled()){
                Asserts.fail("帐号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
            //insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

//    /**
//     * 添加登录记录
//     * @param username 用户名
//     */
//    private void insertLoginLog(String username) {
//        AdminUser admin = getAdminByUsername(username);
//        if(admin==null) return;
//        AdminLoginLog loginLog = new AdminLoginLog();
//        loginLog.setAdminId(admin.getId());
//        loginLog.setCreateTime(new Date());
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        loginLog.setIp(RequestUtil.getRequestIp(request));
//        loginLogMapper.insert(loginLog);
//    }

//    /**
//     * 根据用户名修改登录时间
//     */
//    private void updateLoginTimeByUsername(String username) {
//        AdminUser record = new AdminUser();
//        record.setLoginTime(new Date());
//        AdminUserExample example = new AdminUserExample();
//        example.createCriteria().andUsernameEqualTo(username);
//        adminMapper.updateByExampleSelective(record, example);
//    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

//    @Override
//    public AdminUser getItem(Long id) {
//        return adminMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public List<AdminUser> list(String keyword, Integer pageSize, Integer pageNum) {
//
//        AdminUserExample example = new AdminUserExample();
//        return adminMapper.selectByExample(example);
//    }

//    @Override
//    public int update(Long id, AdminUser admin) {
//        admin.setId(id);
//        AdminUser rawAdmin = adminMapper.selectByPrimaryKey(id);
//        if(rawAdmin.getPassword().equals(admin.getPassword())){
//            //与原加密密码相同的不需要修改
//            admin.setPassword(null);
//        }else{
//            //与原加密密码不同的需要加密修改
//            if(StrUtil.isEmpty(admin.getPassword())){
//                admin.setPassword(null);
//            }else{
//                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
//            }
//        }
//        int count = adminMapper.updateByPrimaryKeySelective(admin);
//        adminCacheService.delAdmin(id);
//        return count;
//    }
//
//    @Override
//    public int delete(Long id) {
//        adminCacheService.delAdmin(id);
//        int count = adminMapper.deleteByPrimaryKey(id);
//        adminCacheService.delResourceList(id);
//        return count;
//    }
//
//    @Override
//    public int updateRole(Long adminId, List<Long> roleIds) {
//        int count = roleIds == null ? 0 : roleIds.size();
//        //先删除原来的关系
//        AdminRoleRelationExample adminRoleRelationExample = new AdminRoleRelationExample();
//        adminRoleRelationExample.createCriteria().andAdminIdEqualTo(adminId);
//        adminRoleRelationMapper.deleteByExample(adminRoleRelationExample);
//        //建立新关系
//        if (!CollectionUtils.isEmpty(roleIds)) {
//            List<AdminRoleRelation> list = new ArrayList<>();
//            for (Long roleId : roleIds) {
//                AdminRoleRelation roleRelation = new AdminRoleRelation();
//                roleRelation.setAdminId(adminId);
//                roleRelation.setRoleId(roleId);
//                list.add(roleRelation);
//            }
//            adminRoleRelationMapper.insertList(list);
//        }
//        adminCacheService.delResourceList(adminId);
//        return count;
//    }
//
//    @Override
//    public List<AdminRole> getRoleList(Long adminId) {
//        return adminRoleRelationMapper.getRoleList(adminId);
//    }
//
//    @Override
//    public List<AdminResource> getResourceList(Long adminId) {
//        List<AdminResource> resourceList = adminCacheService.getResourceList(adminId);
//        if(CollUtil.isNotEmpty(resourceList)){
//            return  resourceList;
//        }
//        resourceList = adminRoleRelationMapper.getResourceList(adminId);
//        if(CollUtil.isNotEmpty(resourceList)){
//            adminCacheService.setResourceList(adminId,resourceList);
//        }
//        return resourceList;
//    }
//
//    @Override
//    public int updatePassword(UpdateAdminPasswordParam param) {
//        if(StrUtil.isEmpty(param.getUsername())
//                ||StrUtil.isEmpty(param.getOldPassword())
//                ||StrUtil.isEmpty(param.getNewPassword())){
//            return -1;
//        }
//        AdminUserExample example = new AdminUserExample();
//        example.createCriteria().andUsernameEqualTo(param.getUsername());
//        List<AdminUser> adminList = adminMapper.selectByExample(example);
//        if(CollUtil.isEmpty(adminList)){
//            return -2;
//        }
//        AdminUser umsAdmin = adminList.get(0);
//        if(!passwordEncoder.matches(param.getOldPassword(),umsAdmin.getPassword())){
//            return -3;
//        }
//        umsAdmin.setPassword(passwordEncoder.encode(param.getNewPassword()));
//        adminMapper.updateByPrimaryKey(umsAdmin);
//        adminCacheService.delAdmin(umsAdmin.getId());
//        return 1;
//    }
//
    @Override
    public UserDetails loadUserByUsername(String username){
        //获取用户信息
        AdminUser admin = getAdminByUsername(username);
//        if (admin != null) {
//            List<AdminResource> resourceList = getResourceList(admin.getId());
//            return new AdminUserDetails(admin,resourceList);
//        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
