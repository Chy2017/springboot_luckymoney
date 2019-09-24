package com.xj.luckymoney.enums;

/**
 * 这些错误码和错误信息太乱，需要用枚举类来管理起来
 */
public enum ResultEnum {
    UN_KNOW_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),

    TOO_LESS(11, "这红包也太太小了，宝宝拒签!"),
    LESS(12, "宝宝要求重新发!"),
    TOO_MUCH(19, "这也太她老娘的大了，宝宝要不起!"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
