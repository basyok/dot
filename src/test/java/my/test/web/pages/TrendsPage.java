package my.test.web.pages;

import my.test.web.elements.Pagination;
import my.test.web.elements.Filters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import javax.imageio.ImageIO;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

public class TrendsPage {

    private WebDriver driver;

    private Pagination pg;
    private Filters fl;

    public TrendsPage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
        this.driver = driver;
    }

    public boolean noData(){
        return fl.isDataVoid(driver);
    }

    public void applyCountryFilter(WebDriver driver, String country){
        fl.selectCountry(driver, country);
    }

    public void applyResolution(WebDriver driver, String resolution){
        fl.selectRegion(driver, resolution);
    }

    public void applyPeriodFilter(WebDriver driver, String period){
        fl.selectPeriod(driver, period);
    }

    public String saveScreen(String name) {
        String to = new File(".").getAbsolutePath().replace(".", "src/main/resources/"+ name + ".png");

        //while (!pg.nxt.getWrappedElement().isDisplayed());

        fl.delay(5);

        Screenshot screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(1000)).takeScreenshot(driver);
        try{
            ImageIO.write(screenshot.getImage(), "PNG", new File(to));

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return to;
    }


}
