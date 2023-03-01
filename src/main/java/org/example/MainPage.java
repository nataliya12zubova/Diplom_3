package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    // URL страницы
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    // Кнопка "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = ".//button [text()='Войти в аккаунт']")
    public SelenideElement loginButton;

    // Кнопка "Личный кабинет"
    @FindBy (how = How.XPATH, using = "//* [@href='/account']")
    private SelenideElement cabinetButton;

    // Кнопка "Конструктор"
    @FindBy (how = How.XPATH, using = "//*[text()='Конструктор']")
    private SelenideElement constructorButton;

    // Кнопка "Stellar burger"
    @FindBy (how = How.XPATH, using = "//*[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement burgerButton;

    // Кнопка "Булки"
    @FindBy (how = How.XPATH, using = ".//span [@class='text text_type_main-default'][text()='Булки']")
    private SelenideElement bunButton;

    // Кнопка "Соусы"
    @FindBy (how = How.XPATH, using = ".//span [@class='text text_type_main-default'][text()='Соусы']")
    private SelenideElement sauceButton;

    // Кнопка "Начинки"
    @FindBy (how = How.XPATH, using = ".//span [@class='text text_type_main-default'][text()='Начинки']")
    private SelenideElement fillingButton;

    // Кнопка "Оформить заказ"
    @FindBy (how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement arrangeOrder;

    // Блок "Булки" в конструкторе
    @FindBy (how = How.XPATH, using = ".//div/ul[1]")
    public SelenideElement burgerBlock;

    // Блок "Соусы" в конструкторе
    @FindBy (how = How.XPATH, using = ".//div/ul[2]")
    public SelenideElement sauceBlock;

    // Блок "Начинки" в конструкторе
    @FindBy (how = How.XPATH, using = ".//div/ul[3]")
    public SelenideElement fillingBlock;

    // Клик по кнопке "Войти в аккаунт"
    public UserLoginPage clickLoginButton (){
        loginButton.click();
        return page (UserLoginPage.class);
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public UserLoginPage clickCabinetButton(){
        cabinetButton.click();
        return page (UserLoginPage.class);
    }

    @Step ("Клик на кнопку 'Булки'")
    public MainPage bunButtonClick(){
        bunButton.click();
        return page(MainPage.class);
    }
    @Step("Проверка видимости раздела 'Булки'")
    public boolean checkBunsClickOpen() {
        return Objects.requireNonNull(burgerBlock.getAttribute("class")).contains("tab_tab__1SPyG tab_tab_type_current__2BEPc");
    }

    @Step ("Клик на кнопку 'Соусы'")
    public MainPage sauceButtonClick(){
        sauceButton.click();
        return page(MainPage.class);
    }

    @Step("Проверка видимости раздела Соусы")
    public boolean checkSaucesClickOpen(){
        return Objects.requireNonNull(sauceBlock.getAttribute("class")).contains("tab_tab__1SPyG tab_tab_type_current__2BEPc");
    }

    @Step ("Клик на кнопку 'Начинки'")
    public MainPage fillingButtonClick(){
        fillingButton.click();
        return page(MainPage.class);
    }

    @Step("Проверка видимости раздела Начинки")
    public boolean checkFillingsClickOpen(){
        return Objects.requireNonNull(fillingBlock.getAttribute("class")).contains("tab_tab__1SPyG tab_tab_type_current__2BEPc");
    }

    // Отображение кнопки "Оформить заказ"
    public boolean arrangeOrderButtonVisible (){
        arrangeOrder.is(Condition.visible);
        return true;
    }
}
