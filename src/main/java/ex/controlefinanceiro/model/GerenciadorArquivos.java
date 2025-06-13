/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex.controlefinanceiro.model;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela persistência dos dados em arquivos.
 * Gerencia a leitura e escrita de receitas e despesas em um único arquivo usando streams.
 * 
 * @author Iniciante
 * @version 2.0
 */
public class GerenciadorArquivos {
    
    // Constantes para os arquivos
    private static final String PASTA_DADOS = "dados";
    private static final String ARQUIVO_DADOS = "financeiro.txt";
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    
    /**
     * Construtor que inicializa o sistema de arquivos.
     * Cria a pasta e arquivo se não existirem.
     */
    public GerenciadorArquivos() {
        inicializarSistemaArquivos();
        }
    
    /**
     * Inicializa o sistema de arquivos criando pasta e arquivo se necessário.
     */
    private void inicializarSistemaArquivos() {
        try {
            criarPastaDados();
            criarArquivoDados();
            System.out.println("Sistema de arquivos inicializado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao inicializar sistema de arquivos: " + e.getMessage());
        }
    }
    
    /**
     * Cria a pasta de dados se ela não existir.
     */
    private void criarPastaDados() {
        File pasta = new File(PASTA_DADOS);
        if (!pasta.exists()) {
            boolean criada = pasta.mkdirs();
            if (criada) {
                System.out.println("Pasta 'dados' criada com sucesso!");
            } else {
                System.err.println("Erro ao criar pasta 'dados'");
            }
        } else {
            System.out.println("Pasta 'dados' já existe.");
        }
    }
    
    /**
     * Cria o arquivo de dados se ele não existir.
     */
    private void criarArquivoDados() throws IOException {
        File arquivo = new File(PASTA_DADOS, ARQUIVO_DADOS);
        if (!arquivo.exists()) {
            boolean criado = arquivo.createNewFile();
            if (criado) {
                System.out.println("Arquivo 'financeiro.txt' criado com sucesso!");
            } else {
                System.err.println("Erro ao criar arquivo 'financeiro.txt'");
            }
        } else {
            System.out.println("Arquivo 'financeiro.txt' já existe.");
        }
    }
   
    
    /**
     * Adiciona uma nova receita ao arquivo.
     * 
     * @param receita Receita a ser adicionada
     * @throws IOException Se ocorrer erro na escrita do arquivo
     */
    public void adicionarReceita(Receita receita)
        throws IOException {
            File arquivo = new File(PASTA_DADOS, ARQUIVO_DADOS);
        try (FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter writer = new BufferedWriter(fw)) {
            String linha =
                          receita.getData().format(FORMATO_DATA) + ";" + 
                          receita.getTipo() + ";" +
                          receita.getValor();
            writer.write(linha);
            writer.newLine();
            writer.flush();
            System.out.println("Receita adicionada com sucesso!");
        }
    }
    
    /**
     * Adiciona uma nova despesa ao arquivo.
     * 
     * @param despesa Despesa a ser adicionada
     * @throws IOException Se ocorrer erro na escrita do arquivo
     */
    public void adicionarDespesa(Despesa despesa) throws IOException {
        File arquivo = new File(PASTA_DADOS, ARQUIVO_DADOS);
        try (FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter writer = new BufferedWriter(fw)) {
            String linha =
                          despesa.getData().format(FORMATO_DATA) + ";" + 
                          despesa.getTipo() + ";" +
                          despesa.getValor();
            writer.write(linha);
            writer.newLine();
            writer.flush();
            System.out.println("Despesa adicionada com sucesso!");
        }
    }
    
    /**
     * Salva todas as receitas no arquivo (sobrescreve o arquivo).
     * 
     * @param receitas Lista de receitas a serem salvas
     * @throws IOException Se ocorrer erro na escrita do arquivo
     */
    public void salvarReceitas(List<Receita> receitas)
        throws IOException {
        // Carrega despesas existentes para não perdê-las
        List<Despesa> despesasExistentes = carregarDespesas();
        File arquivo = new File(PASTA_DADOS, ARQUIVO_DADOS);
        try (FileWriter fw = new FileWriter(arquivo, false); // false = sobrescreve
             BufferedWriter writer = new BufferedWriter(fw)) {
            
            // Salva receitas
            for (Receita receita : receitas) {
                String linha = "RECEITA;" + 
                              receita.getValor() + ";" + 
                              receita.getData().format(FORMATO_DATA) + ";" + 
                              receita.getTipo();
                writer.write(linha);
                writer.newLine();
            }
            
            // Salva despesas existentes
            for (Despesa despesa : despesasExistentes) {
                String linha = "DESPESA;" + 
                              despesa.getValor() + ";" + 
                              despesa.getData().format(FORMATO_DATA) + ";" + 
                              despesa.getTipo();
                writer.write(linha);
                writer.newLine();
            }
        }
    }
    
    /**
     * Salva todas as despesas no arquivo (sobrescreve o arquivo).
     * 
     * @param despesas Lista de despesas a serem salvas
     * @throws IOException Se ocorrer erro na escrita do arquivo
     */
    public void salvarDespesas(List<Despesa> despesas)
        throws IOException {
        // Carrega receitas existentes para não perdê-las
        List<Receita> receitasExistentes = carregarReceitas();
        File arquivo = new File(PASTA_DADOS, ARQUIVO_DADOS);
        
        try (FileWriter fw = new FileWriter(arquivo, false); // false = sobrescreve
             BufferedWriter writer = new BufferedWriter(fw)) {
            
            // Salva receitas existentes
            for (Receita receita : receitasExistentes) {
                String linha = "RECEITA;" + 
                              receita.getValor() + ";" + 
                              receita.getData().format(FORMATO_DATA) + ";" + 
                              receita.getTipo();
                writer.write(linha);
                writer.newLine();
            }
            
            // Salva despesas
            for (Despesa despesa : despesas) {
                String linha = "DESPESA;" + 
                              despesa.getValor() + ";" + 
                              despesa.getData().format(FORMATO_DATA) + ";" + 
                              despesa.getTipo();
                writer.write(linha);
                writer.newLine();
            }
        }
    }
    
    /**
     * Carrega a lista de receitas do arquivo.
     * 
     * @return Lista de receitas carregadas
     * @throws IOException Se ocorrer erro na leitura do arquivo
     */
    public List<Receita> carregarReceitas()
        throws IOException {
        List<Receita> receitas = new ArrayList<>();
        File arquivo = new File(PASTA_DADOS, ARQUIVO_DADOS);
        
        if (!arquivo.exists()) {
            return receitas; // Retorna lista vazia se arquivo não existe
        }
        
        try (FileReader fr = new FileReader(arquivo);
             BufferedReader reader = new BufferedReader(fr)) {
            
            String linha;
            while ((linha = reader.readLine()) != null) {
                try {
                    if (linha.startsWith("RECEITA;")) {
                        Receita receita = parseReceita(linha);
                        if (receita != null) {
                            receitas.add(receita);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao processar linha de receita: " + linha);
                }
            }
        }
        
        return receitas;
    }
    
    /**
     * Carrega a lista de despesas do arquivo.
     * 
     * @return Lista de despesas carregadas
     * @throws IOException Se ocorrer erro na leitura do arquivo
     */
    public List<Despesa> carregarDespesas() throws IOException {
        List<Despesa> despesas = new ArrayList<>();
        File arquivo = new File(PASTA_DADOS, ARQUIVO_DADOS);
        
        if (!arquivo.exists()) {
            return despesas; // Retorna lista vazia se arquivo não existe
        }
        
        try (FileReader fr = new FileReader(arquivo);
             BufferedReader reader = new BufferedReader(fr)) {
            
            String linha;
            while ((linha = reader.readLine()) != null) {
                try {
                    if (linha.startsWith("DESPESA;")) {
                        Despesa despesa = parseDespesa(linha);
                        if (despesa != null) {
                            despesas.add(despesa);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao processar linha de despesa: " + linha);
                }
            }
        }
        
        return despesas;
    }
    
    /**
     * Converte uma linha do arquivo em objeto Receita.
     * 
     * @param linha Linha do arquivo (deve começar com "RECEITA;")
     * @return Objeto Receita ou null se erro
     */
    private Receita parseReceita(String linha) {
        if (linha == null || linha.trim().isEmpty() || !linha.startsWith("RECEITA;")) {
            return null;
        }
        
        // Remove o prefixo "RECEITA;"
        String dadosReceita = linha.substring(8);
        String[] campos = dadosReceita.split(";");
        
        if (campos.length < 3) {
            return null;
        }
        
        try {
            double valor = Double.parseDouble(campos[0]);
            LocalDate data = LocalDate.parse(campos[1], FORMATO_DATA);
            String tipo = campos[2];
            CategoriasReceitas categoria = CategoriasReceitas.valueOf(tipo);
            return new Receita(valor, data, categoria);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Converte uma linha do arquivo em objeto Despesa.
     * 
     * @param linha Linha do arquivo (deve começar com "DESPESA;")
     * @return Objeto Despesa ou null se erro
     */
    private Despesa parseDespesa(String linha) {
        if (linha == null || linha.trim().isEmpty() || !linha.startsWith("DESPESA;")) {
            return null;
        }
        
        // Remove o prefixo "DESPESA;"
        String dadosDespesa = linha.substring(8);
        String[] campos = dadosDespesa.split(";");
        
        if (campos.length < 3) {
            return null;
        }
        
        try {
            double valor = Double.parseDouble(campos[0]);
            LocalDate data = LocalDate.parse(campos[1], FORMATO_DATA);
            String tipo = campos[2];
            CategoriasDespesas categoria = CategoriasDespesas.valueOf(tipo);
            return new Despesa(valor, data, categoria);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Verifica se os arquivos de dados existem.
     * 
     * @return true se o arquivo existe
     */
    public boolean arquivoExiste() {
        File arquivo = new File(PASTA_DADOS, ARQUIVO_DADOS);
        return arquivo.exists();
    }
    
    /**
     * Verifica se a pasta de dados existe.
     * 
     * @return true se a pasta existe
     */
    public boolean pastaExiste() {
        File pasta = new File(PASTA_DADOS);
        return pasta.exists() && pasta.isDirectory();
    }
    
    /**
     * Retorna informações sobre o status do sistema de arquivos.
     * 
     * @return String com informações sobre pasta e arquivo
     */
    public String getStatusSistema() {
        StringBuilder status = new StringBuilder();
        status.append("=== STATUS DO SISTEMA DE ARQUIVOS ===\n");
        status.append("Pasta existe: ").append(pastaExiste() ? "SIM" : "NÃO").append("\n");
        status.append("Arquivo existe: ").append(arquivoExiste() ? "SIM" : "NÃO").append("\n");
        
        if (arquivoExiste()) {
            try {
                File arquivo = new File(PASTA_DADOS, ARQUIVO_DADOS);
                status.append("Tamanho do arquivo: ").append(arquivo.length()).append(" bytes\n");
                
                int totalReceitas = carregarReceitas().size();
                int totalDespesas = carregarDespesas().size();
                
                status.append("Total de receitas: ").append(totalReceitas).append("\n");
                status.append("Total de despesas: ").append(totalDespesas).append("\n");
            } catch (IOException e) {
                status.append("Erro ao ler informações do arquivo: ").append(e.getMessage()).append("\n");
            }
        }
        
        return status.toString();
    }
}