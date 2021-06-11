package dang.kp.manager.controller.system;


import dang.kp.manager.common.result.Result;
import dang.kp.manager.dto.PermissionDTO;
import dang.kp.manager.pojo.BaseAdminPermission;
import dang.kp.manager.pojo.BaseAdminUser;
import dang.kp.manager.response.PageDataResult;
import dang.kp.manager.service.AdminPermissionService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: PermissionController
 * @Description: 权限管理
 * @author: youqing
 * @version: 1.0
 * @date: 2018/11/29 18:16
 */
@Controller
@RequestMapping("permission")
public class PermissionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminPermissionService permissionService;

    /**
     * 功能描述: 跳到权限管理
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/30 9:22
     */
    @RequestMapping("permissionManage")
    public String permissionManage() {
        logger.info("进入权限管理");
        return "/permission/permissionManage";
    }


    /**
     * 功能描述: 获取权限菜单列表
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/30 10:30
     */
    @PostMapping("permissionList")
    @ResponseBody
    public PageDataResult permissionList(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize) {
        logger.info("获取权限菜单列表");
        PageDataResult pdr = new PageDataResult();
        try {
            if (null == pageNum) {
                pageNum = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            // 获取服务类目列表
            pdr = permissionService.getPermissionList(pageNum, pageSize);
            logger.info("权限菜单列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("权限菜单列表查询异常！", e);
        }
        return pdr;
    }


    /**
     * 功能描述: 获取根权限菜单列表
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/30 11:35
     */
    @GetMapping("parentPermissionList")
    @ResponseBody
    public List<PermissionDTO> parentPermissionList() {
        logger.info("获取根权限菜单列表");
        return permissionService.parentPermissionList();
    }


    /**
     * 功能描述:设置权限[新增或更新]
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/30 9:42
     */
    @PostMapping("setPermission")
    @ResponseBody
    public Result setPermission(BaseAdminPermission permission) {
        logger.info("设置权限[新增或更新]！permission:" + permission);
        if (permission.getId() == null) {
            //新增权限
            return permissionService.addPermission(permission);
        } else {
            //修改权限
            return permissionService.updatePermission(permission);
        }
    }

    /**
     * 功能描述: 删除权限菜单
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/30 12:02
     */
    @PostMapping("del")
    @ResponseBody
    public Result del(@RequestParam("id") String id) {
        logger.info("删除权限菜单！id:" + id);
        //删除服务类目类型
        return permissionService.del(id);
    }


    /**
     * 功能描述: 获取登陆用户的权限
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/12/4 9:48
     */
    @GetMapping("getUserPerms")
    @ResponseBody
    public Result getUserPerms() {
        logger.info("获取登陆用户的权限");
        BaseAdminUser user = (BaseAdminUser) SecurityUtils.getSubject().getPrincipal();
        return permissionService.getUserPerms(user);
    }

}
