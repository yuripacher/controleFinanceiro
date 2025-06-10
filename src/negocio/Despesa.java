/* Herda de Lancamento */
package negocio;

import java.time.LocalDate;

/**
 * Classe que representa uma despesa financeira.
 * Herda da classe abstrata Lancamento.
 * 
 * @author Equipe
 * @version 1.0
 */
public class Despesa extends Lancamento {
    
    private TipoDespesa tipo;
    
    /**
     * Construtor da classe Despesa.
     * 
     * @param descricao Descrição da despesa
     * @param valor Valor da despesa
     * @param data Data da despesa
     * @param tipo Tipo/categoria da despesa
     */
    public Despesa(String descricao, double valor, LocalDate data, TipoDespesa tipo) {
        super(descricao, valor, data);
        this.tipo = tipo != null ? tipo : TipoDespesa.OUTRAS;
    }
    
    /**
     * Construtor usado para carregar dados do CSV.
     */
    public Despesa(int id, String descricao, double valor, LocalDate data, TipoDespesa tipo) {
        super(id, descricao, valor, data);
        this.tipo = tipo != null ? tipo : TipoDespesa.OUTRAS;
    }
    
    /**
     * Retorna a categoria da despesa.
     * 
     * @return String com a descrição da categoria
     */
    @Override
    public String getCategoria() {
        return tipo.getDescricao();
    }
    
    /**
     * Indica que este lançamento é uma despesa.
     * 
     * @return false (sempre é despesa)
     */
    @Override
    public boolean isReceita() {
        return false;
    }
    
    /**
     * Retorna o tipo da despesa.
     * 
     * @return TipoDespesa da despesa
     */
    public TipoDespesa getTipo() {
        return tipo;
    }
    
    /**
     * Define o tipo da despesa.
     * 
     * @param tipo Novo tipo da despesa
     */
    public void setTipo(TipoDespesa tipo) {
        this.tipo = tipo != null ? tipo : TipoDespesa.OUTRAS;
    }
    
    /**
     * Verifica se a despesa é essencial (alimentação, residência, saúde).
     * 
     * @return true se for despesa essencial
     */
    public boolean isEssencial() {
        return tipo == TipoDespesa.ALIMENTACAO || 
               tipo == TipoDespesa.RESIDENCIA || 
               tipo == TipoDespesa.SAUDE;
    }
    
    /**
     * Verifica se a despesa é com transporte.
     * 
     * @return true se for despesa de transporte
     */
    public boolean isTransporte() {
        return tipo == TipoDespesa.TRANSPORTE;
    }
    
    /**
     * Verifica se a despesa é com educação.
     * 
     * @return true se for despesa de educação
     */
    public boolean isEducacao() {
        return tipo == TipoDespesa.EDUCACAO;
    }
    
    /**
     * Verifica se a despesa é com entretenimento.
     * 
     * @return true se for despesa de entretenimento
     */
    public boolean isEntretenimento() {
        return tipo == TipoDespesa.ENTRETENIMENTO;
    }
    
    @Override
    public String toString() {
        return String.format("[DESPESA] %s", super.toString());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Despesa)) return false;
        
        Despesa despesa = (Despesa) obj;
        return tipo == despesa.tipo;
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        return result;
    }
}
