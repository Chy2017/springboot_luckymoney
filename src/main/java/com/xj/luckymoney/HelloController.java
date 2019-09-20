package com.xj.luckymoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created by xj
 * 2019-09-20 17:24
 **/
// Spring4之后新加入的注解，返回json。原来返回json需要@ResponseBody和@Controller配合，其等价于
/*
    @Controller
    @ResponseBody
*/
@RestController
public class HelloController {

    @Autowired //这个注解的功能就是为我们注入一个定义好的bean
    //写成一个配置类的原因，是注入的时候不需要一个一个配置的@Value，只需要@Autowired一个配置对象，就可以全部导入
    private LimitConfig limitConfig;

    @GetMapping("/hello")//是在spring4.3加的新注解，整合了@RequestMapping(method = RequestMethod.GET)
    //其等价于 @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String say(){
        return "学习使用springboot发红包！" + limitConfig.getDescription();
    }
}
