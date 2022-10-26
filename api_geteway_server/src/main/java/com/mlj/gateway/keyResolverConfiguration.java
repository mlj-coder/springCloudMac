package com.mlj.gateway;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class keyResolverConfiguration {

    /**
     * 基于请求路径当作redis的key值
     * @return
     */
    //@Bean
    public KeyResolver pathKeyResolver() {
        //自定义的keyResolver
        return new KeyResolver() {
            /**
             *
             * @param exchange Gateway上下文参数
             * @return
             */
            public Mono<String> resolve(ServerWebExchange exchange) {
                return Mono.just(exchange.getRequest().getPath().toString());
            }
        };
    }
    /**
     * 基于请求参数的限流
     */
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(
                exchange.getRequest().getQueryParams().getFirst("userId")
        );
    }
}
