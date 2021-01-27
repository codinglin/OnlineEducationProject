package com.study.commonutils.Exception;

import com.study.commonutils.enums.ResultCodeEnum;
import lombok.Data;
import lombok.ToString;

/**
 * 自定义异常
 */
@Data
@ToString
public class RRException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private Integer code = ResultCodeEnum.ERROR.getCode();

    public RRException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public RRException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public RRException(String msg, Integer code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public RRException(String msg, Integer code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }


}
