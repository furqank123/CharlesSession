package PageObjects;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage  {


    @FindBy(id= "btn_sign_up_later")
    public  WebElement signUpLater;

    @FindBy(id="nextText")
    public  WebElement skip;

    @FindBy(id="android:id/button1")
    public  WebElement accept;



    public HomePage signUpLater(){
        signUpLater.click();
        return this;
    }

    public HomePage tapOnSkip(){
        skip.click();

        return this;
    }

    public HomePage tapOnAccept(){
        accept.click();

        return this;
    }



}
