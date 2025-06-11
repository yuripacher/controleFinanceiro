/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex.controlefinanceiro.model;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe que representa um lançamento financeiro simples.
 * Contém apenas os dados básicos: descrição, valor e data.
 * 
 * @author MKB e YPR
 */
public class Lancamento {
    
    // Atributos privados
    private String descricao;
    private double valor;
    private LocalDate data;
    
    /**
     * Construtor da classe Lancamento.
     * 
     * @param descricao Descrição do lançamento
     * @param valor Valor do lançamento
     * @param data Data do lançamento
     */
    public Lancamento(String descricao, double valor, LocalDate data) {
        setDescricao(descricao);
        setValor(valor);
        setData(data);
    }
    
    // Getter para descrição
    public String getDescricao() {
        return descricao;
    }
    
    // Setter para descrição
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    // Getter para valor
    public double getValor() {
        return valor;
    }
    
    // Setter para valor
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    // Getter para data
    public LocalDate getData() {
        return data;
    }
    
    // Setter para data
    public void setData(LocalDate data) {
        this.data = data;
    }
    
    /**
     * Método toString para exibir os dados do lançamento de forma legível.
     */
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Descricao: " + descricao + 
               " | Valor: R$ " + valor + 
               " | Data: " + data.format(formatter);
    }
}