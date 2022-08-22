package thread;

import junit.framework.TestCase;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 链接：https://juejin.cn/post/6902655550031413262
 * @author dongchao
 * @date 2022/8/22 14:54
 */
public class Demo01 extends TestCase {

    public static final  ExecutorService executorService = Executors.newCachedThreadPool();

    // 1 创建异步任务
    public void test01() {
        // 没有返回值
        // 根据Runnable创建
        CompletableFuture<Void> afuture = CompletableFuture.runAsync(() -> System.out.println("hello aa"), executorService);

        // 有返回值
        // 根据Supplier创建
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.print("bb ");
            return "aa";
        }, executorService);

        System.out.println(afuture.join());

        // 阻塞等待，获取返回值
        String join = future.join();
        System.out.println(join);

        executorService.shutdown();

    }

    // 2 线程串行执行
    // 任务完成则运行action，不关心上一个任务的结果，无返回值
    public void test02() {
        CompletableFuture<Void> future = CompletableFuture
                .supplyAsync(() -> "hello", executorService)// 传入的参数是生产者，有返回值
                .thenRunAsync(() -> System.out.println("ok"), executorService);// 传入的参数是Runnable，无返回值

        executorService.shutdown();

    }

    // 任务完成则运行action，依赖上一个任务的结果，无返回值
    public void test03() {
        CompletableFuture<Void> future = CompletableFuture
                .supplyAsync(() -> "hello", executorService)// 传入的参数是生产者
                .thenAcceptAsync(System.out::println, executorService);// 传入的参数是消费者Consumer

        executorService.shutdown();
    }

    // 任务完成则运行fn，依赖上一个任务的结果，有返回值
    public void test04() {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "hello", executorService)// 传入的参数是生产者
                .thenApplyAsync(data -> {
                    System.out.println(data);
                    return "ok";
                }, executorService);// 传入的参数是Function，有返回值，返回值U

        System.out.println(future.join());
        executorService.shutdown();

    }

    // thenCompose - 任务完成则运行fn，依赖上一个任务的结果，有返回值
    // 类似thenApply（区别是thenCompose的返回值是CompletionStage，thenApply则是返回 U），
    // 提供该方法为了和其他CompletableFuture任务更好地配套组合使用
    public void test05() {
        // 常量任务
        CompletableFuture<String> ok = CompletableFuture.completedFuture("ok");

        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "hello", executorService)// 传入的参数是生产者
                .thenComposeAsync(data -> {
                    System.out.println(data);
                    return ok;// 使用第一个任务作为返回
                }, executorService);
        System.out.println(future.join());
        executorService.shutdown();
    }

    // 3 线程并行执行
    // 两个CompletableFuture并行执行完，然后执行action，不依赖上两个任务的结果，无返回值
    public void test06() {
        // 常量任务
        CompletableFuture<String> first = CompletableFuture.completedFuture("hello world");
        CompletableFuture<Void> future = CompletableFuture
                .supplyAsync(() -> "hello", executorService)// 第二个异步任务
                // 第三个异步任务，传入第一个的参数CompletionStage，第二个参数Runnable
                .runAfterBothAsync(first, () -> System.out.println("ok"), executorService);
        executorService.shutdown();
    }

    // 两个CompletableFuture并行执行完，然后执行action，依赖上两个任务的结果，无返回值
    public void test07() {
        // 第一个异步任务
        CompletableFuture<String> first = CompletableFuture.completedFuture("hello world");
        CompletableFuture<Void> future = CompletableFuture
                .supplyAsync(() -> "hello", executorService)// 第二个异步任务
                // 第三个异步任务，传入第一个的参数CompletionStage，第二个参数BiConsumer
                .thenAcceptBothAsync(first, (s,w) -> System.out.println(w+" "+s), executorService);
        executorService.shutdown();
    }

    // 两个CompletableFuture并行执行完，然后执行fn，依赖上两个任务的结果，有返回值
    public void test08() {
        // 第一个异步任务
        CompletableFuture<String> first = CompletableFuture.completedFuture("hello world");
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "hello", executorService)// 第二个异步任务
                // 第三个异步任务，传入第一个的参数CompletionStage，第二个参数BiFunction
                .thenCombineAsync(first, (s,w) -> {
                    System.out.println(w+" "+s);
                    return "ok";
                }, executorService);
        System.out.println(future.join());
        executorService.shutdown();
    }

    // 4 线程并行执行，谁先执行完则谁触发下一任务（二者选其最快）

    // 上一个任务或者other任务完成, 运行action，不依赖前一任务的结果，无返回值
    public void test09() {
        // 第一个异步任务，休眠1秒，保证最后执行
        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
            System.out.println("hello world");
            return "hello world";
        });

        CompletableFuture<Void> future = CompletableFuture
                // 第二个异步任务
                .supplyAsync(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {}
                    System.out.println("hello ");
                    return "hello";
                }, executorService)
                // 第三个异步任务
                .runAfterEitherAsync(first, () -> System.out.println("ok"), executorService);
        // 当第三个异步任务，比前面异步任务执行早，那么不会打印数据
        executorService.shutdown();
    }

    // 上一个任务或者other任务完成, 运行action，依赖最先完成任务的结果，无返回值
    public void test10() {
        // 第一个异步任务，休眠1秒，保证最后执行
        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
            System.out.println("hello world");
            return "hello world";
        });
        CompletableFuture<Void> future = CompletableFuture
                // 第二个异步任务
                .supplyAsync(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {}
                    System.out.println("hello ");
                    return "hello";
                }, executorService)
                // 第三个异步任务
                .acceptEitherAsync(first, data -> System.out.println(data), executorService);
        // 当第三个异步任务，比前面异步任务执行早，那么不会打印数据
        executorService.shutdown();
    }

    // 上一个任务或者other任务完成, 运行fn，依赖最先完成任务的结果，有返回值
    public void test11() {
        // 第一个异步任务，休眠1秒，保证最后执行
        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
            return "hello world";
        });
        CompletableFuture<String> future = CompletableFuture
                // 第二个异步任务
                .supplyAsync(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {}
                    return "hello";
                }, executorService)
                // 第三个异步任务
                .applyToEitherAsync(first, data -> {
                    System.out.println(data);
                    return "ok";
                }, executorService);
        // join阻塞等待，直到异步任务返回，并执行第三个异步任务
        System.out.println(future.join());
        executorService.shutdown();
    }

    // 5 处理任务结果或者异常

    // exceptionally-处理异常，相当于try...catch
    public void test12() {
        CompletableFuture<Integer> exceptionally = CompletableFuture
                .supplyAsync(() -> {
                    if (true) {
                        throw new RuntimeException("main error!");
                    }
                    return "hello world";
                })
                .thenApply(data -> 1)
                .exceptionally(e -> {
                    e.printStackTrace();
                    return 0;
                });
    }

    // handle-任务完成或者异常时运行fn，返回值为fn的返回
    public void test13() {
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(() -> {
                    if (true) {
                        throw new RuntimeException("main error!");
                    }
                    return "hello world";
                })
                .thenApply(data -> 1)
                .handleAsync((data, e) -> {
                    e.printStackTrace();
                    return data;
                });
        System.out.println(future.join());
    }

    // whenComplete-任务完成或者异常时运行action，有返回值
    // whenComplete与handle的区别在于，它不参与返回结果的处理，把它当成监听器即可
    // 即使异常被处理，在CompletableFuture外层，异常也会再次复现
    // 使用whenCompleteAsync时，返回结果则需要考虑多线程操作问题，毕竟会出现两个线程同时操作一个结果
    public void test14() {
        CompletableFuture<AtomicBoolean> future = CompletableFuture
                .supplyAsync(() -> {
                    if (true) {
                        throw new RuntimeException("main error!");
                    }
                    return "hello world";
                })
                .thenApply(data -> new AtomicBoolean(false))
                .whenCompleteAsync((data, e) -> {
                    // 异常捕捉处理，但是异常还是回在外层复现
                    System.out.println(e.getMessage());
                });
        System.out.println(future.join());
    }

    // 6 多个任务的简单组合

    public void test15() {
        CompletableFuture<Void> future = CompletableFuture
                .allOf(CompletableFuture.completedFuture("a"),
                        CompletableFuture.completedFuture("b"));
        // 全部任务都需要执行完
        future.join();
        CompletableFuture<Object> future2 = CompletableFuture
                .anyOf(CompletableFuture.completedFuture("c"),
                        CompletableFuture.completedFuture("d"));
        // 其中一个任务执行完即可
        System.out.println(future2.join());
    }

}
