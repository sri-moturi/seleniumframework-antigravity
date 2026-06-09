package com.tecskool.pages;

import com.tecskool.base.BasePage;
import com.tecskool.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragDropPage extends BasePage {

    // Locators at the top of the page class (using unique HTML IDs)
    private static final By dragItem = By.id("drag-item");
    private static final By dropTarget = By.id("drop-target");

    public DragDropPage() {
        super();
    }

    public void dragAndDropElement() {
        scrollToElement(dropTarget);
        WebElement source = WaitUtils.waitForVisibility(dragItem);
        WebElement target = WaitUtils.waitForVisibility(dropTarget);
        
        // Try standard Actions drag and drop
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
        applyDelay();
    }

    public void dragAndDropElementUsingJS() {
        scrollToElement(dropTarget);
        WebElement source = WaitUtils.waitForVisibility(dragItem);
        WebElement target = WaitUtils.waitForVisibility(dropTarget);
        
        // HTML5 drag and drop javascript workaround
        String jsScript = "const source = arguments[0];" +
                "const target = arguments[1];" +
                "const dataTransfer = new DataTransfer();" +
                "const dragStartEvent = new DragEvent('dragstart', { dataTransfer, bubbles: true });" +
                "source.dispatchEvent(dragStartEvent);" +
                "const dragEnterEvent = new DragEvent('dragenter', { dataTransfer, bubbles: true });" +
                "target.dispatchEvent(dragEnterEvent);" +
                "const dragOverEvent = new DragEvent('dragover', { dataTransfer, bubbles: true });" +
                "target.dispatchEvent(dragOverEvent);" +
                "const dropEvent = new DragEvent('drop', { dataTransfer, bubbles: true });" +
                "target.dispatchEvent(dropEvent);" +
                "const dragEndEvent = new DragEvent('dragend', { dataTransfer, bubbles: true });" +
                "source.dispatchEvent(dragEndEvent);";
        
        ((JavascriptExecutor) driver).executeScript(jsScript, source, target);
        applyDelay();
    }

    public void scrollToDragDropSection() {
        scrollToElement(dropTarget);
    }

    public boolean isDragItemInsideDropTarget() {
        // After drag & drop, the drag-item is appended as a child of the drop-target.
        // We check if the drag-item exists specifically inside drop-target.
        By innerDragItem = By.xpath("//div[@id='drop-target']/div[@id='drag-item']");
        return isDisplayed(innerDragItem);
    }

    public String getDropTargetText() {
        return getText(dropTarget);
    }
}
