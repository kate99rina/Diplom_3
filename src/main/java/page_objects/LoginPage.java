package page_objects;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    @Getter
    private By enterForm = By.xpath(".//h2[text()='Вход']");
    @Getter
    private By linkRegister = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Зарегистрироваться']");
    private By linkResetPassword = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']");
    private By inputEmail = By.xpath(".//label[text()='Email']//parent::*//input");
    private By inputPassword = By.xpath(".//label[text()='Пароль']//parent::*//input");
    private By buttonLogin = By.xpath(".//button[text()='Войти']");
    private By logo = By.xpath(".//a[@href='/']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переместиться к элементу")
    public void scrollTo(By element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
    }

    @Step("Переход на страницу регистрации")
    public void clickToRegister() {
        driver.findElement(linkRegister).click();
    }

    @Step("Нажать на кнопку входа")
    public void clickToLogin() {
        driver.findElement(buttonLogin).click();
    }

    @Step("Нажать на логотип")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Переход на страницу сброса пароля")
    public void clickResetPassword() {
        driver.findElement(linkResetPassword).click();
    }

    @Step("Заполнение информации о пользователе: {email}, {password}")
    public void fillUserInfo(String email, String password) {
        fillEmail(email);
        fillPassword(password);
    }

    @Step("Заполнение поля Email: {email}")
    public void fillEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }

    @Step("Заполнение поля Password: {password}")
    public void fillPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }
}
