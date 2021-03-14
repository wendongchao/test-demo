package test.demo;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class MyTest2 {
    /**
     * 解析json字符串
     */
    @Test
    public void test1(){
        String a = "[{usertype:\"指定人员\",userinfo:\"80009U00000030,李萍,80009U00000031,杨顺庆\"},{usertype:\"指定角色\",userinfo:\"099,超级管理员,299,平台管理员,399,系统管理员\"},{usertype:\"指定部门\",userinfo:\"800090001,业务一部\"}]";
        JSONArray jsonArray = JSON.parseArray(a);
        for (Object o: jsonArray) {
            JSONObject jsonTemp = JSON.parseObject(o.toString());
             System.out.println(o.toString());
             System.out.println(jsonTemp.get("usertype"));
             System.out.println(jsonTemp.get("userinfo"));
        }
    }

    /**
     * 日期字符串加月份
     */
    @Test
    public void test2(){
        SimpleDateFormat sdf =null;
        Calendar calendar   = null;
        try {
            String predict_acceptance = "2020/04/24";
            sdf = new SimpleDateFormat("yyyy/MM/dd");
            calendar= Calendar.getInstance();
            calendar.setTime(sdf.parse(predict_acceptance));
            calendar.add(Calendar.DAY_OF_WEEK,-1);
            String one = sdf.format(calendar.getTime());
            //one = one.substring(0,7);
            System.out.println(one);
            calendar.add(Calendar.MONTH, 12); // 月数
            String two = sdf.format(calendar.getTime());
            two = two.substring(0,7);
            calendar.add(Calendar.MONTH, 12); // 月数
            String three = sdf.format(calendar.getTime());
            calendar.add(Calendar.MONTH, 12); // 月数
            String four = sdf.format(calendar.getTime());
            calendar.add(Calendar.MONTH, 12); // 月数
            String five = sdf.format(calendar.getTime());
//            System.out.println(two);
//            System.out.println(three);
//            System.out.println(four);
//            System.out.println(five);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("a","");
        if (map.get("a")!=null){
            System.out.println("aaaa");
        }else{
            System.out.println("bbbb");
        }
    }

    @Test
    public void test4(){
        Map<String,String> map = new HashMap<>();
        map.put("LeasFormOneRentExport","直租起始租金表!$A$9");
        map.put("rentplanexport_0","直租起始租金表!$B$3");
        map.put("rentplanexprot_7","直租起始租金表!$L$16");
        map.put("rentplanexport_3","直租起始租金表!$D$6");
        map.put("rentplanexport_2","直租起始租金表!$C$5");
        map.put("rentplanexport_1","直租起始租金表!$B$4");
        map.put("rentplanexprot_4","直租起始租金表!$J$6");
        map.put("rentplanexport_6","直租起始租金表!$B$16");
        map.put("rentplanexprot_5","直租起始租金表!$A$15");

        System.out.println(map.get("rentplanexprot_4"));
    }

    @Test
    public void test5(){
        String aa = "23804e00aea74f27975da9378db0bf20@78c521b1954d4746809c4b4e5f98719a@interest@1adbaaa4200a4b2fabb927be9f0cc56c@78c521b1954d4746809c4b4e5f98719a@interest@501042e40f2441f6970f15b44bc63ec1@78c521b1954d4746809c4b4e5f98719a@interest";
        String[] b = aa.split("@");
        for(int i=0;i<b.length;i+=3){
            System.out.println(b[i]);
        }
    }
    @Test
    public void test6(){
        String aa = "select%20itemno,itemname%20from%20code_library%20where%20codeno='ProductContractTemplate'%20and%20itemno%20like%20'001%25'%20AND%20LENGTH(itemno)=6";
        aa = aa.replaceAll("%20"," ");
        aa = aa.replaceAll("%25","%");
        System.out.println(aa);
    }

    @Test
    public void test7(){
        Map<String,String> map = new HashMap<>();
        map.put("a","1");
        map.put("a","2");
        map.put("c","1");
        map.put("d","1");
        map.put("e","1");
        System.out.println(map);
    }
    public static int binarySerrch(int[] array,int a){
        int low = 0;
        int high = array.length-1;
        int mid;
        while (low<=high){
            mid = (high-low)/2+low;//中间位置
            if(array[mid]==a){
                return mid;
            }else if(a>array[mid]){//向右查找
                low = mid +1;
            }else{//向左查找
                high = mid -1;
            }
        }
        return -1;
    }
}
