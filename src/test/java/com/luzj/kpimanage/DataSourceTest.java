package com.luzj.kpimanage;

import com.luzj.kpimanage.test.entity.User;
import com.luzj.kpimanage.test.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author luzj
 * @description: 测试第二数据源 mysql
 * @date 2018/3/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KpimanageApplication.class)
public class DataSourceTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test(){
        User user1 =userRepository.save(new User("老列宁",44));
        List<User> users = userRepository.findByName("老列宁");
        Assert.assertEquals("老列宁",users.get(0).getName());
    }
}
