// package controleFinanceiro.model;

// /**
//  *
//  * @author MKB e YPR
//  */
// public enum CategoriasReceitas {
//     SALARIO, DECIMO_TERCEIRO, FERIAS, OUTRAS_RECEITAS

// }

package negocio;

/**
 * Enum que define as categorias de receitas disponíveis no sistema.
 * 
 * @author MKB e YPR
 */
public enum CategoriasReceitas {
    SALARIO("Salário"),
    DECIMO_TERCEIRO("13º Salário"),
    FERIAS("Férias"),
    FREELANCE("Freelance"),
    VENDAS("Vendas"),
    INVESTIMENTOS("Investimentos"),
    OUTRAS_RECEITAS("Outras Receitas");
    
    private final String descricao;
    
    CategoriasReceitas(String descricao) {
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