package com.songblog.controller;

import com.songblog.entity.User;
import com.songblog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/")
//    @ResponseBody
//    public List<User> index(){
    public String index(){
        List<User> userList = iUserService.list();
//        return userList;
        return "index";
    }
}
