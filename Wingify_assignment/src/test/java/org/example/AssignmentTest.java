//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AssignmentTest {
    WebDriver driver;

    public AssignmentTest() {
    }

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/sonikumari/Downloads/chromedriver-mac-arm64");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        new WebDriverWait(this.driver, Duration.ofSeconds(10L));
        this.driver.get("https://sakshingp.github.io/assignment/login.html");
    }

    @DataProvider(
            name = "loginData"
    )
    public Object[][] getLoginData() {
        return new Object[][]{{"user1", "pass123"}};
    }

    @Test(
            priority = 1,
            dataProvider = "loginData"
    )
    public void testLogin(String username, String password) {
        WebElement usernameField = this.driver.findElement(By.id("//input[@id='username']"));
        usernameField.clear();
        usernameField.sendKeys(new CharSequence[]{username});
        WebElement passwordField = this.driver.findElement(By.id("//input[@id='password']"));
        passwordField.clear();
        passwordField.sendKeys(new CharSequence[]{password});
        WebElement loginButton = this.driver.findElement(By.id("//button[@id='log-in']"));
        loginButton.click();
        WebElement transactionTable = this.driver.findElement(By.id("//table[@id='transactionsTable']"));
        Assert.assertTrue(transactionTable.isDisplayed(), "Login failed or table not found");
        WebElement amountHeader = this.driver.findElement(By.id("//th[@id='amount']"));
        amountHeader.click();
        List<WebElement> amountElements = this.driver.findElements(By.className("//tbody/tr[5]/td[1]"));
        List<Double> amounts = (List)amountElements.stream().map((e) -> Double.parseDouble(e.getText().replace("$", ""))).collect(Collectors.toList());
        List<Double> sortedAmounts = (List)amounts.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(amounts, sortedAmounts, "Amounts are not sorted correctly");
    }

    @AfterClass
    public void tearDown() {
        this.driver.quit();
    }
}
