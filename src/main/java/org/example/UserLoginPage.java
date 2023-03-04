package org.example;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.page;

public class UserLoginPage {

    // Поле ввода "Email"
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement inputEmail;

    // Поле ввода "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement inputPassword;

    // Кнопка "Войти"
    @FindBy(how = How.XPATH, using = ".//button [text()='Войти']")
    private SelenideElement loginButton;

    // Ссылка "Войти"
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginLink;

    // Ссылка "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//a[text()='Зарегистрироваться']")
    private SelenideElement regLink;

    // Ссылка "Восстановить пароль"
    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement resetPasswordLink;

    // Кнопка "Stellar burger"
    @FindBy (how = How.XPATH, using = "//*[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement burgerButton;

    // Кнопка "Конструктор"
    @FindBy (how = How.XPATH, using = "//*[text()='Конструктор']")
    private SelenideElement constructorButton;

    // Кнопка "Выйти"
    @FindBy (how = How.XPATH,using = ".//button[text()='Выход']")
    private SelenideElement exitButton;


    @Step ("Установка значения в поле вода 'Email'")
    public UserLoginPage setEmail(String email) {
        inputEmail.shouldBe(empty).click();
        inputEmail.setValue(email);
        return this;
    }

    @Step ("Установка значения в поле вода 'Пароль'")
    public UserLoginPage setPassword(String password) {
        inputPassword.click();
        inputPassword.setValue(password);
        return this;
    }

    @Step ("Клик на кнопку 'Войти'")
    public MainPage loginButtonClick() {
        loginButton.click();
        return page(MainPage.class);
    }

    @Step ("Клик на ссылку 'Зарегистрироваться'")
    public RegistrationPage regLinkClick() {
        regLink.click();
        return page (RegistrationPage.class);
    }

    @Step ("Клик на ссылку 'Войти'")
    public UserLoginPage loginLinkClick() {
        loginLink.click();
        return this;
    }

    @Step ("Клик на ссылку 'Восстановить пароль'")
    public UserLoginPage resetPasswordLinkClick() {
        resetPasswordLink.scrollIntoView(true);
        resetPasswordLink.click();
        return this;
    }


    @Step ("Клик на кнопку 'Конструктор'")
    public UserLoginPage constructorButtonClick(){
        constructorButton.click();
        return this;
    }

    @Step ("Клик на лого 'Stellar burger'")
    public UserLoginPage stellarBurgerClick(){
        burgerButton.click();
        return this;
    }

    @Step ("Клик на кнопку 'Выход'")
    public UserLoginPage exitButtonClick(){
        exitButton.click();
        return this;
    }
}