package test.demo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.HashUtil;
import com.alibaba.fastjson.JSONObject;
import com.ql.util.express.parse.Word;
import com.ql.util.express.parse.WordSplit;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wendongchao
 * @ClassName MyTest10
 * @Date 2021/8/11 23:11
 */
public class MyTest10 {
    // + - * / > >= < <= <> == if abs round ( ) , or and $
    private static List<String> func = FunctionEnum.getSignFun();
    @Test
    public void test01() {
        String str = "if(abs(8)>=$yy$,6-23.5,9)";
        String[] splitWord = func.toArray(new String[func.size()]);
        try {
            Word[] parse = WordSplit.parse(splitWord, str);
            System.out.println(parse);
        }catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    public void test02() {
        List<List<String>> list = new ArrayList<>();
        List<String> collect = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            collect.add("aa"+i);
        }

        int n = collect.size() / 5;
        int m = collect.size() % 5;
        if (n == 0) {
            for (int i = 0; i < collect.size(); i++) {
                System.out.println(collect.get(i));
            }
        }else {
            int num = 0;
            num = m == 0 ? num : 1;
            for (int i = 0; i < n + num; i++) {
                int start = i*5;
                int end = (i+1)*5;
                end = end > collect.size() ? collect.size() : end;
                list.add(collect.subList(start,end));
            }
        }

        System.out.println(list.toString());
    }

    @Test
    public void test03() {
        String now = DateUtil.now();
        String str = now.substring(0, now.length()-3);
        String aa = str.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
        System.out.println(aa);
        System.out.println(DateUtil.today());
    }

    @Test
    public void test04(){
        List<String> collect = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            collect.add("aa"+i);
        }

        List<String> collect1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            collect1.add("aa"+i);
        }

        for (String s : collect) {
            for (String s1 : collect1) {
                System.out.println(s1);
                if (!s.equals(s1)) {
                    continue;
                }else{
                    break;
                }
            }
        }
    }

    @Test
    public void test05(){
        String str = "aa&bb&cc&1";
        System.out.println(str.substring(0, str.lastIndexOf("&")));
    }

    @Test
    public void test06(){
        String str = "\\rr";
        String[] split = str.split("\\\\");

        System.out.println(str.substring(str.lastIndexOf("\\")));
    }

    @Test
    public void test07() {
        Set<Integer> statusSet = new HashSet<>();
        statusSet.add(3);
        statusSet.add(4);
        statusSet.add(5);
        statusSet.add(6);
        statusSet.add(99);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        for (Integer integer : list) {
            if (statusSet.contains(integer)) {
                statusSet.remove(integer);
            }
        }
        System.out.println(statusSet.size());
    }

    @Test
    public void test08() {
        String version = "1.0";
        String type = "IMFM";
        System.out.println(version.replaceAll("\\.", "_"));
    }

    @Test
    public void test09() {
        String  str = "{\"Entity\":\"a\",\"Year\":\"2019\",\"Period\":\"1\"}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println(HashUtil.javaDefaultHash(jsonObject.toJSONString()));
    }

    @Test
    public void test10() {
        String str = "test_wck_003";
        if (str.length()>10) {
            System.out.println(str.substring(str.length() - 10));
        }
//        System.out.println(str.substring(11));
    }

    // 1953156812
    //2019-a-1
    @Test
    public void test11() {
        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("a");
        set.add("2019");

        String hashStr = set.stream().collect(Collectors.joining("-"));

        System.out.println(hashStr);
        System.out.println(HashUtil.javaDefaultHash(hashStr));
    }

    @Test
    public void test12() {
        Set<String> r1 = new TreeSet<>();
        r1.add("1");
        r1.add("2019");
        r1.add("2019");
        String key1 = r1.stream().collect(Collectors.joining("-"));
        r1.forEach(e-> System.out.print(e+" "));
        System.out.println(key1);
        System.out.println();
    }

    @Test
    public void test13() {
        Set<String> r2 = new TreeSet<>();
        r2.add("2019");
        r2.add("a");
        r2.add("1");
        String key2 = r2.stream().collect(Collectors.joining("-"));
        r2.forEach(e-> System.out.print(e+" "));
        System.out.println(key2);
    }

    public static void main(String[] args) throws Throwable {


        Set<String> r1 = new HashSet<>();
        r1.add("12");
        r1.add("52");
        r1.add("2");
        String key1 = r1.stream().collect(Collectors.joining("-"));
        r1.forEach(e-> System.out.print(e+" "));
        System.out.println(key1);
        System.out.println();

        Set<String> r2 = new HashSet<>();
        r2.add("52");
        r2.add("12");
        r2.add("2");
        String key2 = r2.stream().collect(Collectors.joining("-"));
        r2.forEach(e-> System.out.print(e+" "));
        System.out.println(key2);
    }

    // {"Entity":"ac","Year":"2019","Period":"1"}
    @Test
    public void test14() {
        Map<String,String> map = new HashMap<>();
        map.put("Year","2019");
        map.put("Entity","f");
        map.put("Period","1");

        String dataBlock = getConDataBlockParam(map);
        System.out.println(dataBlock);
        System.out.println(HashUtil.javaDefaultHash(dataBlock));


    }

    public String getConDataBlockParam(Map<String, String> map) {
        // 查询条件组装
        Set<String> set = new TreeSet<>();
        List<String> collect = map.keySet().stream().collect(Collectors.toList());
        for (String key : collect) {
            String str = key+"@"+String.valueOf(map.get(key)).trim();
            set.add(str);
        }
        return set.stream().collect(Collectors.joining("-"));
    }

    @Test
    public void test15() {
        List<String> list = new ArrayList<>();
        list.add("Account{}->ICP{}->Custom1{}->Custom2{}->Custom3{}->Custom4{}");
        list.add("Account{}->ICP{}");
        System.out.println(JSONObject.toJSONString(list));
    }

    @Test
    public void test16() {
        System.out.println(HashUtil.javaDefaultHash("eentity@0156-period@9-scenario@Actual-year@2022"));
        System.out.println(HashUtil.javaDefaultHash("entity@0170-period@9-scenario@Actual-year@2022"));
        System.out.println(HashUtil.javaDefaultHash("entity@0170-period@9-scenario@Actual-year@2022"));

//        System.out.println("----");
//        System.out.println(HashUtil.javaDefaultHash("entity@0196-period@5-scenario@Actual-year@2022"));
//        System.out.println(HashUtil.javaDefaultHash("entity@0196-period@6-scenario@Actual-year@2022"));
//        System.out.println(HashUtil.javaDefaultHash("entity@0196-period@7-scenario@Actual-year@2022"));
    }

}
