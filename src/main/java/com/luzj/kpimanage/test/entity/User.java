package com.luzj.kpimanage.test.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author luzj
 * @description:
 * @date 2018/3/13
 */
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="name")
    private String name;

    @Column(name = "age")
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User() {
    }
}
