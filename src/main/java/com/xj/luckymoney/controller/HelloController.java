package com.xj.luckymoney.controller;

import com.xj.luckymoney.properties.LimitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xj
 * 2019-09-20 17:24
 **/
// Spring4之后新加入的注解 @RestController = @Controller + @ResponseBody
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired // 注入一个定义好的bean
    private LimitConfig limitConfig;
    // 写成一个配置类的原因，是注入的时候不需要一个个的配置@Value，只需要@Autowired一个配置对象

    // spring4.3新加注解 @GetMapping = @RequestMapping(method = RequestMethod.GET)
    // @PostMapping("/say") + @GetMapping("/say") =  @RequestMapping("/say")
    @GetMapping("/say")
    public String say(){
        return "学习使用springboot发红包！" + limitConfig.getDescription();
    }

    @GetMapping("/say/{id}")
    public String sayId(@PathVariable("id") Integer myId){
        return "id:" + myId;
    }
}
