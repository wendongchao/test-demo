package com.itcast.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 语法分析器
 * @author wendongchao
 * @ClassName Parser
 * @Date 2021/6/21 16:50
 */
public class Parser {
    private static List<String> g_g_l_l_n_e = FunctionEnum.g_g_l_l_n_e;
    private static List<String> p_m_m_d = FunctionEnum.p_m_m_d;
    private static String minus = FunctionEnum.MINUS.sign;
    private static List<String> i_a_r = FunctionEnum.i_a_r;
    private static String ifSign = FunctionEnum.IF.sign;
    private static String absSign = FunctionEnum.ABS.sign;
    private static String roundSign = FunctionEnum.ROUND.sign;
    private static String lp = FunctionEnum.LEFT_PARENTHESES.sign;
    private static String rp = FunctionEnum.RIGHT_PARENTHESES.sign;
    private static String comma = FunctionEnum.COMMMA.sign;
    //整数，小数
    private static String numberRegex = "^\\d+(\\.?\\d)?";
    // 占位符
    private static String placeholder = "$";
    private static String placeholder_i_a_r = "@";
    private static String placeholder_i = "@if@";
    private static String placeholder_a = "@abs@";
    private static String placeholder_r = "@round@";

    public static void checkParser(List<String> list) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            // （） 多个是正确，但是需要有数值
            if (lp.equals(list.get(i)) || rp.equals(list.get(i))){
                Integer volilptp = volilptp(list, i);
                i += volilptp -1;
                continue;
            }
            // 函数
            if (i_a_r.contains(list.get(i))){
                List<String> funs = checkFuns(list, i);
                i += funs.size() -1;
                continue;
            }
            // 运算符
            if (p_m_m_d.contains(list.get(i))){
                if (0 <= i-1 && i < list.size() -1){
                    if (!checkOperator(list.get(i-1),list.get(i+1))){
                        LexicalParser.ExceptionUtil(list,i,null);
                    }
                }
                if (i == 0 && i < list.size() -1) {
                    String s1 = list.get(i + 1);
                    if (s1.indexOf(placeholder) != -1 || s1.indexOf(placeholder_i_a_r) != -1 || s1.matches(numberRegex)) {
                        continue;
                    }else if (list.get(i).equals(minus)&&s1.equals(minus)){
                        continue;
                    } else {
                        LexicalParser.ExceptionUtil(list,i,null);
                    }
                }
            }


        }


    }
    public static Integer volilptp(List<String> list,Integer index) throws Exception {
        int num = index;
        int lp_rp = 0;
        int indexOf = 0;
        for (int i = index; i < list.size(); i++) {
            if (lp.equals(list.get(i))) {
                lp_rp++;
                num++;
                indexOf = i;
            }
            if (rp.equals(list.get(i))){
                lp_rp--;
                num++;
                indexOf = i;
            }
        }
        // 括号必须是一对
        if (lp_rp != 0){
            LexicalParser.ExceptionUtil(list,indexOf,null);
        }
        // 不能全是（）
        if (num == list.size() - index) {
            LexicalParser.ExceptionUtil(list,list.size(),null);
        }
        return num;
    }

    // 条件语句
    public static void checkBoolean(List<String> list) throws Exception {
        int num = 0;
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (g_g_l_l_n_e.contains(list.get(i))){
                num++;
                continue;
            }else {
                List<String> list1 = new ArrayList<String>();
                list1.add(list.get(i));
                checkExp(list1);
            }
        }
        if (num == 0) {
            LexicalParser.ExceptionUtil(list,list.size(),null);
        }

    }

    /**
     * 校验运算符
     * @param
     * @return {@link Boolean}
     * @date 2021/6/21 16:59
     */
    public static Boolean checkOperator(String s1,String s2){
        return (s1.indexOf(placeholder) != -1 || s1.indexOf(placeholder_i_a_r) != -1 || s1.matches(numberRegex)) && (s1.indexOf(placeholder) !=-1 || s2.indexOf(placeholder_i_a_r) != -1 || s1.matches(numberRegex));
    }

    public static void checkExp(List<String> list) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);

            if (s.indexOf(placeholder) != -1 || s.indexOf(placeholder_i_a_r) != -1) {
                if (i < list.size() -1){
                    if (!p_m_m_d.contains(list.get(i+1))) {
                        LexicalParser.ExceptionUtil(list,list.size(),null);
                    }
                }
                continue;
            }
            //
            if (p_m_m_d.contains(s)) {
                if (0 <= i-1 && i < list.size() -1) {
                    if (!checkOperator(list.get(i-1),list.get(i+1))){
                        LexicalParser.ExceptionUtil(list,list.size(),null);
                    }else {
                        continue;
                    }
                }
                if (i == 0 && i < list.size() -1) {
                    String s1 = list.get(i + 1);
                    if (s1.indexOf(placeholder) != -1 || s1.indexOf(placeholder_i_a_r) != -1 || s1.matches(numberRegex)) {
                        continue;
                    }else if (s.equals(minus)&&s1.equals(minus)){
                        continue;
                    } else {
                        LexicalParser.ExceptionUtil(list,list.size(),null);
                    }
                }

            }
            if (s.matches(numberRegex)){
                continue;
            }else {
                LexicalParser.ExceptionUtil(list,list.size(),null);
            }

        }
    }


    /**
     * 校验函数，并整理
     * @return {@link null}
     * @date 2021/6/21 17:01
     */
    public static List<String> checkFuns(List<String> list, Integer index) throws Exception {
        List<String> arrayList = new ArrayList<>();
        int lp_rp = 0;
        int indexOf = 0;
        int endIndex = 0;
        for (int i = index; i < list.size(); i++) {
            // 函数后面是（
            if (index < list.size() -1){
                if (!lp.equals(list.get(index+1))) {
                    LexicalParser.ExceptionUtil(list,i,null);
                }
            }else {
                LexicalParser.ExceptionUtil(list,index,null);
            }

            if (lp.equals(list.get(i))) {
                lp_rp++;
            }
            if (rp.equals(list.get(i))){
                lp_rp--;
            }
            // 嵌套函数
            if (i_a_r.contains(list.get(i)) && lp_rp > 0) {
                List<String> list1 = checkFuns(list, i);
                indexOf = i;
                endIndex = list1.size();
            }
            if (endIndex > 0){
                if (ifSign.equals(list.get(indexOf))) {
                    arrayList.add(placeholder_i);
                }else if (absSign.equals(list.get(indexOf))) {
                    arrayList.add(placeholder_a);
                }else if (roundSign.equals(list.get(indexOf))) {
                    arrayList.add(placeholder_r);
                }
                endIndex --;
                continue;
            }
            arrayList.add(list.get(i));
            if (rp.equals(list.get(i)) && lp_rp == 0){
                break;
            }
        }
        // 括号必须是一对
        if (lp_rp != 0){
            LexicalParser.ExceptionUtil(list,list.size(),null);
        }
        if (arrayList.contains(ifSign)){
            voliFunIf(arrayList);
        }else if (arrayList.contains(absSign)){
            voliFunAbs(arrayList);
        }else if (arrayList.contains(roundSign)){
            voliFunRound(arrayList);
        }
        return arrayList;
    }

    public static void voliFunIf(List<String> funs) throws Exception {
        int size = 0;
        for (String str : funs) {
            if (comma.equals(str)){
                size++;
            }
        }
        if (size < 2) {
            LexicalParser.ExceptionUtil(funs,funs.size(),null);
        }else if (size == 2) {
            int indexOf = funs.indexOf(comma);
            int lastIndexOf = funs.lastIndexOf(comma);
            List<String> subList = funs.subList(ifSign.length(), indexOf);
            List<String> subList1 = funs.subList(indexOf+1, lastIndexOf);
            List<String> subList2 = funs.subList(lastIndexOf+1, funs.size()-1);
            checkBoolean(subList);
            checkExp(subList1);
            checkExp(subList2);
        }

    }

    public static void voliFunAbs(List<String> funs) throws Exception {
        int indexOf = funs.indexOf(lp);
        int indexOf1 = funs.indexOf(rp);
        checkExp(funs.subList(indexOf+1, indexOf1));
    }

    public static void voliFunRound(List<String> funs) throws Exception {
        int indexOf = funs.indexOf(lp);
        int indexOf1 = funs.indexOf(rp);
        List<String> list = funs.subList(indexOf + 1, indexOf1);
        if (list.contains(comma)){
            int i = list.indexOf(comma);
            checkExp(list.subList(0, i));
            checkExp(list.subList(i+1,list.size()-1));
        }else {
            checkExp(list);
        }
    }

}
