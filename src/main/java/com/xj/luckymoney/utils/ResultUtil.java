package com.xj.luckymoney.utils;

import com.xj.luckymoney.enums.ResultEnum;
import com.xj.luckymoney.pojo.Result;

/**
 * Created by xj
 * 2019-09-23 21:29
 **/
public class ResultUtil {

    /**
     * 成功情况的Result的封装并返回
     * @param data 数据内容
     * @return Result对象
     */
    public static Result success(Object data){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    /**
     * 重载一种成功时不需要返回对象的情况
     * @return Result对象
     */
    public static Result success(){
        return success(null);
    }

    /**
     * 错误情况的Result封装并返回
     * @param code 错误码
     * @param msg 错误信息
     * @return Result对象
     */
    public static Result error(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 重载，错误情况的Result的封装并返回
     * @param resultEnum 包含错误码和错误信息的枚举
     * @return Result对象
     */
    public static Result error(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

}
