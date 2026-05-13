package com.parabank.interfaces.pageUIs;

public interface RequestLoanPageUI {
    String LOAN_AMOUNT_INPUT = "id=amount";
    String DOWN_PAYMENT_INPUT = "id=downPayment";
    String FROM_ACCOUNT_SELECT = "id=fromAccountId";
    String APPLY_BUTTON = "//input[@value='Apply Now']";
    String LOAN_STATUS = "id=loanStatus";
    String RESULT_MESSAGE = "//div[@id='rightPanel']/h1";
    String APPROVED_MESSAGE = "id=loanStatus";
    String DENIED_MESSAGE = "//div[@id='rightPanel']//p[@class='error'] | //div[@id='rightPanel']//*[@id='loanRequestDenied']";
    String NEW_ACCOUNT_ID = "id=newAccountId";
    String PAGE_TITLE = "//div[@id='rightPanel']/h1";
}
