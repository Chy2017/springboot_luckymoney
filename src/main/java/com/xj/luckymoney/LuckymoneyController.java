package com.xj.luckymoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Created by xj
 * 2019-09-21 17:45
 **/
@RestController
public class LuckymoneyController {

    @Autowired
    private LuckymoneyRepository luckymoneyRepository;

    @Autowired
    private LuckymoneyService luckymoneyService;

    /**
     * 获取红包列表
     */
    @GetMapping("/luckymoneys")
    public List<Luckymoney> getList(){
        return luckymoneyRepository.findAll();
    }

    /**
     * 创建红包（发红包）
     */
    @PostMapping("/luckymoneys")
    public Luckymoney create(@RequestParam("producer") String producer,
                             @RequestParam("money") BigDecimal money){
        Luckymoney luckymoney = new Luckymoney();
        luckymoney.setProducer(producer);
        luckymoney.setMoney(money);
        return luckymoneyRepository.save(luckymoney);
    }

    /**
     * 通过id查询红包
     */
    @GetMapping("/luckymoneys/{id}")
    public Luckymoney findById(@PathVariable("id") Integer id){
        return luckymoneyRepository.findById(id).orElse(null);
        // 不能直接返回option对象，需要用orElse，指明如果查不到的话返回什么
    }
    /**
     * 更新红包（领红包）
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
