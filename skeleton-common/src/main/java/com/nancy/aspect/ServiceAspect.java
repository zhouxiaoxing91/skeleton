package com.nancy.aspect;

import com.alibaba.fastjson.JSON;
import com.nancy.response.DataResult;
import com.nancy.utils.CheckSign;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

//申明是个切面
@Aspect
//申明是个spring管理的bean
@Component
// 第一个加载
@Order(1)
@Slf4j
public class ServiceAspect {
    // 记录方法相应时间
    private static ThreadLocal<Long> timeTrace = new ThreadLocal<>() ;

    //申明一个切点 里面是 execution表达式
    @Pointcut("execution(public * com.nancy.service.impl.*Impl.*(..))")
    private void pointcut(){}

    //请求method前打印内容
    @Before(value = "pointcut()")
    public void methodBefore(JoinPoint joinPoint){
        timeTrace.set(System.currentTimeMillis());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //打印请求内容
        log.info("===============请求内容===============");
        log.info("请求地址:"+request.getRequestURL().toString());
        log.info("请求方式:"+request.getMethod());
        log.info("请求类方法:"+joinPoint.getSignature());
        log.info("请求类方法参数:"+ Arrays.toString(joinPoint.getArgs()));
        log.info("===============请求内容===============");
    }

    // 环绕通知
    @Around(value = "pointcut()")
    public Object methodAround(ProceedingJoinPoint joinPoint){
        DataResult ds ;
        try {
            // 签名校验
            if( !checkSign(joinPoint) ){
                ds = new DataResult() ;
                ds.setErrorCode("99999");
                ds.setErrorDesc("签名校验失败");
                ds.setElapsedMilliseconds(System.currentTimeMillis() - timeTrace.get()) ;
                log.error("签名校验失败", new Exception("签名校验失败"));
                return ds ;
            }

            // 执行对应的方法
            Object result = joinPoint.proceed() ;

            // 获得结果并返回响应时间（毫秒）
            if( result instanceof DataResult ){
                ds = (DataResult) result;
                ds.setElapsedMilliseconds(System.currentTimeMillis() - timeTrace.get()) ;
            }

            return result;
        } catch (Throwable t) {
            // 异常处理
            log.error("方法 "+joinPoint.getSignature()+"执行异常", t);
            ds = new DataResult() ;
            ds.setErrorCode("10000");
            ds.setErrorDesc(ExceptionUtils.getStackTrace(t));
            return ds ;
        }finally {
            // 清除时间记录
            timeTrace.remove();
        }
    }

    //在方法执行完结后打印返回内容
    @AfterReturning(returning = "o", pointcut = "pointcut()")
    public void methodAfterReturning(Object o){
        log.info("--------------返回内容----------------");
        log.info("Response内容:"+JSON.toJSONString(o));
        log.info("--------------返回内容----------------");
    }

    private boolean checkSign(ProceedingJoinPoint joinPoint){
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        if ( method.isAnnotationPresent(CheckSign.class) ) {
            CheckSign checkSign = method.getAnnotation(CheckSign.class) ;
            // 参数
            Object[] args = joinPoint.getArgs() ;
        }
        // TODO 签名校验
        return true ;
    }
}
