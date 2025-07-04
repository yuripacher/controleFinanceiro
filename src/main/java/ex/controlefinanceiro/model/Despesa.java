/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex.controlefinanceiro.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe que representa uma despesa financeira. Herda da classe Lancamento.
 *
 * @author MKB e YPR
 */
public class Despesa extends Lancamento {

    /**
     * Atributo privado para o tipo de despesa
     */
    private CategoriasDespesas tipo;

    /**
     * Construtor da classe Despesa.
     *
     * @param valor Valor da despesa
     * @param data Data da despesa
     * @param tipo Tipo da despesa (ex: "Alimentação", "Transporte", "Lazer")
     */
    public Despesa(double valor, LocalDate data, CategoriasDespesas tipo) {
        super(valor, data);
        setTipo(tipo);
    }

    /**
     * Getter para tipo
     *
     * @return tipo
     */
    public CategoriasDespesas getTipo() {
        return tipo;
    }

    /**
     * Setter para tipo
     *
     * @param tipo
     */
    public void setTipo(CategoriasDespesas tipo) {
        this.tipo = tipo;
    }

    /**
     * Método toString que retorna a forma que a despesa será apresentada
     * Valor terá o sinal de negativo, identificando que é uma despesa
     * @return String formatada com valor e data do lançamento.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Valor: -R$ " + String.format("%.2f", getValor())
                + " | Data: " + getData().format(formatter)
                + " | Categoria: " + getTipo();
    }
}
