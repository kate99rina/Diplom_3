package page_objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
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

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getLinkRegister() {
        return LINK_REGISTER;
    }

    public By getEnterForm() {
        return ENTER_FORM;
    }

    @Step("Переместиться к элементу")
    public void scrollTo(By element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
    }

    @Step("Переход на страницу регистрации")
    public void clickToRegister() {
        driver.findElement(LINK_REGISTER).click();
    }

    @Step("Нажать на кнопку входа")
    public void clickToLogin() {
        driver.findElement(BUTTON_LOGIN).click();
    }

    @Step("Нажать на логотип")
    public void clickLogo() {
        driver.findElement(LOGO).click();
    }

    @Step("Переход на страницу сброса пароля")
    public void clickResetPassword() {
        driver.findElement(LINK_RESET_PASSWORD).click();
    }

    @Step("Заполнение информации о пользователе: {email}, {password}")
    public void fillUserInfo(String email, String password) {
        fillEmail(email);
        fillPassword(password);
    }

    @Step("Заполнение поля Email: {email}")
    public void fillEmail(String email) {
        driver.findElement(INPUT_EMAIL).sendKeys(email);
    }

    @Step("Заполнение поля Password: {password}")
    public void fillPassword(String password) {
        driver.findElement(INPUT_PASSWORD).sendKeys(password);
    }
}
