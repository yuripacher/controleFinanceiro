/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ex.controlefinanceiro.model;

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