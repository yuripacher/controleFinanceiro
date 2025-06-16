/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ex.controlefinanceiro.model;

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

    /**
     * Construtor do enum que define a descrição legível da categoria.
     * 
     * @param descricao Descrição da categoria de despesa.
     */
    CategoriasDespesas(String descricao) {
        this.descricao = descricao;
    }

     /**
     * Retorna a descrição da categoria.
     * 
     * @return descrição legível da categoria.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna a descrição da categoria ao invés do nome do enum.
     * 
     * @return descrição legível da categoria.
     */
    @Override
    public String toString() {
        return descricao;
    }
}
