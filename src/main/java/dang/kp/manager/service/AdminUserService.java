package dang.kp.manager.service;

import dang.kp.manager.common.result.Result;
import dang.kp.manager.dto.UserSearchDTO;
import dang.kp.manager.pojo.BaseAdminUser;
import dang.kp.manager.response.PageDataResult;


/**
 * @Title: AdminUserService
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/11/21 11:04
 */
public interface AdminUserService {

    PageDataResult getUserList(UserSearchDTO userSearch, Integer pageNum, Integer pageSize);

    Result addUser(BaseAdminUser user);

    Result updateUser(BaseAdminUser user);

    BaseAdminUser getUserById(String id);

    BaseAdminUser findByUserName(String userName);

    int updatePwd(String userName,String password);

    Result delUser(String id,Integer status);

    Result recoverUser(String id,Integer status);
}
