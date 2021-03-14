package test.demo;

import org.junit.Test;

import java.util.Arrays;


public class MyTest5 {

    @Test
    public void Test01(){
//        Thread01 thread01 = new Thread01();
//        Thread02 thread02 = new Thread02();
//        Thread t1 = new Thread(thread01);
//        Thread t2 = new Thread(thread02);
//        t1.setName("t1");
//        t2.setName("t2");
//        t1.start();
//        t2.start();
        String a = "hello";
        String b = a;
        String c = "he";
        String d = c + "llo";
        String e = new String("hello");
        System.out.println(a==b);
        System.out.println(a==d);
        System.out.println(b==d);
        System.out.println(e==d);
    }
}
class Thread01 implements Runnable{
    @Override
    public void run() {
        System.out.println("线程名字："+Thread.currentThread().getName());
    }
}

class Thread02 implements Runnable {
    @Override
    public void run() {
        System.out.println("线程名字：" + Thread.currentThread().getName());
    }


    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        // 递归结束条件：startIndex大等于endIndex的时候    
        if (startIndex >= endIndex) {
            return;
        }
        // 得到基准元素位置    
        int pivotIndex = partition(arr, startIndex, endIndex);
        // 用分治法递归数列的两部分    
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);
    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        // 取第一个位置的元素作为基准元素    
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        // 坑的位置，初始等于pivot的位置    
        int index = startIndex;
        //大循环在左右指针重合或者交错时结束    
        while (right >= left) {
            //right指针从右向左进行比较      
            while (right >= left) {
                if (arr[right] < pivot) {
                    arr[left] = arr[right];
                    index = right;
                    left++;
                    break;
                }
                right--;
            }
            //left指针从左向右进行比较        
            while (right >= left) {
                if (arr[left] > pivot) {
                    arr[right] = arr[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        arr[index] = pivot;

        return index;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 7, 6, 5, 3, 2, 8, 1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}






