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
    
    // Atributos privados
    private double valor;
    private LocalDate data;
    
    public Lancamento(double valor, LocalDate data) {
        setValor(valor);
        setData(data);
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
    
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Valor: R$ " + valor + 
               " | Data: " + data.format(formatter);
    }
}