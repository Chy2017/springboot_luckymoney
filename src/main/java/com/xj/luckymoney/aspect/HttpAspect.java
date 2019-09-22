package com.xj.luckymoney.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xj
 * 2019-09-22 16:29
 **/
@Aspect
@Component //把这个文件引入到spring容器中去
public class HttpAspect {

    //org.slf4j.Logger是spring自带的一个日志的框架
    private final static Logger logger= LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.xj.luckymoney.controller.LuckymoneyController.*(..))")
    public void log(){ // 定义一个公共的方法
    }

    @Before("log()")
    public  void doBefore(JoinPoint joinPoint){
        //我们在这里需要记录http请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        logger.info("url = {}", request.getRequestURL());
        //method
        logger.info("method = {}",request.getMethod());
        //ip
        logger.info("ip = {}", request.getRemoteAddr());
        //类方法
        logger.info("class_method = {}", joinPoint.getSignature().getDeclaringTypeName()
                    + "_" + joinPoint.getSignature().getName());
        //参数
        logger.info("args = {}", joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter(){
        logger.info("222222222");
    }

    // 检查我们回复的请求，有的时候十分有用
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterRetruning(Object object){
        logger.info("response = {}", object.toString());
    }
}
