package com.fly.sys.vo;

import java.io.Serializable;

/**
 * json 接口返回对象
 * <dt>类名：AjaxResultPo</dt>
 * </dl>
 */
public class AjaxResultVO implements Serializable {
    private static final long serialVersionUID = -4148768233386711389L;
    private boolean success;
    private String msg;
    private String returnData;
    private Object returnObject;

    public AjaxResultVO() {
        super();
    }

    public AjaxResultVO(boolean success, String msg, String returnData, Object returnObject) {
        super();
        this.success = success;
        this.msg = msg;
        this.returnData = returnData;
        this.returnObject = returnObject;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    public AjaxResultVO(boolean b) {
        setSuccess(b);
    }

    public AjaxResultVO(boolean b, String msg) {
        setSuccess(b);
        setMsg(msg);
    }

    public AjaxResultVO(boolean b, String msg, String data) {
        setSuccess(b);
        setMsg(msg);
        setReturnData(data);
    }

    public AjaxResultVO(boolean b, String msg, Object data) {
        setSuccess(b);
        setMsg(msg);
        setReturnObject(data);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReturnData() {
        return returnData;
    }

    public void setReturnData(String returnData) {
        this.returnData = returnData;
    }
}
