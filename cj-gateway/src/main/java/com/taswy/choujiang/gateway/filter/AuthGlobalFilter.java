package com.taswy.choujiang.gateway.filter;

import cn.hutool.core.collection.CollUtil;
import com.taswy.choujiang.common.exception.UnauthorizedException;
import com.taswy.choujiang.common.util.JwtTool;
import com.taswy.choujiang.gateway.config.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@EnableConfigurationProperties(AuthProperties.class)
@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    private final AuthProperties authProperties;

    private final AntPathMatcher antPathMatcher;

    private final JwtTool jwtTool;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 获取request
        ServerHttpRequest request = exchange.getRequest();
        // 2. 判断是否需要处理，否则放行
        if(isExclude(request.getPath().toString())) {
            return chain.filter(exchange);
        }
        // 3. 获取header中的token
        String token = null;
        List<String> authorizations = request.getHeaders().get("authorization");
        if(!CollUtil.isEmpty(authorizations)) {
            token = authorizations.get(0);
        }
        // 4. 解析验证token
        Long userId = null;
        try {
            userId = jwtTool.parseToken(token);
        }
        catch (UnauthorizedException e) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // response本身只有code，没有message，所以如果直接用response的code，就不做别的事情
            return response.setComplete();
        }
        // 5. 如果token有效，则将userId加入token
        String userInfo = userId.toString(); // lambda表达式中的外部变量一定是不可变的
        ServerWebExchange webExchange = exchange.mutate() // 获得一个builder
                .request(builder -> builder.header("user-info", userInfo))
                .build();
        System.out.println(userInfo);
        // 6. 放行
        return chain.filter(webExchange);
    }

    // 是否不需要拦截
    private boolean isExclude(String path) {
        for (String excludePath : authProperties.getExcludePaths()) {
            if (antPathMatcher.match(excludePath, path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
