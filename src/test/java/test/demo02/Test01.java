package test.demo02;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author wendong
 * @Date 2022/5/21 15:53
 */
public class Test01 extends TestCase {

    private static final Pattern pattern = Pattern.compile("\\w+\\{\\w+}|-?\\d+(\\.\\d+)?|->|\\+|-|\\*|/|=|\\(|\\)|.");

    private final static String formulaReplaceDim = "$formulareplace$";

    public void test01() {

        String formula = "2-3-3";

        Matcher matcher = pattern.matcher(formula);


        while (matcher.find()) {
            System.out.println("完整匹配: " + matcher.group(0));
            System.out.println("完整匹配: " + matcher.group());

            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("组 " + i + ": " + matcher.group(i));
            }
        }

    }

    public void test02() {
        List<String> list = Arrays.asList("$dxxx$","$cxxx$");
        List<String> collect = list.stream().sorted().collect(Collectors.toList());
        System.out.println(collect);
    }

    public void test03() {
        JSONObject json = new JSONObject(true);
        json.put("d", "aa");
        json.put("c", "aa");

//        System.out.println(JSONObject.toJSONString(json, Feature.OrderedField));
        System.out.println(json.toJSONString());
    }

    public void test04() {
        String formula = "（1-2)-Account{Account001}/(2-3)";
        String povDim = "Account{Account001}";

        int first = formula.indexOf(povDim);
        int end = first + povDim.length();
        String prev = formula.substring(0,first);
        String next = formula.substring(end);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prev).append(formulaReplaceDim).append(next);
        System.out.println(stringBuilder.toString());

    }

    public void test05() {
        BigDecimal bigDecimal = new BigDecimal("0E-10");
        System.out.println(bigDecimal.compareTo(BigDecimal.ZERO) == 0);
    }

    public void test06() {
        String express = "1+aaa#account{1001}";
        String express2 = "1+aaa#account{1001;1002}.value{CNY}";
        String express3 = "aaa#entity{a02}.account{1001}.value{CNY}-1";
        String dimStr = "account{";
        StringBuilder stringBuilder = new StringBuilder();
        List<String> list = new ArrayList<>();
        replaceDimStr(express2, dimStr, list);
        String formulaStr = list.get(0);
        int first = formulaStr.indexOf("{") + 1;
        int end = formulaStr.indexOf("}");
        String substring = formulaStr.substring(first, end);
        String[] split = substring.split(";");
        List<String> list1 = Arrays.asList(split);

        System.out.println(substring);

    }

    public void test07() {
        List<String> list = Arrays.asList("account","value","entity");
        String express = "1+aaa#account{1001}";
        String express2 = "1+aaa#account{1001;1002}.value{CNY}";
        String express3 = "aaa#entity{a02}.account{1001}.value{CNY}-1";
        String name = "aaa";
//        String dimStr = "account{";
        int index = express3.indexOf(name);
        int length = index + name.length();
        String substring = express3.substring(length);
        System.out.println(substring);
        String exp = substring.substring(1);

        String stringBuilder = getDimStr(exp, list);
        String dimMemberStr = stringBuilder.substring(0, stringBuilder.length()-1);
        StringBuilder builder = new StringBuilder();
        builder.append(name).append("#").append(dimMemberStr);
        String prev = express3.substring(0, index);
        int memberLength = builder.toString().length() + index;
        String next = express3.substring(memberLength);

        StringBuilder result = new StringBuilder();
        StringBuilder append = result.append(prev).append(next);
        System.out.println(stringBuilder);
    }


    public void test08() {
        String express2 = "account{1001;1002}.value{CNY}";
        express2.split(".");
    }

    public void test09() {
        String str = "12345678901234567890123456789012345678901234567890";
        String aa = "   ";
        if (StrUtil.isBlank(aa)) {
            System.out.println(aa);
            System.out.println(str.length());
        }
    }



    public String getDimStr(String express, List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        String columnName = getFirstColumnName(list, express);
        if (StrUtil.isNotBlank(columnName)) {
            List<String> formulaStrList = new ArrayList<>();
            String dimStr = columnName + "{";
            String exp = replaceDimStr(express, dimStr, formulaStrList);
            if (CollectionUtil.isNotEmpty(formulaStrList)) {
                stringBuilder.append(formulaStrList.get(0)).append(".");
            }
            List<String> collect = list.stream().filter(tt -> !tt.equals(columnName)).collect(Collectors.toList());

            String result = getDimStr(exp, collect);
            stringBuilder.append(result);
        }
        return stringBuilder.toString();
    }


    public String replaceDimStr(String express, String dimStr, List<String> formulaStrList) {
        String endStr = "}";
        if (express.indexOf(dimStr) != -1 && express.indexOf(endStr) != -1) {
            int end = express.indexOf(endStr);// }匹配的第一个位置
            int endindex = end + endStr.length();// 字符全匹配的最后位置
            String currentStr = express.substring(0, endindex);// 中间的字符串
            formulaStrList.add(currentStr);
            int length = endindex;// 字符长度
            if (express.length() > length) {// 查看第一个位置之后的字符是否大于字符长度，大于+1剔除“.”
                endindex++;
            }
            String next = express.substring(endindex);// 后面的字符
            return next;
        }
        return express;
    }

    /**
     * 获取存在的列
     * @param
     * @return
     * @date 2022/6/6 19:14
     */
    public String getFirstColumnName(List<String> list, String exp) {
        if (CollectionUtil.isEmpty(list)) {
            return null;
        }
        for (String columnName : list) {
            int len = columnName.length();
            String sub = exp.substring(0, len);
            if (columnName.equals(sub)) {
                return columnName;
            }
        }
        return null;
    }


//    public String replaceDimStr(String express, String dimStr, List<String> formulaStrList) {
//        String endStr = "}";
//        if (express.indexOf(dimStr) != -1 && express.indexOf(endStr) != -1) {
//            int first = express.indexOf(dimStr);// 字符匹配的第一个位置
//            int end = express.indexOf(endStr,first);// 字符匹配的最后位置
//            int length = end + endStr.length() - first;// 字符长度
//            int endindex = end + endStr.length();// 字符全匹配的最后位置
//            String prev = express.substring(0,first);// 第一个位置之前的字符串
//            String currentStr = express.substring(first, endindex);// 中间的字符串
//            formulaStrList.add(currentStr);
//            if (express.length() - first > length) {// 查看第一个位置之后的字符是否大于字符长度，大于+1剔除“.”
//                endindex++;
//            }
//            String next = express.substring(endindex);// 后面的字符
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append(prev).append(next);// 前面字符拼接后面字符
//            return stringBuilder.toString();
//        }
//        return express;
//    }

}
