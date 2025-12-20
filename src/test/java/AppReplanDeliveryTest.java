import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;



public class AppReplanDeliveryTest {

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
        open("http://localhost:9999/");
    }

    @Test
    void shouldSuccessReplan() {
        String date1 = DataGenerator.generateDate(7);
        String date2 = DataGenerator.generateDate(14);

        $("[data-test-id='city'] .input__control").setValue(DataGenerator.generateCity());
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $("[data-test-id = 'date'] input").setValue(date1);
        $("[data-test-id='name'] .input__control").setValue(DataGenerator.generateName());
        $(By.xpath("//span[@data-test-id='phone']//input")).setValue(DataGenerator.generatePhone());
        $(By.xpath("//span[@class='checkbox__box']")).click();
        $(".grid-col button").click();
        $(By.xpath("//div[@data-test-id='success-notification']//div[@class='notification__content']")).shouldBe(visible).shouldBe(text("Встреча успешно запланирована на " + date1));


        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $("[data-test-id = 'date'] input").setValue(date2);
        $(".grid-col button").click();
        $("[data-test-id='replan-notification'] .notification__content").shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $(withText("Перепланировать")).click();
        $x("//div[@data-test-id='success-notification']//div[@class='notification__content']").shouldBe(visible).shouldHave(exactText("Встреча успешно запланирована на " + date2));
    }
}
