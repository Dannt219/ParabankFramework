package com.parabank.tests.dataproviders;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "invalidLoginData")
    public static Object[][] invalidLoginData() {
        return new Object[][]{
                {"invalidUser", "invalidPass", "could not be verified"},
                {"john", "wrongPassword", "could not be verified"},
                {"nonexistent", "demo", "could not be verified"},
                {"!@#$%", "!@#$%", "could not be verified"}
        };
    }

    @DataProvider(name = "validLoginData")
    public static Object[][] validLoginData() {
        return new Object[][]{
                {"john", "demo"}
        };
    }
}
