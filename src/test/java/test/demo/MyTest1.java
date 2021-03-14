package test.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itcast.demo.MyTest01;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author: dong
 * @description:
 * @date: created in 2020/3/18 11:22
 * @modified by:
 */
public class MyTest1 {
    /**
     * 解析json字符串
     */
    @Test
    public void test1(){
        String test = "{\"contractTemplateList\":[{\"baseFileId\":\"ff8080816cd6538b016cd76d9eb10232\",\"contractParty\":[{\"sign_type.lessor\":{\"keyWord\":\"出租人（盖章）：\",\"keywordIndex\":\"1\",\"page\":\"0\",\"sealType\":\"SEAL\",\"side\":\"2\"}},{\"sign_type.lessor\":{\"keyWord\":\"签约日期：\",\"keywordIndex\":\"1\",\"offsetX\":\"0\",\"offsetY\":\"0.0025\",\"page\":\"0\",\"sealType\":\"TIMESTAMP\",\"side\":\"2\"}},{\"sign_type.lessor\":{\"keyWord\":\"承租人（签字）：\",\"keywordIndex\":\"1\",\"page\":\"0\",\"sealType\":\"SEAL\",\"side\":\"0\"}},{\"sign_type.lessee\":{\"keyWord\":\"承租人（签字）：\",\"keywordIndex\":\"1\",\"page\":\"0\",\"sealType\":\"SEAL\",\"side\":\"0\"}}],\"displayName\":\"融资租赁合同(C端)-直租\",\"templateName\":\"融资租赁合同(C端)-直租\",\"templateNumber\":\"F-201908095\"},{\"baseFileId\":\"ff8080816cd6538b016cd76eaa6e0233\",\"contractParty\":[{\"sign_type.lessor\":{\"keyWord\":\"买方（盖章）：\",\"keywordIndex\":\"1\",\"page\":\"0\",\"sealType\":\"SEAL\",\"side\":\"2\"}},{\"sign_type.supplier\":{\"keyWord\":\"卖方（盖章）：\",\"keywordIndex\":\"1\",\"page\":\"0\",\"sealType\":\"SEAL\",\"side\":\"1\"}},{\"sign_type.lessor\":{\"keyWord\":\"签约日期：\",\"keywordIndex\":\"1\",\"offsetX\":\"0\",\"offsetY\":\"0.0025\",\"page\":\"0\",\"sealType\":\"TIMESTAMP\",\"side\":\"2\"}}],\"displayName\":\"购买合同(C端)-直租\",\"templateName\":\"购买合同(C端)-直租\",\"templateNumber\":\"F-201908096\"},{\"baseFileId\":\"ff8080816cd6538b016cd77714e40255\",\"contractParty\":[{\"sign_type.lessor\":{\"keyWord\":\"甲方（盖章）：\",\"keywordIndex\":\"1\",\"page\":\"0\",\"sealType\":\"SEAL\",\"side\":\"2\"}},{\"sign_type.supplier\":{\"keyWord\":\"乙方（盖章）：\",\"keywordIndex\":\"1\",\"page\":\"0\",\"sealType\":\"SEAL\",\"side\":\"1\"}},{\"sign_type.lessor\":{\"keyWord\":\"签约日期：\",\"keywordIndex\":\"1\",\"offsetX\":\"0\",\"offsetY\":\"0.0025\",\"page\":\"0\",\"sealType\":\"TIMESTAMP\",\"side\":\"2\"}}],\"displayName\":\"咨询服务协议(C端)-直租\",\"templateName\":\"咨询服务协议(C端)-直租\",\"templateNumber\":\"F-201908098\"},{\"baseFileId\":\"8ab39c6b6d13e95f016d1491fdb30039\",\"contractParty\":[{\"sign_type.lessee\":{\"keyWord\":\"承租人签字：\",\"keywordIndex\":\"1\",\"page\":\"0\",\"sealType\":\"SEAL\",\"side\":\"0\"}},{\"sign_type.supplier\":{\"keyWord\":\"供应商盖章：\",\"keywordIndex\":\"1\",\"page\":\"0\",\"sealType\":\"SEAL\",\"side\":\"1\"}}],\"displayName\":\"作废文件确认表\",\"templateName\":\"作废文件确认表\",\"templateNumber\":\"F-201909099\"},{\"baseFileId\":\"ff8080816cd6538b016cd7764f7e0250\",\"contractParty\":[{\"sign_type.lessee\":{\"keyWord\":\"承租人（签字）：\",\"keywordIndex\":\"1\",\"page\":\"0\",\"sealType\":\"SEAL\",\"side\":\"0\"}}],\"displayName\":\"验收确认书(C端)-直租\",\"templateName\":\"验收确认书(C端)-直租\",\"templateNumber\":\"F-201908097\"},{\"baseFileId\":\"ff80808170468419017046977a1d0007\",\"contractParty\":[{\"sign_type.lessee\":{\"keyWord\":\"承租人（签字）：\",\"keywordIndex\":\"1\",\"page\":\"0\",\"sealType\":\"SEAL\",\"side\":\"0\"}}],\"displayName\":\"知情确认书(直租)\",\"templateName\":\"知情确认书(直租)\",\"templateNumber\":\"F-202002141\"}],\"displayName\":\"美敦力展翅行动\",\"documentList\":[{\"documentId\":\"1e6a5e8edcc14e2397d311283a7c5020,5c3b28f4c2f349c8a4715f632e255c53,958fe724723342bd8de74b8f0c0ae74c,1903184227754dbfbe2253f83721eebd\",\"documentName\":\"医保信息,医院照片,病例信息,住房公积金\",\"documentParty\":[{\"documentGroup\":\"basics\",\"documentName\":\"医保信息\",\"documentNumber\":\"o_medical_insurance\",\"fileType\":\"photo\",\"isRequired\":\"1\"},{\"documentGroup\":\"basics\",\"documentName\":\"医院照片\",\"documentNumber\":\"o_hospital\",\"fileType\":\"photo\",\"isRequired\":\"0\"},{\"documentGroup\":\"basics\",\"documentName\":\"病例信息\",\"documentNumber\":\"o_case\",\"fileType\":\"photo\",\"isRequired\":\"0\"},{\"documentGroup\":\"basics\",\"documentName\":\"住房公积金\",\"documentNumber\":\"o_reserve\",\"fileType\":\"photo\",\"isRequired\":\"1\"}],\"flowName\":\"项目审批(C端)\",\"flowNo\":\"494480\",\"stageName\":\"进单\",\"stageNo\":\"stage1\"}],\"endDate\":\"2021-01-00\",\"leaseForm\":\"lease_form1\",\"productName\":\"美敦力展翅行动\",\"productNumber\":\"JRCP20200111001\",\"startDate\":\"2020-02-00\",\"status\":\"IsInUse.1\"}";
//        JSONObject json = JSON.parseObject(test);
//        String a = JSON.parseArray(json.get("documentList").toString()).getJSONObject(0).get("flowName").toString();
////        String a = json.get("documentList").toString();
//        System.out.println(a);
//        JSONArray mapList = json.getJSONArray("contractTemplateList");
//        JSONArray mapList2  = JSON.parseArray(json.get("documentList").toString());
//        String b = JSON.parseArray(json.get("documentList").toString()).getJSONObject(0).get("documentName").toString();
//        for(Object temp : mapList2){
//            JSONObject jsonTemp = JSON.parseObject(temp.toString());
//            System.out.println(jsonTemp.get("flowName"));
//            System.out.println(jsonTemp.get("documentName"));
//            System.out.println("asdfasdfadsf");
//        }
//        System.out.println(mapList2);
//        System.out.println(b);
//        String [] tt = b.split(",");
//        System.out.println(tt);
//        for(Object temp : mapList){
//            JSONObject jsonTemp = JSON.parseObject(temp.toString());
//            System.out.println(jsonTemp.get("displayName"));
//        }
//
//        MyTest01 myTest01 = new MyTest01();
//       // myTest01.setPassword(null);
//        myTest01.setPassword("hahahha");
//        System.out.println(myTest01.getPassword());
//-------------------------------------------------------------------------
        String aa = "[{\"ID\":\"G93829F24B95452590C0D61E67DDDE3E\",\"param\":[\"2020/06/04\",\"2020/06/11\",\"12\",\"25\"]},{\"ID\":\"X4clgPPXsfApDcUOfwyXhTCcdTVLBs51\",\"param\":[\"2020/06/03\",\"2020/06/16\",\"23\",\"23\"]},{\"ID\":\"Zg9WQb15852977832481585298359413\",\"param\":[\"2020/06/03\",\"2020/06/11\",\"25\",\"25\"]}]\n";
        String bb = "[{\"ID\":\"eb5e451b1bfb402db3ee492bf636833f\",\"param\":[{\"PROJECT_DATE\":\"2020/06/05\"},{\"PAYMENT_NUMBER\":\"\"},{\"PROJECT_MONEY\":\"2323.00\"},{\"RATE\":\"23\"},{\"LOAN_MONEY\":\"232.00\"},{\"LOAN_INFO\":\"32323\"},{\"REMARK\":\"2323\"}]},{\"ID\":\"29f1eb8ddb6a48d0aadac45ec4e0efbe\",\"param\":[{\"PROJECT_DATE\":\"2020/06/16\"},{\"PAYMENT_NUMBER\":\"CIFL2020YW10063-01\"},{\"PROJECT_MONEY\":\"200.00\"},{\"RATE\":\"13\"},{\"LOAN_MONEY\":\"200.00\"},{\"LOAN_INFO\":\"啊发顺丰\"},{\"REMARK\":\"\"}]}]\n";
        //JSONObject jsonaa = JSON.parseObject(aa);
        JSONArray ttarray = JSON.parseArray(bb);
        for(Object temp : ttarray){
            JSONObject jsonTemp = JSON.parseObject(temp.toString());
          //  System.out.println(jsonTemp.get("ID"));
          //  System.out.println(jsonTemp.get("param"));
            JSONArray a = JSON.parseArray(jsonTemp.get("param").toString());
            String str = "";
            for(Object ob : a){
               Map map = (Map) JSON.parse(ob.toString());
                System.out.println(map);
                System.out.println(map.keySet());
                System.out.println(map.get(map.keySet()));
            }



        }

    }

    /**
     * 判断字符串包含关系
     */
    @Test
    public void test2(){
        String file_name="抵押合同(自然人)-20200227测试.pdf";
        String name = "抵押合同(法人)";
        if(file_name.indexOf(name)!=-1){
            System.out.println("haha");
        }else{
            System.out.println("ssss");
        }
    }

    /**
     * 获取UUID
     */
    @Test
    public void test3(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
       // System.out.println(uuid);

        String flowunid = "FBO2020042800000006";
        System.out.println(flowunid.substring(16));
    }

    /**
     * java8Stream练习
     */
    @Test
    public void test4(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        Long filtered2 = strings.stream().filter(string -> !string.isEmpty()).count();
//        System.out.println(strings);
//        System.out.println(filtered2);
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
//        System.out.println(numbers);
//        System.out.println(squaresList);

        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

    /**
     * java8Stream练习
     */
    @Test
    public void test5(){
        System.out.println("使用 Java 7: ");

        // 计算空字符串
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        System.out.println("列表: " +strings);
        long count = getCountEmptyStringUsingJava7(strings);

        System.out.println("空字符数量为: " + count);
        count = getCountLength3UsingJava7(strings);

        System.out.println("字符串长度为 3 的数量为: " + count);

        // 删除空字符串
        List<String> filtered = deleteEmptyStringsUsingJava7(strings);
        System.out.println("筛选后的列表: " + filtered);

        // 删除空字符串，并使用逗号把它们合并起来
        String mergedString = getMergedStringUsingJava7(strings,", ");
        System.out.println("合并字符串: " + mergedString);
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        // 获取列表元素平方数
        List<Integer> squaresList = getSquares(numbers);
        System.out.println("平方数列表: " + squaresList);
        List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);

        System.out.println("列表: " +integers);
        System.out.println("列表中最大的数 : " + getMax(integers));
        System.out.println("列表中最小的数 : " + getMin(integers));
        System.out.println("所有数之和 : " + getSum(integers));
        System.out.println("平均数 : " + getAverage(integers));
        System.out.println("随机数: ");

        // 输出10个随机数
        Random random = new Random();

        for(int i=0; i < 10; i++){
            System.out.println(random.nextInt());
        }

        System.out.println("使用 Java 8: ");
        System.out.println("列表: " +strings);

        count = strings.stream().filter(string->string.isEmpty()).count();
        System.out.println("空字符串数量为: " + count);

        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("字符串长度为 3 的数量为: " + count);

        filtered = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选后的列表: " + filtered);

        mergedString = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        squaresList = numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());
        System.out.println("Squares List: " + squaresList);
        System.out.println("列表: " +integers);

        IntSummaryStatistics stats = integers.stream().mapToInt((x) ->x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("随机数: ");

        random.ints().limit(10).sorted().forEach(System.out::println);

        // 并行处理
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串的数量为: " + count);
    }

    /**
     * java8Stream练习
     */
    @Test
    public void test6(){
        /*
        Predicate <T> 接口是一个函数式接口，它接受一个输入参数 T，返回一个布尔值结果。
        该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑（比如：与，或，非）。
        该接口用于测试对象是 true 或 false。
         */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        // Predicate<Integer> predicate = n -> true
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // n 如果存在则 test 方法返回 true

        System.out.println("输出所有数据:");

        // 传递参数 n
        eval(list, n->true);

        // Predicate<Integer> predicate1 = n -> n%2 == 0
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n%2 为 0 test 方法返回 true

        System.out.println("输出所有偶数:");
        eval(list, n-> n%2 == 0 );

        // Predicate<Integer> predicate2 = n -> n > 3
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n 大于 3 test 方法返回 true

        System.out.println("输出大于 3 的所有数字:");
        eval(list, n-> n > 3 );
    }
    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for(Integer n: list) {
            if(predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

    private static int getCountEmptyStringUsingJava7(List<String> strings){
        int count = 0;

        for(String string: strings){

            if(string.isEmpty()){
                count++;
            }
        }
        return count;
    }

    private static int getCountLength3UsingJava7(List<String> strings){
        int count = 0;

        for(String string: strings){

            if(string.length() == 3){
                count++;
            }
        }
        return count;
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings){
        List<String> filteredList = new ArrayList<String>();

        for(String string: strings){

            if(!string.isEmpty()){
                filteredList.add(string);
            }
        }
        return filteredList;
    }

    private static String getMergedStringUsingJava7(List<String> strings, String separator){
        StringBuilder stringBuilder = new StringBuilder();

        for(String string: strings){

            if(!string.isEmpty()){
                stringBuilder.append(string);
                stringBuilder.append(separator);
            }
        }
        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length()-2);
    }

    private static List<Integer> getSquares(List<Integer> numbers){
        List<Integer> squaresList = new ArrayList<Integer>();

        for(Integer number: numbers){
            Integer square = new Integer(number.intValue() * number.intValue());

            if(!squaresList.contains(square)){
                squaresList.add(square);
            }
        }
        return squaresList;
    }

    private static int getMax(List<Integer> numbers){
        int max = numbers.get(0);

        for(int i=1;i < numbers.size();i++){

            Integer number = numbers.get(i);

            if(number.intValue() > max){
                max = number.intValue();
            }
        }
        return max;
    }

    private static int getMin(List<Integer> numbers){
        int min = numbers.get(0);

        for(int i=1;i < numbers.size();i++){
            Integer number = numbers.get(i);

            if(number.intValue() < min){
                min = number.intValue();
            }
        }
        return min;
    }

    private static int getSum(List numbers){
        int sum = (int)(numbers.get(0));

        for(int i=1;i < numbers.size();i++){
            sum += (int)numbers.get(i);
        }
        return sum;
    }

    private static int getAverage(List<Integer> numbers){
        return getSum(numbers) / numbers.size();
    }
}
