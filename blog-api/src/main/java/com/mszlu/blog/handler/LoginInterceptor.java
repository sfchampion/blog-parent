package com.mszlu.blog.handler;

import com.alibaba.fastjson.JSON;
import com.mszlu.blog.dao.pojo.SysUser;
import com.mszlu.blog.service.LoginService;
import com.mszlu.blog.utils.UserThreadLocal;
import com.mszlu.blog.vo.ResultCode;
import com.mszlu.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sfChampion
 * @date 2023/2/9 9:59
 */

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在执行controller方法（Handler）之前进行执行
        /*
        * 1.需要判断请求的接口路径是否为HandlerMethod（controller方法）
        * 2.判断token是否为空，如果为空，未登录
        * 3.如果token不为空，登录验证 loginService checkToken
        * 4.如果认证成功，放行即可
        * */
        if (!(handler instanceof HandlerMethod)) {
            // handler 可能是 RequestResourceHandler springboot程序访问静态资源 默认去classpath下的static目录查询
            return true;
        }
        String token = request.getHeader("Authorization");
        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}",requestURI);
        log.info("request method:{}",request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");

        if (StringUtils.isBlank(token)) {
            Result result = Result.fail(ResultCode.NO_LOGIN.getCode(), ResultCode.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null) {
            Result result = Result.fail(ResultCode.NO_LOGIN.getCode(), ResultCode.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        // 登录验证成功 放行
        // 在controller中，直接获取用户信息
        UserThreadLocal.put(sysUser);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 如果不删除 ThreadLocal 中用完的信息 就会有内存泄漏的风险
        UserThreadLocal.remove();
    }
}
