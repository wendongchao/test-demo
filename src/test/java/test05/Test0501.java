package test05;

import junit.framework.TestCase;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @auther wendongchao
 * @date 2023/6/8 16:31
 **/
public class Test0501 extends TestCase {

    public void test01() throws Exception {
        String regex = "^[\\+\\-\\*\\/\\d()A-Za-z\\s]+$";
        String str = "E7+D7";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(str);
        String[] tokens = str.split("(?<=[\\+\\-\\*\\/\\(\\)])|(?=[\\+\\-\\*\\/\\(\\)])");

        System.out.println(str.matches(regex));
    }

    public void test02() {
        String formula = "(2+3)*8-12/4";
        String regex = "^[\\+\\-\\*\\/\\d()A-Za-z\\s]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(formula);
        if (matcher.matches()) {
            // 匹配成功，开始解析公式
            String[] tokens = formula.split("(?<=[\\+\\-\\*\\/\\(\\)])|(?=[\\+\\-\\*\\/\\(\\)])");
            String[] handleTokens = handleTokens(tokens);
            double result = evaluate(handleTokens);
            System.out.println("结果是：" + result);
        } else {
            // 匹配失败，公式不符合要求
            System.out.println("公式不符合要求");
        }
    }

    /**
     * 计算算术表达式的值
     * @param tokens 算术表达式的 token 数组
     * @return 算术表达式的值
     */
    public static double evaluate(String[] tokens) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for (String token : tokens) {
            if (token.matches("\\d+")) {
                // 如果是数字，将其压入值栈
                values.push(Double.parseDouble(token));
            } else if (token.matches("[\\+\\-\\*\\/]")) {
                // 如果是运算符，将其压入运算符栈
                while (!operators.empty() && hasPrecedence(operators.peek(), token.charAt(0))) {
                    if (values.size() == 1) {
                        break;
                    }
                    double val2 = values.pop();
                    double val1 = values.pop();
                    char op = operators.pop();
                    values.push(applyOp(val1, val2, op));
                }
                operators.push(token.charAt(0));
            } else if (token.equals("(")) {
                // 如果是左括号，将其压入运算符栈
                operators.push('(');
            } else if (token.equals(")")) {
                // 如果是右括号，计算括号内的表达式，并将结果压入值栈
                while (operators.peek() != '(') {
                    double val2 = values.pop();
                    double val1 = values.pop();
                    char op = operators.pop();
                    values.push(applyOp(val1, val2, op));
                }
                operators.pop();
            }
        }
        // 计算剩余的表达式
        while (!operators.empty()) {
            if (values.size() == 1) {
                break;
            }
            double val2 = values.pop();
            double val1 = values.pop();
            char op = operators.pop();
            values.push(applyOp(val1, val2, op));
        }
        // 返回最终结果
        return values.pop();
    }

    /**
     * 判断运算符 op1 和 op2 的优先级关系
     * @param op1 运算符 1
     * @param op2 运算符 2
     * @return 如果 op1 的优先级高于等于 op2，则返回 true；否则返回 false
     */
    public static boolean hasPrecedence(char op1, char op2) {
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return true;
        } else if ((op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/')) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 对两个数进行运算
     * @param val1 第一个数
     * @param val2 第二个数
     * @param op 运算符
     * @return 运算结果
     */
    public static double applyOp(double val1, double val2, char op) {
        switch (op) {
            case '+':
                return val1 + val2;
            case '-':
                return val1 - val2;
            case '*':
                return val1 * val2;
            case '/':
                if (val2 == 0) {
                    throw new UnsupportedOperationException("除数不能为零");
                }
                return val1 / val2;
            default:
                throw new IllegalArgumentException("非法运算符");
        }
    }

    private String[] handleTokens(String[] tokens) {
        for (String token : tokens) {
            if (!token.matches("[\\+\\-\\*\\/\\(\\)]")) {
                String regex = "(?<=[A-Z])|(?=[A-Z])";
                String[] split = token.toUpperCase().split(regex);
                StringBuffer chatBuffer = new StringBuffer();
                StringBuffer numBuffer = new StringBuffer();
                boolean chatFlag = true;
                for (int i = 0; i < split.length; i++) {
                    if (!split[i].matches("[A-Z]")) {
                        chatFlag = false;
                    }
                    if (chatFlag) {
                        chatBuffer.append(split[i]);
                    } else {
                        numBuffer.append(split[i]);
                    }
                }
                String rowIndex = chatBuffer.toString();
                String colIndex = numBuffer.toString();
                System.out.println(getIndex(rowIndex));

            }
        }
        return null;
    }

    public Integer getIndex(String column) {
        int n = 0;
        for (int i = 0; i < column.length(); i++) {
            char c = column.charAt(i);
            int digit = c - 'A' + 1;
            n = n * 26 + digit;
        }
        return Integer.valueOf(n);
    }

    public void test03() throws Exception {
        String formula = "Ae7+d8";
        String[] tokens = formula.split("(?<=[\\+\\-\\*\\/\\(\\)])|(?=[\\+\\-\\*\\/\\(\\)])");
        String[] handleTokens = handleTokens(tokens);
    }

    public void test04() throws Exception {
        String str = "-1.42232057555E9";
        double num = Double.parseDouble(str);
        NumberFormat formatter = new DecimalFormat("#0.0000");
        String format = formatter.format(num);
        System.out.println(format);
    }

    public void test05() throws Exception {
        String[] handleTokens = new String[]{"(",")","-","7"};
        double result = evaluate(handleTokens);
        System.out.println(result);
    }

    public void test06() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("doc/num.txt");

        int read = fileInputStream.read();
        StringBuffer buffer = new StringBuffer();
        while (read != -1) {
            buffer.append((char) read);
            read = fileInputStream.read();
        }
        fileInputStream.close();

        String[] split = buffer.toString().split(",");
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < split.length; i++) {
            set.add(Integer.valueOf(split[i]));
        }
        getNormalIndexRange(4,set);
        System.out.println(split.length);

    }

    private List<Integer[]> getNormalIndexRange(Integer fromRow, Set<Integer> set) {
        List<Integer[]> list = new ArrayList<>();
        List<Integer> sort = set.stream().sorted().collect(Collectors.toList());
        int start = fromRow;
        int index = 0;
        boolean flag = false;

        Map<Integer,Integer> map = new HashMap<>();
        int n = 0;
        int begin = 0;
        for (int i = 0; i < sort.size(); i++) {
            Integer num = sort.get(i);
            if (n == num) {
                map.put(begin,num);
            } else {
                begin = num;
                n = num;
                map.put(begin,num);
            }
            n++;
        }
        List<Integer> collect = map.keySet().stream().sorted().collect(Collectors.toList());

        for (Integer col : collect) {
            Integer[] startEnd = new Integer[2];
            startEnd[0] = col;
            startEnd[1] = map.get(col);
            list.add(startEnd);
        }

//        for (int i = 0; i < sort.size(); i++) {
//            Integer num = sort.get(i);
//            if (flag) {
//                start = fromRow + num;
//                flag = false;
//            }
//            int add = num + 1;
//            index++;
//            if (!set.contains(add)) {
//                Integer[] startEnd = new Integer[2];
//                startEnd[0] = start;
//                startEnd[1] = start + index - 1;
//                list.add(startEnd);
//                flag = true;
//                index = 0;
//            }
//        }
        return list;
    }


    public void test07() {
        String str = "A1-1-实体A1-1";
        String aa = "A1-1";
        System.out.println(str.substring(0, aa.length()));
    }

    public void test08() {
        String str = "get(S#Month.Y#2020.P#December.A#1002) for journal=\"凭证名称1\"";
        System.out.println(str.indexOf("journal"));
        System.out.println(str.indexOf("="));

        System.out.println(str.substring(44, str.length()));

    }

    public void test09() {
        String str = "20000314-BMT-国投新疆罗布泊钾盐有限责任公司-所有部门";
        int index = str.indexOf("-");
        System.out.println(str.indexOf("-"));
        System.out.println(str.substring(0, index));
        System.out.println(str.substring(index+1, str.length()));
    }


    public void test10() {
        List<Integer> list = IntStream.rangeClosed(1, 30)
                .boxed()
                .collect(Collectors.toList());

        List<List<Integer>> resultList = IntStream.range(0, list.size())
                .boxed()
                .filter(i -> i % 9 == 0)
                .map(i -> list.subList(i, Math.min(i + 9, list.size())))
                .collect(Collectors.toList());

// 打印结果
        for (List<Integer> subList : resultList) {
            System.out.println(subList);
        }

    }

    public void test11() {

        String columnName ="应收/应付利息合计-对账";
        String str ="对账";
        String substring = columnName.substring(columnName.length() - str.length(), columnName.length());
        System.out.println(substring);
        if (str.equals(substring)) {
            int index = columnName.indexOf(str);
            String substring1 = columnName.substring(index-1);
            String bb = "-"+str;
            if (bb.equals(substring1)) {
                System.out.println("ok");
            }
            System.out.println(substring1);
        }

    }

    public void test12() {
        String pro = ",enumerate(1,C201),match_name";
        System.out.println(enumStr(pro));
        String a = "(";
        String b = ")";
        System.out.println(a);
    }

    private String enumStr(String property) {
        if (property.length() > 0 && property.indexOf("enumerate") != -1) {
            int index = property.indexOf("enumerate");
            if (index + 1 < property.length()) {
                char[] charArray = property.toCharArray();
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = index; i < charArray.length; i++) {
                    stringBuffer.append(charArray[i]);
                    if (charArray[i] == ')') {
                        break;
                    }
                }
                return stringBuffer.toString();
            }
        }
        return null;
    }

    public void test13() {
        String columnName ="应收/应付利息合计-对账";
        int index1 = columnName.indexOf("-对账");
        String substring = columnName.substring(0, index1);
        System.out.println(substring);
    }

    public void test14() {
        int dataScale = 8;
        int temp = 1;
        for (int i = 0; i < dataScale; i++) {
            temp = temp * 10;
        }
        System.out.println(temp);
        System.out.println(new BigDecimal(temp));
    }

    public void test15() {
        String str = "企业全称（简体中文）";
        String str2 = "企业全称（简体中文）";
        System.out.println(str.equals(str2));
    }

    public void test16() {
        String str = " .123";
        char[] charArray = str.trim().toCharArray();
        System.out.println(charArray[0]);
        System.out.println(charArray[0] == '.');
    }

    public void test17() {
        String str = "123";
        String str2 = "宁波";
        String str3 = "ningbo";
        System.out.println(str.matches("\\d+"));
        System.out.println(str2.matches("\\d+"));
        System.out.println(str3.matches("\\d+"));
    }

    public void test18() {
        String str = "22,444.88";
        System.out.println(str.replace(",", ""));

        String str2 = "2.2239905269111E11";
        BigDecimal bigDecimal2 = new BigDecimal(str2);
        System.out.println(bigDecimal2.toPlainString());
    }


}
