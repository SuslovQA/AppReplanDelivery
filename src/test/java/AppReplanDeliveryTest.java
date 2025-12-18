import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.w3c.dom.Text;

import java.util.Locale;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class AppReplanDeliveryTest {

    @Test
    void shouldSuccessReplan() {


        open("http://localhost:9999/");

        String date = DataGenerator.generateDate(7);

        $("[data-test-id='city'] .input__control").setValue(DataGenerator.generateCity());
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $("[data-test-id = 'date'] input").setValue(DataGenerator.generateDate(7));
        $("[data-test-id='name'] .input__control").setValue(DataGenerator.generateName());
        $("[data-test-id='phone'] .input__control").setValue(DataGenerator.generatePhone());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(byText("Запланировать")).click();
        $("[data-test-id='success-notification']").text().equals("Встреча успешно запланирована на " + date);



    }
}
