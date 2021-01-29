package com.evan.xh.service;

import com.evan.xh.dao.AdminMenuDao;
import com.evan.xh.pojo.AdminMenu;
import com.evan.xh.pojo.AdminRoleMenu;
import com.evan.xh.pojo.AdminUserRole;
import com.evan.xh.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminMenuService {

    @Autowired
    AdminMenuDao adminMenuDao;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;


    public List<AdminMenu> getMenuByCurrentUser(){
        // 从数据库中获取当前用户
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByName(username);
        // 获取当前用户对应的所有角色id列表
        List<Integer> rids = adminUserRoleService.listAllByUId(user.getId())
                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());
        // 查询出这些角色对应的所有菜单项
        List<Integer> mids = adminRoleMenuService.findAllByRid(rids).stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> adminMenus = adminMenuDao.findAllById(mids).stream().distinct().collect(Collectors.toList());
        // 处理菜单结构
        handleMenus(adminMenus);
        return adminMenus;
    }

    public void handleMenus(List<AdminMenu> adminMenus){
        adminMenus.forEach(m ->{
            List<AdminMenu> children = getAllByParentId(m.getId());
            m.setChildren(children);
        });
        adminMenus.removeIf(m -> m.getParentId() != 0);

    }

    private List<AdminMenu> getAllByParentId(int rid) {
        List<Integer> menuIds = adminRoleMenuService.findAllByRid(rid)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDao.findAllById(menuIds);

        handleMenus(menus);
        return menus;
    }

}
