package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        Car car1 = new Car("Model1", 1);
        car1.setUser(user1);
        user1.setCar(car1);
        userService.add(user1);
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        Car car2 = new Car("Model2", 2);
        car2.setUser(user2);
        user2.setCar(car2);
        userService.add(user2);
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        Car car3 = new Car("Model3", 3);
        car3.setUser(user3);
        user3.setCar(car3);
        userService.add(user3);
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        Car car4 = new Car("Model4", 4);
        car4.setUser(user4);
        user4.setCar(car4);
        userService.add(user4);

        // Вывод всех пользователей в консоль
        List<User> users = userService.listUsers();
        users.forEach(System.out::println);

        // Поиск существующего пользователя
        String modelSearch = "model2";
        int seriesSearch = 2;
        User userSearch = userService.getUserWithAuto(modelSearch, seriesSearch);
        if (userSearch != null) {
            System.out.println("User with auto model='" + modelSearch + "' and series='" + seriesSearch + "': ");
            System.out.println(userSearch);
        } else {
            System.out.println("User with auto model='" + modelSearch + "' and series='" + seriesSearch + "': ");
            System.out.println("NOT FOUND.");

        }

        // Поиск несуществующего пользователя
        modelSearch = "model2";
        seriesSearch = 3;
        userSearch = userService.getUserWithAuto(modelSearch, seriesSearch);
        if (userSearch != null) {
            System.out.println("User with auto model='" + modelSearch + "' and series='" + seriesSearch + "': ");
            System.out.println(userSearch);
        } else {
            System.out.println("User with auto model='" + modelSearch + "' and series='" + seriesSearch + "': ");
            System.out.println("NOT FOUND.");

        }

        context.close();
    }

}