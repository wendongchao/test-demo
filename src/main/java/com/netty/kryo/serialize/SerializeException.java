package com.netty.kryo.serialize;

/**
 * 自定义序列化异常
 * @author wendongchao
 * @Date 2021/3/14 13:55
 */
public class SerializeException extends RuntimeException {
    public SerializeException(String message) {
        super(message);
    }
}
