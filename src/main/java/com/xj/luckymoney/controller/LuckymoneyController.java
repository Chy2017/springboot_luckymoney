package com.xj.luckymoney.controller;

import com.xj.luckymoney.pojo.Luckymoney;
import com.xj.luckymoney.repository.LuckymoneyRepository;
import com.xj.luckymoney.service.LuckymoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by xj
 * 2019-09-21 17:45
 **/
@RestController /* @RestController = @Controller + @ResponseBody */
public class LuckymoneyController {

    @Autowired
    private LuckymoneyRepository luckymoneyRepository;

    @Autowired
    private LuckymoneyService luckymoneyService;

    /**
     * 获取红包列表
     * @return List<Luckymoney>
     */
    @GetMapping("/luckymoneys")
    public List<Luckymoney> getList(){
        return luckymoneyRepository.findAll();
    }

    /**
     * 创建红包（发红包）
     * @return Luckymoney
     * @Tip 现在做这样一件事情：“如果红包的大小小于10块钱，就禁止掉”。
     */
    @PostMapping("/luckymoneys")
    /** @Valid  ：表示要验证的内容    BindingResult：表示验证结果 */
    public Luckymoney create(@Valid Luckymoney luckymoney, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
        return luckymoneyRepository.save(luckymoney);
    }

    /**
     * 通过id查询红包
     * @param id
     * @return Luckymoney
     */
    @GetMapping("/luckymoneys/{id}")
    public Luckymoney findById(@PathVariable("id") Integer id){
        return luckymoneyRepository.findById(id).orElse(null);
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
        Luckymoney luckymoney = luckymoneyRepository.findById(id).orElse(null);
        if(luckymoney != null){
            luckymoney.setConsumer(consumer);
            return luckymoneyRepository.save(luckymoney);
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
}
