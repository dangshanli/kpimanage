package com.luzj.kpimanage.test.service.impl;

import com.luzj.kpimanage.common.ResultValue;
import com.luzj.kpimanage.test.entity.User;
import com.luzj.kpimanage.test.repository.UserRepository;
import com.luzj.kpimanage.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author luzj
 * @description:
 * @date 2018/3/13
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    ResultValue rv;//返回json模板

    /**
     * 根据名称查询用户
     *
     * @param name
     * @return
     */
    @Override
    public ResultValue findUser(String name) {
        rv = new ResultValue();
        List<User> users = null;
        try {

            users = userRepository.findByName(name);
        } catch (Exception e) {
            rv.setCode(-1);
            rv.setMsg("异常");
            return rv;
        }
        rv.setData(users);
        return rv;
    }

    /**
     * 根据名称和年龄查询用户
     *
     * @param name
     * @param age
     * @return
     */
    @Override
    public ResultValue findUser(String name, int age) {
        rv = new ResultValue();
        User user = null;
        try {
            user = userRepository.findByNameAndAge(name, age);
        } catch (Exception e) {
            rv.setCode(-1);
            rv.setMsg("异常");
            return rv;
        }
        rv.setData(user);
        return rv;
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(value = "transactionManagerOrder")
    public ResultValue addUser(User user) {
        rv = new ResultValue();
        User user1 = null;
        try {
            user1 = userRepository.save(user);
        } catch (Exception e) {
            rv.setCode(-1);
            rv.setMsg("异常");
            return rv;
        }
        rv.setData(user);
        return rv;
    }
}
