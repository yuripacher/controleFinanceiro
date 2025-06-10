/*Enum para tipos de despesa */
package negocio;

/**
 * Enumeração que define os tipos de despesa disponíveis no sistema.
 * 
 * @author Equipe
 * @version 1.0
 */
public enum TipoDespesa {
    
    ALIMENTACAO("Alimentação"),
    TRANSPORTE("Transporte"),
    RESIDENCIA("Residência"),
    SAUDE("Saúde"),
    EDUCACAO("Educação"),
    ENTRETENIMENTO("Entretenimento"),
    OUTRAS("Outras Despesas");
    
    private final String descricao;
    
    /**
     * Construtor do enum.
     * 
     * @param descricao Descrição amigável do tipo de despesa
     */
    TipoDespesa(String descricao) {
        this.descricao = descricao;
    }
    
    /**
     * Retorna a descrição amigável do tipo de despesa.
     * 
     * @return Descrição do tipo de despesa
     */
    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Converte uma string para o enum correspondente.
     * 
     * @param valor String a ser convertida
     * @return TipoDespesa correspondente ou OUTRAS se não encontrado
     */
    public static TipoDespesa fromString(String valor) {
        if (valor == null) return OUTRAS;
        
        for (TipoDespesa tipo : TipoDespesa.values()) {
            if (tipo.name().equalsIgnoreCase(valor) || 
                tipo.getDescricao().equalsIgnoreCase(valor)) {
                return tipo;
            }
        }
        return OUTRAS;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
}