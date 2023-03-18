package page_objects;

import dev.failsafe.internal.util.Assert;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;

    private By PERSONAL_CABINET = By.xpath(".//*[text() = 'Личный Кабинет']");

    public By getIngredientsSection() {
        return INGREDIENTS_SECTION;
    }

    private By INGREDIENTS_SECTION = By.className("BurgerIngredients_ingredients__1N8v2");

    private By BUNS = By.cssSelector(".text.text_type_main-default[text='Булки']");
    private By SAUCES = By.cssSelector(".text.text_type_main-default[text='Соусы']");
    private By FILLINGS = By.cssSelector(".text.text_type_main-default[text='Начинки']");

    public By getButtonEnter() {
        return BUTTON_ENTER;
    }

    private By BUTTON_ENTER = By.xpath(".//button[text()='Войти в аккаунт']");

    public By getButtonCreateOrder() {
        return BUTTON_CREATE_ORDER;
    }

    private By BUTTON_CREATE_ORDER = By.xpath(".//button[text()='Оформить заказ']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickPersonalCabinet() {
        driver.findElement(PERSONAL_CABINET).click();
    }

    public void clickEnterButton() {
        driver.findElement(BUTTON_ENTER).click();
    }

    public void waitElement(By element) throws Exception {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            element));
        } catch (Exception e) {
            throw new Exception("ERROR! Expected element is NOT FOUND");
        }
    }

    //todo
    @SneakyThrows
    public void checkElement(By element) {
        waitElement(element);
        WebElement webElement = driver.findElement(element);
        Assert.isTrue(webElement.isEnabled(), "Expected element is not on the page");
    }
}
