package com.ly.springboot.utils.error.code;

/**
 * <p>接口错误响应码可以设计成一个枚举，并实现{@link IEnumError}接口<p/>
 *
 * @author liuyang
 * @date 2020/2/29 18:06
 */
public interface IEnumError {

    /**
     * 获取错误码
     * @return
     */
    int getCode();

    /**
     * 获取错误信息
     * @return
     */
    String getMsg();
}
