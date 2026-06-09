package com.tecskool.pages;

import com.tecskool.base.BasePage;
import org.openqa.selenium.By;

/**
 * Page Object Model for the Web Data Table section.
 * Provides actions to interact with the table rows and edit actions.
 */
public class WebTablePage extends BasePage {

    // Locators for the Web Data Table section
    private static final By tableCard = By.id("table-card");
    private static final By tableTitle = By.id("table-title");
    private static final By rowDavidBrown = By.id("row-5");
    private static final By rowMikeJohnson = By.id("row-3");
    private static final By editButtonDavidBrown = By.cssSelector("#actions-5 button");
    private static final By editButtonMikeJohnson = By.cssSelector("#actions-3 button");
    private static final By editModalTitle = By.cssSelector(".modal .modal-title");

    public WebTablePage() {
        super();
    }

    /** Scrolls the page so that the data table card is in view. */
    public void scrollToWebTableSection() {
        scrollToElement(tableCard);
    }

    /** Checks whether the data table card container is displayed. */
    public boolean isWebTableCardDisplayed() {
        return isDisplayed(tableCard);
    }

    /** Returns the title text of the web data table section. */
    public String getWebTableTitleText() {
        return getText(tableTitle);
    }

    /** Clicks the "Edit" button for the David Brown row (row 5). */
    public void clickEditButtonForDavidBrown() {
        scrollToElement(rowDavidBrown);
        click(editButtonDavidBrown);
    }

    /** Clicks the "Edit" button for the Mike Johnson row (row 4). */
    public void clickEditButtonForMikeJohnson() {
        scrollToElement(rowMikeJohnson);
        click(editButtonMikeJohnson);
    }

    /**
     * Returns the table row WebElement that contains the given person name.
     */
    public org.openqa.selenium.WebElement getRowByName(String personName) {
        // XPath selects the <tr> whose <td> matches the exact name
        return driver.findElement(By.xpath("//tr[td[text()='" + personName + "']]") );
    }

    /**
     * Clicks the Edit button inside the provided row element.
     */
    public void clickEditButtonForRow(org.openqa.selenium.WebElement row) {
        // Scroll the row into view using JavaScript since BasePage.scrollToElement expects a By locator
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", row);
        // Assuming the edit button is the first <button> inside the actions cell
        org.openqa.selenium.WebElement editBtn = row.findElement(By.cssSelector("button"));
        editBtn.click();
        // Apply configured step delay after the click action
        applyDelay();
    }
    
    /**
     * Clicks the Edit button for the row containing the given person name and returns its row index.
     */
    public int editRowByName(String personName) {
        org.openqa.selenium.WebElement row = getRowByName(personName);
        int rowNumber = getRowNumberFromElement(row);
        clickEditButtonForRow(row);
        return rowNumber;
    }

    /**
     * Extracts the numeric row index from a row element's id attribute (e.g., "row-3" -> 3).
     */
    public int getRowNumberFromElement(org.openqa.selenium.WebElement row) {
        String id = row.getAttribute("id");
        return Integer.parseInt(id.replaceAll("[^0-9]", ""));
    }

    public String getEditModalTitle() {
        try {
            org.openqa.selenium.Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            alert.accept();
            return alertText;
        } catch (org.openqa.selenium.NoAlertPresentException e) {
            return getText(editModalTitle);
        }
    }
}
