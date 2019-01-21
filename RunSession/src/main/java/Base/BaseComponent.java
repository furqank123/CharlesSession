package Base;

import PageObjects.HomePage;
import Util.SessionData;
import Util.TestDataUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseComponent {

    public static AppiumDriver appiumDriver = null;
    private static BaseComponent instance;
    private DesiredCapabilities capabilities;
    public File app;

    public static BaseComponent getInstance() {
        if (instance == null) {
            instance = new BaseComponent();
        }

        return instance;
    }

    @Before
    public void setUp() throws MalformedURLException {
        TestDataUtil testData = new TestDataUtil();
        SessionData sessionData = testData.retrieveCapabilities();
        app = new File(sessionData.getAppDirectory());

        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,sessionData.getDeviceName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, sessionData.getPlatformVersion());
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, sessionData.getAutomationName());
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("newCommandTimeout",200);
        appiumDriver = new AndroidDriver(new URL(sessionData.getAppiumServer()),capabilities);
        appiumDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

    }

    @After
    public void cleanUp() {
        appiumDriver.quit();
    }

    public BaseComponent runVideosOnHomePage() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(appiumDriver,HomePage.class);
        homePage.signUpLater()
                .tapOnSkip()
                .tapOnSkip()
                .tapOnAccept();
        Thread.sleep(1000*120);

        return this;
    }

}
