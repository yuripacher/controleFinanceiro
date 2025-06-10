/* Herda de Lancamento */
package negocio;

import java.time.LocalDate;

/**
 * Classe que representa uma receita financeira.
 * Herda da classe abstrata Lancamento.
 * 
 * @author Equipe
 * @version 1.0
 */
public class Receita extends Lancamento {
    
    private TipoReceita tipo;
    
    /**
     * Construtor da classe Receita.
     * 
     * @param descricao Descrição da receita
     * @param valor Valor da receita
     * @param data Data da receita
     * @param tipo Tipo/categoria da receita
     */
    public Receita(String descricao, double valor, LocalDate data, TipoReceita tipo) {
        super(descricao, valor, data);
        this.tipo = tipo != null ? tipo : TipoReceita.OUTRAS;
    }
    
    /**
     * Construtor usado para carregar dados do CSV.
     */
    public Receita(int id, String descricao, double valor, LocalDate data, TipoReceita tipo) {
        super(id, descricao, valor, data);
        this.tipo = tipo != null ? tipo : TipoReceita.OUTRAS;
    }
    
    /**
     * Retorna a categoria da receita.
     * 
     * @return String com a descrição da categoria
     */
    @Override
    public String getCategoria() {
        return tipo.getDescricao();
    }
    
    /**
     * Indica que este lançamento é uma receita.
     * 
     * @return true (sempre é receita)
     */
    @Override
    public boolean isReceita() {
        return true;
    }
    
    /**
     * Retorna o tipo da receita.
     * 
     * @return TipoReceita da receita
     */
    public TipoReceita getTipo() {
        return tipo;
    }
    
    /**
     * Define o tipo da receita.
     * 
     * @param tipo Novo tipo da receita
     */
    public void setTipo(TipoReceita tipo) {
        this.tipo = tipo != null ? tipo : TipoReceita.OUTRAS;
    }
    
    /**
     * Verifica se a receita é do tipo salário.
     * 
     * @return true se for salário
     */
    public boolean isSalario() {
        return tipo == TipoReceita.SALARIO;
    }
    
    /**
     * Verifica se a receita é do tipo décimo terceiro.
     * 
     * @return true se for décimo terceiro
     */
    public boolean isDecimoTerceiro() {
        return tipo == TipoReceita.DECIMO_TERCEIRO;
    }
    
    /**
     * Verifica se a receita é do tipo férias.
     * 
     * @return true se for férias
     */
    public boolean isFerias() {
        return tipo == TipoReceita.FERIAS;
    }
    
    @Override
    public String toString() {
        return String.format("[RECEITA] %s", super.toString());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Receita)) return false;
        
        Receita receita = (Receita) obj;
        return tipo == receita.tipo;
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        return result;
    }
}
