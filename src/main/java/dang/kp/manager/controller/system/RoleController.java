package dang.kp.manager.controller.system;

import dang.kp.manager.common.result.Result;
import dang.kp.manager.pojo.BaseAdminRole;
import dang.kp.manager.response.PageDataResult;
import dang.kp.manager.service.AdminRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Title: RoleController
 * @Description: 角色管理
 * @author: youqing
 * @version: 1.0
 * @date: 2018/11/21 13:43
 */
@Controller
@RequestMapping("role")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminRoleService adminRoleService;

    /**
     * 跳转到角色管理
     *
     * @return
     */
    @RequestMapping("/roleManage")
    public String toPage() {
        logger.info("进入角色管理");
        return "/role/roleManage";
    }

    /**
     * 功能描述: 获取角色列表
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/21 14:29
     */
    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
    @ResponseBody
    public PageDataResult getRoleList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize) {
        logger.info("获取角色列表");
        PageDataResult pdr = new PageDataResult();
        try {
            if (null == pageNum) {
                pageNum = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            // 获取角色列表
            pdr = adminRoleService.getRoleList(pageNum, pageSize);
            logger.info("角色列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("角色列表查询异常！", e);
        }
        return pdr;
    }

    /**
     * 功能描述: 获取角色列表
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/12/3 13:22
     */
    @GetMapping("getRoles")
    @ResponseBody
    public List<BaseAdminRole> getRoles() {
        logger.info("获取角色列表");
        return adminRoleService.getRoles();
    }

    /**
     * 述: 设置角色[新增或更新]
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/12/3 10:54
     */
    @PostMapping("setRole")
    @ResponseBody
    public Result setRole(BaseAdminRole role) {
        logger.info("设置角色[新增或更新]！role:" + role);
        if (role.getId() == null) {
            //新增角色
            return adminRoleService.addRole(role);
        } else {
            //修改角色
            return adminRoleService.updateRole(role);
        }
    }


    /**
     * 功能描述: 删除/恢复角色
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/21 16:00
     */
    @PostMapping("updateRoleStatus")
    @ResponseBody
    public Result updateRoleStatus(@RequestParam("id") String id, @RequestParam("status") Integer status) {
        logger.info("删除/恢复角色！id:" + id + " status:" + status);
        if (status == 0) {
            //删除角色
            return adminRoleService.delRole(id, status);
        } else {
            //恢复角色
            return adminRoleService.recoverRole(id, status);
        }
    }

}
