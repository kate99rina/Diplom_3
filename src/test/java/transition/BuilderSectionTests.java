package transition;

import io.qameta.allure.junit4.DisplayName;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import page_objects.HomePage;
import service.BaseTest;

@DisplayName("Builder")
@RunWith(Parameterized.class)
public class BuilderSectionTests extends BaseTest {

    private String nameSection;

    public BuilderSectionTests(String nameSection) {
        this.nameSection = nameSection;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Соусы"},
                {"Булки"},
                {"Начинки"}
        };
    }

    @SneakyThrows
    @Before
    public void init() {
        driver = new ChromeDriver(getOptions());
        driver.get(WEB_LINK);
        homePage = new HomePage(driver);
    }

    @DisplayName("Переход к разделу конструктора")
    @Test
    public void checkSection() {
        if (nameSection.equals("Булки"))
            homePage.clickSection("Соусы");
        homePage.clickSection(nameSection);
        homePage.checkCurrentSection(nameSection);
    }
}
