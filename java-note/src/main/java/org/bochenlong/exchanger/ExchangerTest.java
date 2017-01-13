package org.bochenlong.exchanger;

import java.util.concurrent.Exchanger;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by bochenlong on 17-1-13.
 */
public class ExchangerTest {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        new Thread(a.apply(exchanger)).start();
        new Thread(b.apply(exchanger)).start();
    }
    
    
    private static Function<Exchanger<Integer>, Runnable> a = (Exchanger<Integer> ex) -> {
        Runnable runnable = () -> {
            
            int i = 0;
            int result = 0;
            while (true) {
                try {
                    System.out.println("this is a ex data is " + ++i);
                    result = ex.exchange(i);
                    System.out.println("this is a ex result is " + result);
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        return runnable;
    };
    
    private static Function<Exchanger<Integer>, Runnable> b = (Exchanger<Integer> ex) -> {
        Runnable runnable = () -> {
            int i = 1000;
            int result = 0;
            while (true) {
                try {
                    System.out.println("this is b ex data is " + ++i);
                    result = ex.exchange(i);
                    System.out.println("this is b ex result is " + result);
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
        };
        return runnable;
    };
    
}
