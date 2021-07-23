package lqh.spring.cloud.modules.demo_admin_system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lqh.spring.cloud.common.api.CommonResult;
import lqh.spring.cloud.modules.demo_admin_system.dto.AdminLoginParam;
import lqh.spring.cloud.modules.demo_admin_system.dto.AdminUserParam;
import lqh.spring.cloud.modules.demo_admin_system.entity.AdminUser;
import lqh.spring.cloud.modules.demo_admin_system.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Locyk
 * @time: 2021/7/12 0012 下午 3:52
 */
@Controller
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class AdminUserController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private AdminUserService adminService;
//    @Autowired
//    private AdminRoleService roleService;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<AdminUser> register(@Validated @RequestBody AdminUserParam adminUserParam) {
        AdminUser umsAdmin = adminService.register(adminUserParam);
        if (umsAdmin == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody AdminLoginParam umsAdminLoginParam) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

//    @ApiOperation(value = "获取当前登录用户信息")
//    @RequestMapping(value = "/info", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult getAdminInfo(Principal principal) {
//        if(principal==null){
//            return CommonResult.unauthorized(null);
//        }
//        String username = principal.getName();
//        AdminUser umsAdmin = adminService.getAdminByUsername(username);
//        Map<String, Object> data = new HashMap<>();
//        data.put("username", umsAdmin.getUsername());
//        data.put("menus", roleService.getMenuList(umsAdmin.getId()));
//        data.put("icon", umsAdmin.getIcon());
//        List<AdminRole> roleList = adminService.getRoleList(umsAdmin.getId());
//        if(CollUtil.isNotEmpty(roleList)){
//            List<String> roles = roleList.stream().map(AdminRole::getName).collect(Collectors.toList());
//            data.put("roles",roles);
//        }
//        return CommonResult.success(data);
//    }
//
////    @ApiOperation(value = "登出功能")
////    @RequestMapping(value = "/logout", method = RequestMethod.POST)
////    @ResponseBody
////    public CommonResult logout() {
////        return CommonResult.success(null);
////    }
////
////    @ApiOperation("根据用户名或姓名分页获取用户列表")
////    @RequestMapping(value = "/list", method = RequestMethod.GET)
////    @ResponseBody
////    public CommonResult<CommonPage<AdminUser>> list(@RequestParam(value = "keyword", required = false) String keyword,
////                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
////                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
////        List<AdminUser> adminList = adminService.list(keyword, pageSize, pageNum);
////        return CommonResult.success(CommonPage.restPage(adminList));
////    }
//
//    @ApiOperation("获取指定用户信息")
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<AdminUser> getItem(@PathVariable Long id) {
//        AdminUser admin = adminService.getItem(id);
//        return CommonResult.success(admin);
//    }
//
//    @ApiOperation("修改指定用户信息")
//    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult update(@PathVariable Long id, @RequestBody AdminUser admin) {
//        int count = adminService.update(id, admin);
//        if (count > 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }
//
//    @ApiOperation("修改指定用户密码")
//    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult updatePassword(@Validated @RequestBody UpdateAdminPasswordParam updatePasswordParam) {
//        int status = adminService.updatePassword(updatePasswordParam);
//        if (status > 0) {
//            return CommonResult.success(status);
//        } else if (status == -1) {
//            return CommonResult.failed("提交参数不合法");
//        } else if (status == -2) {
//            return CommonResult.failed("找不到该用户");
//        } else if (status == -3) {
//            return CommonResult.failed("旧密码错误");
//        } else {
//            return CommonResult.failed();
//        }
//    }
//
//    @ApiOperation("删除指定用户信息")
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult delete(@PathVariable Long id) {
//        int count = adminService.delete(id);
//        if (count > 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }
//
//    @ApiOperation("修改帐号状态")
//    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult updateStatus(@PathVariable Long id,@RequestParam(value = "status") Integer status) {
//        AdminUser umsAdmin = new AdminUser();
//        umsAdmin.setStatus(status);
//        int count = adminService.update(id,umsAdmin);
//        if (count > 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }
//
//    @ApiOperation("给用户分配角色")
//    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult updateRole(@RequestParam("adminId") Long adminId,
//                                   @RequestParam("roleIds") List<Long> roleIds) {
//        int count = adminService.updateRole(adminId, roleIds);
//        if (count >= 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }
//
//    @ApiOperation("获取指定用户的角色")
//    @RequestMapping(value = "/role/{adminId}", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<List<AdminRole>> getRoleList(@PathVariable Long adminId) {
//        List<AdminRole> roleList = adminService.getRoleList(adminId);
//        return CommonResult.success(roleList);
//    }
}
