package test02;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.HashSet;

/**
 * @author wendongchao
 * @ClassName Test01
 * @Date 2022/1/14 17:30
 */
public class Test01 {
    /**
     * 首先，是为了支持哈希表类的如之类的底层使用了哈希表的类。
     * Java Object规范中int hashCode()方法的约定类容有三个：
     * <p>
     * （1） 只要对象的equals方法所用到的信息没有修改，那么hashCode方法必须始终如一的返回一个同一整数，在同一应用程序中多次执行中，每一次执行可以不一样。
     * <p>
     * （2） 如果两个对象的equals方法想到，那么每一个对象单独调用hashCode返回的数字必须相等。
     * <p>
     * （3） 如果两个对象的equals方法不相等，hashCode方法的返回值可以相等，给不同的对象返回不同的值可以提高哈希表的性能。
     * 所有重写 equals的同时 必须重写hashcode
     * @param args
     */
    public static void main(String[] args) {
        UcsAccountLog ucsAccountLog = new UcsAccountLog();
        ucsAccountLog.setVersion("1");
        ucsAccountLog.setId("2");
        UcsAccountLog ucsAccountLog2 = new UcsAccountLog();
        ucsAccountLog2.setVersion("2");
        ucsAccountLog2.setId("2");
        HashSet set = new HashSet();
        set.add(ucsAccountLog);
        set.add(ucsAccountLog2);
        System.out.println(set.size());//1
        System.out.println(ucsAccountLog.hashCode() == ucsAccountLog2.hashCode());//true  因为重写了hashcode 所有这里hashcode是相等的
        //如果没有重写 hashcode是根据内存地址值
        System.out.println(ucsAccountLog.equals(ucsAccountLog2));//true
        //根据hash
        System.out.println(ucsAccountLog == ucsAccountLog2);//false  ==比较的是对象的地址，两个对象地址指向一个内存地址

    }
}

@Data
@EqualsAndHashCode(of = {"version"}, callSuper = true)
class UcsAccountLog extends  BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

}

@Data
class BaseVO {
    public String version;
}


