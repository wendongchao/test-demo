package test04;

import cn.hutool.core.util.StrUtil;
import junit.framework.TestCase;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther: dongchao
 * @data: 2022/10/9 10:48
 */
public class Test01 extends TestCase {

    public void test01() throws ParseException {
        String str = "2022/01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
        Calendar calendar  = Calendar.getInstance();
        calendar.setTime(sdf.parse(str));
        calendar.add(Calendar.MONTH,-1);
        String format = sdf.format(calendar.getTime());
        System.out.println(format);
    }

    public void test02() {
        String regex = "10|11|12";
        String regex1 = "[1-9]?";
        String str = "32";
        System.out.println(str.matches(regex));
        System.out.println(str.matches(regex1));
    }

    public void test03() {
        String num = "302";
        BigDecimal bigDecimal = new BigDecimal(num);
        System.out.println(bigDecimal.add(new BigDecimal(22)).toPlainString());
        System.out.println(bigDecimal.toPlainString());
    }

    public void test04() {
        String str = "\\Test\\ODS_INTERFACE\\";
        String s = "";
        if (str.contains("/")) {
            List<String> strings = Arrays.asList(str.split("/"));
            System.out.println(strings.stream().filter(tt -> StrUtil.isNotBlank(tt)).collect(Collectors.joining("/")));
        } else if (str.contains("\\")){
            List<String> list = Arrays.asList(str.split("\\\\"));
            System.out.println(list.stream().filter(tt -> StrUtil.isNotBlank(tt)).map(tt -> tt.toLowerCase()).collect(Collectors.joining("/")));
        }
    }

    public void test05() {
        String str = "8.022277332E7";

    }

}
