import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.example.MainPage;
import org.example.RegistrationPage;
import org.example.UserOperations;
import org.example.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.Assert.*;

public class UserRegistrationTest {

    RegistrationPage registrationPage = page(RegistrationPage.class);
    MainPage mainPage;
    private static final String HOME= "https://stellarburgers.nomoreparties.site/";

    public static Faker faker = new Faker();

    UserOperations userOperationsValue;
    String email = faker.name().lastName() + "@yandex.ru";
    String password = faker.internet().password();
    String name = faker.name().firstName();
    String shortPassword = faker.internet().password(1, 5);


    @Before
    public void before() {
        Configuration.startMaximized = true;
        mainPage = open(MainPage.URL, MainPage.class);
        // System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
    }

    @After
    public void tearDown() {
//        UserOperations userOperations =
//                userOperationsValue == null ?
//                        new UserOperations() : userOperationsValue;
        UserOperations userOperations = new UserOperations();
        userOperations.authorizationUserForGetToken(email, password);
        UserOperations.delete();
        webdriver().driver().close();
    }

    @Test
    @Description ("Регистрация нового пользоватля. Авторизация новым пользователем")
    public void userRegistrationTest (){
        mainPage
                .clickLoginButton()
                .regLinkClick()
                .setName(name)
                .setEmail(email)
                .setPassword(password)
                .regButtonClick()
                .setEmail(email)
                .setPassword(password)
                .loginButtonClick();
        boolean buttonShow = mainPage.arrangeOrderButtonVisible();
        webdriver().shouldHave(url(HOME));
        assertTrue("Button invisible", buttonShow);
    }

    @Test
    @Description ("Попытка регистрации нового пользоватля, пароль менее 6 символов")
    public void userRegistrationIncorrectPasswordTest (){
        String expectedErrorMessage = "Некорректный пароль";
        mainPage
                .clickLoginButton()
                .regLinkClick()
                .setName(name)
                .setEmail(email)
                .setPassword(shortPassword)
                .regButtonClick();
        String actualErrorMessage = registrationPage.checkInvalidPasswordTextDisplayed();


        assertEquals(expectedErrorMessage,actualErrorMessage);
    }
}
