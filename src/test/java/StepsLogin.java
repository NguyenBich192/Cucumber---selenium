import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class StepsLogin extends RunCucumberTests {
    WebDriver driver;
    @FindBy(xpath = "//input[@placeholder='Email/Số điện thoại/Tên đăng nhập']")
    WebElement email;

    @FindBy(xpath = "//input[@placeholder='Mật khẩu']")
    WebElement password;

    @FindBy(xpath = "//button[contains(text(),'Đăng nhập')]")
    WebElement btn_login;

    @FindBy(xpath = "//div[@class='navbar__username']")
    WebElement user_name;

    @Given("User visits e-commerce website")
    public void userVisitsECommerceWebsite() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        PageFactory.initElements(driver, this);
        driver.get("https://shopee.vn/buyer/login");
        driver.manage().window().maximize();
    }

    @When("User enters valid credentials")
    public void userEntersValidCredentials() {
        email.sendKeys("nguyenthibich01092002@gmail.com");
        password.sendKeys("Miike0109");
        Wait<WebDriver> wait = new FluentWait<>(driver)
                // Chờ 5 giây để một phần tử hiện diện trên trang
                .withTimeout(Duration.ofSeconds(20))
                // Và sẽ thực hiện lặp lại mỗi 30 giây nếu chưa tìm thấy phần tử đó
                .pollingEvery(Duration.ofMillis(10))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.elementToBeClickable(btn_login));
        btn_login.click();
    }

    @Then("User can logged in successfully")
    public void userCanLoggedInSuccessfully() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                // Chờ 5 giây để một phần tử hiện diện trên trang
                .withTimeout(Duration.ofSeconds(15))
                // Và sẽ thực hiện lặp lại mỗi 30 giây nếu chưa tìm thấy phần tử đó
                .pollingEvery(Duration.ofMillis(10))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.elementToBeClickable(user_name));
        user_name.getText();
        if (user_name.isDisplayed()) {
            System.out.println("login success");
        } else {
            System.out.println("login failed");
        }
    }

}
