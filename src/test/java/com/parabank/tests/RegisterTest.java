package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pageObjects.LoginPageObject;
import com.parabank.pageObjects.RegisterPageObject;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

@Epic("ParaBank Automation")
@Feature("Register")
public class RegisterTest extends BaseTest {

    private RegisterPageObject registerPage;

    @BeforeMethod(alwaysRun = true)
    public void navigateToRegister() {
        LoginPageObject loginPage = new LoginPageObject(getDriver());
        registerPage = loginPage.clickRegister();
    }

    @Test(groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Story("Valid Registration")
    @Description("Verify user can register a new account with valid data")
    public void testValidRegistration() {
        String uniqueUsername = "user_" + UUID.randomUUID().toString().substring(0, 8);

        registerPage.registerCustomer(
                "John",
                "Doe",
                "123 Main Street",
                "New York",
                "NY",
                "10001",
                "212-555-1234",
                "123-45-6789",
                uniqueUsername,
                "Test@1234"
        );

        String successTitle = registerPage.getSuccessTitle();
        Assert.assertTrue(
                successTitle.contains("Welcome") || successTitle.contains("welcome"),
                "Success title should contain 'Welcome', got: " + successTitle);
    }

    @Test(groups = {"regression"})
    @Severity(SeverityLevel.NORMAL)
    @Story("Duplicate Username Registration")
    @Description("Verify error when registering with an existing username")
    public void testDuplicateUsernameRegistration() {
        registerPage.registerCustomer(
                "Jane",
                "Smith",
                "456 Oak Ave",
                "Boston",
                "MA",
                "02101",
                "617-555-9876",
                "987-65-4321",
                "john",
                "demo"
        );

        // "john" already exists — should show error
        boolean hasError = registerPage.isErrorDisplayed();
        String pageText = registerPage.getSuccessTitle();
        Assert.assertTrue(
                hasError || pageText.contains("Error") || pageText.contains("error"),
                "Should show error for duplicate username");
    }

    @Test(groups = {"negative", "regression"})
    @Severity(SeverityLevel.NORMAL)
    @Story("Empty Form Registration")
    @Description("Verify validation errors when submitting empty registration form")
    public void testEmptyFormRegistration() {
        registerPage.clickRegister();

        Assert.assertTrue(registerPage.isErrorDisplayed(),
                "Validation errors should be displayed for empty form");
    }
}
