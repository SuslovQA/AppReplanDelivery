import com.github.javafaker.Faker;
import lombok.Data;
import lombok.Value;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class DataGenerator {
    private DataGenerator() {
    }

    private static final Faker faker = new Faker(new Locale("ru"));

    public static String generateDate(int shift) {
        // TODO: добавить логику для объявления переменной date и задания её значения,
        //  для генерации строки с датой
        // Вы можете использовать класс LocalDate и его методы для получения и форматирования даты
        String date = LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity() {
        // TODO: добавить логику для объявления переменной city и задания её значения,
        //  генерацию можно выполнить
        // с помощью Faker, либо используя массив валидных городов и класс Random
        String city = faker.address().cityName();
        return city;
    }

    public static String generateName() {
        // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
        // использовать Faker
        String name = faker.name().fullName();
        return name;
    }

    public static String generatePhone() {
        // TODO: добавить логику для объявления переменной phone и задания её значения,
        //  для генерации можно
        // использовать Faker
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

//    public static class Registration {
//        private static Faker faker;
//
//        private Registration() {
//        }
//
////        public static UserInfo generateUser(String locale) {
////            faker = new Faker(new Locale(locale));
////            // TODO: добавить логику для создания пользователя user с использованием методов
////            //  generateCity(faker),
////            // generateName(faker), generatePhone(faker)
////            UserInfo user = new UserInfo(generateCity(), generateName(), generateCity());
////
////            return user;
////        }
//    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
        String date;
    }
}