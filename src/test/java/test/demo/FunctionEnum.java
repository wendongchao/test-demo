package test.demo;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 关键字枚举字符标记
 * @date 2021/6/17 17:08
 */
public enum FunctionEnum {

     //基础运算符 +，-，*，/
    PLUS("OPERATOR","+"),
    MINUS("OPERATOR","-"),
    MULTIPLY("OPERATOR","*"),
    DIVIDE("OPERATOR","/"),

     //运算符 >，>=，<，<=，<>，or，and
    GREATER_EQUAL("COMPARE",">="),
    LESS_EQUAL("COMPARE","<="),
    NOT_EQUAL("COMPARE","<>"),
    EQUAL_EQUAL("COMPARE","=="),
    GREATER_THAN("COMPARE",">"),
    LESS_THAN("COMPARE","<"),
    OR("OR","or"),
    AND("AND","and"),
    //函数 if，abs，round
    IF("KEYWORD","if"),
    ABS("KEYWORD","abs"),
    ROUND("KEYWORD","round"),

    //( ) , $
    LEFT_PARENTHESES("LEFT_PARENTHESES","("),
    RIGHT_PARENTHESES("RIGHT_PARENTHESES",")"),
    COMMMA("COMMMA",","),
    REPLACE("REPLACE","$"),

    // 数字
    NUMBER("NUMBER",null),


    ;


    public  String key;
    public  String sign;

    public String getKey() {
        return key;
    }

    public String getSign() {
        return sign;
    }


    FunctionEnum(String key, String sign) {
        this.key = key;
        this.sign = sign;
    }

    public static FunctionEnum get(String sign){
        if (StrUtil.isEmpty(sign)){
            return null;
        }
        for(FunctionEnum mt:FunctionEnum.values()){
            if (Objects.equals(mt.sign,sign)){
                return mt;
            }
        }
        return null;
    }

    // + - * /
    public static List<String> p_m_m_d = Arrays.asList(PLUS.sign,MINUS.sign,MULTIPLY.sign,DIVIDE.sign);
    // if abs round
    public static List<String> i_a_r = Arrays.asList(IF.sign,ABS.sign,ROUND.sign);
    // > >= < <= <> ==
    public static List<String> g_g_l_l_n_e =
            Arrays.asList(GREATER_THAN.sign,GREATER_EQUAL.sign,LESS_THAN.sign,LESS_EQUAL.sign,
                    NOT_EQUAL.sign,EQUAL_EQUAL.sign);

    public static List<String> left_right = Arrays.asList(LEFT_PARENTHESES.key,RIGHT_PARENTHESES.key);

    /**
     * 返回集合，sign->key，过滤 sign null
     * @param
     * @return {@link Map< String, String>}
     * @date 2021/7/24 16:24
     */
    public static Map<String,String> getSignFuncMap(){
        return Arrays.stream(FunctionEnum.values())
                .filter(tt -> StrUtil.isNotEmpty(tt.sign))
                .collect(Collectors.toMap(tt -> tt.sign, tt -> tt.key));
    }

    /**
     * 获取类型key
     * @param
     * @return {@link List< String>}
     * @date 2021/7/24 16:38
     */
    public static List<String> getKeyFunc(){
        return Arrays.stream(FunctionEnum.values())
                .map(tt -> tt.key)
                .distinct()
                .collect(Collectors.toList());
    }

    // 不是null，不是if,abs,round ，那么就是其他的
    public static List<String> getSignFun(){
        return Arrays.stream(FunctionEnum.values())
                .filter(tt -> StrUtil.isNotEmpty(tt.sign))
                .map(tt->tt.sign)
                .collect(Collectors.toList());
    }

}
