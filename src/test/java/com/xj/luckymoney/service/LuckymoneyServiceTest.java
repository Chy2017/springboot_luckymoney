package com.xj.luckymoney.service;

import com.xj.luckymoney.pojo.Luckymoney;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by xj
 * 2019-09-24 23:08
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class LuckymoneyServiceTest {

    @Autowired
    private LuckymoneyService luckymoneyService;

    @Test
    public void findOne() {
        Luckymoney luckymoney = luckymoneyService.findOne(15);
        Assert.assertEquals( new BigDecimal(90).toBigInteger(), luckymoney.getMoney().toBigInteger());
    }
}