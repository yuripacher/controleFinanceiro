/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex.controlefinanceiro.model;
import java.time.LocalDate;

/**
 * Classe que representa uma receita financeira.
 * Herda da classe Lancamento.
 * 
 * @author MKB e YPR
 */
public class Receita extends Lancamento {
    
    // Atributo privado para o tipo de receita
    private CategoriasReceitas tipo;
    
    /**
     * Construtor da classe Receita.
     * 
     * @param valor Valor da receita
     * @param data Data da receita
     * @param tipo Tipo da receita (ex: "Salário", "Freelance", "Vendas")
     */
    public Receita(double valor, LocalDate data, CategoriasReceitas tipo) {
        super(valor, data);
        setTipo(tipo);
    }
    
    // Getter para tipo
    public CategoriasReceitas getTipo() {
        return tipo;
    }
    
    // Setter para tipo
    public void setTipo(CategoriasReceitas tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Método toString que inclui o tipo da receita.
     */
    public String toString() {
        return "[RECEITA] " + super.toString() + " | Tipo: " + tipo;
    }
}