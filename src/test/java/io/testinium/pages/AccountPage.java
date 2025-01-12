package io.testinium.pages;

import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class AccountPage extends BasePage {

    public AccountPage() throws IOException {
        super();
    }

    public void updateAccountName(String newAccountName) {
        clickElement("EditAccountButton");
        deleteText("AccountNameUpdateText");
        ssendKeys(newAccountName, "AccountNameUpdateText");
        clickElement("AccountNameUpdateButton");
    }

    public void verifyAccountName(String expectedAccountName) {
        verifyVisibilityAndClickableOfElement("AccountNameCheck", 30);
        String actualAccountName = findElement("AccountNameCheck").getText();
        Assertions.assertEquals(expectedAccountName, actualAccountName,
                String.format("Beklenen hesap adı %s, ancak görünen hesap adı %s",
                        expectedAccountName, actualAccountName));
        logger.info("Hesap adı kontrolü yapıldı: " + actualAccountName);
    }
}