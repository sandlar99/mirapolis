package demo.test;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;


public class MiraTest {
    public static Page page;
    public static WebDriver driver;


    /**
     * осуществление первоначальной настройки
     */
    @BeforeClass
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", Properties.getProperty("chromedriver"));
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        page = new Page(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 1 сек.
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Before
    public void initTests(){
        // Переход на страницу входа
        driver.get(Properties.getProperty("Mira"));
    }

    @After
    public void dismissTests() {
        // Удаляем куки для сброса аутентификации
        driver.manage().deleteAllCookies();
    }

    @Test
    public void loginTest() throws InterruptedException {
        //вводим логин
        page.inputLogin(Properties.getProperty("Login"));
        //вводим пароль
        page.inputPasswd(Properties.getProperty("Password"));
        //нажимаем кнопку входа
        page.clickLogin();

        //Проверяем что имеется кнопка "Выйти"
        Assert.assertTrue(page.hasLogout());
    }

    @Test
    public void wrongCredsTest() throws InterruptedException {
        //вводим логин
        page.inputLogin(Properties.getProperty("Login"));
        //вводим неверный пароль
        page.inputPasswd(Properties.getProperty("WrongPassword"));
        //нажимаем кнопку входа
        page.clickLogin();

        //Закрываем алерт с сообщением о том что введены не верные данные
        driver.switchTo().alert().dismiss();
        //Проверяем что поля логин и пароль подсветились красным
        Assert.assertTrue(page.isLoginError());
    }

    @AfterClass
    public static void close() {
       driver.quit();
    }
}