package demo.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Page {
    private WebDriver driver;

    // Конструктор класса, занимающийся инициализацией полей класса
    public Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Определение поля для логина
    @FindBy(xpath = "//*[ @name = 'user' and contains(@class,'mira-widget-login-input mira-default-login-page-text-input')]")
    private WebElement Login;

    // Определение локатора поля пароля пользователя
    @FindBy(xpath = "//*[ @name = 'password' and contains(@class,'mira-widget-login-input mira-default-login-page-text-input')]")
    private WebElement Password;

    // Определение локатора кнопки входа в аккаунт
    @FindBy(xpath = "//*[contains(@class, 'mira-widget-login-button mira-default-login-page-button-submit')]")
    private WebElement LogButton;

    // Метод для ввода логина
    public void inputLogin(String login) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        Login.sendKeys(login);
    }

    // Метод для ввода пароля
    public void inputPasswd(String passwd) {
        Password.sendKeys(passwd);
    }

    // Метод для нажатия на кнопку "Войти"
    public void clickLogin() {
        LogButton.click();
    }

    // Метод для определения кнопки "Выйти"
    public boolean hasLogout() {
        return elementExistsByXpath("//*[contains(@class, 'mira-user-info-logout')]");
    }

    // Метод для определения ошибки при аутентификации
    public boolean isLoginError() {
        return elementExistsByXpath("//*[ @id = 'login_form_panel' and contains(@class,'invalid')]");
    }

    // Если количество элементов не равна 0,возвращаем true
    private boolean elementExistsByXpath(String xpath) {
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        return elements.size() != 0;
    }
}
