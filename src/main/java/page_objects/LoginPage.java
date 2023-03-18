package page_objects;

import dev.failsafe.internal.util.Assert;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getLinkRegister() {
        return LINK_REGISTER;
    }

    public By getEnterForm() {
        return ENTER_FORM;
    }

    private By ENTER_FORM = By.xpath(".//h2[text()='Вход']");
    //ссылка зарегистрироваться
    private By LINK_REGISTER = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Зарегистрироваться']");
    private By LINK_RESET_PASSWORD = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']");
    //почта
    private By INPUT_EMAIL = By.xpath(".//label[text()='Email']//parent::*//input");
    private By INPUT_PASSWORD = By.xpath(".//label[text()='Пароль']//parent::*//input");
    // кнопка войти
    private By BUTTON_LOGIN = By.xpath(".//button[text()='Войти']");
    private By LOGO = By.xpath(".//a[@href='/']");
    private final WebDriver driver;


    //Переместиться к элементу
    public void scrollTo(By element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
    }

    //todo вынести в общее
    public void waitElement(By element) throws Exception {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            element));
        } catch (Exception e) {
            throw new Exception("ERROR! Expected element is NOT FOUND");
        }

    }

    //todo
    @SneakyThrows
    public void checkElement(By element) {
        waitElement(element);
        WebElement webElement = driver.findElement(element);
        Assert.isTrue(webElement.isEnabled(), "Expected element is not on the page");
    }

    public void clickToRegister() {
        driver.findElement(LINK_REGISTER).click();
    }

    public void clickToLogin() {
        driver.findElement(BUTTON_LOGIN).click();
    }

    public void clickLogo() {
        driver.findElement(LOGO).click();
    }

    public void clickResetPassword() {
        driver.findElement(LINK_RESET_PASSWORD).click();
    }

    public void fillUserInfo(String email, String password) {
        fillEmail(email);
        fillPassword(password);
    }

    public void fillEmail(String email) {
        driver.findElement(INPUT_EMAIL).sendKeys(email);
    }

    public void fillPassword(String password) {
        driver.findElement(INPUT_PASSWORD).sendKeys(password);
    }
}
