package ru.netology.tourPayment.test;

import org.junit.jupiter.api.*;
import ru.netology.tourPayment.data.DataHelper;
import ru.netology.tourPayment.data.SQLHelper;
import ru.netology.tourPayment.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class PaymentHappyPathTest extends BaseTest{



    @Test
    @DisplayName("Полный цикл покупки разрешённой картой : статус 'APPROVED'")
    void fullCyclePaymentByApprovedCard(){
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentOnCardPage = mainPage.paymentByCard();

        paymentOnCardPage.pay(DataHelper.getApprovedCardInfo());
        paymentOnCardPage.shouldNotificationMessage();

        var statusCode = SQLHelper.getPaymentByCardStatusCode();
        var paymentAmount = SQLHelper.getPaymentAmount();
        var transactionId = SQLHelper.getPaymentByCardTransactionId();
        var paymentId = SQLHelper.getPaymentIdFromOrders();




        Assertions.assertEquals("APPROVED",statusCode);
        Assertions.assertEquals(4500000,paymentAmount);
        Assertions.assertEquals(transactionId,paymentId);
    }

    @Test
    @DisplayName("Полный цикл покупки разрешённой картой : статус 'DECLINED'")
    void fullCyclePaymentByDeclinedCard(){
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentOnCardPage = mainPage.paymentByCard();

        paymentOnCardPage.pay(DataHelper.getDeclinedCardInfo());
        paymentOnCardPage.shouldNotificationMessage();

        var statusCode = SQLHelper.getPaymentByCardStatusCode();
        var paymentAmount = SQLHelper.getPaymentAmount();
        var transactionId = SQLHelper.getPaymentByCardTransactionId();
        var paymentId = SQLHelper.getPaymentIdFromOrders();


        Assertions.assertEquals("DECLINED",statusCode);
        Assertions.assertEquals(4500000,paymentAmount);
        Assertions.assertEquals(transactionId,paymentId);
    }

}
