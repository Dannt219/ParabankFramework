package com.parabank.constants;

public final class FrameworkConstants {

    private FrameworkConstants() {
        // Prevent instantiation
    }

    // Timeouts (seconds)
    public static final int EXPLICIT_WAIT_TIMEOUT = 30;
    public static final int PAGE_LOAD_TIMEOUT = 60;

    // Paths
    public static final String CONFIG_PATH = "src/test/resources/config/config.properties";
    public static final String SCREENSHOTS_PATH = "target/screenshots/";
    public static final String TEST_DATA_PATH = "src/test/resources/testdata/";

    // Browser constants
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";

    // ParaBank URLs
    public static final String LOGIN_URL = "/parabank/index.htm";
    public static final String REGISTER_URL = "/parabank/register.htm";
    public static final String ACCOUNTS_OVERVIEW_URL = "/parabank/overview.htm";
    public static final String TRANSFER_FUNDS_URL = "/parabank/transfer.htm";
    public static final String BILL_PAY_URL = "/parabank/billpay.htm";
    public static final String FIND_TRANSACTIONS_URL = "/parabank/findtrans.htm";
    public static final String REQUEST_LOAN_URL = "/parabank/requestloan.htm";
    public static final String OPEN_ACCOUNT_URL = "/parabank/openaccount.htm";
    public static final String UPDATE_PROFILE_URL = "/parabank/updateprofile.htm";
}
