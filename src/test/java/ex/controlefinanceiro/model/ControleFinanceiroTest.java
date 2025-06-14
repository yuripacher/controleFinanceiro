/**
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package ex.controlefinanceiro.model;

/*import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe de testes unitários para ControleFinanceiro
 * 
 * @author Testes Automatizados
 */
/*public class ControleFinanceiroTest {
    
    private ControleFinanceiro controle;
    private LocalDate dataAtual;
    
    @BeforeEach
    void setUp() {
        controle = new ControleFinanceiro();
        dataAtual = LocalDate.now();
    }
    
    // ==================== TESTES DO CONSTRUTOR ====================
    
    @Test
    @DisplayName("Deve inicializar com listas vazias")
    void deveInicializarComListasVazias() {
        assertEquals(0, controle.getTotalReceitas());
        assertEquals(0, controle.getTotalDespesas());
        assertEquals(0.0, controle.consultarSaldo());
        assertTrue(controle.listarReceitas().isEmpty());
        assertTrue(controle.listarDespesas().isEmpty());
    }
    
    // ==================== TESTES DE INCLUIR RECEITA ====================
    
    @Test
    @DisplayName("Deve incluir receita válida com sucesso")
    void deveIncluirReceitaValidaComSucesso() {
        boolean resultado = controle.incluirReceita("Salário", 5000.0, dataAtual, CategoriasReceitas.SALARIO);
        
        assertTrue(resultado);
        assertEquals(1, controle.getTotalReceitas());
        assertEquals(5000.0, controle.getValorTotalReceitas());
    }
    
    @Test
    @DisplayName("Não deve incluir receita com descrição nula")
    void naoDeveIncluirReceitaComDescricaoNula() {
        boolean resultado = controle.incluirReceita(null, 1000.0, dataAtual, CategoriasReceitas.SALARIO);
        
        assertFalse(resultado);
        assertEquals(0, controle.getTotalReceitas());
    }
    
    @Test
    @DisplayName("Não deve incluir receita com descrição vazia")
    void naoDeveIncluirReceitaComDescricaoVazia() {
        boolean resultado = controle.incluirReceita("", 1000.0, dataAtual, CategoriasReceitas.SALARIO);
        
        assertFalse(resultado);
        assertEquals(0, controle.getTotalReceitas());
    }
    
    @Test
    @DisplayName("Não deve incluir receita com descrição apenas espaços")
    void naoDeveIncluirReceitaComDescricaoApenasEspacos() {
        boolean resultado = controle.incluirReceita("   ", 1000.0, dataAtual, CategoriasReceitas.SALARIO);
        
        assertFalse(resultado);
        assertEquals(0, controle.getTotalReceitas());
    }
    
    @Test
    @DisplayName("Não deve incluir receita com valor zero")
    void naoDeveIncluirReceitaComValorZero() {
        boolean resultado = controle.incluirReceita("Receita", 0.0, dataAtual, CategoriasReceitas.SALARIO);
        
        assertFalse(resultado);
        assertEquals(0, controle.getTotalReceitas());
    }
    
    @Test
    @DisplayName("Não deve incluir receita com valor negativo")
    void naoDeveIncluirReceitaComValorNegativo() {
        boolean resultado = controle.incluirReceita("Receita", -100.0, dataAtual, CategoriasReceitas.SALARIO);
        
        assertFalse(resultado);
        assertEquals(0, controle.getTotalReceitas());
    }
    
    @Test
    @DisplayName("Não deve incluir receita com data nula")
    void naoDeveIncluirReceitaComDataNula() {
        boolean resultado = controle.incluirReceita("Receita", 1000.0, null, CategoriasReceitas.SALARIO);
        
        assertFalse(resultado);
        assertEquals(0, controle.getTotalReceitas());
    }
    
    @Test
    @DisplayName("Deve remover espaços da descrição da receita")
    void deveRemoverEspacosDaDescricaoDaReceita() {
        controle.incluirReceita("  Salário Mensal  ", 5000.0, dataAtual, CategoriasReceitas.SALARIO);
        
        List<Receita> receitas = controle.listarReceitas();
        assertEquals("Salário Mensal", receitas.get(0).getDescricao());
    }
    
    // ==================== TESTES DE INCLUIR DESPESA ====================
    
    @Test
    @DisplayName("Deve incluir despesa válida com sucesso")
    void deveIncluirDespesaValidaComSucesso() {
        boolean resultado = controle.incluirDespesa("Aluguel", 1200.0, dataAtual, CategoriasDespesas.MORADIA);
        
        assertTrue(resultado);
        assertEquals(1, controle.getTotalDespesas());
        assertEquals(1200.0, controle.getValorTotalDespesas());
    }
    
    @Test
    @DisplayName("Não deve incluir despesa com descrição nula")
    void naoDeveIncluirDespesaComDescricaoNula() {
        boolean resultado = controle.incluirDespesa(null, 500.0, dataAtual, CategoriasDespesas.MORADIA);
        
        assertFalse(resultado);
        assertEquals(0, controle.getTotalDespesas());
    }
    
    @Test
    @DisplayName("Não deve incluir despesa com descrição vazia")
    void naoDeveIncluirDespesaComDescricaoVazia() {
        boolean resultado = controle.incluirDespesa("", 500.0, dataAtual, CategoriasDespesas.MORADIA);
        
        assertFalse(resultado);
        assertEquals(0, controle.getTotalDespesas());
    }
    
    @Test
    @DisplayName("Não deve incluir despesa com valor zero")
    void naoDeveIncluirDespesaComValorZero() {
        boolean resultado = controle.incluirDespesa("Despesa", 0.0, dataAtual, CategoriasDespesas.MORADIA);
        
        assertFalse(resultado);
        assertEquals(0, controle.getTotalDespesas());
    }
    
    @Test
    @DisplayName("Não deve incluir despesa com valor negativo")
    void naoDeveIncluirDespesaComValorNegativo() {
        boolean resultado = controle.incluirDespesa("Despesa", -50.0, dataAtual, CategoriasDespesas.MORADIA);
        
        assertFalse(resultado);
        assertEquals(0, controle.getTotalDespesas());
    }
    
    @Test
    @DisplayName("Não deve incluir despesa com data nula")
    void naoDeveIncluirDespesaComDataNula() {
        boolean resultado = controle.incluirDespesa("Despesa", 500.0, null, CategoriasDespesas.MORADIA);
        
        assertFalse(resultado);
        assertEquals(0, controle.getTotalDespesas());
    }
    
    // ==================== TESTES DE CONSULTAR SALDO ====================
    
    @Test
    @DisplayName("Deve calcular saldo zero quando não há receitas nem despesas")
    void deveCalcularSaldoZeroQuandoNaoHaReceitasNemDespesas() {
        assertEquals(0.0, controle.consultarSaldo());
    }
    
    @Test
    @DisplayName("Deve calcular saldo positivo quando receitas > despesas")
    void deveCalcularSaldoPositivoQuandoReceitasMaiorQueDespesas() {
        controle.incluirReceita("Salário", 5000.0, dataAtual, CategoriasReceitas.SALARIO);
        controle.incluirDespesa("Aluguel", 1200.0, dataAtual, CategoriasDespesas.MORADIA);
        
        assertEquals(3800.0, controle.consultarSaldo());
    }
    
    @Test
    @DisplayName("Deve calcular saldo negativo quando despesas > receitas")
    void deveCalcularSaldoNegativoQuandoDespesasMaiorQueReceitas() {
        controle.incluirReceita("Freelance", 800.0, dataAtual, CategoriasReceitas.FREELANCE);
        controle.incluirDespesa("Aluguel", 1200.0, dataAtual, CategoriasDespesas.MORADIA);
        
        assertEquals(-400.0, controle.consultarSaldo());
    }
    
    @Test
    @DisplayName("Deve calcular saldo com múltiplas receitas e despesas")
    void deveCalcularSaldoComMultiplasReceitasEDespesas() {
        // Adicionar múltiplas receitas
        controle.incluirReceita("Salário", 5000.0, dataAtual, CategoriasReceitas.SALARIO);
        controle.incluirReceita("Freelance", 1500.0, dataAtual, CategoriasReceitas.FREELANCE);
        controle.incluirReceita("Investimentos", 200.0, dataAtual, CategoriasReceitas.INVESTIMENTOS);
        
        // Adicionar múltiplas despesas
        controle.incluirDespesa("Aluguel", 1200.0, dataAtual, CategoriasDespesas.MORADIA);
        controle.incluirDespesa("Alimentação", 800.0, dataAtual, CategoriasDespesas.ALIMENTACAO);
        controle.incluirDespesa("Transporte", 300.0, dataAtual, CategoriasDespesas.TRANSPORTE);
        
        // Total receitas: 6700.0
        // Total despesas: 2300.0
        // Saldo esperado: 4400.0
        assertEquals(4400.0, controle.consultarSaldo());
    }
    
    // ==================== TESTES DE LISTAGEM ====================
    
    @Test
    @DisplayName("Deve listar receitas vazias inicialmente")
    void deveListarReceitasVaziasInicialmente() {
        List<Receita> receitas = controle.listarReceitas();
        assertTrue(receitas.isEmpty());
    }
    
    @Test
    @DisplayName("Deve listar despesas vazias inicialmente")
    void deveListarDespesasVaziasInicialmente() {
        List<Despesa> despesas = controle.listarDespesas();
        assertTrue(despesas.isEmpty());
    }
    
    @Test
    @DisplayName("Deve retornar cópia defensiva da lista de receitas")
    void deveRetornarCopiaDefensivaDaListaDeReceitas() {
        controle.incluirReceita("Salário", 5000.0, dataAtual, CategoriasReceitas.SALARIO);
        
        List<Receita> receitas1 = controle.listarReceitas();
        List<Receita> receitas2 = controle.listarReceitas();
        
        assertNotSame(receitas1, receitas2);
        assertEquals(receitas1.size(), receitas2.size());
    }
    
    @Test
    @DisplayName("Deve retornar cópia defensiva da lista de despesas")
    void deveRetornarCopiaDefensivaDaListaDeDespesas() {
        controle.incluirDespesa("Aluguel", 1200.0, dataAtual, CategoriasDespesas.MORADIA);
        
        List<Despesa> despesas1 = controle.listarDespesas();
        List<Despesa> despesas2 = controle.listarDespesas();
        
        assertNotSame(despesas1, despesas2);
        assertEquals(despesas1.size(), despesas2.size());
    }
    
    // ==================== TESTES DE REMOÇÃO ====================
    
    @Test
    @DisplayName("Deve remover receita existente")
    void deveRemoverReceitaExistente() {
        controle.incluirReceita("Salário", 5000.0, dataAtual, CategoriasReceitas.SALARIO);
        List<Receita> receitas = controle.listarReceitas();
        Receita receita = receitas.get(0);
        
        boolean resultado = controle.removerReceita(receita);
        
        assertTrue(resultado);
        assertEquals(0, controle.getTotalReceitas());
        assertEquals(0.0, controle.getValorTotalReceitas());
    }
    
    @Test
    @DisplayName("Deve remover despesa existente")
    void deveRemoverDespesaExistente() {
        controle.incluirDespesa("Aluguel", 1200.0, dataAtual, CategoriasDespesas.MORADIA);
        List<Despesa> despesas = controle.listarDespesas();
        Despesa despesa = despesas.get(0);
        
        boolean resultado = controle.removerDespesa(despesa);
        
        assertTrue(resultado);
        assertEquals(0, controle.getTotalDespesas());
        assertEquals(0.0, controle.getValorTotalDespesas());
    }
    
    @Test
    @DisplayName("Não deve remover receita inexistente")
    void naoDeveRemoverReceitaInexistente() {
        Receita receitaInexistente = new Receita("Inexistente", 100.0, dataAtual, CategoriasReceitas.SALARIO);
        
        boolean resultado = controle.removerReceita(receitaInexistente);
        
        assertFalse(resultado);
    }
    
    @Test
    @DisplayName("Não deve remover despesa inexistente")
    void naoDeveRemoverDespesaInexistente() {
        Despesa despesaInexistente = new Despesa("Inexistente", 100.0, dataAtual, CategoriasDespesas.MORADIA);
        
        boolean resultado = controle.removerDespesa(despesaInexistente);
        
        assertFalse(resultado);
    }
    
    // ==================== TESTES DE CONTADORES E TOTAIS ====================
    
    @Test
    @DisplayName("Deve contar receitas corretamente")
    void deveContarReceitasCorretamente() {
        assertEquals(0, controle.getTotalReceitas());
        
        controle.incluirReceita("Receita 1", 1000.0, dataAtual, CategoriasReceitas.SALARIO);
        assertEquals(1, controle.getTotalReceitas());
        
        controle.incluirReceita("Receita 2", 2000.0, dataAtual, CategoriasReceitas.FREELANCE);
        assertEquals(2, controle.getTotalReceitas());
    }
    
    @Test
    @DisplayName("Deve contar despesas corretamente")
    void deveContarDespesasCorretamente() {
        assertEquals(0, controle.getTotalDespesas());
        
        controle.incluirDespesa("Despesa 1", 500.0, dataAtual, CategoriasDespesas.MORADIA);
        assertEquals(1, controle.getTotalDespesas());
        
        controle.incluirDespesa("Despesa 2", 300.0, dataAtual, CategoriasDespesas.ALIMENTACAO);
        assertEquals(2, controle.getTotalDespesas());
    }
    
    @Test
    @DisplayName("Deve calcular valor total das receitas corretamente")
    void deveCalcularValorTotalDasReceitasCorretamente() {
        assertEquals(0.0, controle.getValorTotalReceitas());
        
        controle.incluirReceita("Receita 1", 1000.0, dataAtual, CategoriasReceitas.SALARIO);
        assertEquals(1000.0, controle.getValorTotalReceitas());
        
        controle.incluirReceita("Receita 2", 1500.0, dataAtual, CategoriasReceitas.FREELANCE);
        assertEquals(2500.0, controle.getValorTotalReceitas());
    }
    
    @Test
    @DisplayName("Deve calcular valor total das despesas corretamente")
    void deveCalcularValorTotalDasDespesasCorretamente() {
        assertEquals(0.0, controle.getValorTotalDespesas());
        
        controle.incluirDespesa("Despesa 1", 800.0, dataAtual, CategoriasDespesas.MORADIA);
        assertEquals(800.0, controle.getValorTotalDespesas());
        
        controle.incluirDespesa("Despesa 2", 400.0, dataAtual, CategoriasDespesas.ALIMENTACAO);
        assertEquals(1200.0, controle.getValorTotalDespesas());
    }
    
    // ==================== TESTE INTEGRADO ====================
    
    @Test
    @DisplayName("Deve funcionar corretamente em cenário completo")
    void deveFuncionarCorretamenteEmCenarioCompleto() {
        // Cenário: Pessoa física com controle financeiro mensal
        
        // Adicionar receitas
        controle.incluirReceita("Salário", 4000.0, dataAtual, CategoriasReceitas.SALARIO);
        controle.incluirReceita("Freelance", 800.0, dataAtual, CategoriasReceitas.FREELANCE);
        controle.incluirReceita("Rendimento Poupança", 50.0, dataAtual, CategoriasReceitas.INVESTIMENTOS);
        
        // Adicionar despesas
        controle.incluirDespesa("Aluguel", 1200.0, dataAtual, CategoriasDespesas.MORADIA);
        controle.incluirDespesa("Supermercado", 600.0, dataAtual, CategoriasDespesas.ALIMENTACAO);
        controle.incluirDespesa("Combustível", 300.0, dataAtual, CategoriasDespesas.TRANSPORTE);
        controle.incluirDespesa("Academia", 80.0, dataAtual, CategoriasDespesas.SAUDE);
        
        // Verificações
        assertEquals(3, controle.getTotalReceitas());
        assertEquals(4, controle.getTotalDespesas());
        assertEquals(4850.0, controle.getValorTotalReceitas());
        assertEquals(2180.0, controle.getValorTotalDespesas());
        assertEquals(2670.0, controle.consultarSaldo());
        
        // Remover uma despesa
        List<Despesa> despesas = controle.listarDespesas();
        Despesa academia = despesas.stream()
            .filter(d -> d.getDescricao().equals("Academia"))
            .findFirst()
            .orElse(null);
        
        assertNotNull(academia);
        assertTrue(controle.removerDespesa(academia));
        
        // Verificações após remoção
        assertEquals(3, controle.getTotalDespesas());
        assertEquals(2100.0, controle.getValorTotalDespesas());
        assertEquals(2750.0, controle.consultarSaldo());
    }
}
*/
// ==================== ENUMS AUXILIARES PARA TESTE ====================

/**
 * Enum simulando categorias de receitas
 */
enum CategoriasReceitas {
    SALARIO,
    FREELANCE,
    INVESTIMENTOS,
    OUTROS
}

/**
 * Enum simulando categorias de despesas
 */
enum CategoriasDespesas {
    MORADIA,
    ALIMENTACAO,
    TRANSPORTE,
    SAUDE,
    EDUCACAO,
    LAZER,
    OUTROS
}

/**
 * Classe simulando Receita para os testes
 */
/*class Receita {
    private String descricao;
    private double valor;
    private LocalDate data;
    private CategoriasReceitas categoria;
    
    public Receita(String descricao, double valor, LocalDate data, CategoriasReceitas categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
    }
    
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public LocalDate getData() { return data; }
    public CategoriasReceitas getCategoria() { return categoria; }
}

/**
 * Classe simulando Despesa para os testes
 */
/*class Despesa {
    private String descricao;
    private double valor;
    private LocalDate data;
    private CategoriasDespesas categoria;
    
    public Despesa(String descricao, double valor, LocalDate data, CategoriasDespesas categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
    }
    
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public LocalDate getData() { return data; }
    public CategoriasDespesas getCategoria() { return categoria; }

}*/