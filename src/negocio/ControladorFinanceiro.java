/* Classe principal de controle */
package negocio;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe controladora principal que gerencia todas as operações financeiras.
 * Implementa todos os requisitos funcionais do sistema.
 * 
 * @author Equipe
 * @version 1.0
 */
public class ControladorFinanceiro {
    
    private List<Receita> receitas;
    private List<Despesa> despesas;
    private GerenciadorArquivos gerenciadorArquivos;
    
    /**
     * Construtor da classe ControladorFinanceiro.
     * Inicializa as listas e carrega os dados existentes.
     */
    public ControladorFinanceiro() {
        this.receitas = new ArrayList<>();
        this.despesas = new ArrayList<>();
        this.gerenciadorArquivos = new GerenciadorArquivos();
        carregarDados();
    }
    
    /**
     * Requisito 1: Incluir receitas.
     * Adiciona uma nova receita ao sistema.
     * 
     * @param descricao Descrição da receita
     * @param valor Valor da receita
     * @param data Data da receita
     * @param tipo Tipo da receita
     * @return true se a receita foi adicionada com sucesso
     */
    public boolean incluirReceita(String descricao, double valor, LocalDate data, TipoReceita tipo) {
        if (descricao == null || descricao.trim().isEmpty()) {
            return false;
        }
        if (valor <= 0) {
            return false;
        }
        if (data == null) {
            return false;
        }
        
        Receita receita = new Receita(descricao.trim(), valor, data, tipo);
        receitas.add(receita);
        salvarDados();
        return true;
    }
    
    /**
     * Requisito 2: Incluir despesas.
     * Adiciona uma nova despesa ao sistema.
     * 
     * @param descricao Descrição da despesa
     * @param valor Valor da despesa
     * @param data Data da despesa
     * @param tipo Tipo da despesa
     * @return true se a despesa foi adicionada com sucesso
     */
    public boolean incluirDespesa(String descricao, double valor, LocalDate data, TipoDespesa tipo) {
        if (descricao == null || descricao.trim().isEmpty()) {
            return false;
        }
        if (valor <= 0) {
            return false;
        }
        if (data == null) {
            return false;
        }
        
        Despesa despesa = new Despesa(descricao.trim(), valor, data, tipo);
        despesas.add(despesa);
        salvarDados();
        return true;
    }
    
    /**
     * Requisito 3: Consultar saldo disponível até a data atual.
     * Calcula o saldo considerando apenas lançamentos até hoje.
     * 
     * @return Saldo atual
     */
    public double consultarSaldoAtual() {
        return consultarSaldoAteData(LocalDate.now());
    }
    
    /**
     * Requisito 4: Consultar saldo disponível independente do período.
     * Calcula o saldo considerando todos os lançamentos.
     * 
     * @return Saldo total
     */
    public double consultarSaldoTotal() {
        double totalReceitas = receitas.stream()
                .mapToDouble(Receita::getValor)
                .sum();
        
        double totalDespesas = despesas.stream()
                .mapToDouble(Despesa::getValor)
                .sum();
        
        return totalReceitas - totalDespesas;
    }
    
    /**
     * Consulta o saldo até uma data específica.
     * 
     * @param dataLimite Data limite para cálculo
     * @return Saldo até a data especificada
     */
    public double consultarSaldoAteData(LocalDate dataLimite) {
        if (dataLimite == null) {
            return consultarSaldoTotal();
        }
        
        double totalReceitas = receitas.stream()
                .filter(r -> !r.getData().isAfter(dataLimite))
                .mapToDouble(Receita::getValor)
                .sum();
        
        double totalDespesas = despesas.stream()
                .filter(d -> !d.getData().isAfter(dataLimite))
                .mapToDouble(Despesa::getValor)
                .sum();
        
        return totalReceitas - totalDespesas;
    }
    
    /**
     * Requisito 5: Listar todas as receitas lançadas.
     * 
     * @return Lista de receitas ordenada por data
     */
    public List<Receita> listarReceitas() {
        return receitas.stream()
                .sorted(Comparator.comparing(Receita::getData).reversed())
                .collect(Collectors.toList());
    }
    
    /**
     * Requisito 6: Listar todas as despesas lançadas.
     * 
     * @return Lista de despesas ordenada por data
     */
    public List<Despesa> listarDespesas() {
        return despesas.stream()
                .sorted(Comparator.comparing(Despesa::getData).reversed())
                .collect(Collectors.toList());
    }
    
    /**
     * Requisito 7: Listar todos os lançamentos ordenados por data.
     * Gera um extrato com saldo acumulado.
     * 
     * @return Lista de strings representando o extrato
     */
    public List<String> gerarExtrato() {
        List<String> extrato = new ArrayList<>();
        List<Lancamento> todosLancamentos = new ArrayList<>();
        
        // Adicionar todas as receitas e despesas
        todosLancamentos.addAll(receitas);
        todosLancamentos.addAll(despesas);
        
        // Ordenar por data
        todosLancamentos.sort(Comparator.comparing(Lancamento::getData));
        
        double saldoAcumulado = 0.0;
        
        for (Lancamento lancamento : todosLancamentos) {
            saldoAcumulado += lancamento.getValorComSinal();
            
            String linha = String.format("%s | Saldo: R$ %.2f", 
                    lancamento.toString(), saldoAcumulado);
            extrato.add(linha);
        }
        
        return extrato;
    }
    
    /**
     * Lista receitas por categoria.
     * 
     * @param tipo Tipo de receita a filtrar
     * @return Lista de receitas do tipo especificado
     */
    public List<Receita> listarReceitasPorTipo(TipoReceita tipo) {
        return receitas.stream()
                .filter(r -> r.getTipo() == tipo)
                .sorted(Comparator.comparing(Receita::getData).reversed())
                .collect(Collectors.toList());
    }
    
    /**
     * Lista despesas por categoria.
     * 
     * @param tipo Tipo de despesa a filtrar
     * @return Lista de despesas do tipo especificado
     */
    public List<Despesa> listarDespesasPorTipo(TipoDespesa tipo) {
        return despesas.stream()
                .filter(d -> d.getTipo() == tipo)
                .sorted(Comparator.comparing(Despesa::getData).reversed())
                .collect(Collectors.toList());
    }
    
    /**
     * Calcula total de receitas por período.
     * 
     * @param dataInicio Data inicial
     * @param dataFim Data final
     * @return Total de receitas no período
     */
    public double calcularReceitasPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return receitas.stream()
                .filter(r -> !r.getData().isBefore(dataInicio) && !r.getData().isAfter(dataFim))
                .mapToDouble(Receita::getValor)
                .sum();
    }
    
    /**
     * Calcula total de despesas por período.
     * 
     * @param dataInicio Data inicial
     * @param dataFim Data final
     * @return Total de despesas no período
     */
    public double calcularDespesasPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return despesas.stream()
                .filter(d -> !d.getData().isBefore(dataInicio) && !d.getData().isAfter(dataFim))
                .mapToDouble(Despesa::getValor)
                .sum();
    }
    
    /**
     * Remove uma receita do sistema.
     * 
     * @param receita Receita a ser removida
     * @return true se removida com sucesso
     */
    public boolean removerReceita(Receita receita) {
        if (receitas.remove(receita)) {
            salvarDados();
            return true;
        }
        return false;
    }
    
    /**
     * Remove uma despesa do sistema.
     * 
     * @param despesa Despesa a ser removida
     * @return true se removida com sucesso
     */
    public boolean removerDespesa(Despesa despesa) {
        if (despesas.remove(despesa)) {
            salvarDados();
            return true;
        }
        return false;
    }
    
    /**
     * Carrega os dados dos arquivos CSV.
     */
    private void carregarDados() {
        try {
            receitas = gerenciadorArquivos.carregarReceitas();
            despesas = gerenciadorArquivos.carregarDespesas();
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
            receitas = new ArrayList<>();
            despesas = new ArrayList<>();
        }
    }
    
    /**
     * Salva os dados nos arquivos CSV.
     */
    private void salvarDados() {
        try {
            gerenciadorArquivos.salvarReceitas(receitas);
            gerenciadorArquivos.salvarDespesas(despesas);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
    
    /**
     * Retorna estatísticas do sistema.
     * 
     * @return Array com [totalReceitas, totalDespesas, saldo, numReceitas, numDespesas]
     */
    public double[] obterEstatisticas() {
        double totalReceitas = receitas.stream().mapToDouble(Receita::getValor).sum();
        double totalDespesas = despesas.stream().mapToDouble(Despesa::getValor).sum();
        double saldo = totalReceitas - totalDespesas;
        
        return new double[] {
            totalReceitas,
            totalDespesas, 
            saldo,
            receitas.size(),
            despesas.size()
        };
    }
}