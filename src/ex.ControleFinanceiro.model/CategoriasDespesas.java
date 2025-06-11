/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex.controlefinanceiro.model;

import java.time.LocalDate;
import java.util.List;


/**
 * Classe principal para testar o sistema de controle financeiro.
 * Demonstra todas as funcionalidades implementadas.
 * 
 * @author Teste do Sistema
 */
public class Main {
    
    public static void main(String[] args) {
        // Criando uma instância do controlador financeiro
        ControleFinanceiro controlador = new ControleFinanceiro();
        
        System.out.println("=== SISTEMA DE CONTROLE FINANCEIRO ===\n");
        
        
        // Testando inclusão de receitas;
        System.out.println("1. ADICIONANDO RECEITAS:");
        System.out.println("------------------------");
        
        boolean receita1 = controlador.incluirReceita("Salário", 5000.00, LocalDate.now(), CategoriasReceitas.SALARIO);
        boolean receita2 = controlador.incluirReceita("Freelance", 1200.00, LocalDate.of(2024, 6, 15), CategoriasReceitas.OUTRAS_RECEITAS);
        boolean receita3 = controlador.incluirReceita("Vendas", 800.00, LocalDate.of(2024, 6, 10), CategoriasReceitas.OUTRAS_RECEITAS);
        
        System.out.println("Receita 1 adicionada: " + (receita1 ? "V Sucesso" : "X Falhou"));
        System.out.println("Receita 2 adicionada: " + (receita2 ? "V Sucesso" : "X Falhou"));
        System.out.println("Receita 3 adicionada: " + (receita3 ? "V Sucesso" : "X Falhou"));
        
        // Testando inclusão de despesas
        System.out.println("\n2. ADICIONANDO DESPESAS:");
        System.out.println("------------------------");
        
        boolean despesa1 = controlador.incluirDespesa("Supermercado", 300.00, LocalDate.now(), CategoriasDespesas.ALIMENTACAO);
        boolean despesa2 = controlador.incluirDespesa("Gasolina", 150.00, LocalDate.of(2024, 6, 12), CategoriasDespesas.TRANSPORTE);
        boolean despesa3 = controlador.incluirDespesa("Aluguel", 1200.00, LocalDate.of(2024, 6, 1), CategoriasDespesas.RESIDENCIA);
        boolean despesa4 = controlador.incluirDespesa("Cinema", 50.00, LocalDate.of(2024, 6, 8), CategoriasDespesas.ENTRETENIMENTO);
        
        System.out.println("Despesa 1 adicionada: " + (despesa1 ? "V Sucesso" : "X Falhou"));
        System.out.println("Despesa 2 adicionada: " + (despesa2 ? "V Sucesso" : "X Falhou"));
        System.out.println("Despesa 3 adicionada: " + (despesa3 ? "V Sucesso" : "X Falhou"));
        System.out.println("Despesa 4 adicionada: " + (despesa4 ? "V Sucesso" : "X Falhou"));
        
        
        // Testando validações (valores inválidos)
        System.out.println("\n3. TESTANDO VALIDAÇÕES:");
        System.out.println("-----------------------");
        
        boolean receitaInvalida1 = controlador.incluirReceita("", 1000.00, LocalDate.now(), CategoriasReceitas.SALARIO);
        boolean receitaInvalida2 = controlador.incluirReceita("Teste", -100.00, LocalDate.now(), CategoriasReceitas.SALARIO);
        boolean receitaInvalida3 = controlador.incluirReceita("Teste", 1000.00, null, CategoriasReceitas.SALARIO);
        
        System.out.println("Receita com descrição vazia: " + (receitaInvalida1 ? "V Passou" : "X Rejeitada corretamente"));
        System.out.println("Receita com valor negativo: " + (receitaInvalida2 ? "V Passou" : "X Rejeitada corretamente"));
        System.out.println("Receita com data nula: " + (receitaInvalida3 ? "V Passou" : "X Rejeitada corretamente"));
        
        // Listando todas as receitas
        System.out.println("\n4. LISTANDO RECEITAS:");
        System.out.println("---------------------");
        
        List<Receita> receitas = controlador.listarReceitas();
        if (receitas.isEmpty()) {
            System.out.println("Nenhuma receita cadastrada.");
        } else {
            for (int i = 0; i < receitas.size(); i++) {
                System.out.println((i + 1) + ". " + receitas.get(i));
            }
        }
        
        // Listando todas as despesas
        System.out.println("\n5. LISTANDO DESPESAS:");
        System.out.println("---------------------");
        
        List<Despesa> despesas = controlador.listarDespesas();
        if (despesas.isEmpty()) {
            System.out.println("Nenhuma despesa cadastrada.");
        } else {
            for (int i = 0; i < despesas.size(); i++) {
                System.out.println((i + 1) + ". " + despesas.get(i));
            }
        }
        
        // Mostrando resumo financeiro
        System.out.println("\n6. RESUMO FINANCEIRO:");
        System.out.println("---------------------");
        
        double totalReceitas = controlador.getValorTotalReceitas();
        double totalDespesas = controlador.getValorTotalDespesas();
        double saldo = controlador.consultarSaldo();
        
        System.out.printf("Total de Receitas: R$ %.2f%n", totalReceitas);
        System.out.printf("Total de Despesas: R$ %.2f%n", totalDespesas);
        System.out.printf("Saldo Atual: R$ %.2f%n", saldo);
        
        System.out.println("\nQuantidade de lançamentos:");
        System.out.println("- Receitas cadastradas: " + controlador.getTotalReceitas());
        System.out.println("- Despesas cadastradas: " + controlador.getTotalDespesas());
        
        // Testando remoção
        System.out.println("\n7. TESTANDO REMOÇÃO:");
        System.out.println("--------------------");
        
        if (!receitas.isEmpty()) {
            Receita receitaParaRemover = receitas.get(0);
            boolean removidaReceita = controlador.removerReceita(receitaParaRemover);
            System.out.println("Receita removida: " + (removidaReceita ? "V Sucesso" : "X Falhou"));
            System.out.println("Nova quantidade de receitas: " + controlador.getTotalReceitas());
        }
        
        if (!despesas.isEmpty()) {
            Despesa despesaParaRemover = despesas.get(0);
            boolean removidaDespesa = controlador.removerDespesa(despesaParaRemover);
            System.out.println("Despesa removida: " + (removidaDespesa ? "V Sucesso" : "X Falhou"));
            System.out.println("Nova quantidade de despesas: " + controlador.getTotalDespesas());
        }
        
        // Saldo final após remoções
        System.out.println("\n8. SALDO FINAL:");
        System.out.println("---------------");
        double saldoFinal = controlador.consultarSaldo();
        System.out.printf("Saldo após remoções: R$ %.2f%n", saldoFinal);
        
        if (saldoFinal > 0) {
            System.out.println("V Situação financeira: POSITIVA");
        } else if (saldoFinal < 0) {
            System.out.println("A Situação financeira: NEGATIVA");
        } else {
            System.out.println("= Situação financeira: EQUILIBRADA");
        }
        
        System.out.println("\n=== FIM DOS TESTES ===");
    }
}