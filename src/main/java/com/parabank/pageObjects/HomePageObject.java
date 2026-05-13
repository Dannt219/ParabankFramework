package com.parabank.pageObjects;

import com.parabank.base.BasePage;
import com.parabank.interfaces.pageUIs.HomePageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends BasePage {

    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Check if logo is displayed")
    public boolean isLogoDisplayed() {
        return isElementDisplayed(driver, HomePageUI.LOGO);
    }

    @Step("Check if Customer Login title is displayed")
    public boolean isCustomerLoginTitleDisplayed() {
        return isElementDisplayed(driver, HomePageUI.CUSTOMER_LOGIN_TITLE);
    }

    @Step("Click ATM Services link")
    public void clickAtmServicesLink() {
        clickToElement(driver, HomePageUI.ATM_SERVICES_LINK);
    }

    @Step("Click Online Services link")
    public void clickOnlineServicesLink() {
        clickToElement(driver, HomePageUI.ONLINE_SERVICES_LINK);
    }

    @Step("Click Home link in footer")
    public void clickFooterHomeLink() {
        clickToElement(driver, HomePageUI.FOOTER_HOME_LINK);
    }

    @Step("Click About Us link in footer")
    public void clickFooterAboutLink() {
        clickToElement(driver, HomePageUI.FOOTER_ABOUT_LINK);
    }

    @Step("Click Contact Us link in footer")
    public void clickFooterContactLink() {
        clickToElement(driver, HomePageUI.FOOTER_CONTACT_LINK);
    }
}
