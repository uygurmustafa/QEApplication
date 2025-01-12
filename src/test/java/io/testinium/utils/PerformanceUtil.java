package io.testinium.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class PerformanceUtil {
    
    private WebDriver driver;
    
    public PerformanceUtil(WebDriver driver) {
        this.driver = driver;
    }
    
    @Attachment(value = "Performance Metrics", type = "text/plain")
    public String capturePerformanceMetrics() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        Long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");
        Long navigationStart = (Long) js.executeScript("return window.performance.timing.navigationStart;");
        Long responseStart = (Long) js.executeScript("return window.performance.timing.responseStart;");
        
        StringBuilder metrics = new StringBuilder();
        metrics.append("Sayfa Yüklenme Süresi: ").append(loadEventEnd - navigationStart).append(" ms\n");
        metrics.append("Sunucu Yanıt Süresi: ").append(responseStart - navigationStart).append(" ms\n");
        
        return metrics.toString();
    }
    
    public Long getFirstContentfulPaint() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Long) js.executeScript(
            "return window.performance.getEntriesByType('paint')" +
            ".find(entry => entry.name === 'first-contentful-paint').startTime;"
        );
    }
} 