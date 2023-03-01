import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import org.example.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static org.junit.Assert.assertTrue;

public class ConstructorTest {

    MainPage mainPage;

    @Before
    public void before() {
        Configuration.startMaximized = true;
        mainPage = open(MainPage.URL, MainPage.class);
        // System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
    }

    @Test
    @Description("Проверка что есть скролл к элементу конструктора 'Булки'. Блок 'Булки' отображается.")
    public void scrollWithBurgerElementTest(){

        mainPage.fillingButtonClick();
        mainPage.bunButtonClick();
        mainPage.checkBunsClickOpen();
    }

    @Test
    @Description ("Проверка что есть скролл к элементу конструктора 'Соусы'. Блок 'Соусы' отображается.")
    public void scrollWithSauceElementTest(){

        mainPage.sauceButtonClick();
        mainPage.checkSaucesClickOpen();
    }

    @Test
    @Description ("Проверка что есть скролл к элементу конструктора 'Начинки'. Блок 'Начинки' отображается.")
    public void scrollWithFillingElementTest(){

        mainPage.fillingButtonClick();
        mainPage.checkFillingsClickOpen();
    }

    @After
    public void tearDown (){
        webdriver().driver().close();
    }
}