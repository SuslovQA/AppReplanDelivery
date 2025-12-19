import com.codeborne.selenide.Condition;
import com.codeborne.selenide.conditions.Text;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class AppReplanDeliveryTest {

    @Test
    void shouldSuccessReplan() {
        open("http://localhost:9999/");

        String date1 = DataGenerator.generateDate(7);
        String date2 = DataGenerator.generateDate(14);

        $("[data-test-id='city'] .input__control").setValue(DataGenerator.generateCity());
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $("[data-test-id = 'date'] input").setValue(date1);
        $("[data-test-id='name'] .input__control").setValue(DataGenerator.generateName());
        $(By.xpath("//span[@data-test-id='phone']//input")).setValue(DataGenerator.generatePhone());
        $(By.xpath("//span[@class='checkbox__box']")).click();
        $(".grid-col button").click();
        $(By.xpath("//div[@data-test-id='success-notification']//div[@class='notification__content']")).shouldBe(Condition.exactText("Встреча успешно запланирована на " + date1));
        $(".grid-col button").click();
        $("[data-test-id = 'date'] input").setValue(date2);
        $(".notification_visible .notification__content").should(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));

        $(".button_size_s").click();
    }
}
