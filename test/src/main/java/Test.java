import java.util.UUID;

/**
 * Created by bochenlong on 16-9-13.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Test.class.getName());
        System.out.println(UUID.randomUUID().toString().replace("-", "").hashCode() % 2 == 0);
    }


}
