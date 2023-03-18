package transition;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import page_objects.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BuilderSectionTests {
    private final static String WEB_LINK = "http://stellarburgers.nomoreparties.site/";

    private WebDriver driver;
    private HomePage homePage;

    @SneakyThrows
    @Before
    public void init() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(option);
        driver.get(WEB_LINK);
        homePage = new HomePage(driver);
    }

    @Test
    public void checkBunsSection() {

    }

    @Test
    public void checkSaucesSection() {

    }

    @Test
    public void checkFillingsSection() {

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
