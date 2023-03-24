package service;

import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.HomePage;
import page_objects.LoginPage;
import page_objects.RegisterPage;

import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected final static String WEB_LINK = "http://stellarburgers.nomoreparties.site/";

    @Step("Авторизация пользователя")
    @SneakyThrows
    protected void login(String email, String password) {
        waitElement(loginPage.getEnterForm());
        loginPage.fillUserInfo(email, password);
        loginPage.clickToLogin();
    }

    @Step("Регистрация пользователя")
    protected void registration(String email, String password, String name) throws Exception {
        homePage = new HomePage(driver);
        homePage.clickPersonalCabinet();

        loginPage = new LoginPage(driver);
        loginPage.scrollTo(loginPage.getLinkRegister());
        loginPage.clickToRegister();

        registerPage = new RegisterPage(driver);
        registerPage.fillUserInfo(email, name, password);
        registerPage.clickButtonToRegister();

        waitElement(loginPage.getEnterForm());
        loginPage.clickLogo();
        waitElement(homePage.getButtonEnter());
    }

    @Step("Проверить наличие элемента на странице")
    @SneakyThrows
    public void checkElement(By element) {
        waitElement(element);
        WebElement webElement = driver.findElement(element);
        Assert.isTrue(webElement.isEnabled(), "Expected element is not on the page");
    }

    public void waitElement(By element) throws Exception {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (Exception e) {
            throw new Exception("ERROR! Expected element is NOT FOUND");
        }
    }

    protected ChromeOptions getOptions() {
        Allure.step("Tests with chrome driver");
        WebDriverManager.chromedriver().setup();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");

        //Allure.step("Tests with yandex driver");
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
        return option;
    }

    @Step("Закрытие браузера")
    @After
    public void tearDown() {
        driver.quit();
    }
}
