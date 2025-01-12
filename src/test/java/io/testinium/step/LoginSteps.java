package io.testinium.step;

import io.testinium.model.StepDetail;
import io.testinium.pages.LoginPage;
import com.thoughtworks.gauge.Step;
import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;

import java.io.IOException;

@Epic("Web Automation")
@Feature("Login İşlemleri")
public class LoginSteps {

    private LoginPage loginPage;

    public LoginSteps() throws IOException {
        loginPage = new LoginPage();
    }

    @Story("Başarılı Login")
    @Description("Geçerli kullanıcı bilgileriyle sisteme giriş yapılması")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({@Tag("smoke"), @Tag("login"), @Tag("critical")})
    @StepDetail("Kullanıcı girişi yapılıyor: {0}")
    @Step("Login with username <username> and password <password>")
    public void login(String username, String password) {
        loginPage.goToUrl("https://catchylabs-webclient.testinium.com/signIn");
        loginPage.login(username, password);
    }

    @Story("Login Performance")
    @Description("Login sayfası performans ve UX kontrolü")
    @Severity(SeverityLevel.NORMAL)
    @Step("Check login page UX metrics")
    public void checkLoginPageMetrics() throws IOException {
        loginPage.checkPagePerformance();
        loginPage.checkVisualRegression("loginForm");
    }
}