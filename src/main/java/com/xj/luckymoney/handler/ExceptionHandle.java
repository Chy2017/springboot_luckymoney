package com.xj.luckymoney.handler;

import com.xj.luckymoney.enums.ResultEnum;
import com.xj.luckymoney.exception.LuckymoneyException;
import com.xj.luckymoney.pojo.Result;
import com.xj.luckymoney.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常捕获（统一异常处理）
 * Created by xj
 * 2019-09-23 23:16
 **/
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(Exception.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody // 由于要返回json，所以必须要加上
    public Result handler(Exception e){
        if(e instanceof LuckymoneyException){
            LuckymoneyException luckyException = (LuckymoneyException) e;
            return ResultUtil.error(luckyException.getCode(), luckyException.getMessage());
        } else {
            logger.error("【系统异常】{}", e);
            return ResultUtil.error(ResultEnum.UN_KNOW_ERROR);
        }
    }
}
