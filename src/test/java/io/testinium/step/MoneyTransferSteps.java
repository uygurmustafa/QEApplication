package io.testinium.step;

import io.testinium.model.StepDetail;
import io.testinium.pages.MoneyTransferPage;
import com.thoughtworks.gauge.Step;
import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;

import java.io.IOException;

@Epic("Web Automation")
@Feature("Para İşlemleri")
public class MoneyTransferSteps {

    private MoneyTransferPage moneyTransferPage;

    public MoneyTransferSteps() throws IOException {
        moneyTransferPage = new MoneyTransferPage();
    }

    @Story("Kredi Kartı ile Para Yükleme")
    @Description("Kredi kartı bilgileri ile hesaba para yükleme işlemi")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("payment"), @Tag("creditCard"), @Tag("money")})
    @StepDetail("Kart ile para yükleme - Kart No: {0}, Tutar: {4}")
    @Step("Add money with card details <cardNumber>, <cardHolder>, <expireDate>, <cvv> and amount <amount>")
    public void addMoney(String cardNumber, String cardHolder, String expireDate, String cvv, String amount) {
        moneyTransferPage.addMoney(cardNumber, cardHolder, expireDate, cvv, amount);
    }

    @Story("Para Transferi")
    @Description("Hesaplar arası para transferi işlemi")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("transfer"), @Tag("money")})
    @StepDetail("Tutar kontrolü yapılıyor: {0}")
    @Step("Transfer money with amount <amount>")
    public void transferMoney(String amount) {
        moneyTransferPage.transferMoney(amount);
    }


    @Story("Alan Kontrolleri")
    @Description("Zorunlu alan kontrollerinin yapılması")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag("validation"), @Tag("required")})
    @StepDetail("Required kontrolü yapılıyor")
    @Step("Verify required field messages")
    public void verifyRequiredFieldMessages() {
        moneyTransferPage.verifyRequiredFieldMessages();
    }

    @StepDetail("Tutar kontrolü yapılıyor: {0}")
    @Step("Check if amount is <expectedAmount>")
    public void verifyAmount(String expectedAmount) {
        moneyTransferPage.checkAmount(expectedAmount);
    }

    @Story("Tutar Doğrulama")
    @Description("Transfer tutarının doğrulanması")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("verification"), @Tag("amount")})
    @StepDetail("Tutar giriliyor: {0}")
    @Step("Enter <text> to amount field")
    public void enterAmountText(String text) {
        moneyTransferPage.checkAmountFieldWithText(text);
    }

    @Story("Uyarı mesajı Doğrulama")
    @Description("Uyarı mesajı doğrulanması")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("verification"), @Tag("amount")})
    @StepDetail("Uyarı mesajı dogrulaması yapılıyor {0}")
    @Step("Verify warning message <message> in element <elementKey>")
    public void verifyWarningMessage(String elementKey, String message) {
        moneyTransferPage.verifyWarningMessage(elementKey, message);
    }
}