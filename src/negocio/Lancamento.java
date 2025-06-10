/* Classe Abstrada base */
package negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe abstrata que representa um lançamento financeiro.
 * Serve como base para receitas e despesas, implementando o conceito de herança.
 * 
 * @author Equipe
 * @version 1.0
 */
public abstract class Lancamento {
    
    protected int id;
    protected String descricao;
    protected double valor;
    protected LocalDate data;
    protected static int proximoId = 1;
    
    /**
     * Construtor da classe Lancamento.
     * 
     * @param descricao Descrição do lançamento
     * @param valor Valor do lançamento (sempre positivo)
     * @param data Data do lançamento
     */
    public Lancamento(String descricao, double valor, LocalDate data) {
        this.id = proximoId++;
        this.descricao = descricao;
        this.valor = Math.abs(valor); // Garantir valor positivo
        this.data = data;
    }
    
    /**
     * Construtor usado para carregar dados do CSV (com ID específico).
     */
    public Lancamento(int id, String descricao, double valor, LocalDate data) {
        this.id = id;
        this.descricao = descricao;
        this.valor = Math.abs(valor);
        this.data = data;
        
        // Atualizar próximo ID se necessário
        if (id >= proximoId) {
            proximoId = id + 1;
        }
    }
    
    /**
     * Método abstrato para obter a categoria do lançamento.
     * Deve ser implementado pelas classes filhas.
     * 
     * @return String representando a categoria
     */
    public abstract String getCategoria();
    
    /**
     * Método abstrato para determinar se é receita ou despesa.
     * 
     * @return true se for receita, false se for despesa
     */
    public abstract boolean isReceita();
    
    /**
     * Retorna o valor do lançamento considerando se é receita (+) ou despesa (-).
     * 
     * @return Valor com sinal correto
     */
    public double getValorComSinal() {
        return isReceita() ? valor : -valor;
    }
    
    /**
     * Converte o lançamento para formato CSV.
     * 
     * @return String no formato CSV
     */
    public String toCSV() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%d,%s,%.2f,%s,%s", 
            id, descricao, valor, data.format(formatter), getCategoria());
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = Math.abs(valor);
    }
    
    public LocalDate getData() {
        return data;
    }
    
    public void setData(LocalDate data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%s - R$ %.2f - %s (%s)", 
            descricao, valor, data.format(formatter), getCategoria());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Lancamento that = (Lancamento) obj;
        return id == that.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}