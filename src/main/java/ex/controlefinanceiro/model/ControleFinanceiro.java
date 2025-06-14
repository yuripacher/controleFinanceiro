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
 * Classe controladora principal que gerencia todas as operações financeiras.
 * Implementa funcionalidades básicas para receitas e despesas. Responsável por
 * todos os cálculos e lógica de negócio.
 *
 * @author IMKB e YPR
 */
public class ControleFinanceiro {

    private List<Receita> receitas;
    private List<Despesa> despesas;
    private GerenciadorArquivos gerenciador;
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ControleFinanceiro() {
        this.receitas = new ArrayList<>();
        this.despesas = new ArrayList<>();
        this.gerenciador = new GerenciadorArquivos();
        carregarDados();
    }

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

    private boolean validarLancamento(double valor, LocalDate data) {
        return valor > 0 && data != null;
    }

    private boolean validarPeriodo(LocalDate inicio, LocalDate fim) {
        return inicio != null && fim != null && !inicio.isAfter(fim);
    }

    public double getValorTotalReceitas() {
        double total = 0.0;
        for (Receita receita : receitas) {
            total += receita.getValor();
        }
        return total;
    }

    public double getValorTotalDespesas() {
        double total = 0.0;
        for (Despesa despesa : despesas) {
            total += despesa.getValor();
        }
        return total;
    }

    public double consultarSaldo() {
        return getValorTotalReceitas() - getValorTotalDespesas();
    }

    public List<Receita> getReceitas() {
        return new ArrayList<>(receitas);
    }

    public List<Despesa> getDespesas() {
        return new ArrayList<>(despesas);
    }

    public List<Lancamento> listarTodosLancamentos() {
        List<Lancamento> todos = new ArrayList<>();
        todos.addAll(receitas);
        todos.addAll(despesas);
        // Ordenar por data
        todos.sort(Comparator.comparing(Lancamento::getData));
        return todos;
    }

    public List<String> gerarExtrato(LocalDate dataInicio, LocalDate dataFim) {
        if (!validarPeriodo(dataInicio, dataFim)) {
            return new ArrayList<>();
        }

        List<String> extrato = new ArrayList<>();

        // Criar lista de todos os lançamentos no período
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

        // Ordenar por data
        lancamentosNoPeriodo.sort(Comparator.comparing(Lancamento::getData));

        // Converter para strings formatadas
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
}
