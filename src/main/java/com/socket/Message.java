package com.socket;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wendongchao
 * @des:
 * @Date 2021/3/11 17:54
 */
@Data
@AllArgsConstructor
public class Message implements Serializable {

    private String content;
}
