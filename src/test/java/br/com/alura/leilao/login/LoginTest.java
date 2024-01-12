package br.com.alura.leilao.login;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private WebDriver browser;
    private static final String URL_LOGIN = "http://localhost:8080/login";

    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    }

    @BeforeEach
    public void beforeEach(){
        this.browser = new ChromeDriver();
        browser.navigate().to(URL_LOGIN);
    }

    @AfterEach
    public void afterEach(){
        this.browser.quit();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos(){
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
        Assertions.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos(){
        browser.findElement(By.id("username")).sendKeys("invalido");
        browser.findElement(By.id("password")).sendKeys("123123");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
        Assertions.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        Assertions.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
    }
}
