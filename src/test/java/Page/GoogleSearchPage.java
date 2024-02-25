package Page;

import loopcamp.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage {

    public GoogleSearchPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(name = "q")
    public WebElement searchBox;

    @FindBy(xpath = "//input[@id='gbqfbb']/preceding-sibling::input")
    public WebElement searchButton;

    @FindBy(xpath = "//a[@class='FLP8od']")
    public WebElement capital;


}
