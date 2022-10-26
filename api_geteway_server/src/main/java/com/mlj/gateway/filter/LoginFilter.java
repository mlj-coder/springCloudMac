package com.mlj.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义一个全局过滤器
 *      需要实现GlobalFilter、Ordered两个接口

 */
//交给spring容器管理
@Component
public class LoginFilter implements GlobalFilter, Ordered {
    /**
     * 执行过滤器业务逻辑
     * @param exchange 相当于请求和相应的上下文，可以获取request和response对象
     * @param chain
     *      对请求参数种的access-token进行判断
     *          如果存在此参数-成功
     *          如果不存在-失败
     */
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //System.out.println("执行过滤器1111111");
        //1.获取请求参数access-token
        ServerHttpRequest request = exchange.getRequest();
        String first = request.getQueryParams().getFirst("access-token");
        //2.判断是否存在
        if(first == null){
            //3.不存在：认证失败
            System.out.println("没有登陆");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete(); //请求结束
        }
        //4.存在：继续执行
        return chain.filter(exchange);//继续向下执行
    }

    /**
     * 指定过滤器的执行顺序，返回值越小 执行优先级越高
     * @return
     */
    public int getOrder() {
        return 0;
    }
}
