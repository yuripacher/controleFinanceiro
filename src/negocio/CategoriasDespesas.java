// package controleFinanceiro.model;

// /**
//  *
//  * @author MKB e YPR
//  */
// public enum CategoriasDespesas {
//     ALIMENTACAO, TRANSPORTE, RESIDENCIA, SAUDE, EDUCACAO, ENTRETERIMENTO, OUTRAS_DESPESAS
// }

package negocio;

/**
 * Enum que define as categorias de despesas disponíveis no sistema.
 * 
 * @author MKB e YPR
 */
public enum CategoriasDespesas {
    ALIMENTACAO("Alimentação"),
    TRANSPORTE("Transporte"),
    RESIDENCIA("Residência"),
    SAUDE("Saúde"),
    EDUCACAO("Educação"),
    ENTRETENIMENTO("Entretenimento"),
    VESTUARIO("Vestuário"),
    OUTRAS_DESPESAS("Outras Despesas");
    
    private final String descricao;
    
    CategoriasDespesas(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
}