package com.qa.locators;

public class LoginLocator {
    protected String USEREMAIL_NAME, NEXT_BTN_ID, PASSWORD_NAME, SIGN_IN_BTN_ID;

    public LoginLocator() {
        // TODO Auto-generated constructor stub
        USEREMAIL_NAME = "username";
        NEXT_BTN_ID = "idp-discovery-submit";
        PASSWORD_NAME = "password";
        SIGN_IN_BTN_ID = "okta-signin-submit";
    }
}
