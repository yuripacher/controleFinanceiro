/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex.controlefinanceiro.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe que representa um lançamento financeiro.
 * Contém valor e data do lançamento.
 * 
 * @author MKB e YPR
 */
public class Lancamento {
/**
 * Atributos privado para o valor do lançamento e a data
 */
    private double valor;
    private LocalDate data;
    
    /**
     * Construtor da classe Lançamento
     * 
     * @param valor Valor do lançamento
     * @param data Data do lançamento
     */
    public Lancamento(double valor, LocalDate data) {
        setValor(valor);
        setData(data);
    }
    
    /** Getter para valor
     * 
     * @return valor
     */
    public double getValor() {
        return valor;
    }
    
    /** Setter para valor
     * 
     * @param valor 
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    /** Getter para data
     * 
     * @return data
     */
    public LocalDate getData() {
        return data;
    }
    
    /** Setter para data
     * 
     * @param data 
     */
    public void setData(LocalDate data) {
        this.data = data;
    }
    
    /**
     * Método toString que retorna a forma que o lançamento será apresentada
     * 
     * @return String formatada com valor e data do lançamento.
     */
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Valor: R$ " + valor + 
               " | Data: " + data.format(formatter);
    }
}