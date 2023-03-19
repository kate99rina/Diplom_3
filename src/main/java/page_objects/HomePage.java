package page_objects;

import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final WebDriver driver;

    private By PERSONAL_CABINET = By.xpath(".//*[text() = 'Личный Кабинет']");
    private By INGREDIENTS_SECTION = By.className("BurgerIngredients_ingredients__1N8v2");
    private String SECTION_PATH = "//span[contains(@class,'text_type_main-default') and text()='(section)']";
    private String SELECTED_SECTION_PATH = ".//div[contains(@class,'tab_tab_type_current__2BEPc')]";
    private By BUTTON_ENTER = By.xpath(".//button[text()='Войти в аккаунт']");
    private By BUTTON_CREATE_ORDER = By.xpath(".//button[text()='Оформить заказ']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public By getIngredientsSection() {
        return INGREDIENTS_SECTION;
    }

    @SneakyThrows
    @Step("Нажать на секцию '{nameSection}'")
    public void clickSection(String nameSection) {
        By section = By.xpath(".".concat(SECTION_PATH.replace("(section)", nameSection)));
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

    public By getButtonEnter() {
        return BUTTON_ENTER;
    }

    public By getButtonCreateOrder() {
        return BUTTON_CREATE_ORDER;
    }

    @Step("Нажать на 'Личный кабинет'")
    public void clickPersonalCabinet() {
        driver.findElement(PERSONAL_CABINET).click();
    }

    @Step("Нажать на кнопку входа")
    public void clickEnterButton() {
        driver.findElement(BUTTON_ENTER).click();
    }

    public String getSelectedSectionPath(String sectionName) {
        return SELECTED_SECTION_PATH.concat(SECTION_PATH.replace("(section)", sectionName));
    }
}
