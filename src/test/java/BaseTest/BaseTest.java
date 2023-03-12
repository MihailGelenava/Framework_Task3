package BaseTest;

import Driver.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeTest
    public void setUp(){
        logger.info("BeforeTest STARTED");

        Driver.getDriver();
    }

    @BeforeMethod
    public void openSite(){
        logger.info("BeforeMethod STARTED");

        Driver.getDriver().get(BrowserConfiguration.getConfig().get("url").toString());
    }

    @AfterTest
    public void tearDown() {
        Driver.closeDriver();

        logger.info("AfterTest FINISHED");
    }
}
