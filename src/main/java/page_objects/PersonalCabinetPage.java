package page_objects;

import dev.failsafe.internal.util.Assert;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalCabinetPage {
    private final WebDriver driver;

    public PersonalCabinetPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getMenu() {
        return MENU;
    }

    private By MENU = By.className("Account_nav__Lgali");
    private By BUTTON_EXIT = By.xpath(".//button[text()='Выход']");
    private By BUILDER = By.xpath(".//p[text()='Конструктор']");
    private By LOGO = By.xpath(".//a[@href='/']");


    //todo вынести в общее
    public void waitElement(By element) throws Exception {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            element));
        } catch (Exception e) {
            throw new Exception("ERROR! Expected element is NOT FOUND");
        }

    }

    //todo вынести
    @SneakyThrows
    public void checkElement(By element) {
        waitElement(element);
        WebElement webElement = driver.findElement(element);
        Assert.isTrue(webElement.isEnabled(), "Expected element is not on the page");
    }


    public void clickExitButton() {
        driver.findElement(BUTTON_EXIT).click();
    }

    public void clickBuilder() {
        driver.findElement(BUILDER).click();
    }

    public void clickLogo() {
        driver.findElement(LOGO).click();
    }
}
