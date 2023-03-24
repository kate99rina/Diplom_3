package page_objects;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalCabinetPage {
    private final WebDriver driver;
    @Getter
    private By menu = By.className("Account_nav__Lgali");
    private By buttonExit = By.xpath(".//button[text()='Выход']");
    private By builder = By.xpath(".//p[text()='Конструктор']");
    private By logo = By.xpath(".//a[@href='/']");

    public PersonalCabinetPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать на выход из системы")
    public void clickExitButton() {
        driver.findElement(buttonExit).click();
    }

    @Step("Нажать на конструктор")
    public void clickBuilder() {
        driver.findElement(builder).click();
    }

    @Step("Нажать на логотип")
    public void clickLogo() {
        driver.findElement(logo).click();
    }
}
