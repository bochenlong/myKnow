package org.bochenlong.collector;

import org.bochenlong.collector.Container;
import org.bochenlong.collector.Dish;
import org.bochenlong.collector.ToMyCollector;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bcl on 2016/9/1.
 */
public class StreamTest {
    public static void main(String[] args) throws InterruptedException {
        List<Dish> menu = Arrays.asList(
                new Dish("duck", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );


//        Map<Dish.Type, Set<CaloricLevel>> map4 = menu.stream().collect(
//                groupingBy(Dish::getType,mapping(dish -> {
//                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
//                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
//                    return CaloricLevel.FAT;
//                },toCollection(HashSet::new))));
//        Map<Boolean,Map<Dish.Type,List<Dish>>> map = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian,groupingBy(Dish::getType)));
//        System.out.println(map);
        Thread.sleep(5000);

        long t = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Container c = menu.stream().collect(new ToMyCollector());
        }
        long t2 = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - t);
        for (int i = 0; i < 100000; i++) {
            Container c = menu.parallelStream().collect(new ToMyCollector());
        }
        System.out.println(System.currentTimeMillis() - t2);

        menu.stream().collect(Container::new, (Container c, Dish t1) -> {
            if (t1.isVegetarian()) c.getOk().add(t1);
            c.getNo().add(t1);
        }, (Container c1, Container c2) -> {
            c1.getOk().addAll(c2.getOk());
            c1.getNo().addAll(c2.getNo());
        });

        new Thread(System.out::println).start();
    }

    static enum CaloricLevel {
        NORMAL, DIET, FAT
    }
}
