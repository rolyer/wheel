package com.wheel.common.result;

import java.util.HashSet;

/**
 * 类ReturnCodeEnum.java的实现描述：返回异常码
 */
public enum ReturnCodeEnum {
    /*成功 */
    RESTFUL_SUCCESS(200, "成功"),
    /* 处理失败，未知异常 */
    RESTFU_FAIL(500, "处理失败，未知异常"),
    RESTFUL_REQUEST_OBJECT_INVALID(400, "参数验证失败"),
    SIGN_FAIL(400, "验签错误"),

    UNKNOWN_FAIL(10000, "处理失败[未知原因]"),
    DATA_NOT_EXIST(10001, "数据不存在"),
    DATA_REPEAT_ERROR(10002, "数据重复"),
    UPDATE_ERROR(10003, "数据更新失败"),

    THIRD_ERROR(10004, "调用外部系统失败"),
    STATUS_ERROR(10005, "状态错误不存在"),
    VERSION_ERROR(10006, "版本错误，请获取最新数据"),
    STATUS_UPDATE_ERROR(10007, "状态不允许更新"),
    ORDERBY_ERROR(10008, "orderBy错误"),

    THIRD_TIMEOUT_ERROR(10009, "调用外部系统超时"),

    BPM_TOKEN_ERROR(10010, "TOKEN失效"),
    BPM_AUTHORITY_ERROR(10011, "此用户没有权限发流程"),
    SURPLUSAMOUNT_ERROR(20001, "剩余可拆分余额不足"),
    ASSETPACKAGE_STATUS_ERROR(20002, "资产包状态错误"),
    ASSET_STATUS_ERROR(20003, "资产状态错误"),
    SURPLUSAMOUNT_NOTZERO_ERROR(20004, "剩余可拆分余额不为0"),
    START_AMOUNT_ERROR(20005, "起投金额应小于资产金额"),
    CONTRACT_ERROR(20006, "合同为空，没有生成完成"),
    USERTYPE_ERROR(20007, "用户类型错误"),
    INCREASE_AMOUNT_ERROR(20008, "起投金额应小于资产金额"),
    ADD_QUEUE_ERROR(20009, "当前状态不允许上标"),
    BPM_NOTIFY_ERROR(20010, "BPM通知数据格式不正确"),
    ASSET_PACKAGE_ACCOUNT_ERROR(20011, "项目账号编号或者名称不能为空"),
    RAISE_DAYS_ERROR(20012, "募集天数0-100"),;


    private final int value;

    /**
     * The desc.
     */
    private final String desc;

    /**
     * Instantiates a new return status enum.
     *
     * @param value
     */
    private ReturnCodeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public int value() {
        return value;
    }

    /**
     * Gets the desc.
     *
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    private static HashSet<Integer> hashSet;

    static {
        hashSet = new HashSet<Integer>();
        hashSet.clear();
        for (ReturnCodeEnum returnStatus : ReturnCodeEnum.values()) {
            hashSet.add(returnStatus.value());
        }
    }

    public static boolean isDefined(int value) {
        if (hashSet.contains(value)) {
            return true;
        }
        return false;
    }
}
