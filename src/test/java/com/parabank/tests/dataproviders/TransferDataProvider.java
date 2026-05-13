package com.parabank.tests.dataproviders;

import org.testng.annotations.DataProvider;

public class TransferDataProvider {

    @DataProvider(name = "transferAmounts")
    public static Object[][] transferAmounts() {
        return new Object[][]{
                {"10.00", true},
                {"100.00", true},
                {"0.01", true},
                {"500.00", true}
        };
    }

    @DataProvider(name = "invalidTransferAmounts")
    public static Object[][] invalidTransferAmounts() {
        return new Object[][]{
                {"0", false},
                {"-100", false},
                {"abc", false}
        };
    }
}
