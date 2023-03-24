package page_objects;

import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final WebDriver driver;

    @Getter
    private By personalCabinet = By.xpath(".//*[text() = 'Личный Кабинет']");
    @Getter
    private By ingredientsSection = By.className("BurgerIngredients_ingredients__1N8v2");
    @Getter
    private String sectionPath = "//span[contains(@class,'text_type_main-default') and text()='(section)']";
    private String selectedSectionPath = ".//div[contains(@class,'tab_tab_type_current__2BEPc')]";
    @Getter
    private By buttonEnter = By.xpath(".//button[text()='Войти в аккаунт']");
    @Getter
    private By buttonCreateOrder = By.xpath(".//button[text()='Оформить заказ']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @SneakyThrows
    @Step("Нажать на секцию '{nameSection}'")
    public void clickSection(String nameSection) {
        By section = By.xpath(".".concat(sectionPath.replace("(section)", nameSection)));
        driver.findElement(section).click();
    }

    @Step("Проверить, что нужная секция выбрана")
    public void checkCurrentSection(String nameSection) {
        try {
            By sectionElement = By.xpath(getSelectedSectionPath(nameSection));
            WebElement webElement = driver.findElement(sectionElement);
            Assert.notNull(webElement, "Not found section ".concat(nameSection));
        } catch (Exception e) {
            throw new NullPointerException("This element is not found");
        }
    }

    @Step("Нажать на 'Личный кабинет'")
    public void clickPersonalCabinet() {
        driver.findElement(personalCabinet).click();
    }

    @Step("Нажать на кнопку входа")
    public void clickEnterButton() {
        driver.findElement(buttonEnter).click();
    }

    public String getSelectedSectionPath(String sectionName) {
        return selectedSectionPath.concat(sectionPath.replace("(section)", sectionName));
    }
}
