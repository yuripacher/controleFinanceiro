package negocio;

import java.time.LocalDate;

/**
 * Classe que representa uma despesa financeira.
 * Herda da classe Lancamento.
 * 
 * @author MKB e YPR
 */
public class Despesa extends Lancamento {
    
    // Atributo privado para o tipo de despesa
    private String tipo;
    
    /**
     * Construtor da classe Despesa.
     * 
     * @param descricao Descrição da despesa
     * @param valor Valor da despesa
     * @param data Data da despesa
     * @param tipo Tipo da despesa (ex: "Alimentação", "Transporte", "Lazer")
     */
    public Despesa(String descricao, double valor, LocalDate data, String tipo) {
        super(descricao, valor, data);
        setTipo(tipo);
    }
    
    // Getter para tipo
    public String getTipo() {
        return tipo;
    }
    
    // Setter para tipo
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Método toString que inclui o tipo da despesa.
     */
    public String toString() {
        return "[DESPESA] " + super.toString() + " | Tipo: " + tipo;
    }
}