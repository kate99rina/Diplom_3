package page_objects;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    private final WebDriver driver;

    //ввод имени
    private By INPUT_NAME = By.xpath(".//label[text()='Имя']//parent::*//input");
    private By INPUT_EMAIL = By.xpath(".//label[text()='Email']//parent::*//input");
    private By INPUT_PASSWORD = By.xpath(".//label[text()='Пароль']//parent::*//input");
    private By BUTTON_REGISTER = By.xpath(".//button[text()='Зарегистрироваться']");
    //.sendKeys
    private By INCORRECT_PASSWORD = By.xpath(".//p[text()='Некорректный пароль']");

    public By getLinkEnter() {
        return LINK_ENTER;
    }

    private By LINK_ENTER = By.className("Auth_link__1fOlj");

    public void fillUserInfo(String email, String name, String password) {
        fillEmail(email);
        fillName(name);
        fillPassword(password);
    }

    public void fillName(String name) {
        driver.findElement(INPUT_NAME).sendKeys(name);
    }

    public void fillEmail(String email) {
        driver.findElement(INPUT_EMAIL).sendKeys(email);
    }

    public void fillPassword(String password) {
        driver.findElement(INPUT_PASSWORD).sendKeys(password);
    }

    public void clickButtonToRegister() {
        driver.findElement(BUTTON_REGISTER).click();
    }

    public void clickEnter(){
        driver.findElement(LINK_ENTER).click();
    }

    public void checkErrorIncorrectPassword() {
        WebElement webElement = driver.findElement(INCORRECT_PASSWORD);
        Assert.isTrue(webElement.isEnabled(), "Error message");
    }

    public void scrollTo(By element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
    }
}
