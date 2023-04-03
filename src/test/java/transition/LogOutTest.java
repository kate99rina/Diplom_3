package transition;

import io.qameta.allure.junit4.DisplayName;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import page_objects.PersonalCabinetPage;
import service.BaseTest;

import static util.FakerData.*;

@DisplayName("Personal cabinet")
public class LogOutTest extends BaseTest {

    private String email = getEmail();
    private String password = getPassword();
    private String name = getName();

    @SneakyThrows
    @Before
    public void init() {
        driver = new ChromeDriver(getOptions());
        driver.get(WEB_LINK);
        registration(email, password, name);
        homePage.clickEnterButton();
        login(email, password);
    }

    @DisplayName("Выход из аккаунта")
    @SneakyThrows
    @Test
    public void checkPersonalCabinet() {
        homePage.clickPersonalCabinet();

        PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(driver);
        waitElement(personalCabinetPage.getMenu());
        personalCabinetPage.clickExitButton();

        checkElement(loginPage.getEnterForm());
    }

}
