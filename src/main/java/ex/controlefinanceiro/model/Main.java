package ex.controlefinanceiro.model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Classe para testar o GerenciadorArquivos
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== TESTE DO GERENCIADOR DE ARQUIVOS ===\n");
                

        // Criar instância do gerenciador
        GerenciadorArquivos gerenciador = new GerenciadorArquivos();
        
        // Testar status inicial
        System.out.println("1. STATUS INICIAL:");
        System.out.println(gerenciador.getStatusSistema());
        
        // Testar criação e adição de receitas
        testarReceitas(gerenciador);
        
        // Testar criação e adição de despesas
        testarDespesas(gerenciador);
        
        // Testar carregamento dos dados
        testarCarregamento(gerenciador);
        
        // Status final
        System.out.println("\n=== STATUS FINAL ===");
        System.out.println(gerenciador.getStatusSistema());
        
        // Teste interativo (opcional)
        testeInterativo(gerenciador);
    }
    
    /**
     * Testa a criação e persistência de receitas
     */
    private static void testarReceitas(GerenciadorArquivos gerenciador) {
        System.out.println("\n2. TESTANDO RECEITAS:");
        
        try {
            // Criar receitas de teste
            Receita receita1 = new Receita(5000.0, LocalDate.now(), CategoriasReceitas.SALARIO);
            Receita receita2 = new Receita(1500.0, LocalDate.now().minusDays(5), CategoriasReceitas.FREELANCE);
            Receita receita3 = new Receita(800.0, LocalDate.now().minusDays(10), CategoriasReceitas.VENDAS);
            
            // Adicionar receitas
            gerenciador.adicionarReceita(receita1);
            gerenciador.adicionarReceita(receita2);
            gerenciador.adicionarReceita(receita3);
            
            System.out.println("✓ Receitas adicionadas com sucesso!");
            
        } catch (IOException e) {
            System.err.println("✗ Erro ao adicionar receitas: " + e.getMessage());
        }
    }
    
    /**
     * Testa a criação e persistência de despesas
     */
    private static void testarDespesas(GerenciadorArquivos gerenciador) {
        System.out.println("\n3. TESTANDO DESPESAS:");
        
        try {
            // Criar despesas de teste
            Despesa despesa1 = new Despesa(350.0, LocalDate.now(), CategoriasDespesas.ALIMENTACAO);
            Despesa despesa2 = new Despesa(200.0, LocalDate.now().minusDays(2), CategoriasDespesas.TRANSPORTE);
            Despesa despesa3 = new Despesa(80.0, LocalDate.now().minusDays(3), CategoriasDespesas.ENTRETENIMENTO);
            Despesa despesa4 = new Despesa(150.0, LocalDate.now().minusDays(7), CategoriasDespesas.RESIDENCIA);
            
            // Adicionar despesas
            gerenciador.adicionarDespesa(despesa1);
            gerenciador.adicionarDespesa(despesa2);
            gerenciador.adicionarDespesa(despesa3);
            gerenciador.adicionarDespesa(despesa4);
            
            System.out.println("✓ Despesas adicionadas com sucesso!");
            
        } catch (IOException e) {
            System.err.println("✗ Erro ao adicionar despesas: " + e.getMessage());
        }
    }
    
    /**
     * Testa o carregamento dos dados salvos
     */
    private static void testarCarregamento(GerenciadorArquivos gerenciador) {
        System.out.println("\n4. TESTANDO CARREGAMENTO:");
        
        try {
            // Carregar receitas
            List<Receita> receitas = gerenciador.carregarReceitas();
            System.out.println("✓ Receitas carregadas: " + receitas.size());
            
            System.out.println("\n--- RECEITAS CARREGADAS ---");
            for (int i = 0; i < receitas.size(); i++) {
                System.out.println((i + 1) + ". " + receitas.get(i));
            }
            
            // Carregar despesas
            List<Despesa> despesas = gerenciador.carregarDespesas();
            System.out.println("\n✓ Despesas carregadas: " + despesas.size());
            
            System.out.println("\n--- DESPESAS CARREGADAS ---");
            for (int i = 0; i < despesas.size(); i++) {
                System.out.println((i + 1) + ". " + despesas.get(i));
            }
            
            // Calcular totais
            double totalReceitas = receitas.stream().mapToDouble(Receita::getValor).sum();
            double totalDespesas = despesas.stream().mapToDouble(Despesa::getValor).sum();
            double saldo = totalReceitas - totalDespesas;
            
            System.out.println("\n--- RESUMO FINANCEIRO ---");
            System.out.printf("Total de Receitas: R$ %.2f%n", totalReceitas);
            System.out.printf("Total de Despesas: R$ %.2f%n", totalDespesas);
            System.out.printf("Saldo: R$ %.2f%n", saldo);
            
        } catch (IOException e) {
            System.err.println("✗ Erro ao carregar dados: " + e.getMessage());
        }
    }
    
    
    
    
    /**
     * Teste interativo para o usuário
     */
    private static void testeInterativo(GerenciadorArquivos gerenciador) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n=== TESTE INTERATIVO ===");
        System.out.println("Deseja adicionar uma receita ou despesa? (r/d/n): ");
        String opcao = scanner.nextLine().toLowerCase();
        
        if (opcao.equals("r")) {
            adicionarReceitaInterativa(gerenciador, scanner);
        } else if (opcao.equals("d")) {
            adicionarDespesaInterativa(gerenciador, scanner);
        } else {
            System.out.println("Teste finalizado!");
        }
    }
    
    /**
     * Adiciona receita de forma interativa
     */
    private static void adicionarReceitaInterativa(GerenciadorArquivos gerenciador, Scanner scanner) {
        try {
            System.out.print("Digite a descrição da receita: ");
            String descricao = scanner.nextLine();
            
            System.out.print("Digite o valor: R$ ");
            double valor = Double.parseDouble(scanner.nextLine());
            
            System.out.println("Categorias disponíveis:");
            CategoriasReceitas[] categorias = CategoriasReceitas.values();
            for (int i = 0; i < categorias.length; i++) {
                System.out.println((i + 1) + ". " + categorias[i]);
            }
            
            System.out.print("Escolha a categoria (1-" + categorias.length + "): ");
            int escolha = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (escolha >= 0 && escolha < categorias.length) {
                Receita receita = new Receita(valor, LocalDate.now(), categorias[escolha]);
                gerenciador.adicionarReceita(receita);
                System.out.println("✓ Receita adicionada: " + receita);
            } else {
                System.out.println("✗ Categoria inválida!");
            }
            
        } catch (Exception e) {
            System.err.println("✗ Erro: " + e.getMessage());
        }
    }
    
    
    
    /**
     * Adiciona despesa de forma interativa
     */
    private static void adicionarDespesaInterativa(GerenciadorArquivos gerenciador, Scanner scanner) {
        try {
            System.out.print("Digite a descrição da despesa: ");
            String descricao = scanner.nextLine();
            
            System.out.print("Digite o valor: R$ ");
            double valor = Double.parseDouble(scanner.nextLine());
            
            System.out.println("Categorias disponíveis:");
            CategoriasDespesas[] categorias = CategoriasDespesas.values();
            for (int i = 0; i < categorias.length; i++) {
                System.out.println((i + 1) + ". " + categorias[i]);
            }
            
            System.out.print("Escolha a categoria (1-" + categorias.length + "): ");
            int escolha = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (escolha >= 0 && escolha < categorias.length) {
                Despesa despesa = new Despesa(valor, LocalDate.now(), categorias[escolha]);
                gerenciador.adicionarDespesa(despesa);
                System.out.println("✓ Despesa adicionada: " + despesa);
            } else {
                System.out.println("✗ Categoria inválida!");
            }
            
        } catch (Exception e) {
            System.err.println("✗ Erro: " + e.getMessage());
        }
    }
}