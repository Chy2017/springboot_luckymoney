package com.xj.luckymoney.controller;

import com.xj.luckymoney.enums.ResultEnum;
import com.xj.luckymoney.pojo.Luckymoney;
import com.xj.luckymoney.pojo.Result;
import com.xj.luckymoney.repository.LuckymoneyRepository;
import com.xj.luckymoney.service.LuckymoneyService;
import com.xj.luckymoney.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by xj
 * 2019-09-21 17:45
 **/
@RestController /* @RestController = @Controller + @ResponseBody */
public class LuckymoneyController {

    //org.slf4j.Logger是spring自带的一个日志的框架
    private final static Logger logger= LoggerFactory.getLogger(LuckymoneyController.class);

    @Autowired
    private LuckymoneyRepository repository;

    @Autowired
    private LuckymoneyService luckymoneyService;

    /**
     * 获取红包列表
     * @return List<Luckymoney>
     */
    @GetMapping("/luckymoneys")
    public List<Luckymoney> getList(){
        logger.info("getList");
        return repository.findAll();
    }

    /**
     * 创建红包（发红包）
     * @return Luckymoney
     * @Tip 现在做这样一件事情：“如果红包的大小小于10块钱，就禁止掉”。
     */
    @PostMapping("/luckymoneys")
    /** @Valid  ：表示要验证的内容    BindingResult：表示验证结果 */
    public Result<Luckymoney> create(@Valid Luckymoney luckymoney, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(repository.save(luckymoney));
    }

    /**
     * 通过id查询红包
     * @param id
     * @return Luckymoney
     */
    @GetMapping("/luckymoneys/{id}")
    public Luckymoney findById(@PathVariable("id") Integer id){
        return repository.findById(id).orElse(null);
        // 不能直接返回option对象，需要用orElse，指明如果查不到的话返回什么
    }

    /**
     * 更新红包（领红包）
     * @param id
     * @param consumer
     * @return Luckymoney
     */
    @PutMapping("/luckymoneys/{id}")
    public Luckymoney update(@PathVariable("id") Integer id,
                             @RequestParam("consumer") String consumer){
        Luckymoney luckymoney = repository.findById(id).orElse(null);
        if(luckymoney != null){
            luckymoney.setConsumer(consumer);
            return repository.save(luckymoney);
        }
        return null;
        /**另一种写法
        Optional<Luckymoney> optional = luckymoneyRepository.findById(id);
        if (optional.isPresent()) {
            Luckymoney luckymoney1 = optional.get();
            luckymoney1.setConsumer(consumer);
            return luckymoneyRepository.save(luckymoney1);
        }
        return null;*/
    }

    /**
     * 实现发两个红包的事务
     */
    @PostMapping("/luckysmoneys/two")
    public void createTwo(){
        luckymoneyService.createTwo();
    }

    /**
     * 查看某个红包是否会让宝宝开心接受
     * @param id 红包id
     */
    @GetMapping("/luckymoney/ishappy/{id}")
    public Result getIsHappy(@PathVariable("id") Integer id) throws Exception{
        /**
         * 捕获异常，再封装，再返回给浏览器
         */
        return luckymoneyService.happyOrNot(id);
    }
}
