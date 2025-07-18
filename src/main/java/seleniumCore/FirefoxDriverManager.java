package seleniumCore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class FirefoxDriverManager extends DriverManager{
    @Override
    public void createDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("start-maximized");
        options.setImplicitWaitTimeout(Duration.ofSeconds(600));
        driver = new FirefoxDriver(options);
    }
}
