package transition;

import io.qameta.allure.junit4.DisplayName;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import page_objects.PersonalCabinetPage;
import service.BaseTest;

import static util.FakerData.*;

@DisplayName("Builder")
public class BuilderTests extends BaseTest {
    private PersonalCabinetPage personalCabinetPage;

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
        homePage.clickPersonalCabinet();

        personalCabinetPage = new PersonalCabinetPage(driver);
        waitElement(personalCabinetPage.getMenu());
    }

    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    @SneakyThrows
    @Test
    public void checkClickBuilder() {
        personalCabinetPage.clickBuilder();
        checkElement(homePage.getIngredientsSection());
    }

    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    @SneakyThrows
    @Test
    public void checkClickLogo() {
        personalCabinetPage.clickLogo();
        checkElement(homePage.getIngredientsSection());
    }

}
