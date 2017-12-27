package my.test.web;


import my.test.web.pages.MainPage;
import my.test.web.pages.TrendsPage;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Title;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;


@Title("Three cases")
public class TestCases {
    public WebDriver driver;

    public TestCases() {

        System.setProperty("webdriver.gecko.driver", "/home/i/Downloads/geckodriver");
        System.setProperty("webdriver.chrome.driver", "/home/i/Downloads/chromedriver");

        String browser = System.getProperty("browser");

        if (browser != null)
        driver = browser.contains("CHROME") ? new ChromeDriver() : new FirefoxDriver();
        else driver = new FirefoxDriver();
    }

    @Before
    public void loadStartPage() {
        driver.get("http://trends.google.ru/trends");
    }

    @Test
    public void trendsMain() {
        MainPage mainPage = new MainPage(driver);
        TrendsPage page = mainPage.findTrend("Точка Банк");
        page.saveScreen("main");
        saveImageAttach("/main.png");
    }

    @Test
    public void trendsFiltered(){
        MainPage mainPage = new MainPage(driver);
        TrendsPage page = mainPage.findTrend("Точка Банк");

        page.applyCountryFilter(driver, "Россия");
        page.applyPeriodFilter(driver, "90 дней");
        page.applyResolution(driver, "Город");
        page.saveScreen("filtered");
        saveImageAttach("/filtered.png");
    }

    @Test
    public void trendsNone(){
        MainPage mainPage = new MainPage(driver);
        TrendsPage page = mainPage.findTrend("Точка Банк");

        page.applyCountryFilter(driver, "Уганда");
        Assert.assertTrue(page.noData());
        page.saveScreen("void");
        saveImageAttach("/void.png");
    }

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] saveImageAttach(String attachName) {
        try {
            URL defaultImage = TestCases.class.getResource(attachName);
            File imageFile = new File(defaultImage.toURI());
            return toByteArray(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private static byte[] toByteArray(File file) throws IOException {
        return Files.readAllBytes(Paths.get(file.getPath()));
    }


    @After
    public void killWebDriver() {
        driver.quit();
    }
}