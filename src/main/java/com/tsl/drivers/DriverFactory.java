package com.tsl.drivers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.tsl.enums.PlatformType;
import com.tsl.exceptions.CustomRuntimeException;
import com.tsl.exceptions.FileNotFoundException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Map;

import static com.tsl.constants.FrameworkConstant.DEFAULT_APPIUM_URL;

public class DriverFactory {
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    // Private constructor to prevent instantiation
    private DriverFactory() {
    }

    /**
     * Initializes the AppiumDriver based on the specified platform type.
     *
     * @param platform The platform type (ANDROID or IOS).
     * @return The initialized AppiumDriver instance.
     */
    public static AppiumDriver initializeDriver(PlatformType platform) {
        DesiredCapabilities caps = new DesiredCapabilities();

        if (platform == PlatformType.ANDROID) {
            loadAndroidCapabilities(caps);
        } else {
            loadIOSCapabilities(caps);
        }

        // Remote execution settings if needed
        boolean isRemote = Boolean.parseBoolean(System.getProperty("remote", "false"));
        String appiumServerUrl = System.getProperty("appium.server.url", DEFAULT_APPIUM_URL);
        if (isRemote) {
            caps.setCapability("browserstack.user", System.getProperty("browserstack.user"));
            caps.setCapability("browserstack.key", System.getProperty("browserstack.key"));
        }

        // Initialize driver with the Appium server URL
        URL serverUrl = createServerUrl(appiumServerUrl);
        AppiumDriver appiumDriver = platform == PlatformType.ANDROID
                ? new AndroidDriver(serverUrl, caps)
                : new IOSDriver(serverUrl, caps);

        driver.set(appiumDriver);
        return appiumDriver;
    }

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void quit() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        } else {
            throw new CustomRuntimeException("Driver closed...");
        }
    }

    // ------------------- Helper Methods -------------------

    // Creates a URL instance from the provided Appium server URL string.
    private static URL createServerUrl(String appiumServerUrl) {
        try {
            return URI.create(appiumServerUrl).toURL();
        } catch (MalformedURLException e) {
            throw new CustomRuntimeException(e);
        }
    }

    // Loads Android capabilities from the JSON file.
    private static void loadAndroidCapabilities(DesiredCapabilities caps) {
        String filePath = "src/test/resources/appium/caps1.android.json";
        JsonObject capsJson = readJsonFile(filePath);

        // Load common capabilities if available.
        if (capsJson.has("common")) {
            loadCapabilitiesFromJsonObject(capsJson.getAsJsonObject("common"), caps);
        }

        // Load device-specific capabilities.
        if (capsJson.has("selected") && capsJson.has("devices")) {
            String selectedDevice = capsJson.get("selected").getAsString();
            JsonObject devices = capsJson.getAsJsonObject("devices");
            if (devices.has(selectedDevice)) {
                loadCapabilitiesFromJsonObject(devices.getAsJsonObject(selectedDevice), caps);
            } else {
                throw new CustomRuntimeException("Selected device not found in JSON: " + selectedDevice);
            }
        }
    }

    // Loads iOS capabilities from the JSON file.
    private static void loadIOSCapabilities(DesiredCapabilities caps) {
        String filePath = "src/test/resources/appium/caps.ios.json";
        JsonObject capsJson = readJsonFile(filePath);
        loadCapabilitiesFromJsonObject(capsJson, caps);
    }

    // Reads and parses a JSON file from the given file path.
    private static JsonObject readJsonFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            throw new FileNotFoundException("Capabilities file error: " + filePath, e);
        }
    }

    // Loads capabilities from a JsonObject into DesiredCapabilities.
    private static void loadCapabilitiesFromJsonObject(JsonObject jsonObject, DesiredCapabilities caps) {
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            JsonElement value = entry.getValue();
            if (value.isJsonPrimitive()) {
                JsonPrimitive primitive = value.getAsJsonPrimitive();
                if (primitive.isBoolean()) {
                    caps.setCapability(entry.getKey(), primitive.getAsBoolean());
                } else if (primitive.isNumber()) {
                    caps.setCapability(entry.getKey(), primitive.getAsNumber());
                } else {
                    caps.setCapability(entry.getKey(), primitive.getAsString());
                }
            }
        }
    }
}