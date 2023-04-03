package page_objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {
    private final WebDriver driver;
    private By linkEnter = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Войти']");

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать на кнопку входа")
    public void clickEnterLink() {
        driver.findElement(linkEnter).click();
    }
}
