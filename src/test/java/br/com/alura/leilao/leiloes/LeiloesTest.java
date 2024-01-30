package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeiloesTest {

    private LeiloesPage paginaDeLeiloes;


    @AfterEach
    public void afterEach(){
        this.paginaDeLeiloes.fecharNavegador();
    }

    @Test
    public void deveriaCadastrarLeilao(){
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
        this.paginaDeLeiloes = paginaDeLogin.efetuaLogin();
        CadastroLeilaoPage paginaDeCadastro = paginaDeLeiloes.carregarFormulario();

        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia" + data;
        String valor = "500.00";

        this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome, valor, data);
        Assert.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valor, data));
    }

}
