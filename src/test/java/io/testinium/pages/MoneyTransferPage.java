package io.testinium.pages;

import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class MoneyTransferPage extends BasePage {

    public MoneyTransferPage() throws IOException {
        super();
    }

    public void addMoney(String cardNumber, String cardHolder, String expireDate, String cvv, String amount) {
        clickElement("AddMoneyButton");

        deleteText("CardNumberText");

        ssendKeys(cardNumber, "CardNumberText");

        ssendKeys(cardHolder, "CardHolderNameText");

        ssendKeys(expireDate, "CardExpireDateText");

        ssendKeys(cvv, "CVVText");

        ssendKeys(amount, "AmountText");

        clickElement("AddButton");
    }
    public void verifyRequiredFieldMessages() {
        verifyVisibilityAndClickableOfElement("RequiredCardNo", 30);
        verifyVisibilityAndClickableOfElement("RequiredCardHolderName", 30);
        verifyVisibilityAndClickableOfElement("RequiredCardExpireDate", 30);
        verifyVisibilityAndClickableOfElement("RequiredCardCVV", 30);

        String expectedMessage = "Required";

        verifyElementText("RequiredCardNo", expectedMessage);
        verifyElementText("RequiredCardHolderName", expectedMessage);
        verifyElementText("RequiredCardExpireDate", expectedMessage);
        verifyElementText("RequiredCardCVV", expectedMessage);

        logger.info("Tüm required alan kontrolleri yapıldı");
    }

    private void verifyElementText(String elementKey, String expectedText) {
        String actualText = findElement(elementKey).getText();
        Assertions.assertEquals(expectedText, actualText,
                String.format("%s elementi için beklenen metin %s, ancak görünen metin %s",
                        elementKey, expectedText, actualText));
    }

    public void transferMoney(String amount) {
        clickElement("TransferMoneyButton");
        ssendKeys(amount, "TransferMoneyAmountText");
        clickElement("SendButton");
    }

    public void verifyAmount(String expectedAmount) {
        compareTwoText("AmountCheck", expectedAmount);
    }

    public void checkAmount(String expectedAmount) {
        verifyVisibilityAndClickableOfElement("AmountCheck", 60);
        String actualAmount = findElement("AmountCheck").getText();
        Assertions.assertEquals(expectedAmount, actualAmount,
                String.format("Beklenen miktar %s, ancak görünen miktar %s", expectedAmount, actualAmount));
        logger.info("Miktar kontrolü yapıldı: " + actualAmount);
    }

    public void verifyWarningMessage(String elementKey, String expectedText) {
        verifyVisibilityAndClickableOfElement(elementKey, 30);
        String actualText = findElement(elementKey).getText();

        Assertions.assertTrue(actualText.contains(expectedText),
                String.format("%s elementi beklenen '%s' metnini içermiyor. Mevcut metin: '%s'",
                        elementKey, expectedText, actualText));

        logger.info(String.format("'%s' uyarı mesajı doğrulandı: %s", elementKey, actualText));
    }

    public void checkAmountFieldWithText(String text) {
        ssendKeys(text, "AmountText");
        clickElement("CardExpireDateText"); // focus'u değiştirmek için
    }
}