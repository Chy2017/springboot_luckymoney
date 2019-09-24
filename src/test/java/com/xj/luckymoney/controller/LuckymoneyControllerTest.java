package com.xj.luckymoney.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by xj
 * 2019-09-24 23:08
 * Api测试 打包的时候（mvn clean package），会自动执行全部的测试
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc //api测试必须要加
public class LuckymoneyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/luckymoneys"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}