package com.luzj.kpimanage.test.service;

import com.luzj.kpimanage.common.ResultValue;
import com.luzj.kpimanage.test.entity.User;

/**
 * @author luzj
 * @description: 用户操作接口
 * @date 2018/3/13
 */
public interface UserService {
    ResultValue findUser(String name);

    ResultValue findUser(String name, int age);

    ResultValue addUser(User user);
}
