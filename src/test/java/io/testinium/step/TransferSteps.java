package io.testinium.step;

import io.testinium.model.StepDetail;
import io.testinium.pages.TransferPage;
import com.thoughtworks.gauge.Step;
import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;

import java.io.IOException;

@Epic("Web Automation")
@Feature("Para Transferi İşlemleri")
public class TransferSteps {

    private TransferPage transferPage;

    public TransferSteps() throws IOException {
        transferPage = new TransferPage();
    }

    @Story("Transfer Başlatma")
    @Description("Para transfer modülünün açılması")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("transfer"), @Tag("init")})
    @StepDetail("money transgfer modulu aciliyor")
    @Step("Initiate money transfer")
    public void initiateTransfer() {
        transferPage.initiateTransfer();
    }

    @Story("Transfer Tutarı Girişi")
    @Description("Transfer edilecek tutarın girilmesi")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("transfer"), @Tag("amount")})
    @StepDetail("amount transferi yapiliyor {0}")
    @Step("Enter transfer amount <amount>")
    public void enterTransferAmount(String amount) {
        transferPage.enterTransferAmount(amount);
    }

    @Story("Transfer Onayı")
    @Description("Para transfer işleminin onaylanması")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("transfer"), @Tag("confirmation")})
    @StepDetail("transfer butonuna tiklaniliyor")
    @Step("Click send transfer button")
    public void clickSendButton() {
        transferPage.clickSendButton();
    }

    @Story("Transfer Doğrulama")
    @Description("Transfer edilen tutarın doğrulanması")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("transfer"), @Tag("verification")})
    @StepDetail("transfer tutari kontrol ediliyor: {0}")
    @Step("Verify transfer amount is <expectedAmount>")
    public void verifyTransferAmount(String expectedAmount) {
        transferPage.verifyTransferAmount(expectedAmount);
    }
}