package com.xj.luckymoney.service;

import com.xj.luckymoney.pojo.Luckymoney;
import com.xj.luckymoney.repository.LuckymoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by xj
 * 2019-09-22 00:14
 **/
@Service
public class LuckymoneyService {

    @Autowired
    private LuckymoneyRepository repository;

    /**
     * 事务：指的是数据库事务，注意MyISAM是不支持事务的，我们要选择InnoDB；
     * 也就是说，除了要在方法前加上@Transactional这个注释，还需要数据库引擎支持事务。
     *
     * 事务举例：
     * 买东西时 “扣库存” 与 “创建订单”
     */
    @Transactional
    public void createTwo(){
        Luckymoney luckymoney1 = new Luckymoney();
        luckymoney1.setProducer("xiongjj");
        luckymoney1.setMoney(new BigDecimal(520));
        repository.save(luckymoney1);

        Luckymoney luckymoney2 = new Luckymoney();
        luckymoney2.setProducer("xiongjj");
        luckymoney2.setMoney(new BigDecimal(1314));
        repository.save(luckymoney2);
    }
}
