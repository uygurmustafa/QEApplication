package io.testinium.pages;

import io.testinium.base.BaseTest;
import io.testinium.model.LocatorFinder;
import com.thoughtworks.gauge.Step;
import io.testinium.model.StepDetail;
import io.testinium.utils.PerformanceUtil;
import io.testinium.utils.VisualUtil;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasePage extends BaseTest {

    protected PerformanceUtil performanceUtil;
    protected VisualUtil visualUtil;

    public BasePage() throws IOException {
        this.performanceUtil = new PerformanceUtil(driver);
        this.visualUtil = new VisualUtil(driver);
        String currentWorkingDir = System.getProperty("user.dir");
        initMap(getFileList(currentWorkingDir + "/src/test/resources/elementValues"));
    }

    WebElement findElement(String key) {
        By infoParam = getElementInfoToBy(findElementInfoByKey(key));
        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        WebElement webElement = webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(infoParam));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;
    }

    public By getElementInfoToBy(LocatorFinder elementInfo) {
        By by = null;
        if (elementInfo.getType().equals("css")) {
            by = By.cssSelector(elementInfo.getValue());
        } else if (elementInfo.getType().equals(("name"))) {
            by = By.name(elementInfo.getValue());
        } else if (elementInfo.getType().equals("id")) {
            by = By.id(elementInfo.getValue());
        } else if (elementInfo.getType().equals("xpath")) {
            by = By.xpath(elementInfo.getValue());
        } else if (elementInfo.getType().equals("linkText")) {
            by = By.linkText(elementInfo.getValue());
        } else if (elementInfo.getType().equals(("partialLinkText"))) {
            by = By.partialLinkText(elementInfo.getValue());
        }
        return by;
    }

    private void clickElement(WebElement element) {
        element.click();
    }
    @Step({"Go to <url> address",
            "<url> adresine git"})
    public void goToUrl(String url) {
        logger.info(url + " adresi goruldu.");
        driver.get(url);
        logger.info(url + " adresine gidiliyor.");
    }

    @Step({"Write value <text> to element <key>",
            "<text> textini <key> elemente yaz"})
    public void ssendKeys(String text, String key) {
        verifyVisibilityAndClickableOfElement(key, 60);
        if (!key.equals("")) {
            findElement(key).sendKeys(text);
            logger.info(key + " elementine " + text + " texti yazıldı.");
        }
    }

    @Step({"Click to element <key>",
            "Elementine tıkla <key>"})
    public void clickElement(String key) {
        verifyVisibilityAndClickableOfElement(key, 60);
        if (!key.isEmpty()) {
            // hoverElement(findElement(key));
            clickElement(findElement(key));
            logger.info(key + " elementine tıklandı.");
        }
    }

    @Step("<key> elemenin textiyle, <element> textini karsilastir")
    public void compareTwoText(String key, String element) {
        verifyVisibilityAndClickableOfElement(key, 60);
        String firstText = findElement(key).getText();
        Assertions.assertEquals(firstText, element, "text'ler esittir.");
        logger.info("Iki text karsilastirildi.");
    }

    @Step({"Check if element <key> contains text <expectedText>",
            "<key> elementi <text> değerini içeriyor mu kontrol et"})
    public void checkElementContainsText(String key, String expectedText) {
        Boolean containsText = findElement(key).getText().contains(expectedText);
        assertTrue(containsText, "Expected text is not contained");
        logger.info(key + " elementi" + expectedText + "değerini içeriyor.");
    }

    @Step({"Delete all the text in the element <key>",
            "<key> elementindeki tüm texti sil"})
    public void deleteText(String key) {
        WebElement element = findElement(key);
        element.sendKeys(Keys.CONTROL + "a");
        findElement(key).sendKeys(Keys.BACK_SPACE);
        logger.info(key + "<key> elementindeki tüm text silindi");
    }

    @Step({"Wait <value> seconds",
            "<int> saniye bekle"})
    public void waitBySeconds(int seconds) {
        try {
            logger.info(seconds + " saniye bekleniyor.");
            Thread.sleep(seconds * 1000);
            logger.info(seconds + " saniye beklendi");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step({"Verify <key> element is visible and clickable in <x> seconds at most",
            "<key> elementinin gorunur ve tiklanabilir oldugunu en fazla <x> saniye icinde dogrula"})
    public void verifyVisibilityAndClickableOfElement(String key, long seconds) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver).
                    withTimeout(ofSeconds(seconds)).
                    pollingEvery(ofMillis(500)).
                    ignoring(NotFoundException.class).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.elementToBeClickable(getElementInfoToBy(findElementInfoByKey(key))));
            logger.info(key + " elementinin gorunur oldugu dogrulandi.");
        } catch (TimeoutException ignored) {
            Assertions.fail(String.format("%s elementinin gorunur oldugu dogrulanamadi!", key));
        }
    }

    @StepDetail("Check page performance")
    public void checkPagePerformance() {
        performanceUtil.capturePerformanceMetrics();
    }

    @StepDetail("Check visual regression")
    public void checkVisualRegression(String elementKey) throws IOException {
        WebElement element = findElement(elementKey);
        visualUtil.compareScreenshots("baseline/" + elementKey + ".png", element);
    }
}
