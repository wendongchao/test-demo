package test;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTest4 {

    @Test
    public void test01() throws IOException {
        String le = "Q";
        read("C:\\Users\\dong\\Desktop\\aa.txt",le);
    }
    public static void read(String path,String le) throws IOException {
        //解析为文件
        File file = new File(path);
        if(file.isFile()){//判断是否是文件
            String a = file.getName();
            System.out.println(a);
            FileInputStream inputStream = new FileInputStream(file);//文件流
            InputStreamReader reader = new InputStreamReader(inputStream);//读取
            BufferedReader bufferedReader = new BufferedReader(reader);//放到缓存流中
            StringBuffer sb = new StringBuffer();
            String text = null;
            while ((text = bufferedReader.readLine()) != null){
                sb.append(text);
            }
            //文件中字符串
            String str = sb.toString();
            int i = 0;
            while (str.contains(le)){//循环判断是否包含
                str = str.substring(str.indexOf(le)+le.length(),str.length());
                i++;
            }
        }
    }

    @Test
    public void test02(){
        int[] a = new int[10];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        a[3] = 3;
        System.arraycopy(a, 2, a, 3, 3);
        a[2]=99;
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

}
