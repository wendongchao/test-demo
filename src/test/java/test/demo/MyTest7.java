package test.demo;

import org.junit.Test;

/**
 * @author wendongchao
 * @ClassName MyTest7
 * @Date 2021/4/12 15:30
 */
public class MyTest7 {

    @Test
    public void test01(){
        System.out.println(testMethod("11","22"));
    }
    public String testMethod(String... params){
        String str = "haha{1},kkkk{2}";
        StringBuffer buf = new StringBuffer(str);
        String prefix = "{";
        String suffix = "}";
        for (int i = 1; i <= params.length; i++) {
            String oldChar = prefix+i+suffix;
            buf.replace(buf.indexOf(prefix), buf.indexOf(suffix) + 1, params[i-1]);
        }
        return buf.toString();
    }
}
