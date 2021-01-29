package com.evan.xh.dao;

import com.evan.xh.pojo.AdminUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminUserRoleDao extends JpaRepository<AdminUserRole,Integer> {
    void deleteAllByUid(int uid);

    List<AdminUserRole> findAllByUid(int uid);
}
