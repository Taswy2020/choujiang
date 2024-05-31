package com.taswy.choujiang.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordEncoderTest {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void passwordEncoderTest() {
        String encodePassword = passwordEncoder.encode("123456");
        System.out.println(encodePassword);
        Assert.isTrue( passwordEncoder.matches("123456",encodePassword));
    }
}
