package io.testinium.pages;

import java.io.IOException;

public class LoginPage extends BasePage {

    public LoginPage() throws IOException {
        super();
    }

    public void login(String username, String password) {
        ssendKeys(username, "Username");
        ssendKeys(password, "Password");
        clickElement("Button");
    }
}