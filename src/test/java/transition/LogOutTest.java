package transition;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import page_objects.HomePage;
import page_objects.LoginPage;
import page_objects.PersonalCabinetPage;
import page_objects.RegisterPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static util.util.FakerData.*;

public class LogOutTest {
    //Выход из аккаунта
    //Проверь выход по кнопке «Выйти» в личном кабинете.

    private final static String WEB_LINK = "http://stellarburgers.nomoreparties.site/";

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private RegisterPage registerPage;

    private String email = getEmail();
    private String password = getPassword();
    private String name = getName();

    @SneakyThrows
    @Before
    public void init() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(option);
        driver.get(WEB_LINK);
        registration();
        homePage.clickEnterButton();
        login();
    }

    @SneakyThrows
    @Test
    public void checkPersonalCabinet() {
        homePage.clickPersonalCabinet();

        PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(driver);
        personalCabinetPage.waitElement(personalCabinetPage.getMenu());
        personalCabinetPage.clickExitButton();

        loginPage.checkElement(loginPage.getEnterForm());
    }

    //todo вынести
    private void registration(/*WebDriver driver, String email, String name, String password*/) throws Exception {
        homePage = new HomePage(driver);
        homePage.clickPersonalCabinet();

        loginPage = new LoginPage(driver);
        loginPage.scrollTo(loginPage.getLinkRegister());
        loginPage.clickToRegister();

        registerPage = new RegisterPage(driver);
        registerPage.fillUserInfo(email, name, password);
        registerPage.clickButtonToRegister();

        loginPage.waitElement(loginPage.getEnterForm());
        loginPage.clickLogo();
        homePage.waitElement(homePage.getButtonEnter());
    }
    //todo вынести
    @SneakyThrows
    private void login() {
        loginPage.waitElement(loginPage.getEnterForm());
        loginPage.fillUserInfo(email, password);
        loginPage.clickToLogin();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
