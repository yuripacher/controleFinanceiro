package negocio;

import java.time.LocalDate;

/**
 * Classe que representa uma receita financeira.
 * Herda da classe Lancamento.
 * 
 * @author MKB e YPR
 */
public class Receita extends Lancamento {
    
    // Atributo privado para o tipo de receita
    private String tipo;
    
    /**
     * Construtor da classe Receita.
     * 
     * @param descricao Descrição da receita
     * @param valor Valor da receita
     * @param data Data da receita
     * @param tipo Tipo da receita (ex: "Salário", "Freelance", "Vendas")
     */
    public Receita(String descricao, double valor, LocalDate data, String tipo) {
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
     * Método toString que inclui o tipo da receita.
     */
    public String toString() {
        return "[RECEITA] " + super.toString() + " | Tipo: " + tipo;
    }
}