package com.evan.xh.dao;

import com.evan.xh.pojo.AdminMenu;
import com.evan.xh.pojo.AdminUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AdminMenuDao extends JpaRepository<AdminMenu,Integer> {
    AdminMenu findById(int id);
    List<AdminMenu> findAllByParentId(int parentId);
}
