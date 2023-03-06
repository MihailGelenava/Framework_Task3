package Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

import java.util.List;

public enum BrowserFactory {
    CHROME {
        @Override
        public WebDriver createDriver() {
            WebDriverManager.chromedriver().setup();

            return new ChromeDriver(setOptions());
        }

        @Override
        public ChromeOptions setOptions() {
            ChromeOptions chromeOptions = new ChromeOptions();
            for (String o : options){
                chromeOptions.addArguments(o);
            }
            return chromeOptions;
        }

    },
    FIREFOX {
        @Override
        public WebDriver createDriver() {
            WebDriverManager.firefoxdriver().setup();

            return new FirefoxDriver(setOptions());
        }

        @Override
        public FirefoxOptions setOptions() {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            for (String o : options){
                firefoxOptions.addArguments(o);
            }
            return firefoxOptions;
        }
    };

    private static final List<String> options = (List<String>)BrowserConfiguration.getConfig().get("options");

    public abstract WebDriver createDriver();
    public abstract AbstractDriverOptions<?> setOptions();
}
