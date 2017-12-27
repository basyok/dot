package my.test.web.pages;

import my.test.web.elements.TrendsSearch;
import my.test.web.pages.TrendsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;


public class MainPage {

    private WebDriver driver;

    private TrendsSearch ts;

    public MainPage(final WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
        this.driver = driver;
    }

    public TrendsPage findTrend(String request) {
        this.ts.Go(request);
        return new TrendsPage(driver);
    }

}