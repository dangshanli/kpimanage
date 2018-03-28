package com.luzj.kpimanage.test.repository;

import com.luzj.kpimanage.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author luzj
 * @description: jpa测试
 * @date 2018/3/13
 */
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.name = ?1")
    List<User> findByName(String name);

    User findByNameAndAge(String name,int age);


}
