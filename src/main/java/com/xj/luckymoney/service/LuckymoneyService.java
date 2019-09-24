package com.xj.luckymoney.service;

import com.xj.luckymoney.enums.ResultEnum;
import com.xj.luckymoney.exception.LuckymoneyException;
import com.xj.luckymoney.pojo.Luckymoney;
import com.xj.luckymoney.pojo.Result;
import com.xj.luckymoney.repository.LuckymoneyRepository;
import com.xj.luckymoney.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 逻辑尽量都放在service
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

    /**
     * 查看某个红包是否会让宝宝开心接受（这里用到统一异常处理）
     * @param id 红包id
     */
    public Result happyOrNot(Integer id) throws Exception{
        Luckymoney luckymoney = repository.findById(id).orElse(new Luckymoney());
        int amountOfMoney = luckymoney.getMoney().intValue();
        if (amountOfMoney <= 5.20){
            //返回“宝宝很不开心，要炸了，拒绝签收”  错误码 code = 10
            throw new LuckymoneyException(ResultEnum.TOO_LESS);//定义过于随意，所以要用到枚举
            //可能马上要哄宝宝
        } else if (amountOfMoney > 5.20 && amountOfMoney < 10){
            //返回“宝宝不开心，拒绝签收”           错误码 code = 11
            throw new LuckymoneyException(ResultEnum.LESS);
        } else if (amountOfMoney > 1000) {
            //返回宝宝超级开心                   错误码 code = 19
            throw new LuckymoneyException(ResultEnum.TOO_MUCH);
        }
        /**
         * 如果系统任何地方，出现运行时异常的话，系统都应该捕获异常
         * 并返回前端【code = -1, msg = "未知错误"】的Result封装
         */
        return ResultUtil.success(luckymoney);
    }
}
