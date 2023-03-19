package register;

import io.qameta.allure.junit4.DisplayName;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import page_objects.HomePage;
import page_objects.LoginPage;
import page_objects.RegisterPage;
import service.BaseTest;

import static util.FakerData.*;

@DisplayName("Register")
public class RegisterTests extends BaseTest {

    @Before
    public void init() {
        driver = new ChromeDriver(getOptions());
        driver.get(WEB_LINK);
    }

    @DisplayName("Успешная регистрация пользователя")
    @SneakyThrows
    @Test
    public void checkSuccessRegister() {
        String email = getEmail();
        String name = getName();
        String password = getPassword();

        HomePage homePage = new HomePage(driver);
        homePage.clickPersonalCabinet();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.scrollTo(loginPage.getLinkRegister());
        loginPage.clickToRegister();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillUserInfo(email, name, password);
        registerPage.clickButtonToRegister();

        waitElement(loginPage.getEnterForm());
        checkElement(loginPage.getEnterForm());
    }

    @DisplayName("Неуспешная регистрация пользователя с некорректным паролем")
    @Test
    public void checkIncorrectPasswordRegister() {
        String email = getEmail();
        String name = getName();
        String password = "123";

        HomePage homePage = new HomePage(driver);
        homePage.clickPersonalCabinet();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.scrollTo(loginPage.getLinkRegister());
        loginPage.clickToRegister();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillUserInfo(email, name, password);
        registerPage.clickButtonToRegister();
        registerPage.checkErrorIncorrectPassword();
    }

}
