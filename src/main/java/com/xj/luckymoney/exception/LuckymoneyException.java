package com.xj.luckymoney.exception;

/**
 * Created by xj
 * 2019-09-24 12:25
 **/

import com.xj.luckymoney.enums.ResultEnum;

/**
 * 注意这里要继承RuntimeException
 * RuntimeException也是继承的Exception
 * 在spring中，只有对RuntimeException才有回滚，对Exception没有回滚操作
 */
public class LuckymoneyException extends RuntimeException {

    private Integer code;

    public LuckymoneyException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
