package test03;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dongchao
 * @date 2022/6/24 14:40
 */
public class Test extends TestCase {

    public void test01() {
        String exp = "scenario{Actual}";
        String name = "scenario";
        int i = exp.indexOf(name);
        int len = i + name.length();
        String substring = exp.substring(0, len);
        System.out.println(substring);
        int end = exp.lastIndexOf("}");
        String substring1 = exp.substring(len + 1, end);
        System.out.println(substring1);

        int i1 = exp.indexOf("{");
        int i2 = exp.indexOf("}");
        String substring2 = exp.substring(i1 + 1, i2);
        System.out.println(substring2);
        String substring3 = exp.substring(0, i1);
        System.out.println(substring3);
    }

    public void test02() {
        String formula = "合并模型#account{1001}.View{Closing}-10+aa#account{120}";
        Map<String,String> map = new HashMap<>();
        map.put("aa","aa#account{120}");
        map.put("合并模型","合并模型#account{1001}.View{Closing}");
        for (String key : map.keySet()) {
            String replace = map.get(key);
            formula = formula.replace(replace,key);
        }
        System.out.println(formula);
    }
}
