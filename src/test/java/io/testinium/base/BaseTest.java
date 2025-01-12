package io.testinium.base;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.testinium.model.LocatorFinder;
import io.testinium.model.StepDetail;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import javassist.bytecode.SignatureAttribute;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


public class BaseTest {


    protected static WebDriver driver;
    protected static Actions actions;
    protected Logger logger = LoggerFactory.getLogger(getClass());
    DesiredCapabilities capabilities;
    ChromeOptions chromeOptions;
    FirefoxOptions firefoxOptions;

    String browserName = "chrome";
    String selectPlatform = System.getProperty("os.name").toLowerCase();
    ConcurrentMap<String, Object> elementMapList = new ConcurrentHashMap<>();

    @BeforeScenario
    public void setUp() {
        logger.info("************************************  BeforeScenario  ************************************");
        try {
            if (StringUtils.isEmpty(System.getenv("key"))) {
                logger.info(selectPlatform + " ortamında " + browserName + " browserında test ayağa kalkacak");
                if (selectPlatform.contains("win")) {
                    if ("chrome".equalsIgnoreCase(browserName)) {
                        driver = new ChromeDriver(chromeOptions(selectPlatform));
                        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                    } else if ("firefox".equalsIgnoreCase(browserName)) {
                        driver = new FirefoxDriver(firefoxOptions(selectPlatform));
                        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                    }
                } else if (selectPlatform.contains("mac")) {
                    if ("chrome".equalsIgnoreCase(browserName)) {
                        driver = new ChromeDriver(chromeOptions(selectPlatform));
                        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                    } else if ("firefox".equalsIgnoreCase(browserName)) {
                        driver = new FirefoxDriver(firefoxOptions(selectPlatform));
                        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                    }
                    actions = new Actions(driver);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterScenario
    public void tearDown() {
        saveScreenshot();
        driver.quit();
        logger.info("Driver kapatildi");
    }


    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void initMap(List<File> fileList) {
        elementMapList = new ConcurrentHashMap<>();
        Type elementType = new TypeToken<List<LocatorFinder>>() {
        }.getType();
        Gson gson = new Gson();
        List<LocatorFinder> elementInfoList = null;
        for (File file : fileList) {
            try {
                FileReader filez = new FileReader(file);
                elementInfoList = gson
                        .fromJson(new FileReader(file), elementType);
                elementInfoList.parallelStream()
                        .forEach(elementInfo -> elementMapList.put(elementInfo.getKey(), elementInfo));
                System.out.println(file.getName() + " Dosyasında " + elementInfoList.size() + " Adet element değeri bulundu.");
            } catch (FileNotFoundException e) {

            }
        }
    }

    public static List<File> getFileList(String directoryName) throws IOException {
        List<File> dirList = new ArrayList<>();
        try (Stream<Path> walkStream = Files.walk(Paths.get(directoryName))) {
            walkStream.filter(p -> p.toFile().isFile()).forEach(f -> {
                if (f.toString().endsWith(".json")) {
                    dirList.add(f.toFile());
                }
            });
        }
        return dirList;
    }


    /**
     * Set Chrome options
     *
     * @return the chrome options
     */
    public ChromeOptions chromeOptions(String platformName) {
        chromeOptions = new ChromeOptions();
        capabilities = DesiredCapabilities.chrome();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        chromeOptions.setExperimentalOption("prefs", prefs);
        //chromeOptions.addArguments("--kiosk");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--start-fullscreen");
        if(platformName.contains("win")){
            System.setProperty("webdriver.chrome.driver", "./web_driver/chromedriver.exe");
        }else if (platformName.contains("mac")){
            System.setProperty("webdriver.chrome.driver", "./web_driver/chromedriver");
        }
        chromeOptions.merge(capabilities);
        return chromeOptions;
    }

    /**
     * Set Firefox options
     *
     * @return the firefox options
     */
    public FirefoxOptions firefoxOptions(String platformName) {
        firefoxOptions = new FirefoxOptions();
        capabilities = DesiredCapabilities.firefox();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        firefoxOptions.addArguments("--kiosk");
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.addArguments("--start-fullscreen");
        FirefoxProfile profile = new FirefoxProfile();
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        capabilities.setCapability("marionette", true);
        firefoxOptions.merge(capabilities);
        if(platformName.contains("win")){
            System.setProperty("webdriver.gecko.driver", "./web_driver/geckodriver.exe");
        }else if (platformName.contains("mac")){
            System.setProperty("webdriver.gecko.driver", "./web_driver/geckodriver");
        }
        return firefoxOptions;
    }

    public LocatorFinder findElementInfoByKey(String key) {
        return (LocatorFinder) elementMapList.get(key);
    }

    public void saveValue(String key, String value) {
        elementMapList.put(key, value);
    }

    public String getValue(String key) {
        return elementMapList.get(key).toString();
    }

    @Around("@annotation(com.testinium.utils.StepDetail)")
    public Object stepLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        StepDetail stepDetail = methodSignature.getMethod().getAnnotation(StepDetail.class);

        String stepDescription = stepDetail.value();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            stepDescription = stepDescription.replace("{" + i + "}", args[i].toString());
        }

        Allure.step(stepDescription);

        return joinPoint.proceed();
    }
}
