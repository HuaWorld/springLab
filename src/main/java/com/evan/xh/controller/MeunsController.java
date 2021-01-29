package com.evan.xh.controller;

import com.evan.xh.pojo.AdminMenu;
import com.evan.xh.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MeunsController {

    @Autowired
    AdminMenuService adminMenuService;

    @GetMapping("/api/menu")
    public List<AdminMenu> menu(){
        return adminMenuService.getMenuByCurrentUser();
    }
}
