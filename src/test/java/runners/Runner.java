package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = "src/test/java/features",
        glue = "steps",
        tags = "(@Smoke or @Regression)",
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)

public class Runner extends AbstractTestNGCucumberTests {
    @DataProvider(parallel = false)
    @Override
    public Object[][] scenarios(){
        return super.scenarios();
    }
}
