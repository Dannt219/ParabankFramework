package com.parabank.interfaces.pageUIs;

public interface BillPayPageUI {
    String PAYEE_NAME_INPUT = "name=payee.name";
    String ADDRESS_INPUT = "name=payee.address.street";
    String CITY_INPUT = "name=payee.address.city";
    String STATE_INPUT = "name=payee.address.state";
    String ZIP_CODE_INPUT = "name=payee.address.zipCode";
    String PHONE_INPUT = "name=payee.phoneNumber";
    String ACCOUNT_NUMBER_INPUT = "name=payee.accountNumber";
    String VERIFY_ACCOUNT_INPUT = "name=verifyAccount";
    String AMOUNT_INPUT = "name=amount";
    String FROM_ACCOUNT_SELECT = "name=fromAccountId";
    String SEND_PAYMENT_BUTTON = "//input[@value='Send Payment']";
    String SUCCESS_MESSAGE = "//div[@id='rightPanel']//h1";
    String PAYMENT_DETAILS = "//div[@id='rightPanel']//p";
    String PAGE_TITLE = "//div[@id='rightPanel']//h1";
}
