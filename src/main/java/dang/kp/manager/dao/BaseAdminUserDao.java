package dang.kp.manager.dao;

import dang.kp.manager.pojo.BaseAdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseAdminUserDao extends JpaRepository<BaseAdminUser, String> {

    BaseAdminUser getUserBySysUserNameAndUserStatus(String sysUserName, Integer userStatus);
}
