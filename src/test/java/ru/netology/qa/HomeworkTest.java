package ru.netology.qa;
import io.appium.java_client.MobileElement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import ru.netology.qa.screens.HWScreen;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.remote.DesiredCapabilities;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HomeworkTest {

    private AndroidDriver driver;

    @BeforeAll
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:app", "C:\\\\app.apk");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

    }

    @Test
    public void enterSpaceTest() {
        HWScreen screen = new HWScreen(driver);
        String baseInput = screen.textToBeChanged.getText();
        screen.userInput.sendKeys(" ");
        screen.buttonChange.click();
        assertEquals(baseInput, screen.textToBeChanged.getText());
    }
    @Test
    public void emptyStringTest() {
        HWScreen screen = new HWScreen(driver);
        String baseInput = screen.textToBeChanged.getText();
        screen.userInput.sendKeys("");
        screen.buttonChange.click();
        assertEquals(baseInput, screen.textToBeChanged.getText());
    }
    @Test
    public void newActivityTest() {
        HWScreen screen = new HWScreen(driver);
        String newInput = "New input";
        screen.userInput.sendKeys(newInput);
        screen.buttonActivity.click();
        assertEquals(newInput, screen.text.getText());
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}