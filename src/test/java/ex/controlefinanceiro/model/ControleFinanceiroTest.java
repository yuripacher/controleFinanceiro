/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ex.controlefinanceiro.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author MKB e YPR
 */
public class ControleFinanceiroTest {

    private ControleFinanceiro controle;
    private LocalDate data;

    public ControleFinanceiroTest() {
    }

    @BeforeEach
    public void setup() {
        controle = new ControleFinanceiro(new MockGerenciadorArquivos());
        data = LocalDate.of(2025, 6, 15);
    }

    public class MockGerenciadorArquivos extends GerenciadorArquivos {

        @Override
        public void adicionarReceita(Receita receita) {
            // não faz nada
        }

        @Override
        public void adicionarDespesa(Despesa despesa) {
            // não faz nada
        }

        @Override
        public List<Receita> carregarReceitas() {
            return new ArrayList<>();
        }

        @Override
        public List<Despesa> carregarDespesas() {
            return new ArrayList<>();
        }
    }

    @Test
    public void testeIncluirReceitaValida() {

        boolean resultado = controle.incluirReceita(100.0, LocalDate.of(2025, 6, 15), CategoriasReceitas.SALARIO);

        assertTrue(resultado);
        assertEquals(1, controle.getReceitas().size());
    }
    
    @Test
    public void testeIncluirReceitaComValorNegativo() {

        boolean resultado = controle.incluirReceita(-100.0, LocalDate.of(2025, 6, 15), CategoriasReceitas.SALARIO);

        assertFalse(resultado);
        assertEquals(0, controle.getReceitas().size());
    }
    
     @Test
    public void testeIncluirReceitaComValorZero() {
        boolean resultado = controle.incluirReceita(0.0, data, CategoriasReceitas.SALARIO);
        assertFalse(resultado);
        assertEquals(0, controle.getReceitas().size());
    }

    @Test
    public void testeIncluirReceitaComDataNula() {
        boolean resultado = controle.incluirReceita(100.0, null, CategoriasReceitas.SALARIO);
        assertFalse(resultado);
        assertEquals(0, controle.getReceitas().size());
    }

    @Test
    public void testeIncluirDespesaValida() {
        boolean resultado = controle.incluirDespesa(50.0, LocalDate.of(2025, 6, 15), CategoriasDespesas.ALIMENTACAO);

        assertTrue(resultado);
        assertEquals(1, controle.getDespesas().size());
    }
    
    @Test
    public void testeIncluirDespesaComValorNegativo() {
        boolean resultado = controle.incluirDespesa(-50.0, LocalDate.of(2025, 6, 15), CategoriasDespesas.ALIMENTACAO);

        assertFalse(resultado);
        assertEquals(0, controle.getDespesas().size());
    }
    
     @Test
    public void testeIncluirDespesaComValorZero() {
        boolean resultado = controle.incluirDespesa(0.0, data, CategoriasDespesas.ALIMENTACAO);
        assertFalse(resultado);
        assertEquals(0, controle.getDespesas().size());
    }
     
    @Test
    public void testeIncluirDespesaComDataNula() {
        boolean resultado = controle.incluirDespesa(50.0, null, CategoriasDespesas.ALIMENTACAO);
        assertFalse(resultado);
        assertEquals(0, controle.getDespesas().size());
    }

    @Test
    public void testeConsultarSaldo() {
        controle.incluirReceita(500.0, LocalDate.of(2025, 6, 1), CategoriasReceitas.SALARIO);
        controle.incluirDespesa(150.0, LocalDate.of(2025, 6, 2), CategoriasDespesas.ALIMENTACAO);

        double resultado = controle.consultarSaldo();
        double esperado = 350.0;
        float tolerancia = 0.01f;

        assertEquals(esperado, resultado, tolerancia);
    }

    @Test
    public void testeGerarExtratoComLancamentos() {
        controle.incluirReceita(100.0, LocalDate.of(2025, 6, 10), CategoriasReceitas.SALARIO);
        controle.incluirDespesa(50.0, LocalDate.of(2025, 6, 11), CategoriasDespesas.ALIMENTACAO);

        List<String> extrato = controle.gerarExtrato(LocalDate.of(2025, 6, 9), LocalDate.of(2025, 6, 12));

        assertEquals(2, extrato.size());
        System.out.println(extrato);
        assertTrue(extrato.get(0).contains("RECEITA - "));
        assertTrue(extrato.get(1).contains("DESPESA - "));
    }
    
    @Test
    public void testeGerarExtratoVazio() {
        List<String> extrato = controle.gerarExtrato(LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 30));
        assertTrue(extrato.isEmpty());
    }
    
    @Test
    public void testeGerarExtratoComPeriodoSemLancamentos() {
        controle.incluirReceita(100.0, LocalDate.of(2025, 5, 1), CategoriasReceitas.SALARIO);
        controle.incluirDespesa(50.0, LocalDate.of(2025, 5, 2), CategoriasDespesas.ALIMENTACAO);

        List<String> extrato = controle.gerarExtrato(LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 30));
        assertTrue(extrato.isEmpty());
    }

    @Test
    public void testeTotalReceitasDespesas() {
        controle.incluirReceita(200.0, LocalDate.of(2025, 6, 1), CategoriasReceitas.SALARIO);
        controle.incluirReceita(300.0, LocalDate.of(2025, 6, 2), CategoriasReceitas.OUTRAS_RECEITAS);

        controle.incluirDespesa(100.0, LocalDate.of(2025, 6, 3), CategoriasDespesas.ENTRETENIMENTO);

        assertEquals(500.0, controle.getValorTotalReceitas(), 0.01);
        assertEquals(100.0, controle.getValorTotalDespesas(), 0.01);
    }

    @Test
    public void testeListarTodosLancamentosOrdenado() {
        controle.incluirDespesa(100, LocalDate.of(2025, 6, 20), CategoriasDespesas.SAUDE);
        controle.incluirReceita(200, LocalDate.of(2025, 6, 10), CategoriasReceitas.SALARIO);

        List<Lancamento> lancamentos = controle.listarTodosLancamentos();

        assertEquals(2, lancamentos.size());
        assertTrue(lancamentos.get(0).getData().isBefore(lancamentos.get(1).getData()));
    }

}
