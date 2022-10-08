import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;
import static java.awt.SystemColor.menu;


public class CardDelivery {
    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    };

    @BeforeEach
    void setup() {
        String date = generateDate(3);
    }


    @Test
    void shouldTestValidForm() {
        open("http://localhost:7777");
        $("[data-test-id='city']").setValue("Москва");
        $(".class(menu-item__control)").click();
        $("[data-test-id='data']").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.DELETE);
        $("[data-test-id='data']").sendKeys(Keys.ENTER);
        $("[data-test-id='name']").setValue("Петров Иван");
        $("[data-test-id='phone']").setValue("+79998888888");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $(byText("Встреча успешно запланирована на ")).shouldBe(visible, Duration.ofSeconds(10));
    }

}
