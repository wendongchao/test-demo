package com.netty.kryo.dto;

import lombok.*;

/**
 * @des: 客户端请求实体类
 * @author wendongchao
 * @Date 2021/3/14 11:20
 */
@AllArgsConstructor//有参
@Getter
@NoArgsConstructor//无参
@Builder
@ToString
public class RpcRequest {
    private String interfaceName;
    private String methodName;
}
