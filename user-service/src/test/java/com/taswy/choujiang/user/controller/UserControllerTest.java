package com.taswy.choujiang.user.controller;

import cn.hutool.json.JSONObject;
import com.taswy.choujiang.user.UserApplication;
import com.taswy.choujiang.user.domain.dto.LoginDTO;
import com.taswy.choujiang.user.domain.vo.UserVo;
import com.taswy.choujiang.user.service.IUserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)

public class UserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
//    @Autowired
    private MockMvc mvc;

    private LoginDTO loginDTO;

    @MockBean
    private IUserService userService;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        loginDTO = new LoginDTO();
        loginDTO.setPassword("123456");
        loginDTO.setUsername("1");
        UserVo userVo = new UserVo();
        userVo.setId(1L);
        Mockito.when(userService.login(loginDTO)).thenReturn(userVo);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void login() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new JSONObject(loginDTO).toString())
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1L))
                .andReturn();
    }
}