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
        user1.setCar(new Car("Model1", 1));
        userService.add(user1);
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        user2.setCar(new Car("Model2", 2));
        userService.add(user2);
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        user3.setCar(new Car("Model3", 3));
        userService.add(user3);
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        user4.setCar(new Car("Model4", 4));
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