package thread;

import junit.framework.TestCase;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther: dongchao
 * @data: 2023/2/2 14:06
 */
public class CompletableFutureTest extends TestCase {


    public void test01() throws ExecutionException, InterruptedException {
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("原始CompletableFuture方法任务");
                    return "捡田螺的小男孩";
                }
        );

        // thenApply,会把CompletableFuture关联的函数都执行完,只是有返回值
        CompletableFuture<String> thenApplyFuture = orgFuture.thenApply((a) -> {
            if ("捡田螺的小男孩".equals(a)) {
                return "关注了";
            }

            return "先考虑考虑";
        });

        System.out.println(orgFuture.get());
//        System.out.println(thenApplyFuture.get());
    }

    public void test02() throws ExecutionException, InterruptedException {
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("原始CompletableFuture方法任务");
                    return "捡田螺的小男孩";
                }
        );

        // thenAccept,会把CompletableFuture关联的函数都执行完，没有返回值
        CompletableFuture thenAcceptFuture = orgFuture.thenAccept((a) -> {
            if ("捡田螺的小男孩".equals(a)) {
                System.out.println("关注了");
            }

            System.out.println("先考虑考虑");
        });

        System.out.println(orgFuture.get());
//        System.out.println(thenAcceptFuture.get());
    }


    public void test03() throws ExecutionException, InterruptedException {
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("当前线程名称：" + Thread.currentThread().getName());
                    throw new RuntimeException();
                }
        );

        // exceptionally,异常方法不会中断程序
        CompletableFuture<String> exceptionFuture = orgFuture.exceptionally((e) -> {
            e.printStackTrace();
            return "你的程序异常啦";
        });

        System.out.println(exceptionFuture.get());

    }

    public void test04() throws ExecutionException, InterruptedException {
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("当前线程名称：" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "捡田螺的小男孩";
                }
        );

        CompletableFuture<String> rstFuture = orgFuture.whenComplete((a, throwable) -> {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            System.out.println("上个任务执行完啦，还把" + a + "传过来");
            if ("捡田螺的小男孩".equals(a)) {
                System.out.println("666");
            }
            System.out.println("233333");
        });

        System.out.println(orgFuture.get());

//        System.out.println(rstFuture.get());
    }

    // CompletableFuture会把执行的函数都执行

    public void test05() throws ExecutionException, InterruptedException {
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("当前线程名称：" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "捡田螺的小男孩";
                }
        );

        CompletableFuture<String> rstFuture = orgFuture.handle((a, throwable) -> {
            System.out.println("上个任务执行完啦，还把" + a + "传过来");
            if ("捡田螺的小男孩".equals(a)) {
                System.out.println("666");
                return "关注了";
            }
            System.out.println("233333");
            return "有返回值了";
        });

        System.out.println(orgFuture.get());
//        System.out.println(rstFuture.get());
    }


    public void test06() {
        CompletableFuture<String> first = CompletableFuture.completedFuture("第一个异步任务");
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CompletableFuture<String> future = CompletableFuture
                //第二个异步任务
                .supplyAsync(() -> "第二个异步任务", executor)
                // (w, s) -> System.out.println(s) 是第三个任务
                .thenCombineAsync(first, (s, w) -> {
                    System.out.println(w);
                    System.out.println(s);
                    return "两个异步任务的组合";
                }, executor);
        System.out.println(future.join());
        executor.shutdown();
    }

    public void test07() {
        CompletableFuture<String> f = CompletableFuture.completedFuture("第一个任务");
        //第二个异步任务
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "第二个任务", executor)
                .thenComposeAsync(data -> {
                    System.out.println(data); return null; //使用第一个任务作为返回
                }, executor);
        System.out.println(future.join());
        executor.shutdown();
    }

    // 不会把异常抛出来，会把结果转换
    public void test08() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> handle = CompletableFuture.supplyAsync(() -> 1 / 0)
                .handle((result, ex) -> {
                    if (null != ex) {
                        System.out.println(ex.getMessage());
                        return 0;
                    } else {
                        return result;
                    }
                });

        System.out.println(handle.get());
        System.out.println("aaaaa");
    }


    // 内部抛出异常，中断程序
    public void test09() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> whenComplete = CompletableFuture.supplyAsync(() -> 1 / 0)
                .whenComplete((result, ex) -> {
                    if (null != ex) {
                        System.out.println("whenComplete error:\t" + ex.getMessage());
                    }
                });

        System.out.println(whenComplete.get());
        System.out.println("aaaa");
    }

    // 如果有异常，就执行exceptionally，内部异常不会抛出来
    // 如果没有异常，那么直接跳过exceptionally函数逻辑
    public void test10() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> exceptionally = CompletableFuture.supplyAsync(() -> 1 / 1)
                .exceptionally(ex -> {
                    System.out.println("ex:\t" + ex.getMessage());
                    return 0;
                });
        System.out.println(exceptionally.get());
    }

}
