package io.testinium.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VisualUtil {
    
    private WebDriver driver;
    
    public VisualUtil(WebDriver driver) {
        this.driver = driver;
    }
    
    @Attachment(value = "Visual Comparison", type = "image/png")
    public BufferedImage compareScreenshots(String baselinePath, WebElement element) throws IOException {
        Screenshot actual = new AShot()
            .coordsProvider(new WebDriverCoordsProvider())
            .takeScreenshot(driver, element);
            
        BufferedImage expectedImage = ImageIO.read(new File(baselinePath));
        ImageDiff diff = new ImageDiffer().makeDiff(actual.getImage(), expectedImage);
        
        return diff.getMarkedImage();
    }
} 