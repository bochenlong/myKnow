import java.util.Arrays;
import java.util.List;

/**
 * Created by bochenlong on 16-9-13.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        List<String> stringList = Arrays.asList("1", "2");
        stringList.stream().forEach(a -> a = "3");
        System.out.println(stringList);
        List<User> users = Arrays.asList(new User[]{new User("1"),new User("1")});
//        users.stream().forEach(a -> System.out.println(a == users.get(0)));
        users.stream().forEach(a -> a.setName("2"));
        users.stream().forEach(System.out::println);
    }
    
    static class User {
        private String name;
    
        public User(String name) {
            this.name = name;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
