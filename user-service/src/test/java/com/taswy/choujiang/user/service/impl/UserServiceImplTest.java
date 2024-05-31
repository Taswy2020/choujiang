package com.taswy.choujiang.user.service.impl;

import com.taswy.choujiang.user.UserApplication;
import com.taswy.choujiang.user.domain.po.User;
import com.taswy.choujiang.user.domain.vo.UserVo;
import com.taswy.choujiang.user.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
class UserServiceImplTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        //userMapper = Mockito.mock(UserMapper.class);
        User user = new User();
        user.setId(1L);
        user.setUsername("1");
        Mockito.when(userMapper.selectById(1L)).thenReturn(user);

    }

    @Test
    void login() {
    }
}