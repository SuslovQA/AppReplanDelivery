import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class AppReplanDeliveryTest {

    @Test
    void shouldSuccessReplan() {


        open("http://localhost:9999/");

        $("[data-test-id='city'] .input__control").setValue(DataGenerator.generateCity(new Faker()));
    }
}
