/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex.controlefinanceiro.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe controladora principal que gerencia todas as operações financeiras.
 * Implementa funcionalidades básicas para receitas e despesas.
 * 
 * @author IMKB e YPR
 */
public class ControleFinanceiro {
    
    // Atributos privados
    private List<Receita> receitas;
    private List<Despesa> despesas;
    
    /**
     * Construtor da classe ControladorFinanceiro.
     * Inicializa as listas vazias.
     */
    public ControleFinanceiro() {
        setReceita(new ArrayList<>());
        setDespesa(new ArrayList<>());
    }
    
    /**
     * Adiciona uma nova receita ao sistema.
     * 
     * @param valor Valor da receita
     * @param data Data da receita
     * @param tipo Tipo da receita
     * @return true se a receita foi adicionada com sucesso
     */
    public boolean incluirReceita(double valor, LocalDate data, CategoriasReceitas tipo) {
        if (valor <= 0) {
            return false;
        }
        if (data == null) {
            return false;
        }
        
        Receita receita = new Receita(valor, data, tipo);
        receitas.add(receita);
        return true;
    }
    
    /**
     * Adiciona uma nova despesa ao sistema.
     * 
     * @param valor Valor da despesa
     * @param data Data da despesa
     * @param tipo Tipo da despesa
     * @return true se a despesa foi adicionada com sucesso
     */
    public boolean incluirDespesa(double valor, LocalDate data, CategoriasDespesas tipo) {
        if (valor <= 0) {
            return false;
        }
        if (data == null) {
            return false;
        }
        
        Despesa despesa = new Despesa(valor, data, tipo);
        despesas.add(despesa);
        return true;
    }
    
    /**
     * Calcula o saldo atual (receitas - despesas).
     * 
     * @return Saldo atual
     */
    public double consultarSaldo() {
        double totalReceitas = 0.0;
        double totalDespesas = 0.0;
        
        // Soma todas as receitas
        for (Receita receita : receitas) {
            totalReceitas += receita.getValor();
        }
        
        // Soma todas as despesas
        for (Despesa despesa : despesas) {
            totalDespesas += despesa.getValor();
        }
        
        return totalReceitas - totalDespesas;
    }
    
    /**
     * Lista todas as receitas cadastradas.
     * 
     * @return Lista de receitas
     */
    public List<Receita> listarReceitas() {
        return new ArrayList<>(receitas);
    }
    
    /**
     * Lista todas as despesas cadastradas.
     * 
     * @return Lista de despesas
     */
    public List<Despesa> listarDespesas() {
        return new ArrayList<>(despesas);
    }
    
    /**
     * Remove uma receita do sistema.
     * 
     * @param receita Receita a ser removida
     * @return true se removida com sucesso
     */
    public boolean removerReceita(Receita receita) {
        return receitas.remove(receita);
    }
    
    /**
     * Remove uma despesa do sistema.
     * 
     * @param despesa Despesa a ser removida
     * @return true se removida com sucesso
     */
    public boolean removerDespesa(Despesa despesa) {
        return despesas.remove(despesa);
    }
    
    /**
     * Retorna o total de receitas cadastradas.
     * 
     * @return Número de receitas
     */
    public int getTotalReceitas() {
        return receitas.size();
    }
    
    /**
     * Retorna o total de despesas cadastradas.
     * 
     * @return Número de despesas
     */
    public int getTotalDespesas() {
        return despesas.size();
    }
    
    /**
     * Calcula o valor total das receitas.
     * 
     * @return Soma de todas as receitas
     */
    public double getValorTotalReceitas() {
        double total = 0.0;
        for (Receita receita : receitas) {
            total += receita.getValor();
        }
        return total;
    }
    
    /**
     * Calcula o valor total das despesas.
     * 
     * @return Soma de todas as despesas
     */
    public double getValorTotalDespesas() {
        double total = 0.0;
        for (Despesa despesa : despesas) {
            total += despesa.getValor();
        }
        return total;
    }

    private void setReceita(List<Receita> receitas) {
        this.receitas = receitas;
    }

    private void setDespesa(List<Despesa> despesas) {
        this.despesas = despesas;
    }
}