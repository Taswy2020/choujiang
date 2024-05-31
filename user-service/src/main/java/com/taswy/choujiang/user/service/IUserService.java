package com.taswy.choujiang.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.taswy.choujiang.user.domain.dto.LoginDTO;
import com.taswy.choujiang.user.domain.po.User;
import com.taswy.choujiang.user.domain.vo.UserVo;

public interface IUserService extends IService<User> {
    UserVo login(LoginDTO loginDTO);
}
