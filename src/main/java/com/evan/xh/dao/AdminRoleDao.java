package com.evan.xh.dao;

import com.evan.xh.pojo.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRoleDao extends JpaRepository<AdminRole,Integer> {
    AdminRole findById(int id);
}
