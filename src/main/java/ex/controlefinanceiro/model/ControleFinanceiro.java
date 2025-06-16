/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex.controlefinanceiro.model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Classe principal que gerencia todas as operações financeiras. Implementa
 * funcionalidades básicas para receitas e despesas. Responsável por todos os
 * cálculos e lógica de negócio.
 *
 * @author MKB e YPR
 */
public class ControleFinanceiro {

    private List<Receita> receitas;
    private List<Despesa> despesas;
    private GerenciadorArquivos gerenciador;
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Construtor padrão que inicializa o controle financeiro com um gerenciador
     * de arquivos padrão.
     */
    public ControleFinanceiro() {
        this.receitas = new ArrayList<>();
        this.despesas = new ArrayList<>();
        this.gerenciador = new GerenciadorArquivos();
        carregarDados();
    }

    /**
     * Construtor que permite especificar o gerenciador de arquivos para o
     * controle financeiro.
     *
     * @param gerenciador instância do GerenciadorArquivos a ser usada para
     * carregar e salvar dados.
     */
    public ControleFinanceiro(GerenciadorArquivos gerenciador) {
        this.receitas = new ArrayList<>();
        this.despesas = new ArrayList<>();
        this.gerenciador = gerenciador;
        carregarDados();
    }

    public void carregarDados() {
        try {
            this.receitas = gerenciador.carregarReceitas();
            this.despesas = gerenciador.carregarDespesas();
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
            this.receitas = new ArrayList<>();
            this.despesas = new ArrayList<>();
        }
    }

    /**
     * Inclui uma nova receita e adicionando ao arquivo.
     *
     * @param valor valor da receita, deve ser maior que 0.
     * @param data data da receita.
     * @param tipo categoria da receita.
     * @return true se a inclusão for bem-sucedida, false caso contrário.
     */
    public boolean incluirReceita(double valor, LocalDate data, CategoriasReceitas tipo) {
        if (!validarLancamento(valor, data)) {
            return false;
        }

        Receita receita = new Receita(valor, data, tipo);

        try {
            gerenciador.adicionarReceita(receita);
            receitas.add(receita);
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar receita: " + e.getMessage());
            return false;
        }
    }

    /**
     * Inclui uma nova despesa e adicionando ao arquivo.
     *
     * @param valor valor da despesa, deve ser maior que 0.
     * @param data data da despesa.
     * @param tipo categoria da despesa.
     * @return true se a inclusão for bem-sucedida, false caso contrário.
     */
    public boolean incluirDespesa(double valor, LocalDate data, CategoriasDespesas tipo) {
        if (!validarLancamento(valor, data)) {
            return false;
        }

        Despesa despesa = new Despesa(valor, data, tipo);

        try {
            gerenciador.adicionarDespesa(despesa);
            despesas.add(despesa);
            return true;

        } catch (IOException e) {
            System.err.println("Erro ao salvar despesa: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verifica se um lançamento financeiro é válido. Um lançamento é válido se
     * o valor for maior que zero e a data não for nula.
     *
     * @param valor valor do lançamento.
     * @param data data do lançamento.
     * @return true se for válido, false caso contrário.
     */
    private boolean validarLancamento(double valor, LocalDate data) {
        return valor > 0 && data != null;
    }

    /**
     * Verifica se o período de datas informado é válido. Um período é válido se
     * ambas as datas forem não nulas e a data inicial não for posterior à
     * final.
     *
     * @param inicio data inicial.
     * @param fim data final.
     * @return true se o período for válido, false caso contrário.
     */
    private boolean validarPeriodo(LocalDate inicio, LocalDate fim) {
        return inicio != null && fim != null && !inicio.isAfter(fim);
    }

    /**
     * Calcula o valor total de todas as receitas registradas.
     *
     * @return soma de todas as receitas.
     */
    public double getValorTotalReceitas() {
        double total = 0.0;
        for (Receita receita : receitas) {
            total += receita.getValor();
        }
        return total;
    }

    /**
     * Calcula o valor total de todas as despesas registradas.
     *
     * @return soma de todas as despesas.
     */
    public double getValorTotalDespesas() {
        double total = 0.0;
        for (Despesa despesa : despesas) {
            total += despesa.getValor();
        }
        return total;
    }

    /**
     * Calcula o saldo atual, subtraindo o total de despesas do total de
     * receitas.
     *
     * @return saldo atual.
     */
    public double consultarSaldo() {
        return getValorTotalReceitas() - getValorTotalDespesas();
    }

    /**
     * Retorna uma cópia da lista de todas as receitas registradas.
     *
     * @return lista de receitas.
     */
    public List<Receita> getReceitas() {
        return new ArrayList<>(receitas);
    }

    /**
     * Retorna uma cópia da lista de todas as despesas registradas.
     *
     * @return lista de despesas.
     */
    public List<Despesa> getDespesas() {
        return new ArrayList<>(despesas);
    }

    /**
     * Retorna uma lista com todos os lançamentos (receitas e despesas)
     * ordenados por data. A lista é composta por instâncias das classes Receita
     * e Despesa, que estendem Lancamento.
     *
     * @return lista de todos os lançamentos ordenados por data.
     */
    public List<Lancamento> listarTodosLancamentos() {
        List<Lancamento> todos = new ArrayList<>();
        todos.addAll(receitas);
        todos.addAll(despesas);
        // Ordenar por data
        todos.sort(Comparator.comparing(Lancamento::getData));
        return todos;
    }

    /**
     * Gera um extrato com receitas e despesas no período informado, formatado
     * em strings.
     *
     * @param dataInicio data inicial do extrato.
     * @param dataFim data final do extrato.
     * @return lista de strings contendo os lançamentos no período, ou lista
     * vazia se o período for inválido.
     */
    public List<String> gerarExtrato(LocalDate dataInicio, LocalDate dataFim) {
        
        if (!validarPeriodo(dataInicio, dataFim)) {
            return new ArrayList<>();
        }

        List<String> extrato = new ArrayList<>();

        List<Lancamento> lancamentosNoPeriodo = new ArrayList<>();

        for (Receita receita : receitas) {
            if (!receita.getData().isBefore(dataInicio) && !receita.getData().isAfter(dataFim)) {
                lancamentosNoPeriodo.add(receita);
            }
        }

        for (Despesa despesa : despesas) {
            if (!despesa.getData().isBefore(dataInicio) && !despesa.getData().isAfter(dataFim)) {
                lancamentosNoPeriodo.add(despesa);
            }
        }

        lancamentosNoPeriodo.sort(Comparator.comparing(Lancamento::getData));

        for (Lancamento lancamento : lancamentosNoPeriodo) {
            if (lancamento instanceof Receita) {
                Receita receita = (Receita) lancamento;
                extrato.add("RECEITA - " + receita.getData().format(FORMATO_DATA)
                        + " - " + receita.getTipo() + " - R$ " + String.format("%.2f", receita.getValor()));
            } else if (lancamento instanceof Despesa) {
                Despesa despesa = (Despesa) lancamento;
                extrato.add("DESPESA - " + despesa.getData().format(FORMATO_DATA)
                        + " - " + despesa.getTipo() + " - R$ " + String.format("%.2f", despesa.getValor()));
            }
        }

        return extrato;
    }

    /**
     * Retorna uma lista com todas as receitas registradas dentro do período
     * informado. A lista é ordenada por data em ordem crescente.
     *
     * @param inicio data inicial do período.
     * @param fim data final do período.
     * @return lista de receitas no período, ou lista vazia se o período for
     * inválido.
     */
    public List<Receita> listarReceitasPorPeriodo(LocalDate inicio, LocalDate fim) {
        List<Receita> resultado = new ArrayList<>();
        if (!validarPeriodo(inicio, fim)) {
            return resultado;
        }

        for (Receita r : receitas) {
            if (!r.getData().isBefore(inicio) && !r.getData().isAfter(fim)) {
                resultado.add(r);
            }
        }
        resultado.sort(Comparator.comparing(Receita::getData));
        return resultado;
    }

    /**
     * Retorna uma lista com todas as despesas registradas dentro do período
     * informado. A lista é ordenada por data em ordem crescente.
     *
     * @param inicio data inicial do período.
     * @param fim data final do período.
     * @return lista de despesas no período, ou lista vazia se o período for
     * inválido.
     */
    public List<Despesa> listarDespesasPorPeriodo(LocalDate inicio, LocalDate fim) {
        List<Despesa> resultado = new ArrayList<>();
        if (!validarPeriodo(inicio, fim)) {
            return resultado;
        }

        for (Despesa d : despesas) {
            if (!d.getData().isBefore(inicio) && !d.getData().isAfter(fim)) {
                resultado.add(d);
            }
        }
        resultado.sort(Comparator.comparing(Despesa::getData));
        return resultado;
    }

    /**
     * Calcula o saldo dentro de um intervalo de datas informado. O saldo é
     * obtido subtraindo o total de despesas do total de receitas no período.
     *
     * @param inicio data inicial do período.
     * @param fim data final do período.
     * @return saldo do período (receitas - despesas), ou 0 se o período for
     * inválido.
     */
    public double calcularSaldoPorPeriodo(LocalDate inicio, LocalDate fim) {
        double totalReceitas = 0;
        double totalDespesas = 0;

        for (Receita r : listarReceitasPorPeriodo(inicio, fim)) {
            totalReceitas += r.getValor();
        }

        for (Despesa d : listarDespesasPorPeriodo(inicio, fim)) {
            totalDespesas += d.getValor();
        }

        return totalReceitas - totalDespesas;
    }

}
