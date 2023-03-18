package register;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import page_objects.HomePage;
import page_objects.LoginPage;
import page_objects.RegisterPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static util.util.FakerData.*;

public class RegisterTests {
    private final static String WEB_LINK = "http://stellarburgers.nomoreparties.site/";
    private WebDriver driver;

    //Проверь:
    //Успешную регистрацию.
    //Ошибку для некорректного пароля. Минимальный пароль — шесть символов.

    @Before
    public void init() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(option);
        driver.get(WEB_LINK);
    }

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

        loginPage.waitElement(loginPage.getEnterForm());//todo expected nothing error
    }

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

    @After
    public void tearDown() {
        driver.quit();
    }
}
