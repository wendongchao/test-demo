package test.demo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itcast.demo.LexicalParser;
import com.itcast.demo.Parser;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wendongchao
 * @ClassName MyTest8
 * @Date 2021/6/17 17:20
 */
public class MyTest8 {

    @Test
    public void test01(){
        String regex = "\\+";
        String add = "++";
        System.out.println(add.matches(regex));

    }

    @Test
    public void test02(){
        String str = "温东超wen35.4";
//        String regex = "[a-zA-Z]";
        String regex = "[0-9]";
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.println(String.valueOf(chars[i]).matches(regex));
//            System.out.println(regex.matches(String.valueOf(chars[i])));
            System.out.println(chars[i]);
        }
    }

    @Test
    public void test03(){
        String exp = "if($xx$>=$yy$,a,b)";
        String placeholder = "$";
        StringBuffer stringBuffer = new StringBuffer(exp);
        List<Integer> list = new ArrayList<>();
        if (exp.indexOf(placeholder) != -1){
            int index = exp.indexOf(placeholder);
            getIndex(exp,index,list);
        }
        list.stream().forEach(System.out::println);
        System.out.println(exp.indexOf(placeholder));
        System.out.println(exp.indexOf(placeholder, exp.indexOf(placeholder)+1));
        System.out.println(exp.substring(exp.indexOf(placeholder), exp.indexOf(placeholder, exp.indexOf(placeholder) + 1)));
        System.out.println(stringBuffer.substring(stringBuffer.indexOf(placeholder), stringBuffer.indexOf(placeholder, stringBuffer.indexOf(placeholder) + 1)));
        System.out.println(stringBuffer.substring(exp.indexOf(placeholder)+1));
    }
    public void getIndex(String exp,Integer index,List<Integer> list){
        String placeholder = "$";
        list.add(index);
        if (exp.indexOf(placeholder,index+1) != -1){
            index = exp.indexOf(placeholder,index+1);
            getIndex(exp,index,list);
        }
    }

    @Test
    public void test04() throws Exception {
        String exp = "if(abs(8)>=$yy$,-23.5,9)if";
        List<String> parse = LexicalParser.parse(exp);
        System.out.println(LexicalParser.parse(exp));
        Parser.checkParser(parse);
    }

    @Test
    public void test05(){
        int a = 0;
        if (3<3){
            System.out.println("aaa");
        }
        String exp = "if(abs(8)>=$yy$,6-23.5,9)";
        String s = "abs()";
        List<String> list = new ArrayList<>();
        list.add("abs");
        list.add("(");
        list.add("8");
        list.add("9");
        list.add(")");
        list.indexOf("(");
        list.indexOf(")");
        System.out.println(list.subList(list.indexOf("(")+1, list.indexOf(")")));


        System.out.println(exp.substring(s.length() + 1, exp.length() - 1));
        String num = "23.5";
        String regex = "^\\d+(\\.?\\d)?";
        System.out.println(num.matches(regex));
    }

    @Test
    public void test06(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("f");
        list.add("b");
        List<String> list1 = new ArrayList<>();
        list1.add("e");
        list1.add("f");
        list1.add("f");
        list1.add("b");
        List<String> list2 = new ArrayList<>();
        for (String s : list) {
            if (!list1.contains(s)){
                list2.add(s);
            }
        }
        System.out.println(list2);
    }
    @Test
    public void test07(){
        String str = "Account{Base(BS1001,0)},dimensionDisplay(showmemberdesc)";
        String aa = "account**adsfasdfa";
//        System.out.println(aa.substring(0, aa.indexOf("**")));
//        System.out.println(aa.substring(aa.indexOf("**") + 2));
//        System.out.println(str.substring(0,str.lastIndexOf(",")));
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        System.out.println(JSONArray.parseArray(JSONObject.toJSONString(list)));
    }

    @Test
    public void test08(){
        String str = "Children(TotalPeriod,0);Beginning;Descendant(TotalPeriod,0);IDescendant(TotalPeriod,0);IChildren(TotalPeriod,0);Base(TotalPeriod,0);IBase(TotalPeriod,0)";
        String str1 = "1,Descendant(TotalPeriod,0),2";
        List<String> list = new ArrayList<>();
        String nodeChild = "Children";
        String nodeIChild = "IChildren";
        String nodeDesc = "Descendant";
        String nodeIDesc = "IDescendant";
        String nodeBase = "Base";
        String nodeIBase = "IBase";
        list.add(nodeChild);
        list.add(nodeIChild);
        list.add(nodeDesc);
        list.add(nodeIDesc);
        list.add(nodeBase);
        list.add(nodeIBase);

        System.out.println(str.indexOf(";"));
        String[] split = str.split(";");
        for (int i = 0; i < split.length; i++) {
//            System.out.println(split[i]);
            for (int j = 0; j < list.size(); j++) {
                if (split[i].contains(list.get(j))) {
                    System.out.println(split[i].indexOf(")"));
                    System.out.println(split[i]);
                    break;
                }
            }
        }

    }

    @Test
    public void test09(){
        String str = "http://192.168.8.181:30042/reconciliation-report-server1-0";
        System.out.println(str.substring(str.lastIndexOf("/")+1));
    }

    @Test
    public void test10(){
//        String str = "a37c2764-ae48-4280-a9fa-d9cde16821cf";
//        System.out.println(str.length());
        // 系统当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        String nowtime = df.format(new Date(System.nanoTime()));
        System.out.println(nowtime);
        System.out.println(RandomUtil.randomString(32));
        Long nanoTime = System.nanoTime(); // 纳秒
        System.out.println(nanoTime);
    }

}
