package tech.iooo.coco;

import java.io.Serializable;

/**
 * @author 龙也
 * @date 2022/3/17 3:28 PM
 */
public class BoosterResult<T> implements Serializable {

  private boolean success;
  private String code;
  private String msg;
  private T data;

  public static <T> BoosterResult<T> of(T data) {
    BoosterResult<T> result = new BoosterResult<>();
    result.setSuccess(true);
    result.setMsg("success");
    result.setCode("success");
    result.setData(data);
    return result;
  }

  public static <T> BoosterResult<T> error(String code, String msg) {
    BoosterResult<T> result = new BoosterResult<>();
    result.setSuccess(false);
    result.setMsg(msg);
    result.setCode(code);
    return result;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
