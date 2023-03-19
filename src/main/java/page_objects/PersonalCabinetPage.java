package page_objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalCabinetPage {
    private final WebDriver driver;
    private By MENU = By.className("Account_nav__Lgali");
    private By BUTTON_EXIT = By.xpath(".//button[text()='Выход']");
    private By BUILDER = By.xpath(".//p[text()='Конструктор']");
    private By LOGO = By.xpath(".//a[@href='/']");

    public PersonalCabinetPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getMenu() {
        return MENU;
    }

    @Step("Нажать на выход из системы")
    public void clickExitButton() {
        driver.findElement(BUTTON_EXIT).click();
    }

    @Step("Нажать на конструктор")
    public void clickBuilder() {
        driver.findElement(BUILDER).click();
    }

    @Step("Нажать на логотип")
    public void clickLogo() {
        driver.findElement(LOGO).click();
    }
}
