package com.taswy.choujiang.user.config;

import com.taswy.choujiang.common.condition.UserCondition;
import com.taswy.choujiang.common.util.JwtTool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public UserCondition userCondition() {
        return new UserCondition();
    }

}
