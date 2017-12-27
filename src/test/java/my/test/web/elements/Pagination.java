package my.test.web.elements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Block(@FindBy(css = ".pagination"))
public class Pagination extends HtmlElement {

    @FindBy(css = "button.md-button:nth-child(3)")
    public Button nxt;
}
