package Driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Driver {

    private static final Logger logger = LogManager.getLogger(Driver.class);

    private static WebDriver driver;

    public static WebDriver getDriver(){
        if (driver == null){

            logger.info(((String)BrowserConfiguration.getConfig().get("browser")).toUpperCase() + " Browser init");

            driver = BrowserFactory.valueOf(
                    ((String)BrowserConfiguration.getConfig().get("browser")).toUpperCase()
            ).createDriver();
        }
        return driver;
    }

    public static void closeDriver(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }

        logger.info("Driver closed");
    }
}
