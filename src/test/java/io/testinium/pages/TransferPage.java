package io.testinium.pages;

import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class TransferPage extends BasePage {

    public TransferPage() throws IOException {
        super();
    }

    public void initiateTransfer() {
        clickElement("TransferMoneyButton");
        logger.info("Para transfer ekranı açıldı");
    }

    public void enterTransferAmount(String amount) {
        deleteText("TransferMoneyAmountText");
        ssendKeys(amount, "TransferMoneyAmountText");
        logger.info("Transfer miktarı girildi: " + amount);
    }

    public void clickSendButton() {
        clickElement("SendButton");
        logger.info("Transfer gönder butonuna tıklandı");
    }

    public void verifyTransferAmount(String expectedAmount) {
        verifyVisibilityAndClickableOfElement("AmountCheck", 30);
        String actualAmount = findElement("AmountCheck").getText();
        Assertions.assertEquals(expectedAmount, actualAmount,
                String.format("Transfer için beklenen miktar %s, ancak görünen miktar %s",
                        expectedAmount, actualAmount));
        logger.info("Transfer miktarı kontrolü yapıldı: " + actualAmount);
    }
}