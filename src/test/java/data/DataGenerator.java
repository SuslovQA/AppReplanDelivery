package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        // TODO: добавить логику для объявления переменной date и задания её значения, для генерации строки с датой
        //  Вы можете использовать класс LocalDate и его методы для получения и форматирования даты
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity(Faker faker) {
        // TODO: добавить логику для объявления переменной city и задания её значения, генерацию можно выполнить
        //  с помощью Faker, либо используя массив валидных городов и класс Random
        return faker.address().cityName();
    }

    public static String generateName(Faker faker) {
        // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
        //  использовать Faker
        return faker.name().firstName() + faker.name().lastName();
    }

    public static String generatePhone(Faker faker) {
        // TODO: добавить логику для объявления переменной phone и задания её значения, для генерации можно
        //  использовать Faker
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private static Faker faker;

        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            faker = new Faker(new Locale(locale));
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(faker),
            //  generateName(faker), generatePhone(faker)
            return new UserInfo(generateCity(faker), generateName(faker), generatePhone(faker));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
