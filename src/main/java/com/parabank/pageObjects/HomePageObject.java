package com.parabank.pageObjects;

import com.parabank.base.BasePage;
import com.parabank.interfaces.pageUIs.HomePageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends BasePage {

    public HomePageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Check if logo is displayed")
    public boolean isLogoDisplayed() {
        return isElementDisplayed(HomePageUI.LOGO);
    }

    @Step("Check if Customer Login title is displayed")
    public boolean isCustomerLoginTitleDisplayed() {
        return isElementDisplayed(HomePageUI.CUSTOMER_LOGIN_TITLE);
    }

    @Step("Click ATM Services link")
    public void clickAtmServicesLink() {
        clickToElement(HomePageUI.ATM_SERVICES_LINK);
    }

    @Step("Click Online Services link")
    public void clickOnlineServicesLink() {
        clickToElement(HomePageUI.ONLINE_SERVICES_LINK);
    }

    @Step("Click Home link in footer")
    public void clickFooterHomeLink() {
        clickToElement(HomePageUI.FOOTER_HOME_LINK);
    }

    @Step("Click About Us link in footer")
    public void clickFooterAboutLink() {
        clickToElement(HomePageUI.FOOTER_ABOUT_LINK);
    }

    @Step("Click Contact Us link in footer")
    public void clickFooterContactLink() {
        clickToElement(HomePageUI.FOOTER_CONTACT_LINK);
    }
}
