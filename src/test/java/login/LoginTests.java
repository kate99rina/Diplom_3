package login;

import dev.failsafe.internal.util.Assert;
import io.qameta.allure.junit4.DisplayName;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import page_objects.ResetPasswordPage;
import service.BaseTest;

import static util.FakerData.*;

@DisplayName("Log in")
public class LoginTests extends BaseTest {
    private String email = getEmail();
    private String password = getPassword();
    private String name = getName();

    @SneakyThrows
    @Before
    public void init() {
        driver = new ChromeDriver(getOptions());
        driver.get(WEB_LINK);
        registration(email, password, name);
    }

    @DisplayName("Войти по кнопке «Войти в аккаунт» на главной странице")
    @SneakyThrows
    @Test
    public void check() {
        homePage.clickEnterButton();
        login(email, password);

        waitElement(homePage.getButtonCreateOrder());
        WebElement element = driver.findElement(homePage.getButtonCreateOrder());
        Assert.isTrue(element.isEnabled(), "User doesn't log in");
    }

    @DisplayName("Вход через кнопку «Личный кабинет»")
    @SneakyThrows
    @Test
    public void checkLoginAfterPrivateCabinet() {
        homePage.clickPersonalCabinet();
        login(email, password);

        waitElement(homePage.getButtonCreateOrder());
        WebElement element = driver.findElement(homePage.getButtonCreateOrder());
        Assert.isTrue(element.isEnabled(), "User doesn't log in");
    }

    @DisplayName("Вход через кнопку в форме регистрации")
    @SneakyThrows
    @Test
    public void checkEnterAfterFormRegister() {
        homePage.clickPersonalCabinet();
        loginPage.clickToRegister();

        registerPage.scrollTo(registerPage.getLinkEnter());
        registerPage.clickEnter();

        login(email, password);
        waitElement(homePage.getButtonCreateOrder());
        WebElement element = driver.findElement(homePage.getButtonCreateOrder());
        Assert.isTrue(element.isEnabled(), "User doesn't log in");
    }

    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @SneakyThrows
    @Test
    public void checkEnterAfterResetPassword() {
        homePage.clickPersonalCabinet();
        loginPage.clickResetPassword();

        ResetPasswordPage resetPage = new ResetPasswordPage(driver);
        resetPage.clickEnterLink();

        login(email, password);
        waitElement(homePage.getButtonCreateOrder());
        WebElement element = driver.findElement(homePage.getButtonCreateOrder());
        Assert.isTrue(element.isEnabled(), "User doesn't log in");
    }

}
