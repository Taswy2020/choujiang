package com.taswy.choujiang.common.config;

import com.taswy.choujiang.common.condition.UserCondition;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import java.time.Duration;

@Data
@ConfigurationProperties(prefix = "cj.jwt")
//@ConditionalOnClass(UserCondition.class)
public class JwtProperties {
    private String location;
    private String password;
    private String alias;
    private Duration tokenTTL = Duration.ofMinutes(10);
}