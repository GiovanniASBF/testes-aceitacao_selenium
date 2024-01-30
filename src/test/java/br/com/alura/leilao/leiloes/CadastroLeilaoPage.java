package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPage {

    private WebDriver browser;

    public CadastroLeilaoPage(WebDriver browser) {
        this.browser = browser;
    }

    public void fecharNavegador(){
        this.browser.quit();
    }

    public LeiloesPage cadastrarLeilao(String nome, String valorInicial, String dataAbertura) {
        this.browser.findElement(By.id("nome")).sendKeys(nome);
        this.browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
        this.browser.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
        this.browser.findElement(By.id("button-submit")).submit();

        return new LeiloesPage(browser);
    }

    public boolean isMensagensDeValidacaoVisiveis() {
        String PageSource = browser.getPageSource();
        return PageSource.contains("minimo 3 caracteres")
                && PageSource.contains("não deve estar em branco")
                && PageSource.contains("deve ser um valor maior de 0.1")
                && PageSource.contains("deve ser uma data no formato dd/MM/yyyy");
    }
}
