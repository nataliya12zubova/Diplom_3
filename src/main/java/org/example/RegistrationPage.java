package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {

    // Поле ввода "Имя"
    @FindBy(how = How.XPATH, using = ".//fieldset[1]//input")
    private SelenideElement inputName;

    // Поле ввода "Email"
    @FindBy(how = How.XPATH, using = ".//fieldset[2]//input")
    private SelenideElement inputEmail;

    // Поле ввода "Пароль"
    @FindBy(how = How.XPATH, using = ".//fieldset[3]//input")
    private SelenideElement inputPassword;

    // Кнопка "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement regButton;

    // Сообщение "Некрорректный пароль"
    @FindBy(how = How.XPATH, using = ".//*[@class='input__error text_type_main-default']")
    private SelenideElement passErrorMessage;

    // Ссылка "Войти"
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginLink;

    @Step ("Установка значения в поле вода 'Имя'")
    public RegistrationPage setName(String name) {
        inputName.click();
        inputName.setValue(name);
        return this;
    }

    @Step ("Установка значения в поле вода 'Email'")
    public RegistrationPage setEmail(String email) {
        inputEmail.click();
        inputEmail.setValue(email);
        return this;
    }

    @Step ("Установка значения в поле вода 'Пароль'")
    public RegistrationPage setPassword(String password) {
        inputPassword.click();
        inputPassword.setValue(password);
        return this;
    }

    @Step ("Клик на кнопку 'Регистрация'")
    public UserLoginPage regButtonClick() {
        regButton.click();
        return page(UserLoginPage.class);
    }

    @Step ("Клик на кнопку 'Логин'")
    public UserLoginPage loginLinkClick() {
        loginLink.click();
        return page(UserLoginPage.class);
    }


    @Step ("Сообщение об ошибке при неккоректном пароле")
    public String getPassErrorMessageText() {
        passErrorMessage.shouldBe(Condition.visible);
        return passErrorMessage.getText();
    }
}