package login;

import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import page_objects.HomePage;
import page_objects.LoginPage;
import page_objects.RegisterPage;
import page_objects.ResetPasswordPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static util.util.FakerData.*;

public class LoginTests {
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
    }

    //вход по кнопке «Войти в аккаунт» на главной
    @SneakyThrows
    @Test
    public void check() {
        homePage.clickEnterButton();
        login();

        homePage.waitElement(homePage.getButtonCreateOrder());
        WebElement element = driver.findElement(homePage.getButtonCreateOrder());
        Assert.isTrue(element.isEnabled(), "User doesn't log in");
    }

    //вход через кнопку «Личный кабинет»,
    @SneakyThrows
    @Test
    public void checkLoginAfterPrivateCabinet() {
        homePage.clickPersonalCabinet();
        login();

        homePage.waitElement(homePage.getButtonCreateOrder());
        WebElement element = driver.findElement(homePage.getButtonCreateOrder());
        Assert.isTrue(element.isEnabled(), "User doesn't log in");
    }

    //вход через кнопку в форме регистрации,
    @SneakyThrows
    @Test
    public void checkEnterAfterFormRegister() {
        homePage.clickPersonalCabinet();
        loginPage.clickToRegister();

        registerPage.scrollTo(registerPage.getLinkEnter());
        registerPage.clickEnter();

        login();
        homePage.waitElement(homePage.getButtonCreateOrder());
        WebElement element = driver.findElement(homePage.getButtonCreateOrder());
        Assert.isTrue(element.isEnabled(), "User doesn't log in");
    }

    //вход через кнопку в форме восстановления пароля.
    @SneakyThrows
    @Test
    public void checkEnterAfterResetPassword() {
        homePage.clickPersonalCabinet();
        loginPage.clickResetPassword();

        ResetPasswordPage resetPage = new ResetPasswordPage(driver);
        resetPage.clickEnterLink();

        login();
        homePage.waitElement(homePage.getButtonCreateOrder());
        WebElement element = driver.findElement(homePage.getButtonCreateOrder());
        Assert.isTrue(element.isEnabled(), "User doesn't log in");
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
