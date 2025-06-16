/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex.controlefinanceiro.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe que representa uma receita financeira. Herda da classe Lancamento.
 *
 * @author MKB e YPR
 */
public class Receita extends Lancamento {

    /**
     * Atributo privado para o tipo de receita
     */
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

    /**
     * Getter para tipo
     *
     * @return tipo
     */
    public CategoriasReceitas getTipo() {
        return tipo;
    }

    /**
     * Setter para tipo
     *
     * @param tipo
     */
    public void setTipo(CategoriasReceitas tipo) {
        this.tipo = tipo;
    }

    /**
     * Método toString que retorna a forma que a receita será apresentada
     * 
     * @return String formatada com valor e data do lançamento.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Valor positivo com sinal +
        return "Valor: +R$ " + String.format("%.2f", getValor())
                + " | Data: " + getData().format(formatter)
                + " | Categoria: " + getTipo();
    }
}
