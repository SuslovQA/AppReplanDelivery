package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        // TODO: добавить логику теста в рамках которого будет выполнено планирование и перепланирование встречи.
        //  Для заполнения полей формы можно использовать пользователя validUser и строки с датами в переменных
        //  firstMeetingDate и secondMeetingDate. Можно также вызывать методы generateCity(locale),
        //  generateName(locale), generatePhone(locale) для генерации и получения в тесте соответственно города,
        //  имени и номера телефона без создания пользователя в методе generateUser(String locale) в датагенераторе
        $("[data-test-id='city'] .input__control").setValue(validUser.getCity());
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $("[data-test-id = 'date'] input").setValue(firstMeetingDate);
        $("[data-test-id='name'] .input__control").setValue(validUser.getName());
        $x("//span[@data-test-id='phone']//input").setValue(validUser.getPhone());
        $x("//span[@class='checkbox__box']").click();
        $(".grid-col button").click();
        $x("//div[@data-test-id='success-notification']//div[@class='notification__content']").shouldBe(visible, Duration.ofSeconds(15)).shouldBe(text("Встреча успешно запланирована на " + firstMeetingDate));

        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $("[data-test-id = 'date'] input").setValue(secondMeetingDate);
        $(".grid-col button").click();
        $("[data-test-id='replan-notification'] .notification__content").shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $(withText("Перепланировать")).click();
        $x("//div[@data-test-id='success-notification']//div[@class='notification__content']").shouldBe(visible).shouldHave(exactText("Встреча успешно запланирована на " + secondMeetingDate));
    }
}
