package com.tsl.pages;

import com.google.gson.JsonObject;
import com.tsl.constants.FrameworkConstant;
import com.tsl.utils.LocatorManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class BasePage {
    protected AppiumDriver driver;
    private static final JsonObject locators;

    static {
        // Load locators from the singleton
        locators = LocatorManager.getLocators();
    }

    /**
     * Constructs a new BasePage instance.
     *
     * @param driver The AppiumDriver instance to use for interacting with the app.
     * @throws RuntimeException If the locator file cannot be found or loaded.
     */
    public BasePage(AppiumDriver driver) {
        this.driver = driver;
    }

    public final void click(String screen, String element) {
        WebElement webElement = findElement(screen, element);
        webElement.click();
    }

    public final void type(String screen, String element, String value) {
        WebElement webElement = findElement(screen, element);
        webElement.sendKeys(value);
    }

    /**
     * Retrieves the locator for a given screen and element.
     *
     * @param screen  The screen name where the element is located.
     * @param element The element name to find.
     * @return The locator for the specified element.
     * @throws IllegalArgumentException If the screen or element is not found, or if the locator format is invalid.
     */
    protected By getLocator(String screen, String element) {
        if (!locators.has(screen)) {
            throw new IllegalArgumentException("Screen '" + screen + "' not found in locators file.");
        }

        JsonObject screenLocators = locators.getAsJsonObject(screen);
        if (!screenLocators.has(element)) {
            throw new IllegalArgumentException("Element '" + element + "' not found in screen '" + screen + "'.");
        }

        String locator = screenLocators.get(element).getAsString();
        String[] parts = locator.split(":", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid locator format: " + locator);
        }

        String strategy = parts[0].trim();
        String value = parts[1].trim();

        return switch (strategy.toLowerCase()) {
            case "ui" -> AppiumBy.androidUIAutomator(value);
            case "id" -> AppiumBy.id(value);
            case "xpath" -> By.xpath(value);
            case "accessibility_id" -> new AppiumBy.ByAccessibilityId(value);
            case "name" -> AppiumBy.name(value);
            default -> throw new IllegalArgumentException("Invalid locator strategy: " + strategy);
        };
    }

    /**
     * Waits for a web element to be visible on the screen.
     *
     * @param locator The locator of the web element to wait for.
     * @return The located WebElement once it is visible.
     */
    private WebElement waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstant.WAIT_STRATEGY));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Finds a web element on the screen by its locator.
     *
     * @param screen  The screen name where the element is located.
     * @param element The element name to find.
     * @return The located WebElement.
     */
    protected WebElement findElement(String screen, String element) {
        if (element.equals("add_menu")) {
            System.out.println(screen + " " + element);
        }
        return waitForElement(getLocator(screen, element));
    }

    public void clear(String screen, String locator) {
        findElement(screen, locator).clear();
    }

    public String getText(String screen, String locator) {
        return findElement(screen, locator).getText();
    }

    protected String getTitle() {
        return driver.getTitle();
    }

    public void scroll(String screen, String element) {
        while (true) {
            try {
                WebElement el = findElement(screen, element);
                if (el.isDisplayed()) {
                    System.out.println("Element found: " + element);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Element not in view, scrolling...");
            }

            boolean isEndOfScroll = isEndOfScrollableContent();
            if (isEndOfScroll) {
                System.out.println("Reached the end of scrollable content. Element not found: " + element);
                break;
            }
        }
    }

    private void swipeUp() {
        var size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    private boolean isEndOfScrollableContent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        return wait.until(driver -> {
            String beforeScroll = driver.getPageSource();
            swipeUp();
            String afterScroll = driver.getPageSource();
            assert beforeScroll != null;
            return beforeScroll.equals(afterScroll);
        });
    }
}
