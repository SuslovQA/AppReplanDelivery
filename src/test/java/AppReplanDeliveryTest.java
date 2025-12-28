import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;


public class AppReplanDeliveryTest {

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldSuccessReplan() {
        String date1 = DataGenerator.generateDate(7);
        String date2 = DataGenerator.generateDate(14);

        open("http://localhost:9999/");

        $("[data-test-id='city'] .input__control").setValue(DataGenerator.generateCity());
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $("[data-test-id = 'date'] input").setValue(date1);
        $("[data-test-id='name'] .input__control").setValue(DataGenerator.generateName());
        $(By.xpath("//span[@data-test-id='phone']//input")).setValue(DataGenerator.generatePhone());
        $(By.xpath("//span[@class='checkbox__box']")).click();
        $(".grid-col button").click();
        $(By.xpath("//div[@data-test-id='success-notification']//div[@class='notification__content']"))
                .shouldBe(Condition.exactText("Встреча успешно запланирована на " + date1));
        $(".grid-col button").click();
        $x("//div[@data-test-id='replan-notification']//div[@class='notification__content']")
                .should(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id = 'date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $("[data-test-id = 'date'] input").setValue(date2);
        $x("//div[@data-test-id='replan-notification']//button[@class='button button_view_extra button_size_s button_theme_alfa-on-white']").click();
        $x("//div[@data-test-id='success-notification']//div[@class='notification__content']")
                .shouldHave(Condition.exactText("Встреча успешно запланирована на " + date2));
    }
}