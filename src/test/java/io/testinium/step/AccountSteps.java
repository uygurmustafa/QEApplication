package io.testinium.step;

import io.testinium.model.StepDetail;
import io.testinium.pages.AccountPage;
import io.testinium.pages.LoginPage;
import com.thoughtworks.gauge.Step;
import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;

import java.io.IOException;
@Epic("Web Automation")
@Feature("Hesap Yönetimi")
public class AccountSteps {

    private AccountPage accountPage;
    private LoginPage loginPage;

    public AccountSteps() throws IOException {
        accountPage = new AccountPage();
        loginPage = new LoginPage();
    }

    @Story("Hesap Adı Güncelleme")
    @Description("Kullanıcı hesap adının başarılı bir şekilde güncellenmesi")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("account"), @Tag("update")})
    @Step("Update account name to <newAccountName>")
    public void updateAccountName(String newAccountName) {
        accountPage.updateAccountName(newAccountName);
    }

    @Story("Hesap Adı Doğrulama")
    @Description("Güncellenen hesap adının doğrulanması")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("account"), @Tag("verification")})
    @StepDetail("Hesap adi dogrulaniyor {0}")
    @Step("Verify account name is <expectedAccountName>")
    public void verifyAccountName(String expectedAccountName) {
        accountPage.verifyAccountName(expectedAccountName);
    }
}