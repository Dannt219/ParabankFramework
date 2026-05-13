package com.parabank.interfaces.pageUIs;

public interface RegisterPageUI {
    String FIRST_NAME_INPUT = "id=customer.firstName";
    String LAST_NAME_INPUT = "id=customer.lastName";
    String ADDRESS_INPUT = "id=customer.address.street";
    String CITY_INPUT = "id=customer.address.city";
    String STATE_INPUT = "id=customer.address.state";
    String ZIP_CODE_INPUT = "id=customer.address.zipCode";
    String PHONE_INPUT = "id=customer.phoneNumber";
    String SSN_INPUT = "id=customer.ssn";
    String USERNAME_INPUT = "id=customer.username";
    String PASSWORD_INPUT = "id=customer.password";
    String CONFIRM_PASSWORD_INPUT = "id=repeatedPassword";
    String REGISTER_BUTTON = "//input[@value='Register']";
    String SUCCESS_MESSAGE = "//div[@id='rightPanel']//h1";
    String WELCOME_MESSAGE = "//div[@id='rightPanel']//p";
    String ERROR_MESSAGES = "//span[@class='error']";
}
