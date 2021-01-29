package com.evan.xh.service;

import com.evan.xh.dao.AdminUserRoleDao;
import com.evan.xh.pojo.AdminRole;
import com.evan.xh.pojo.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminUserRoleService {
    @Autowired
    AdminUserRoleDao adminUserRoleDao;

    public List<AdminUserRole> listAllByUId(int Uid) {
        return adminUserRoleDao.findAllByUid(Uid);
    }

    @Transactional
    public void saveRoleChanges(int uid, List<AdminRole> roles) {
        adminUserRoleDao.deleteAllByUid(uid);
        List<AdminUserRole> urs = new ArrayList<>();
        roles.forEach(r -> {
            AdminUserRole ur = new AdminUserRole();
            ur.setUid(uid);
            ur.setRid(r.getId());
            urs.add(ur);
        });
        adminUserRoleDao.saveAll(urs);
    }

}
