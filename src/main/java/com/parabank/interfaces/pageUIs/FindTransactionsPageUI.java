package com.parabank.interfaces.pageUIs;

public interface FindTransactionsPageUI {
    String ACCOUNT_SELECT = "id=accountId";
    String FIND_BY_ID_INPUT = "id=criteria.transactionId";
    String FIND_BY_ID_BUTTON = "//button[@ng-click='criteria.searchType = \"ID\"'] | //div[@id='rightPanel']//button[1]";
    String FIND_BY_DATE_INPUT = "id=criteria.onDate";
    String FIND_BY_DATE_BUTTON = "//button[@ng-click='criteria.searchType = \"DATE\"'] | //div[@id='rightPanel']//button[2]";
    String FIND_BY_DATE_RANGE_FROM_INPUT = "id=criteria.fromDate";
    String FIND_BY_DATE_RANGE_TO_INPUT = "id=criteria.toDate";
    String FIND_BY_DATE_RANGE_BUTTON = "//button[@ng-click='criteria.searchType = \"DATE_RANGE\"'] | //div[@id='rightPanel']//button[3]";
    String FIND_BY_AMOUNT_INPUT = "id=criteria.amount";
    String FIND_BY_AMOUNT_BUTTON = "//button[@ng-click='criteria.searchType = \"AMOUNT\"'] | //div[@id='rightPanel']//button[4]";
    String TRANSACTION_RESULTS = "//table[@id='transactionTable']//tbody/tr";
    String NO_RESULTS_MESSAGE = "//div[@id='rightPanel']//p[@class='error'] | //div[@id='rightPanel']//*[contains(@class,'error')]";
    String PAGE_TITLE = "//div[@id='rightPanel']/h1";
}
