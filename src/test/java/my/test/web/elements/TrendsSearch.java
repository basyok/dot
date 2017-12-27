package my.test.web.elements;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

@Block(@FindBy(css = ".header-topbar-center"))
public class TrendsSearch extends HtmlElement {

    @FindBy(id = "input-0")
    public TextInput requestInput;

    public void Go(String trend) {
        requestInput.clear();
        requestInput.sendKeys(trend);
        requestInput.sendKeys(Keys.RETURN);

    }
}

