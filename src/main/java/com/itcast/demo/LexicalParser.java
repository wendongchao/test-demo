package com.itcast.demo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 词法解析器
 * @author wendongchao
 * @ClassName LexicalParser
 * @Date 2021/6/18 16:24
 */
public class LexicalParser {

    // 字母正则
    private static String alphabetRegex = "[a-zA-Z]";
    // 数字正则
    private static String numberRegex = "[0-9]";
    // 占位符
    private static String placeholder = "$";

    /**注册自定义函数*/
    private static List<String> functionRegister = FunctionEnum.getSignFunc();

    private static List<String> p_m_m_d_g_l_l_r = FunctionEnum.p_m_m_d_g_l_l_r;

    private static Map<Integer,String> funIndexMap = new HashMap<>();


    public static List<String> parse(String expression) throws Exception {
        List<String> tokens = new ArrayList<>();
        String exp = expression.trim();

        // 获取公式中自定义函数的位置
        functionRegister.stream().forEach(fun -> {
            if (exp.indexOf(fun) != -1){
                getFunIndex(exp,exp.indexOf(fun),funIndexMap,fun);
            }
        });

        // 验证占位符
        Map<Integer,String> placeholderMap = new HashMap<>();
        if (exp.indexOf(placeholder) != -1){
            getFunIndex(exp,exp.indexOf(placeholder),placeholderMap,placeholder);
        }
        List<Integer> placeholderIndexs = placeholderMap.keySet().stream().collect(Collectors.toList());
        placeholderIndexs.stream().sorted();

        if (placeholderMap.size() % 2 != 0){
            ExceptionUtil(null,placeholderIndexs.get(placeholderIndexs.size()-1),exp);
        }


        checkPlaceholderParam(exp,placeholderIndexs);

        char[] chars = expression.trim().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            // 该位置是否有函数
            if (funIndexMap.get(i) != null){
                String fun = funIndexMap.get(i);
                tokens.add(fun);
                i+=fun.length()-1;
                continue;
            }
            // 占位符
            if (placeholderIndexs.contains(i)) {
                String placeholderLength = getPlaceholderParam(i,placeholderIndexs,exp);
                tokens.add(placeholderLength);
                i+=placeholderLength.length()-1;
                continue;
            }

            // 数值
            if (String.valueOf(c).matches(numberRegex)){
                StringBuffer stringBuffer = new StringBuffer();
                getNumberParam(i,chars,stringBuffer);
                tokens.add(stringBuffer.toString());
                i+=stringBuffer.length()-1;
                continue;
            }

            // 运算符
            if (p_m_m_d_g_l_l_r.contains(String.valueOf(c))) {
                tokens.add(String.valueOf(c));
                continue;
            }else {
                ExceptionUtil(null,i,chars.toString());
            }
        }
        return tokens;
    }


    /**
     * 判断数字
     * @param index
     * @param chars
     * @return
     * @date 2021/6/21 16:02
     */
    public static void getNumberParam(Integer index,char[] chars,StringBuffer stringBuffer) throws Exception {
        char c = chars[index];
        boolean pointFlag = String.valueOf(c).equals(".");
        boolean numFlag = String.valueOf(c).matches(numberRegex);
        boolean pointNextFlag = false;
        if (pointFlag){
            pointNextFlag = String.valueOf(chars[index+1]).matches(numberRegex);
            if (!pointNextFlag){
                ExceptionUtil(null,index,chars.toString());
            }
        }
        if (numFlag||pointFlag){
            stringBuffer.append(c);
            getNumberParam(index+1, chars, stringBuffer);
        }
    }

    /**
     * 检查替换符中的字符是否符合正则
     * @param exp
     * @param list
     * @return
     * @date 2021/6/21 15:30
     */
    public static void checkPlaceholderParam(String exp, List<Integer> list) throws Exception {
        for (int i = 0; i < list.size(); i+=2) {
            String str = exp.substring(list.get(i)+1,list.get(i+1));
            char[] chars = str.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (!String.valueOf(chars[j]).matches(alphabetRegex)){
                    ExceptionUtil(null,list.get(i)+j,exp);
                }
            }
        }
    }

    public static String getPlaceholderParam(Integer index, List<Integer> list, String exp){
        for (int i = 0; i < list.size(); i++) {
            if (index.equals(list.get(i)) && i % 2==0){
                return exp.substring(list.get(i),list.get(i+1)+1);
            }
        }
        return null;
    }


    /**
     * 获取字符的位置信息
     * @param exp
     * @param index
     * @param map
     * @param fun
     * @return
     * @date 2021/6/21 15:31
     */
    public static void getFunIndex(String exp,Integer index,Map<Integer,String> map,String fun){
        map.put(index,fun);
        if (exp.indexOf(fun,index+1) != -1){
            index = exp.indexOf(fun,index+1);
            getFunIndex(exp,index,map,fun);
        }
    }

    public static void ExceptionUtil(List<String> list,Integer index,String exp) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        String message = "";
        if (list != null && list.size() != 0) {
            list.subList(0, index).stream().forEach(str -> {
                stringBuffer.append(str);
            });
            message = stringBuffer.toString()+"^";
        }
        if (exp != null && !"".equals(exp)){
            message = exp.substring(0, index)+"^";
        }
        throw new Exception(message);
    }

}
