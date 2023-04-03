package page_objects;

import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    private final WebDriver driver;
    private By inputName = By.xpath(".//label[text()='Имя']//parent::*//input");
    private By inputEmail = By.xpath(".//label[text()='Email']//parent::*//input");
    private By inputPassword = By.xpath(".//label[text()='Пароль']//parent::*//input");
    private By buttonRegister = By.xpath(".//button[text()='Зарегистрироваться']");
    private By incorrectPassword = By.xpath(".//p[text()='Некорректный пароль']");
    @Getter
    private By linkEnter = By.className("Auth_link__1fOlj");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillUserInfo(String email, String name, String password) {
        fillEmail(email);
        fillName(name);
        fillPassword(password);
    }

    @Step("Заполнение поля Name: {name}")
    public void fillName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }

    @Step("Заполнение поля Email: {email}")
    public void fillEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }

    @Step("Заполнение поля Password: {password}")
    public void fillPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }

    @Step("Нажать на кнопку подтверждения регистрации")
    public void clickButtonToRegister() {
        driver.findElement(buttonRegister).click();
    }

    @Step("Нажать на вход в аккаунт")
    public void clickEnter() {
        driver.findElement(linkEnter).click();
    }

    @Step("Проверка ввода некорректного пароля")
    public void checkErrorIncorrectPassword() {
        WebElement webElement = driver.findElement(incorrectPassword);
        Assert.isTrue(webElement.isEnabled(), "Error message");
    }

    @Step("Переместиться к элементу")
    public void scrollTo(By element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
    }
}
