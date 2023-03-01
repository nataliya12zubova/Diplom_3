package org.example;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.model.Tokens;
import org.example.model.UserRegisterResponse;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.example.Base.BASE_URL;

public class UserOperations {

    private static final String EMAIL_POSTFIX = "@yandex.ru";
    private static final String REGISTER = BASE_URL + "auth/register";
    private static final String USER = BASE_URL + "auth/user";
    private static final String LOGIN = BASE_URL +"auth/login";

    /*
     метод регистрации нового пользователя
     возвращает мапу с данными: имя, пароль, имэйл
     если регистрация не удалась, возвращает пустую мапу
     */
    @Step ("Регистрация нового пользователя")
    public Map<String, String> register() {

        // с помощью библиотеки RandomStringUtils генерируем имэйл
        // метод randomAlphabetic генерирует строку, состоящую только из букв, в качестве параметра передаём длину строки
        String email = RandomStringUtils.randomAlphabetic(10) + EMAIL_POSTFIX;
        // с помощью библиотеки RandomStringUtils генерируем пароль
        String password = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем имя пользователя
        String name = RandomStringUtils.randomAlphabetic(10);

        // создаём и заполняем мапу для передачи трех параметров в тело запроса
        Map<String, String> inputDataMap = new HashMap<>();
        inputDataMap.put("email", email);
        inputDataMap.put("password", password);
        inputDataMap.put("name", name);

        // отправляем запрос на регистрацию пользователя и десериализуем ответ в переменную response
        UserRegisterResponse response = given()
                .spec(Base.getBaseSpec())
                .and()
                .body(inputDataMap)
                .when()
                .post(REGISTER)
                .body()
                .as(UserRegisterResponse.class);

        // возвращаем мапу с данными
        Map<String, String> responseData = new HashMap<>();
        if (response != null) {
            responseData.put("email", response.getUser().getEmail());
            responseData.put("name", response.getUser().getName());
            responseData.put("password", password);

            // токен, нужный для удаления пользователя, кладем в статическое поле класса с токенами
            // убираем слово Bearer в начале токена
            // так же запоминаем refreshToken
            Tokens.setAccessToken(response.getAccessToken().substring(7));
            Tokens.setRefreshToken(response.getRefreshToken());
        }
        return responseData;
    }
    /*
     метод авторизации пользователя, после создания нового пользователя(после UI тестов), для получения токена.
     */
    @Step("Авторизация тестового пользователя для получения токена")
    public void authorizationUserForGetToken(String email, String password) {

        Map<String, String> inputDataMap = new HashMap<>();
        inputDataMap.put("email", email);
        inputDataMap.put("password", password);

        UserRegisterResponse response = given()
                .spec(Base.getBaseSpec())
                .and()
                .body(inputDataMap)
                .when()
                .post(LOGIN)
                .body()
                .as(UserRegisterResponse.class);

        if (Tokens.getAccessToken() == null) {
            return;
        }
        Tokens.setAccessToken(response.getAccessToken().substring(7));
    }

    /*
     метод удаления пользователя по токену, возвращенному после создания
     пользователя. Удаляем только в случае, если token заполнен.
     */
    @Step ("Удаление пользователя")
    public static void delete() {
        if (Tokens.getAccessToken() == null) {
            return;
        }
        given()
                .spec(Base.getBaseSpec())
                .auth().oauth2(Tokens.getAccessToken())
                .when()
                .delete(USER)
                .then()
                .statusCode(202);
    }
}
