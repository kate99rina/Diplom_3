package page_objects;

import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    private final WebDriver driver;
    private By INPUT_NAME = By.xpath(".//label[text()='Имя']//parent::*//input");
    private By INPUT_EMAIL = By.xpath(".//label[text()='Email']//parent::*//input");
    private By INPUT_PASSWORD = By.xpath(".//label[text()='Пароль']//parent::*//input");
    private By BUTTON_REGISTER = By.xpath(".//button[text()='Зарегистрироваться']");
    private By INCORRECT_PASSWORD = By.xpath(".//p[text()='Некорректный пароль']");
    private By LINK_ENTER = By.className("Auth_link__1fOlj");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getLinkEnter() {
        return LINK_ENTER;
    }

    public void fillUserInfo(String email, String name, String password) {
        fillEmail(email);
        fillName(name);
        fillPassword(password);
    }

    @Step("Заполнение поля Name: {name}")
    public void fillName(String name) {
        driver.findElement(INPUT_NAME).sendKeys(name);
    }

    @Step("Заполнение поля Email: {email}")
    public void fillEmail(String email) {
        driver.findElement(INPUT_EMAIL).sendKeys(email);
    }

    @Step("Заполнение поля Password: {password}")
    public void fillPassword(String password) {
        driver.findElement(INPUT_PASSWORD).sendKeys(password);
    }

    @Step("Нажать на кнопку подтверждения регистрации")
    public void clickButtonToRegister() {
        driver.findElement(BUTTON_REGISTER).click();
    }

    @Step("Нажать на вход в аккаунт")
    public void clickEnter() {
        driver.findElement(LINK_ENTER).click();
    }

    @Step("Проверка ввода некорректного пароля")
    public void checkErrorIncorrectPassword() {
        WebElement webElement = driver.findElement(INCORRECT_PASSWORD);
        Assert.isTrue(webElement.isEnabled(), "Error message");
    }

    @Step("Переместиться к элементу")
    public void scrollTo(By element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
    }
}
