package com.taswy.choujiang.user.controller;

import com.taswy.choujiang.user.domain.dto.LoginDTO;
import com.taswy.choujiang.user.domain.vo.UserVo;
import com.taswy.choujiang.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户接口相关") // tags用于对接口做分类标记
@RequestMapping("/user")
@RequiredArgsConstructor // 构造函数用于注入
public class UserController {
    private final IUserService userService;

    @ApiOperation("用户登录")
    @PostMapping("login")
    public UserVo login(@RequestBody @Validated LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }
}
