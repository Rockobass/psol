package org.orz.psol.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("Response对象")
@Data
public class RespBean {
    @ApiModelProperty("响应状态码")
    private Integer status;
    @ApiModelProperty("响应消息")
    private String msg;
    @ApiModelProperty("响应返回对象")
    private Object obj;

    public static RespBean build() {
        return new RespBean();
    }

    @ApiModelProperty("返回200ok")
    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    @ApiModelProperty("返回200ok，响应中返回obj对象")
    public static RespBean ok(String msg, Object obj) {
        return new RespBean(200, msg, obj);
    }

    @ApiModelProperty("返回500error")
    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    public static RespBean error(String msg, Object obj) {
        return new RespBean(500, msg, obj);
    }

    private RespBean() {
    }

    private RespBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }
}
