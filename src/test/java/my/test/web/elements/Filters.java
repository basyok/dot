package my.test.web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.*;
import java.util.concurrent.TimeUnit;


//<div class="compare-pickers content-wrap">
@Block(@FindBy(css = ".compare-pickers"))
public class Filters extends HtmlElement {

    @FindBy(css = "hierarchy-picker.compare-picker:nth-child(1) > ng-include:nth-child(1) > div:nth-child(1)")
    public Button cntr;

    @FindBy(css = "custom-date-picker.compare-picker")
    public Button pd;

    public boolean isDataVoid(WebDriver driver){
         delay(5);
         return driver.findElement(By.className("widget-error-title")).isDisplayed();
    }

    public void selectRegion(WebDriver driver, String region){
        delay(3);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String id1 = (String) js.executeScript("var all = document.getElementsByTagName(\"md-select\");" +
                "var res;" +
                "for (i = 0; i < all.length; i++)" +
                "{" +
                " if(all[i].getAttribute(\"ng-change\") == \"ctrl.updateResolution()\"){res = all[i].parentNode.getAttribute(\"class\")}" +
                "}" +
                "return res;");

        System.out.println("Hello");
        System.out.println(id1);

        WebElement el = driver.findElement(By.className(id1));


        //js.executeScript("arguments[0].scrollIntoView();", el);


        js.executeScript("window.scrollTo(0, 300);");
        el.click();

        String id2 = (String) js.executeScript("var all = document.getElementsByClassName(\"_md-text\");" +
                "var res;" +
                "var pattern = new RegExp(\"" + region +"\");" +
                "for (i = 0; i < all.length; i++)" +
                "{" +
                " if(pattern.test(all[i].innerHTML)){res = all[i].parentNode.id}" +
                "}" +
                "return res;");

        new Actions(driver).moveToElement(driver.findElement(By.id(id2))).click().perform();




    }


    public void selectPeriod(WebDriver driver, String period){
        delay(5);
        pd.click();
        delay(3);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String id1 = (String) js.executeScript("var all = document.getElementsByClassName(\"_md-text\");" +
                "var res;" +
                "var pattern = new RegExp(\"" + period +"\");" +
                "for (i = 0; i < all.length; i++)" +
                "{" +
                " if(pattern.test(all[i].innerHTML)){res = all[i].parentNode.id}" +
                "}" +
                "return res;");

        System.out.println("Hello");
        System.out.println(id1);

        new Actions(driver).moveToElement(driver.findElement(By.id(id1))).click().perform();


    }

    public void selectCountry(WebDriver driver, String country) {
        delay(5);

        cntr.click();

        delay(3);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String id1 = (String) js.executeScript("var z = document.getElementsByTagName(\"md-autocomplete-wrap\")[1].getElementsByTagName(\"input\")[0].id; return z;");

        driver.findElement(By.id(id1)).sendKeys(country);

        delay(7);

        new Actions(driver).moveToElement(driver.findElement(By.className("hierarchy-picker-node"))).click().perform();



    }

    public void delay(int seconds){
        try
        {
            Thread.sleep(seconds*1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}