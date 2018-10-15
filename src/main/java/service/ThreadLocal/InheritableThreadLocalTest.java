package service.ThreadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhanglinxing on 2018/10/15.
 * from https://zhuanlan.zhihu.com/p/28501035
 * 存在问题：同一级子线程会影响共同的value
 *
 */
public class InheritableThreadLocalTest {
    public static void main(String[] args) throws Exception {
        final InheritableThreadLocal<Span> inheritableThreadLocal = new InheritableThreadLocal<Span>();
        inheritableThreadLocal.set(new Span("xiexiexie"));
        //输出 xiexiexie
        Object o = inheritableThreadLocal.get();
        Runnable runnable = new Runnable() {

            public void run() {
                System.out.println("========");
                System.out.println(inheritableThreadLocal.get());
                inheritableThreadLocal.set(new Span("zhangzhangzhang"));
                System.out.println (inheritableThreadLocal.get());
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(1);
        executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("========");
        Span span = inheritableThreadLocal.get();
    }
    static class Span {
        public String name;
        public int age;
        public Span(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Span{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    }


