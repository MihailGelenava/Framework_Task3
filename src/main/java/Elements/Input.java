package Elements;

import BasePageObjects.BaseElement;
import org.openqa.selenium.By;

public class Input extends BaseElement {

    public Input(By locator, String name) {
        super(locator, name);
    }

    public void sendText(String text){
        getElement().clear();
        getElement().sendKeys(text);

        logger.info("In " + getName() + " sent text: " + text);
    }
    public void sendText(int number){
        sendText(Integer.toString(number));
    }

}
