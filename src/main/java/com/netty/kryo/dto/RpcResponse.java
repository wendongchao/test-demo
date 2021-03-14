package com.netty.kryo.dto;

import lombok.*;

/**
 * @des: 服务端响应实体类
 * @author wendongchao
 * @Date 2021/3/14 11:25
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
@ToString
public class RpcResponse {
    private String message;
}
