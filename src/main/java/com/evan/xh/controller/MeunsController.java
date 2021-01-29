package com.evan.xh.controller;

import com.evan.xh.pojo.AdminMenu;
import com.evan.xh.service.AdminMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Api(tags = "菜单管理")
@Controller
public class MeunsController {

    @Autowired
    AdminMenuService adminMenuService;

    @GetMapping("/api/menu")
    public List<AdminMenu> menu(){
        return adminMenuService.getMenuByCurrentUser();
    }
}
