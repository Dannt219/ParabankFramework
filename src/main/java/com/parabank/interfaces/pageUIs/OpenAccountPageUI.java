package com.parabank.interfaces.pageUIs;

public interface OpenAccountPageUI {
    String ACCOUNT_TYPE_SELECT = "id=type";
    String FROM_ACCOUNT_SELECT = "id=fromAccountId";
    String OPEN_ACCOUNT_BUTTON = "//input[@value='Open New Account']";
    String SUCCESS_MESSAGE = "//div[@id='rightPanel']//h1";
    String NEW_ACCOUNT_ID = "id=newAccountId";
    String PAGE_TITLE = "//div[@id='rightPanel']//h1";
}
