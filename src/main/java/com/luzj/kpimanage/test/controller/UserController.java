package com.luzj.kpimanage.test.controller;

import com.luzj.kpimanage.common.ResultValue;
import com.luzj.kpimanage.test.entity.User;
import com.luzj.kpimanage.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luzj
 * @description:
 * @date 2018/3/13
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findUser",method = RequestMethod.GET)
    public ResultValue getUser(String name){
        ResultValue rv = null;
        rv = userService.findUser(name);
        return rv;
    }

    @RequestMapping("/addUser")
    public ResultValue addUser(User user){
        ResultValue rv = userService.addUser(user);
        return rv;
    }

}
