package seleniumCore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class ChromeDriverManager extends DriverManager{
    @Override
    public void createDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setImplicitWaitTimeout(Duration.ofSeconds(600));
        driver = new ChromeDriver(options);
    }
}
