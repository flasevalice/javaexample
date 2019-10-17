package ru.stqa.Task19;

import org.junit.jupiter.api.Test;

public class AddProductsAndClearCart extends TestBase {
    @Test
    public void mainTest()  {
        app.mainPage.open();
        for (int i = 1; i<=3; i++) {
            app.mainPage.chooseFirstItem();
            app.productPage.addProductToCart();
            app.productPage.goToMainPage();
        }
        app.cartPage.open();
        app.cartPage.clearCart();
        app.stop();
    }
}