package test.demo;

import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wendongchao
 * @des:
 * @Date 2021/3/14 10:27
 */
public class MyTest6 {

    @Test
    public void test01(){
        List<String> list = new ArrayList<String>();
        list.add("zhang");
        list.add("wang");
        list.add("zhao");
        list.add("qian");
        list.add("sun");
        String json = JSONUtil.toJsonStr(list);
        System.out.println(json);
    }

}
