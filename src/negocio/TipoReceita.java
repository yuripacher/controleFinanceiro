/*Enum para tipos de receita */
package negocio;

/**
 * Enumeração que define os tipos de receita disponíveis no sistema.
 * 
 * @author Equipe
 * @version 1.0
 */
public enum TipoReceita {
    
    SALARIO("Salário"),
    DECIMO_TERCEIRO("Décimo Terceiro"),
    FERIAS("Férias"),
    OUTRAS("Outras Receitas");
    
    private final String descricao;
    
    /**
     * Construtor do enum.
     * 
     * @param descricao Descrição amigável do tipo de receita
     */
    TipoReceita(String descricao) {
        this.descricao = descricao;
    }
    
    /**
     * Retorna a descrição amigável do tipo de receita.
     * 
     * @return Descrição do tipo de receita
     */
    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Converte uma string para o enum correspondente.
     * 
     * @param valor String a ser convertida
     * @return TipoReceita correspondente ou OUTRAS se não encontrado
     */
    public static TipoReceita fromString(String valor) {
        if (valor == null) return OUTRAS;
        
        for (TipoReceita tipo : TipoReceita.values()) {
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