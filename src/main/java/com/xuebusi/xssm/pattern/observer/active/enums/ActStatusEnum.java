package com.xuebusi.xssm.pattern.observer.active.enums;

/**
 * 活动状态枚举
 */
public enum ActStatusEnum {

    START("1"), END("2"), NOT_START("3");

    private String code;

    ActStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
