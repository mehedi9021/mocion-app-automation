package com.tsl.utils;

import com.tsl.exceptions.CustomRuntimeException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.tsl.drivers.DriverFactory.getDriver;

public class ReportUtils {
    private ReportUtils() {
        // Prevent instantiation
    }

    public static void captureScreenshot(String fileName) {
        File srcFile = getDriver().getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = System.getProperty("user.dir") + "/screenshots/" + fileName + "_" + timestamp + ".png";

        try {
            FileUtils.copyFile(srcFile, new File(filePath));
        } catch (IOException e) {
            throw new CustomRuntimeException("Error capturing screenshot: " + e.getMessage());
        }
    }
}