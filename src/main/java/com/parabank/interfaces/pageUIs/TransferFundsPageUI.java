package com.parabank.interfaces.pageUIs;

public interface TransferFundsPageUI {
    String AMOUNT_INPUT = "id=amount";
    String FROM_ACCOUNT_SELECT = "id=fromAccountId";
    String TO_ACCOUNT_SELECT = "id=toAccountId";
    String TRANSFER_BUTTON = "//input[@value='Transfer']";
    String SUCCESS_MESSAGE = "//div[@id='rightPanel']//h1[contains(., 'Complete')]";
    String TRANSFER_DETAILS = "//div[@id='rightPanel']//p[contains(., 'transferred')]";
    String ERROR_MESSAGE = "//div[@id='rightPanel']//p[@class='error'] | //div[@id='rightPanel']//*[contains(@class,'error')]";
    String PAGE_TITLE = "//div[@id='rightPanel']//h1";
}
