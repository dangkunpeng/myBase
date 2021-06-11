package dang.kp.manager.dao;


import dang.kp.manager.pojo.BaseAdminPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseAdminPermissionDao extends JpaRepository<BaseAdminPermission, String> {

    List<BaseAdminPermission> findByPid(String pid);
}