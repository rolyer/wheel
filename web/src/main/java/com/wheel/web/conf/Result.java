package com.wheel.web.conf;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result extends BaseResult {
    private Object data;

    public Result() {
        this(null);
    }

    public Result(Object data) {
        if (data != null) {
            this.data = data;
        }
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
