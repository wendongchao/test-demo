package test.demo;

import cn.hutool.core.util.StrUtil;
import com.netty.kryo.dto.RpcRequest;
import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wendongchao
 * @ClassName MyTest11
 * @Date 2022/2/25 16:37
 */
public class MyTest11 extends TestCase {


    public void test01() {
        String str = "0E-7";
        BigDecimal bigDecimal = new BigDecimal(str);
        System.out.println(bigDecimal.toPlainString());
    }

    public void test02() {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add("ee");
        list.add("ff");
        list.add("tt");
        for (int i = list.size() - 1; i > 0; i--) {
            System.out.println(list.get(i));
        }

//        String collect = list.stream().collect(Collectors.joining("#"));
//        System.out.println(collect);
    }

    public void test03() {
        List<String> list = getList();
        List<String> list2 = new ArrayList<>();
        list2.add("aa");
        list2.add("bb");
        list2.add("cc");
        list2.add("dd");
        list2.add("ee");

        for (String s : list) {
            if (list2.contains(s)) {
                list2.remove(s);
            }
        }
    }


    public List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add("ee");
        list.add("ff");
        list.add("tt");
        return list;
    }

    public void test04() {
        LinkedList<Map<String, String>> linkedList = new LinkedList<>();
        getLinKedList(linkedList, "100");
        getLinKedList(linkedList, "200");
        getLinKedList(linkedList, "100");
        getLinKedList(linkedList, "400");
        getLinKedList(linkedList, "500");
        getLinKedList(linkedList, "600");

    }

    public void getLinKedList(LinkedList<Map<String, String>> linkedList, String value) {
        Map<String, String> data = getData();
        data.put("data", value);
        linkedList.add(data);
    }

    public Map<String, String> getData() {
        Map<String, String> map = new HashMap<>();
        map.put("Account", "1001");
        map.put("Entity", "2000C");
        map.put("Year", "2020");
        map.put("Period", "1");
        return map;
    }

    public String getConDataBlockParam(Map<String, String> map) {
        // 查询条件组装
        List<String> list = new ArrayList<>();
        map.forEach((k, v) -> {
            if (k.equals("data")) {
                return;
            }
            list.add(k + "@" + v);
        });
        return list.stream().sorted().collect(Collectors.joining("-"));
    }


    public void test05() {
        RpcRequest rpcRequest = null;
        List<RpcRequest> rpcRequests = Arrays.asList(rpcRequest);
        for (RpcRequest request : rpcRequests) {
            System.out.println(request);
        }
        System.out.println(rpcRequests);
    }

    public void test06() {
        String aa = "处置子公司";
        String bb = "|处置子公司|";
//        System.out.println(aa.equals(bb));
//        System.out.println(aa.contains(bb));
        System.out.println(bb.contains(aa));

        List<Integer> list = Arrays.asList(0, 2, 3, -1, -6, 98);
        System.out.println(list.stream().sorted().collect(Collectors.toList()));
    }

    public void test07() {
        Object obj = " ";
        System.out.println(obj != null);
        System.out.println(StrUtil.isBlank(obj.toString()));
        System.out.println(obj.toString().length());
    }


}
