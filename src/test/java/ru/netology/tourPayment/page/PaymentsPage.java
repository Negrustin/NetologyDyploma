package ru.netology.tourPayment.page;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.netology.tourPayment.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PaymentsPage {




    @FindBy(xpath = "//*[text()='Номер карты']/..//input")
    SelenideElement cardNumberField;

    @FindBy(xpath = "//*[text()='Номер карты']//..//*[@class='input__sub']")
    SelenideElement cardNumberFieldSub;
    @FindBy(xpath = "//*[text()='Месяц']/..//input")
    SelenideElement monthField;

    @FindBy(xpath = "//*[text()='Месяц']//..//*[@class='input__sub']")
    SelenideElement monthFieldSub;

    @FindBy(xpath = "//*[text()='Год']/..//input")
    SelenideElement yearField;

    @FindBy(xpath = "//*[text()='Год']//..//*[@class='input__sub']")
    SelenideElement yearFieldSub;

    @FindBy(xpath = "//*[text()='Владелец']/..//input")
    SelenideElement ownerField;

    @FindBy(xpath = "//*[text()='Владелец']//..//*[@class='input__sub']")
    SelenideElement ownerFieldSub;
    @FindBy(xpath = "//*[text()='CVC/CVV']/..//input")
    SelenideElement cvvField;

    @FindBy(xpath = "//*[text()='CVC/CVV']//..//*[@class='input__sub']")
    SelenideElement cvvFieldSub;
    @FindBy(xpath = "//*[text()='Продолжить']")
    SelenideElement continueButton;

    @FindBy(css = ".notification")
    SelenideElement notification;
    @FindBy(css = ".notification_status_ok")
    SelenideElement notificationSuccess;
    @FindBy(css = ".notification_status_error")
    SelenideElement notificationFailed;
    @FindBy(css = ".notification button")
    SelenideElement notificationWindowCloseButton;




    public String shouldFiledSubMessage(String fieldText){
        return $x("//*[text()='" + fieldText +  "']//..//*[@class='input__sub']").text();
    }




    public void pay(DataHelper.CardInfo info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        ownerField.setValue(info.getOwner());
        cvvField.setValue(info.getCvv());
        continueButton.click();
    }

    public void shouldNotificationMessage(String msg) {
        notification.shouldBe(Condition.text(msg), Duration.ofSeconds(10));
        notificationWindowCloseButton.click();
        notification.shouldBe(Condition.not(Condition.visible));
    }

    public void shouldNotificationMessage(){
        notification.shouldBe((Condition.visible), Duration.ofSeconds(10));
    }

    public void badFormatNumericField(DataHelper.BadNumericFormat number){
        cardNumberField.setValue(number.getNumber());
        monthField.setValue(number.getNumber());
        yearField.setValue(number.getNumber());
        cvvField.setValue(number.getNumber());
        continueButton.click();

    }







}
