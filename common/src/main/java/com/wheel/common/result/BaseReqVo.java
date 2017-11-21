package com.wheel.common.result;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class BaseReqVo implements java.io.Serializable {
    private Integer pageNum;
    private Integer pageSize;
    @NotBlank
    private String logId;
    @NotBlank
    private String source;
    public String sign;



}
