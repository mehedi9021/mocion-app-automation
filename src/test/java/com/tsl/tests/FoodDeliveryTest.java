package com.tsl.tests;

import com.tsl.enums.LoginType;
import com.tsl.pages.*;
import org.testng.annotations.Test;

public class FoodDeliveryTest extends BaseTest {
    private static final String USER_ADDRESS = "97 sohrawardi avenue";
    private static final int BKASH_PIN = 12121;

    public PermissionPage permissionPage;
    public AccountPage accountPage;
    public LoginPage loginPage;
    public HomePage homePage;
    public LocationPage locationPage;
    public BranchDetailsPage branchDetailsPage;
    public MyCartPage myCartPage;
    public CheckoutPage checkoutPage;
    public FoodCampaignPage foodCampaignPage;
    public FoodCuisinePage foodCuisinePage;
    public FoodDeliveryPage foodDeliveryPage;
    public PaymentMethodPage paymentMethodPage;
    public PopularBrandPage popularBrandPage;

    @Test
    public void verifyFoodDeliveryOrderPlaceFromCampaignShouldSucceed() throws InterruptedException {
        homePage = new HomePage(driver);
        locationPage = new LocationPage(driver);
        foodCampaignPage = new FoodCampaignPage(driver);
        branchDetailsPage = new BranchDetailsPage(driver);
        myCartPage = new MyCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        foodDeliveryPage = new FoodDeliveryPage(driver);
        paymentMethodPage = new PaymentMethodPage(driver);

        userLoginWithPhone();

        homePage
                .clickHomeIcon();
        locationPage
                .clickDeliveryTo()
                .clickAddNewAddress()
                .enterAddressToSearchField(USER_ADDRESS)
                .clickConfirmLocationButton()
                .selectLabelAsHome()
                .clickSaveAndContinueButton();
        homePage
                .clickFoodDelivery();
        foodDeliveryPage
                .clickFoodCampaign();
        foodCampaignPage
                .clickRestaurant();
        branchDetailsPage
                .selectMenu()
                .clickAddToCartButton()
                .clickViewYourCartButton();
        myCartPage
                .clickReviewPaymentAndAddressButton();
        checkoutPage
                .clickToChangePaymentMethod();
        paymentMethodPage
                .selectCashOnDelivery()
                .clickConfirmButton();
        checkoutPage
                .clickPlaceOrderButton();
    }

    @Test
    public void verifyFoodDeliveryOrderPlaceFromCuisineShouldSucceed() throws InterruptedException {
        homePage = new HomePage(driver);
        locationPage = new LocationPage(driver);
        branchDetailsPage = new BranchDetailsPage(driver);
        myCartPage = new MyCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        foodCuisinePage = new FoodCuisinePage(driver);
        foodDeliveryPage = new FoodDeliveryPage(driver);
        paymentMethodPage = new PaymentMethodPage(driver);

        userLoginWithPhone();

        homePage
                .clickHomeIcon();
        locationPage
                .clickDeliveryTo()
                .clickAddNewAddress()
                .enterAddressToSearchField(USER_ADDRESS)
                .clickConfirmLocationButton()
                .selectLabelAsHome()
                .clickSaveAndContinueButton();
        homePage
                .clickFoodDelivery();
        foodDeliveryPage
                .clickFoodCuisine();
        foodCuisinePage
                .clickRestaurant();
        branchDetailsPage
                .selectMenu()
                .clickAddToCartButton()
                .clickViewYourCartButton();
        myCartPage
                .clickReviewPaymentAndAddressButton();
        checkoutPage
                .clickToChangePaymentMethod();
        paymentMethodPage
                .selectCashOnDelivery()
                .clickConfirmButton();
        checkoutPage
                .clickPlaceOrderButton();
    }

    @Test
    public void verifyFoodDeliveryOrderPlaceFromPopularBrandShouldSucceed() throws InterruptedException {
        homePage = new HomePage(driver);
        locationPage = new LocationPage(driver);
        branchDetailsPage = new BranchDetailsPage(driver);
        myCartPage = new MyCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        foodDeliveryPage = new FoodDeliveryPage(driver);
        paymentMethodPage = new PaymentMethodPage(driver);
        popularBrandPage = new PopularBrandPage(driver);

        userLoginWithPhone();

        homePage
                .clickHomeIcon();
        locationPage
                .clickDeliveryTo()
                .clickAddNewAddress()
                .enterAddressToSearchField(USER_ADDRESS)
                .clickConfirmLocationButton()
                .selectLabelAsHome()
                .clickSaveAndContinueButton();
        homePage
                .clickFoodDelivery();
        foodDeliveryPage
                .clickPopularBrand();
        popularBrandPage
                .clickRestaurant();
        branchDetailsPage
                .selectMenu()
                .clickAddToCartButton()
                .clickViewYourCartButton();
        myCartPage
                .clickReviewPaymentAndAddressButton();
        checkoutPage
                .clickToChangePaymentMethod();
        paymentMethodPage
                .selectCashOnDelivery()
                .clickConfirmButton();
        checkoutPage
                .clickPlaceOrderButton();
    }

    @Test
    public void verifyFoodDeliveryOrderPlaceFromAllRestaurantShouldSucceed() throws InterruptedException {
        homePage = new HomePage(driver);
        locationPage = new LocationPage(driver);
        branchDetailsPage = new BranchDetailsPage(driver);
        myCartPage = new MyCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        foodDeliveryPage = new FoodDeliveryPage(driver);
        paymentMethodPage = new PaymentMethodPage(driver);

        userLoginWithPhone();

        homePage
                .clickHomeIcon();
        locationPage
                .clickDeliveryTo()
                .clickAddNewAddress()
                .enterAddressToSearchField(USER_ADDRESS)
                .clickConfirmLocationButton()
                .selectLabelAsHome()
                .clickSaveAndContinueButton();
        homePage
                .clickFoodDelivery();
        foodDeliveryPage
                .clickRestaurant();
        branchDetailsPage
                .selectMenu()
                .clickAddToCartButton()
                .clickViewYourCartButton();
        myCartPage
                .clickReviewPaymentAndAddressButton();
        checkoutPage
                .clickToChangePaymentMethod();
        paymentMethodPage
                .selectCashOnDelivery()
                .clickConfirmButton();
        checkoutPage
                .clickPlaceOrderButton();
    }

    @Test
    public void verifyOrderForSomeoneElseFromFoodDeliveryShouldSucceed() throws InterruptedException {
        homePage = new HomePage(driver);
        locationPage = new LocationPage(driver);
        branchDetailsPage = new BranchDetailsPage(driver);
        myCartPage = new MyCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        foodDeliveryPage = new FoodDeliveryPage(driver);
        paymentMethodPage = new PaymentMethodPage(driver);
        String receiversName = "Ariful Islam";
        String receiversMobileNumber = "01739819000";

        userLoginWithPhone();

        homePage
                .clickHomeIcon();
        locationPage
                .clickDeliveryTo()
                .clickAddNewAddress()
                .enterAddressToSearchField(USER_ADDRESS)
                .clickConfirmLocationButton()
                .selectLabelAsHome()
                .clickSaveAndContinueButton();
        homePage
                .clickFoodDelivery();
        foodDeliveryPage
                .clickRestaurant();
        branchDetailsPage
                .selectMenu()
                .clickAddToCartButton()
                .clickViewYourCartButton();
        myCartPage
                .clickReviewPaymentAndAddressButton();
        checkoutPage
                .clickDoItNowButton()
                .enterReceiversName(receiversName)
                .enterReceiversMobileNumber(receiversMobileNumber)
                .clickToConfirmButtonForOrderForSomeoneElse()
                .clickToChangePaymentMethod();
        paymentMethodPage
                .selectPaymentMethodBkash()
                .clickConfirmButton();
        checkoutPage
                .clickPlaceOrderButton();
        paymentMethodPage
                .fillBkashPin(BKASH_PIN)
                .clickBkashPinConfirmButton();
    }

    @Test
    public void verifyFoodDeliveryOrderPlaceWithRiderTipsShouldSucceed() throws InterruptedException {
        homePage = new HomePage(driver);
        locationPage = new LocationPage(driver);
        branchDetailsPage = new BranchDetailsPage(driver);
        myCartPage = new MyCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        foodDeliveryPage = new FoodDeliveryPage(driver);
        paymentMethodPage = new PaymentMethodPage(driver);

        userLoginWithPhone();

        homePage
                .clickHomeIcon();
        locationPage
                .clickDeliveryTo()
                .clickAddNewAddress()
                .enterAddressToSearchField(USER_ADDRESS)
                .clickConfirmLocationButton()
                .selectLabelAsHome()
                .clickSaveAndContinueButton();
        homePage
                .clickFoodDelivery();
        foodDeliveryPage
                .clickRestaurant();
        branchDetailsPage
                .selectMenu()
                .clickAddToCartButton()
                .clickViewYourCartButton();
        myCartPage
                .clickReviewPaymentAndAddressButton();
        checkoutPage
                .selectRiderTips()
                .clickToChangePaymentMethod();
        paymentMethodPage
                .selectPaymentMethodBkash()
                .clickConfirmButton();
        checkoutPage
                .clickPlaceOrderButton();
        paymentMethodPage
                .fillBkashPin(BKASH_PIN)
                .clickBkashPinConfirmButton();
    }

    @Test
    public void verifyFoodDeliveryOrderPlaceWithAdditionalInstructionsShouldSucceed() throws InterruptedException {
        homePage = new HomePage(driver);
        locationPage = new LocationPage(driver);
        branchDetailsPage = new BranchDetailsPage(driver);
        myCartPage = new MyCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        foodDeliveryPage = new FoodDeliveryPage(driver);
        paymentMethodPage = new PaymentMethodPage(driver);
        String additionalInstructionsToRestaurant = "Add some extra spices";

        userLoginWithPhone();

        homePage
                .clickHomeIcon();
        locationPage
                .clickDeliveryTo()
                .clickAddNewAddress()
                .enterAddressToSearchField(USER_ADDRESS)
                .clickConfirmLocationButton()
                .selectLabelAsHome()
                .clickSaveAndContinueButton();
        homePage
                .clickFoodDelivery();
        foodDeliveryPage
                .clickRestaurant();
        branchDetailsPage
                .selectMenu()
                .clickAddToCartButton()
                .clickViewYourCartButton();
        myCartPage
                .clickReviewPaymentAndAddressButton();
        checkoutPage
                .clickAdditionalInstructionsField()
                .enterAdditionalInstructions(additionalInstructionsToRestaurant)
                .clickSaveButton()
                .clickToChangePaymentMethod();
        paymentMethodPage
                .selectCashOnDelivery()
                .clickConfirmButton();
        checkoutPage
                .clickPlaceOrderButton();
    }

    @Test
    public void verifyFoodDeliveryOrderPlaceWithRiderRiderInstructionsShouldSucceed() throws InterruptedException {
        homePage = new HomePage(driver);
        locationPage = new LocationPage(driver);
        branchDetailsPage = new BranchDetailsPage(driver);
        myCartPage = new MyCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        foodDeliveryPage = new FoodDeliveryPage(driver);
        paymentMethodPage = new PaymentMethodPage(driver);

        userLoginWithPhone();

        homePage
                .clickHomeIcon();
        locationPage
                .clickDeliveryTo()
                .clickAddNewAddress()
                .enterAddressToSearchField(USER_ADDRESS)
                .clickConfirmLocationButton()
                .selectLabelAsHome()
                .clickSaveAndContinueButton();
        homePage
                .clickFoodDelivery();
        foodDeliveryPage
                .clickRestaurant();
        branchDetailsPage
                .selectMenu()
                .clickAddToCartButton()
                .clickViewYourCartButton();
        myCartPage
                .clickReviewPaymentAndAddressButton();
        checkoutPage
                .selectRiderInstructions()
                .clickToChangePaymentMethod();
        paymentMethodPage
                .selectCashOnDelivery()
                .clickConfirmButton();
        checkoutPage
                .clickPlaceOrderButton();
    }

    private void userLoginWithPhone() {
        permissionPage = new PermissionPage(driver);
        accountPage = new AccountPage(driver);
        loginPage = new LoginPage(driver);
        String phoneNumber = "01739819005";
        String password = "P@ssword123";

        permissionPage
                .clickAllowButton()
                .clickContinueButton()
                .clickWhileUsingAppOption();
        accountPage
                .clickAccountButton();
        loginPage
                .clickLoginButton()
                .clickLoginWithPhoneOrEmailButton(LoginType.PHONE);
        loginPage
                .enterPhoneNumber(phoneNumber)
                .enterPassword(password)
                .clickSignInWithPhoneButton();
    }
}
