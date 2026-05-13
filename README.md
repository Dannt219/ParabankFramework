# ParaBank Selenium Test Automation Framework

A production-grade UI test automation framework for the [ParaBank](https://parabank.parasoft.com/parabank/index.htm) demo banking application, built with **Java 17**, **Selenium WebDriver 4**, **TestNG**, and **Allure**. Designed around the Page Object Model with a clean separation between locators, page actions, and test logic.

[![Java](https://img.shields.io/badge/Java-17-007396?logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/17/)
[![Selenium](https://img.shields.io/badge/Selenium-4.18-43B02A?logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.9-orange)](https://testng.org/)
[![Allure](https://img.shields.io/badge/Allure-2.25-yellow)](https://allurereport.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?logo=apache-maven&logoColor=white)](https://maven.apache.org/)

---

## Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Test Coverage](#test-coverage)
- [Key Features](#key-features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Running Tests](#running-tests)
- [Test Reporting](#test-reporting)
- [Configuration](#configuration)
- [Coding Conventions](#coding-conventions)
- [Roadmap](#roadmap)
- [Author](#author)

---

## Overview

This framework automates end-to-end functional and regression tests for ParaBank, covering core banking flows such as user registration, authentication, fund transfers, bill payments, and loan requests. It is structured to be **scalable**, **maintainable**, and **CI-friendly**, demonstrating modern QA automation practices suitable for enterprise projects.

**Highlights**

- 30+ automated test cases across 7 functional areas
- Page Object Model with **dedicated UI locator interfaces** (separation of concerns)
- Thread-safe parallel execution across Chrome and Firefox
- Data-driven testing via JSON + TestNG `DataProvider`
- Rich Allure reports with severity, epics, features, stories, and failure screenshots
- Headless execution ready for CI pipelines

---

## Tech Stack

| Category          | Technology                                  |
| ----------------- | ------------------------------------------- |
| Language          | Java 17                                     |
| Automation        | Selenium WebDriver 4.18                     |
| Test Runner       | TestNG 7.9                                  |
| Build Tool        | Maven 3.9+                                  |
| Reporting         | Allure 2.25                                 |
| Logging           | Log4j2 2.22                                 |
| Driver Management | WebDriverManager 5.7                        |
| Data Parsing      | Jackson Databind 2.16                       |
| Aspect Weaving    | AspectJ 1.9 (Allure step interception)      |
| Browsers          | Chrome, Firefox (headless + headed)         |

---

## Architecture

The framework follows a **layered Page Object Model** with strict separation between *what* a page looks like (locators), *what it does* (actions), and *what it verifies* (tests):

```
┌────────────────────────────────────────────────────────────┐
│  Test Layer  (testng-*.xml + *Test.java)                   │
│  - Assertions, test data, Allure annotations               │
└────────────────────────────────────────────────────────────┘
                          │
                          ▼
┌────────────────────────────────────────────────────────────┐
│  Page Object Layer  (pageObjects/*PageObject.java)         │
│  - Business actions (loginAs, transferFunds, payBill...)   │
│  - Returns next page object for fluent chaining            │
└────────────────────────────────────────────────────────────┘
                          │
                          ▼
┌────────────────────────────────────────────────────────────┐
│  UI Locator Layer  (interfaces/pageUIs/*PageUI.java)       │
│  - Constants only: XPath / CSS / id locators               │
└────────────────────────────────────────────────────────────┘
                          │
                          ▼
┌────────────────────────────────────────────────────────────┐
│  Framework Core  (base/, utils/, constants/)               │
│  - BasePage, BaseTest                                      │
│  - DriverFactory (ThreadLocal), ConfigReader, WaitHelper   │
│  - ScreenshotUtil, FrameworkConstants                      │
└────────────────────────────────────────────────────────────┘
```

**Why locator interfaces?**
Storing locators in dedicated `*PageUI` interfaces (e.g. `LoginPageUI`) keeps page object classes focused on actions, eliminates locator duplication, and makes UI maintenance trivial when the application markup changes.

---

## Project Structure

```
ProjectCv/
├── pom.xml
├── README.md
├── .gitignore
└── src/
    ├── main/java/com/parabank/
    │   ├── base/
    │   │   ├── BasePage.java          # Reusable Selenium wrappers
    │   │   └── BaseTest.java          # @BeforeMethod / @AfterMethod hooks
    │   ├── constants/
    │   │   └── FrameworkConstants.java
    │   ├── interfaces/pageUIs/        # Locator-only interfaces
    │   │   ├── LoginPageUI.java
    │   │   ├── HomePageUI.java
    │   │   ├── RegisterPageUI.java
    │   │   ├── AccountOverviewPageUI.java
    │   │   ├── TransferFundsPageUI.java
    │   │   ├── BillPayPageUI.java
    │   │   ├── RequestLoanPageUI.java
    │   │   ├── OpenAccountPageUI.java
    │   │   └── FindTransactionsPageUI.java
    │   ├── pageObjects/               # Page actions
    │   │   ├── LoginPageObject.java
    │   │   ├── HomePageObject.java
    │   │   ├── RegisterPageObject.java
    │   │   ├── AccountOverviewPageObject.java
    │   │   ├── TransferFundsPageObject.java
    │   │   ├── BillPayPageObject.java
    │   │   ├── RequestLoanPageObject.java
    │   │   ├── OpenAccountPageObject.java
    │   │   └── FindTransactionsPageObject.java
    │   └── utils/
    │       ├── DriverFactory.java     # ThreadLocal driver pool
    │       ├── ConfigReader.java      # config.properties loader
    │       ├── WaitHelper.java        # Explicit wait wrappers
    │       └── ScreenshotUtil.java
    └── test/
        ├── java/com/parabank/tests/
        │   ├── LoginTest.java
        │   ├── RegisterTest.java
        │   ├── AccountOverviewTest.java
        │   ├── TransferFundsTest.java
        │   ├── BillPayTest.java
        │   ├── RequestLoanTest.java
        │   ├── FailDemoTest.java
        │   └── dataproviders/
        │       ├── LoginDataProvider.java
        │       └── TransferDataProvider.java
        └── resources/
            ├── config/config.properties
            ├── testdata/
            │   ├── login_data.json
            │   └── transfer_data.json
            ├── log4j2.xml
            ├── allure.properties
            ├── testng-smoke.xml
            └── testng-regression.xml
```

---

## Test Coverage

| Module             | Test Class               | Scenarios                                                                 |
| ------------------ | ------------------------ | ------------------------------------------------------------------------- |
| Authentication     | `LoginTest`              | Valid login · Invalid credentials (DDT) · Empty fields · Logout            |
| User Onboarding    | `RegisterTest`           | New user registration · Field validation · Duplicate user                  |
| Account Overview   | `AccountOverviewTest`    | Account list rendering · Balance display · Account drill-down              |
| Fund Transfer      | `TransferFundsTest`      | Successful transfer · Insufficient funds · Same-account transfer (DDT)     |
| Bill Payment       | `BillPayTest`            | Pay bill end-to-end · Required field validation                            |
| Loan Application   | `RequestLoanTest`        | Approved loan · Denied loan based on amount/down-payment                   |
| Negative Scenarios | (cross-module)           | Tagged with `@Test(groups = "negative")` for targeted runs                 |

Tests are tagged into the following TestNG groups for selective execution:

- `smoke` — fast critical-path subset
- `regression` — full suite
- `negative` — failure-path scenarios

---

## Key Features

- **Parallel cross-browser execution** — `parallel="tests"` with `thread-count="2"` in the TestNG suite runs Chrome and Firefox in parallel.
- **Thread-safe WebDriver** — `ThreadLocal<WebDriver>` in `DriverFactory` prevents driver collisions across parallel threads.
- **Data-Driven Testing** — Test data isolated in JSON files (`src/test/resources/testdata/`) and fed via TestNG `@DataProvider`.
- **Automatic screenshot on failure** — `BaseTest.tearDown()` captures and attaches screenshots to Allure when a test fails.
- **Rich Allure annotations** — `@Epic`, `@Feature`, `@Story`, `@Severity`, `@Description` produce business-readable reports.
- **Externalised configuration** — Browser, base URL, credentials, and timeouts in `config.properties`; overridable from CLI.
- **Multi-locator strategy** — `BasePage` resolves `xpath`, `css=`, `id=`, `name=`, `class=`, and `link=` prefixes automatically.
- **Structured logging** — Log4j2 with separate appenders for console and rolling file output.

---

## Prerequisites

- **JDK 17** or newer
- **Maven 3.9+**
- **Google Chrome** and/or **Mozilla Firefox** installed locally
  *(Selenium Manager / WebDriverManager handles driver binaries automatically)*

Verify your setup:

```bash
java -version    # should print 17.x
mvn -version     # should print 3.9.x
```

---

## Getting Started

```bash
# 1. Clone
git clone https://github.com/<your-username>/parabank-selenium-framework.git
cd parabank-selenium-framework

# 2. Install dependencies
mvn clean install -DskipTests

# 3. Run the smoke suite
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng-smoke.xml
```

---

## Running Tests

### Smoke suite (fast)

```bash
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng-smoke.xml
```

### Full regression suite

```bash
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng-regression.xml
```

### Run a single test class

```bash
mvn clean test -Dtest=LoginTest
```

### Run a single test method

```bash
mvn clean test -Dtest=LoginTest#testValidLogin
```

### Run with a specific browser

```bash
mvn clean test -Dbrowser=firefox
```

### Headless mode (CI)

Set `headless=true` in `config.properties`, or override via CLI:

```bash
mvn clean test -Dheadless=true
```

---

## Test Reporting

This framework ships with **Allure** for interactive HTML reports.

### Generate & open the report

```bash
mvn allure:serve
```

This spins up a local web server and opens the report in your default browser, including:

- Test execution timeline and trends
- Severity-based grouping (Blocker / Critical / Normal / Minor)
- Step-by-step execution logs
- Attached screenshots for failed tests
- Categorised features, stories, and epics

### Generate static HTML (for CI artifact upload)

```bash
mvn allure:report
# Output: target/site/allure-maven-plugin/index.html
```

---

## Configuration

All runtime parameters live in `src/test/resources/config/config.properties`:

```properties
# ParaBank Test Configuration
base.url=https://parabank.parasoft.com/parabank/index.htm
browser=chrome
headless=false

# Credentials
username=john
password=demo

# Timeouts (seconds)
explicit.wait=15
implicit.wait=10
page.load.timeout=30

# Reporting
screenshot.on.failure=true
```

Any property can be overridden at runtime, e.g.:

```bash
mvn test -Dbrowser=firefox -Dheadless=true -Dbase.url=https://staging.example.com
```

---

## Coding Conventions

- **One `*PageUI` interface per page** — locators only, no logic.
- **One `*PageObject` class per page** — exposes business actions; never exposes `WebElement` to tests.
- **Fluent navigation** — page actions that navigate return the resulting page object (e.g. `LoginPageObject.loginAs(...)` returns `AccountOverviewPageObject`).
- **No `Thread.sleep`** — use `WaitHelper` (explicit waits) only.
- **Test classes are thin** — they orchestrate page objects and assert outcomes; never call Selenium APIs directly.
- **Allure annotations are mandatory** on every test (`@Epic`, `@Feature`, `@Story`, `@Severity`, `@Description`).

---

## Roadmap

- [ ] GitHub Actions CI pipeline with Allure published to GitHub Pages
- [ ] Dockerised execution via Selenium Grid (`docker-compose.yml`)
- [ ] REST API layer using RestAssured for faster test data setup
- [ ] Retry mechanism for flaky tests (`IRetryAnalyzer`)
- [ ] Multi-environment configuration profiles (dev / staging / prod)
- [ ] Listener-based automatic screenshot + log attachment

---

## Author

**TienDan**
Email: Dannt.219@gmail.com

Built as a portfolio project to demonstrate end-to-end QA automation engineering practices.
