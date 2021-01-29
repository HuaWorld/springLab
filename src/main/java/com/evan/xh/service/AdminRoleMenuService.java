package com.evan.xh.service;

import com.evan.xh.dao.AdminRoleMenuDao;
import com.evan.xh.pojo.AdminRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AdminRoleMenuService {

    @Autowired
    AdminRoleMenuDao adminRoleMenuDao;

    public List<AdminRoleMenu> findAllByRid(int rid) {
        return adminRoleMenuDao.findAllByRid(rid);
    }

    public List<AdminRoleMenu> findAllByRid(List<Integer> rids) {
        return adminRoleMenuDao.findAllByRid(rids);
    }

    public void save(AdminRoleMenu rm) {
        adminRoleMenuDao.save(rm);
    }

    @Modifying
    @Transactional
    public void updateRoleMenu(int rid, Map<String, List<Integer>> menusIds) {
        adminRoleMenuDao.deleteAllByRid(rid);
        List<AdminRoleMenu> rms = new ArrayList<>();
        for (Integer mid : menusIds.get("menusIds")) {
            AdminRoleMenu rm = new AdminRoleMenu();
            rm.setMid(mid);
            rm.setRid(rid);
            rms.add(rm);
        }

        adminRoleMenuDao.saveAll(rms);
    }


}
