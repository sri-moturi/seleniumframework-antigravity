# Selenium Antigravity Automation Framework

A production-ready, clean, and multi-browser Java Selenium automation framework using Maven, TestNG, WebDriverManager, Log4j2, and Extent Reports following the Page Object Model (POM) design pattern.

This project was developed using an AI-assisted approach with Antigravity to accelerate framework creation, boilerplate generation, and project setup. The framework was structured, configured, and enhanced to demonstrate industry-standard Selenium automation practices and robust framework design.

---

## Folder Structure

```text
Selenium Antigravity
├── logs
├── screenshots
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── tecskool
│   │               ├── base
│   │               │   └── BasePage.java
│   │               ├── factory
│   │               │   └── DriverFactory.java
│   │               ├── pages
│   │               │   ├── AlertsPage.java
│   │               │   ├── AutoSuggestionPage.java
│   │               │   ├── ButtonControlsPage.java
│   │               │   ├── DragDropPage.java
│   │               │   ├── DropdownPage.java
│   │               │   ├── FileUploadPage.java
│   │               │   ├── HoverPage.java
│   │               │   ├── IFramePage.java
│   │               │   ├── LoginPage.java
│   │               │   ├── PersonalInfoPage.java
│   │               │   ├── SelectionControlsPage.java
│   │               │   ├── SliderPage.java
│   │               │   ├── TabNavigationPage.java
│   │               │   ├── TogglePage.java
│   │               │   ├── WebTablePage.java
│   │               │   └── WindowTabControlPage.java
│   │               ├── reports
│   │               │   └── ExtentReportManager.java
│   │               └── utils
│   │                   ├── ConfigReader.java
│   │                   ├── WaitUtils.java
│   │                   └── ScreenshotUtils.java
│   │
│   └── test
│       ├── java
│       │   └── com
│       │       └── tecskool
│       │           ├── base
│       │           │   └── BaseTest.java
│       │           ├── listeners
│       │           │   └── TestListener.java
│       │           └── tests
│       │               ├── AlertsPageTest.java
│       │               ├── AutoSuggestionPageTest.java
│       │               ├── ButtonControlsPageTest.java
│       │               ├── DragDropPageTest.java
│       │               ├── DropdownPageTest.java
│       │               ├── FileUploadPageTest.java
│       │               ├── HoverPageTest.java
│       │               ├── IFramePageTest.java
│       │               ├── LoginTest.java
│       │               ├── PersonalInfoPageTest.java
│       │               ├── SelectionControlsPageTest.java
│       │               ├── SliderPageTest.java
│       │               ├── TabNavigationPageTest.java
│       │               ├── TogglePageTest.java
│       │               ├── WebTablePageTest.java
│       │               └── WindowTabControlPageTest.java
│       │
│       └── resources
│           ├── config
│           │   └── config.properties
│           ├── downloads
│           │   └── 500px-White_tern_%28Gygis_alba_candida%29_in_flight_Rarotonga_2.jpg
│           ├── log4j2.xml
│           └── sample.txt
│
├── target
├── test-output
│   └── ExtentReports
│       └── ExtentReport.html
│
├── pom.xml
├── testng.xml
└── README.md
```

---

## Technology Stack

* Java 17
* Selenium WebDriver
* TestNG
* Maven
* WebDriverManager
* Extent Reports
* Log4j2

---

## Framework Features

* Page Object Model (POM)
* Reusable BasePage implementation
* DriverFactory for centralized browser management
* Multi-browser support

  * Chrome
  * Edge
  * Firefox
* Explicit Wait utilities
* Screenshot capture on failures
* Extent Reports integration
* TestNG Listener implementation
* Log4j2 logging
* Maven-based execution
* Configurable framework settings via properties files

---

## Configuration

Framework configuration is managed through:

```text
src/test/resources/config/config.properties
```

Example:

```properties
baseUrl=https://practice.tecskool.com/
browser=chrome
implicitWait=10
explicitWait=15
```

---

## Running Tests

Run all tests:

```bash
mvn clean test
```

---

## Reports

After execution, Extent Reports are generated under:

```text
test-output/ExtentReports/
```

Main report:

```text
test-output/ExtentReports/ExtentReport.html
```

---

## Screenshots

Failure screenshots are automatically captured and stored under:

```text
screenshots/
```

---

## Logging

Execution logs are generated using Log4j2 and stored under:

```text
logs/
```

---

## Current Test Coverage

The framework is designed to support automation for the Tecskool Practice Application, including:

* Login and Logout
* Personal Information Forms
* Radio Buttons
* Checkboxes
* Dropdowns
* Multi-Select Controls
* Auto Suggestions
* Button Actions
* Drag and Drop
* Alerts and Popups
* Hover Menus
* Slider Controls
* Toggle Switches
* File Upload
* Window and Tab Handling
* Web Tables
* Tab Navigation
* iFrame Handling

---

## Design Principles

The framework follows:

* Page Object Model (POM)
* Separation of Test Logic and Page Logic
* Reusable Utility Components
* Centralized Driver Management
* Clean Code Practices
* Maintainable and Scalable Framework Design

---

## AI-Assisted Development

This framework was created using Antigravity AI to accelerate framework setup, component generation, and project scaffolding.

AI-generated components were reviewed, validated, organized, and customized to demonstrate Selenium framework architecture, automation design patterns, and software engineering best practices.

---

## Generated Artifacts

The following folders are automatically generated during test execution:

```text
logs/
screenshots/
target/
test-output/
```

These folders contain execution artifacts and may be excluded from source control using `.gitignore`.

---

## Author

Sri Moturi
