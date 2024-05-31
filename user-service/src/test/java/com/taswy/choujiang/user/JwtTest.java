package com.taswy.choujiang.user;

import com.taswy.choujiang.common.util.JwtTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {
    @Autowired
    private JwtTool jwtTool;

    @Test
    public void jwtTest() {
        Long userId = 1111L;
        String token = jwtTool.createToken(userId, Duration.ofDays(1));
        Assert.isTrue( jwtTool.parseToken(token).equals(userId), "1");
        Assert.isTrue( !jwtTool.parseToken(token).equals(2222L), "2");
    }

    @Test
    public void test() {
        System.out.println("OK");
    }
}
