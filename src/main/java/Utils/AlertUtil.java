package Utils;

import Driver.Driver;
import org.openqa.selenium.Alert;

public class AlertUtil {
    private static Alert alert;

    public static void switchToAlert(){
        alert = Driver.getDriver().switchTo().alert();
    }

    public static String getAlertMessage(){
        return alert.getText();
    }

    public static void acceptAlert(){
        alert.accept();
    }

    public static void fillAlertInput(String string){
        alert.sendKeys(string);
    }

}
