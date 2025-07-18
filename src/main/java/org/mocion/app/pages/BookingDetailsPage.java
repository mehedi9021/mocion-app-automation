package org.mocion.app.pages;

import io.appium.java_client.AppiumDriver;

public class BookingDetailsPage extends BasePage {
    private static final String BOOKING_DETAILS_SCREEN = "booking_details_screen";

    public BookingDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    public void clickConfirmPaymentButton() {
        scrollUntilVisible(BOOKING_DETAILS_SCREEN, "confirm_payment_button");
        click(BOOKING_DETAILS_SCREEN, "confirm_payment_button");
    }

    public BookingDetailsPage clickCancelBookingButton() {
        scrollUntilVisible(BOOKING_DETAILS_SCREEN, "cancel_booking_button");
        click(BOOKING_DETAILS_SCREEN, "cancel_booking_button");
        return this;
    }

    public void clickYesToCancelBooking() {
        click(BOOKING_DETAILS_SCREEN, "yes_to_cancel_booking_button");
    }

    public BookingDetailsPage selectSplitPayment() {
        click(BOOKING_DETAILS_SCREEN, "split_payment");
        return this;
    }

    public BookingDetailsPage selectFullPayment() {
        scrollUntilVisible(BOOKING_DETAILS_SCREEN, "full_payment");
        click(BOOKING_DETAILS_SCREEN, "full_payment");
        return this;
    }
}
