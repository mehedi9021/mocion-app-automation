package com.tsl.drivers;

import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilitiesFactory {
    public static DesiredCapabilities getCapabilitiesForVersion(String androidVersion) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        /*switch (androidVersion) {
            *//*case "9.0":
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_3");
                capabilities.setCapability(MobileCapabilityType.APP, "path/to/app_v9.apk");
                break;

            case "10.0":
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_4");
                capabilities.setCapability(MobileCapabilityType.APP, "path/to/app_v10.apk");
                break;

            case "11.0":
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_5");
                capabilities.setCapability(MobileCapabilityType.APP, "path/to/app_v11.apk");
                break;*//*

            default:
                throw new IllegalArgumentException("Unsupported Android version: " + androidVersion);
        }*/

        return capabilities;
    }
}

