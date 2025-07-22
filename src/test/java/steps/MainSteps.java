package steps;

import excel.ExcelReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Description;
import org.testng.Reporter;
import pages.SauceDemoLoginPage;
import tests.BaseTest;

import java.io.IOException;
import java.util.Map;

public class MainSteps extends BaseTest {

    String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
    String wait = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("wait");
    String quit = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("quit");

    Map<String, String> data;

    @Before
    @Description("Firing up teh browser")
    public void setup() throws Exception {
        init(browser,wait);
    }

    @After
    @Description("Closing the browser")
    public void tearDown(){
        if(quit.equalsIgnoreCase("yes")){
            quit();
        }
    }

    @Given("I am on the sauce demo login page")
    public void iAmOnTheSauceDemoLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @Given("I load test data from {string} {string} {string}")
    public void iLoadTestDataFrom(String fileName, String sheetName, String row) throws IOException {
        data = new ExcelReader().getRowData(fileName, sheetName, Integer.parseInt(row));
    }

    @When("I enter my username")
    public void iEnterMyUsername() throws Exception {
        new SauceDemoLoginPage(driver).enterUsername(data.get("username"));
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) throws Exception {
//        Može ovako ali je malo duže
//        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(driver);
//        sauceDemoLoginPage.enterUsername(username);
        new SauceDemoLoginPage(driver).enterUsername(username);
    }

    @And("I enter my password")
    public void iEnterMyPassword() throws Exception {
        new SauceDemoLoginPage(driver).enterPassword(data.get("password"));
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) throws Exception {
        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(driver);
        sauceDemoLoginPage.enterPassword(password);
    }

    @And("I click on the login button")
    public void iClickOnTheLoginButton() throws Exception {
        new SauceDemoLoginPage(driver).clickOnLoginButton();
    }

    @Then("I should be logged in")
    public void iShouldBeLoggedIn() {
        new SauceDemoLoginPage(driver).menuShouldBeVisible();
    }

    @And("I should be able to see products")
    public void iShouldBeAbleToSeeProducts() {
        new SauceDemoLoginPage(driver).titleShouldBeVisible();
    }

    @Then("I should get error message {string}")
    public void iShouldGetErrorMessage(String errorMessage) {
        new SauceDemoLoginPage(driver).verifyErrorMessageText(errorMessage);
    }

    @Given("I load test data from {string} {string} for {string}")
    public void iLoadTestDataFromFor(String fileName, String sheetName, String tc_id) throws IOException {
        data = new ExcelReader().getRowDataByID(fileName, sheetName, tc_id);
    }
}
