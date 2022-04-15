package test.demo;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.InstructionSet;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wendongchao
 * @ClassName MyTest9
 * @Date 2021/7/27 23:58
 */
public class MyTest9 {

    @Test
    public void test01() throws Exception {
        ExpressRunner runner = new ExpressRunner(false,true);
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("a",-80.2225888888);
        context.put("b",2);
        context.put("c",0);
        runner.addFunctionOfClassMethod("abs", Math.class.getName(), "abs",
                new String[] { "double" }, null);
//        String express = "round(a)";
//        String express = "(8+ 9>a?6-23.5999999999:9)";
        String express = "(abs(8)>=a?6-23.5:9)";
//        String exp = "9";
        InstructionSet instructionSet = runner.parseInstructionSet(express);
//        List<String> errorList = new ArrayList<>();
//        Object r = runner.execute(express, context, errorList, true, true);
//        BigDecimal bigDecimal = new BigDecimal(r.toString());
//        errorList.stream().forEach(System.out::println);
//        System.out.println(bigDecimal);
//        String str = "vnkkdr003_MainTable22444_aftdj";
//        System.out.println(str.length());
    }

    @Test
    public void test02() {
        System.out.println((8 + 9   > 10));
    }

    private static String toSufExpr(String expr) throws Exception {
        System.out.println("将"+expr+"解析为后缀表达式...");
        /*返回结果字符串*/
        StringBuffer sufExpr = new StringBuffer();
        /*盛放运算符的栈*/
        Stack<String> operator = new Stack<String>();
        operator.push(null);//在栈顶压人一个null，配合它的优先级，目的是减少下面程序的判断
        /* 将expr打散分散成运算数和运算符 */
        Pattern p = Pattern.compile("(?<!\\d)-?\\d+(\\.\\d+)?|[+\\-*/()]");//这个正则为匹配表达式中的数字或运算符
        Matcher m = p.matcher(expr);
        while (m.find()) {
            String temp = m.group();
            if (temp.matches("[+\\-*/()]")) { //是运算符
                if (temp.equals("(")) { //遇到左括号，直接压栈
                    operator.push(temp);
                    System.out.println("'('压栈");
                } else if (temp.equals(")")) { //遇到右括号，弹栈输出直到弹出左括号（左括号不输出）
                    String topItem = null;
                    while (!(topItem = operator.pop()).equals("(")) {
                        System.out.println(topItem+"弹栈");
                        sufExpr.append(topItem+" ");
                        System.out.println("输出:"+sufExpr);
                    }
                } else {//遇到运算符，比较栈顶符号，若该运算符优先级大于栈顶，直接压栈；若小于栈顶，弹栈输出直到大于栈顶，然后将改运算符压栈。
                    while(getPriority(temp) <= getPriority(operator.peek())) {
                        sufExpr.append(operator.pop()+" ");
                        System.out.println("输出sufExpr:"+sufExpr);
                    }
                    operator.push(temp);
                    System.out.println("\""+temp+"\""+"压栈");
                }
            }else {//遇到数字直接输出
                sufExpr.append(temp+" ");
                System.out.println("输出sufExpr:"+sufExpr);
            }

        }

        String topItem = null;//最后将符合栈弹栈并输出
        while(null != (topItem = operator.pop())) {
            sufExpr.append(topItem+" ");
        }
        return sufExpr.toString();
    }

    private static int getPriority(String s) throws Exception {
        if(s==null) return 0;
        switch(s) {
            case "(":return 1;
            case "+":;
            case "-":return 2;
            case "*":;
            case "/":return 3;
            default:break;
        }
        throw new Exception("illegal operator!");
    }

    public static String getResult(String expr) throws Exception {
        String sufExpr = toSufExpr(expr);// 转为后缀表达式
        System.out.println("开始计算后缀表达式...");
        /* 盛放数字栈 */
        Stack<Double> number = new Stack<Double>();
        /* 这个正则匹配每个数字和符号 */
        Pattern p = Pattern.compile("-?\\d+(\\.\\d+)?|[+\\-*/]");
        Matcher m = p.matcher(sufExpr);
        while (m.find()) {
            String temp = m.group();
            if (temp.matches("[+\\-*/]")) {// 遇到运算符，将最后两个数字取出，进行该运算，将结果再放入容器
                System.out.println("符号"+temp);
                double a1 = number.pop();
                double a2 = number.pop();
//                double res = doubleCal(a2, a1, temp.charAt(0));
//                number.push(res);
                System.out.println(a2 + "和" + a1 + "弹栈，并计算" + a2 + temp + a1);
                System.out.println("数字栈：" + number);
            } else {// 遇到数字直接放入容器
                number.push(Double.valueOf(temp));
                System.out.println("数字栈：" + number);
            }
        }
        return number.pop() + "";
    }
}
