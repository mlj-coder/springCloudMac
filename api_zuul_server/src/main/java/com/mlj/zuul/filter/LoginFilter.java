package com.mlj.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义的zuul过滤器
 * 继承抽象父类
 */
//交给spring容器管理
@Component
public class LoginFilter extends ZuulFilter {
    /**
     * 自定义过滤器类型
     * pre
     * routing
     * post
     * error
     */
    public String filterType() {
        return "pre";
    }

    /**
     * 指定过滤器的执行顺序
     *  返回值越小，执行顺序也高
     */
    public int filterOrder() {
        return 1;
    }

    /**
     * true：使用此过滤器
     * false：相反
     */
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 执行过滤器中业务逻辑
     * 身份认真
     *      1。所有的请求需要携带一个参数：access-token
     *      2。获取request请求
     *      3。通过request获取参数access-token
     *      4。token==null身份验证失败，token!=null 成功
     * 在zuul网关中，通过requestContext的上下文对象，可以获取对象request对象
     */
    public Object run() throws ZuulException {
        //System.out.println("执行了过滤器");
        //1。获取zuul提供的上下文对象requestContext
        RequestContext ctx = RequestContext.getCurrentContext();
        //2。从requestContext中获取request
        HttpServletRequest request = ctx.getRequest();
        //3。获取请求参数access-token
        String touken = request.getParameter("access-token");
        //4。判断
        if(touken==null){
            ctx.setSendZuulResponse(false);//拦截请求
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }

        return null;
    }
}
