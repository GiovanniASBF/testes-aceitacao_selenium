package br.com.alura.leilao.login;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private LoginPage paginaDeLogin;

    @BeforeEach
    public void beforeEach(){
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach(){
        this.paginaDeLogin.fecharNavegador();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos(){
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
        paginaDeLogin.efetuaLogin();

        Assertions.assertFalse(paginaDeLogin.isPaginaDeLogin());
        Assertions.assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos(){
        paginaDeLogin.preencheFormularioDeLogin("invalido", "123123");
        paginaDeLogin.efetuaLogin();

        Assertions.assertTrue(paginaDeLogin.isPaginaDeLoginInvalido());
        Assertions.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
        Assertions.assertNull(paginaDeLogin.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado(){
        paginaDeLogin.navegaParaPaginaDeLances();

        Assertions.assertTrue(paginaDeLogin.isPaginaDeLogin());
        Assertions.assertFalse(paginaDeLogin.contemTexto("Dados Do Leilão"));

    }
}
