package com.itcast.demo;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 关键字枚举
 * @date 2021/6/17 17:08
 */
public enum FunctionEnum {

     //基础运算符 +，-，*，/
    PLUS("plus","+","\\+"),
    MINUS("minus","-","\\-"),
    MULTIPLY("multiply","*","\\*"),
    DIVIDE("divide","/","\\/"),

     //运算符 >，>=，<，<=，<>，or，and
    GREATER_THAN("gt",">",">"),
    GREATER_EQUAL("morethen",">=",">="),
    LESS_THAN("lt","<","<"),
    LESS_EQUAL("le","<=","<="),
    NOT_EQUAL("ne","<>","<>"),
    EQUAL_EQUAL("ee","==","=="),
    OR("or","or","or"),
    AND("and","and","and"),
    //函数 if，abs，round
    IF("if","if","if"),
    ABS("abs","abs","abs"),
    ROUND("round","round",null),

    //关键词 字母，(，)，“，”
    ALPHABET("alphabet",null,"[a-zA-Z]"),
    LEFT_PARENTHESES("lp","(","("),
    RIGHT_PARENTHESES("rp",")","("),
    COMMMA("comma",",",null),

    ;

    public  String key;
    public  String sign;
    public  String regex;



    FunctionEnum(String key, String sign, String regex) {
        this.key = key;
        this.sign = sign;
        this.regex = regex;
    }

    public static FunctionEnum get(String key){
        if (StrUtil.isEmpty(key)){
            return null;
        }
        for(FunctionEnum mt:FunctionEnum.values()){
            if (Objects.equals(mt.key,key)){
                return mt;
            }
        }
        return null;
    }

    public static FunctionEnum get(char key){
        for (FunctionEnum fn:FunctionEnum.values()){
            if (Objects.equals(fn.sign.charAt(0),key)){
                return fn;
            }
        }
        return null;
    }

    // + - * /
    public static List<String> p_m_m_d =
            Arrays.asList(PLUS.sign,MINUS.sign,MULTIPLY.sign,DIVIDE.sign);
    public static List<String> p_m_m_d_g_l_l_r =
            Arrays.asList(PLUS.sign,MINUS.sign,MULTIPLY.sign,DIVIDE.sign,
                    GREATER_THAN.sign,LESS_THAN.sign,LEFT_PARENTHESES.sign,
                    RIGHT_PARENTHESES.sign,COMMMA.sign);
    public static List<String> p_m =
            Arrays.asList(PLUS.sign,MINUS.sign);
    public static List<String> i_a_r =
            Arrays.asList(IF.sign,ABS.sign,ROUND.sign);

    public static List<String> g_g_l_l_n_e =
            Arrays.asList(GREATER_THAN.sign,GREATER_EQUAL.sign,LESS_THAN.sign,LESS_EQUAL.sign,
                    NOT_EQUAL.sign,EQUAL_EQUAL.sign);

    // 不是null，不是+ - * / ，那么就是其他的
    public static List<String> getSignFunc(){
        return Arrays.stream(FunctionEnum.values())
                .filter(tt -> StrUtil.isNotEmpty(tt.sign))
                .filter(tt->!p_m_m_d_g_l_l_r.contains(tt.sign))
                .map(tt->tt.sign)
                .collect(Collectors.toList());
    }



}
