package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {
    private final WebDriver driver;

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private By LINK_ENTER = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Войти']");

    public void clickEnterLink(){
        driver.findElement(LINK_ENTER).click();
    }
}
