package com.taswy.choujiang.gateway.config;

import com.taswy.choujiang.common.condition.UserCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

@Configuration
public class UserConfig {
    @Bean
    public UserCondition userCondition() {
        return new UserCondition();
    }

    @Bean
    AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }
}
