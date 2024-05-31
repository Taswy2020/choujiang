package com.taswy.choujiang.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taswy.choujiang.common.config.JwtProperties;
import com.taswy.choujiang.common.exception.BadRequestException;
import com.taswy.choujiang.common.util.JwtTool;
import com.taswy.choujiang.user.domain.dto.LoginDTO;
import com.taswy.choujiang.user.domain.po.User;
import com.taswy.choujiang.user.domain.vo.UserVo;
import com.taswy.choujiang.user.mapper.UserMapper;
import com.taswy.choujiang.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final JwtProperties jwtProperties;
    private final JwtTool jwtTool;

    private final UserMapper userMapper;

    @Override
    public UserVo login(LoginDTO loginDTO) {
        // 1. 获取用户名和密码
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        // 2. 根据用户名查询用户存在
        User user = userMapper.selectById(Long.valueOf(username));
        Assert.notNull(user);
        // 3. 校验密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("用户名或密码错误！");
        }
        // 4. 生成token
        String token = jwtTool.createToken(user.getId(), jwtProperties.getTokenTTL());

        // 5. 封装成vo
        UserVo vo = new UserVo();
        vo.setId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setToken(token);
        return vo;

    }
}
