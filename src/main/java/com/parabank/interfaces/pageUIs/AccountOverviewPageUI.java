package com.parabank.interfaces.pageUIs;

public interface AccountOverviewPageUI {
    String PAGE_TITLE = "//div[@id='rightPanel']//h1";
    String ACCOUNTS_TABLE = "id=accountTable";
    String ACCOUNT_LINKS = "//table[@id='accountTable']//a";
    String LOGOUT_LINK = "link=Log Out";
    String WELCOME_MESSAGE = "//div[@id='leftPanel']/p";
    String TRANSFER_FUNDS_LINK = "link=Transfer Funds";
    String BILL_PAY_LINK = "link=Bill Pay";
    String FIND_TRANSACTIONS_LINK = "link=Find Transactions";
    String REQUEST_LOAN_LINK = "link=Request Loan";
    String OPEN_ACCOUNT_LINK = "link=Open New Account";
    String UPDATE_CONTACT_LINK = "link=Update Contact Info";
    String ACCOUNTS_OVERVIEW_LINK = "link=Accounts Overview";
}
