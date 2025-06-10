/* Gerencia persistência CSV */
package negocio;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela persistência dos dados em arquivos CSV.
 * Gerencia a leitura e escrita de receitas e despesas.
 * 
 * @author Equipe
 * @version 1.0
 */
public class GerenciadorArquivos {
    
    private static final String PASTA_DADOS = "dados";
    private static final String ARQUIVO_RECEITAS = "receitas.csv";
    private static final String ARQUIVO_DESPESAS = "despesas.csv";
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    /**
     * Construtor que cria a pasta de dados se ela não existir.
     */
    public GerenciadorArquivos() {
        criarPastaDados();
    }
    
    /**
     * Cria a pasta de dados se ela não existir.
     */
    private void criarPastaDados() {
        File pasta = new File(PASTA_DADOS);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }
    
    /**
     * Salva a lista de receitas no arquivo CSV.
     * 
     * @param receitas Lista de receitas a serem salvas
     * @throws IOException Se ocorrer erro na escrita do arquivo
     */
    public void salvarReceitas(List<Receita> receitas) throws IOException {
        File arquivo = new File(PASTA_DADOS, ARQUIVO_RECEITAS);
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo))) {
            // Cabeçalho do CSV
            writer.println("id,descricao,valor,data,categoria");
            
            // Dados das receitas
            for (Receita receita : receitas) {
                writer.println(receita.toCSV());
            }
        }
    }
    
    /**
     * Salva a lista de despesas no arquivo CSV.
     * 
     * @param despesas Lista de despesas a serem salvas
     * @throws IOException Se ocorrer erro na escrita do arquivo
     */
    public void salvarDespesas(List<Despesa> despesas) throws IOException {
        File arquivo = new File(PASTA_DADOS, ARQUIVO_DESPESAS);
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo))) {
            // Cabeçalho do CSV
            writer.println("id,descricao,valor,data,categoria");
            
            // Dados das despesas
            for (Despesa despesa : despesas) {
                writer.println(despesa.toCSV());
            }
        }
    }
    
    /**
     * Carrega a lista de receitas do arquivo CSV.
     * 
     * @return Lista de receitas carregadas
     * @throws IOException Se ocorrer erro na leitura do arquivo
     */
    public List<Receita> carregarReceitas() throws IOException {
        List<Receita> receitas = new ArrayList<>();
        File arquivo = new File(PASTA_DADOS, ARQUIVO_RECEITAS);
        
        if (!arquivo.exists()) {
            return receitas; // Retorna lista vazia se arquivo não existe
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha = reader.readLine(); // Pula o cabeçalho
            
            while ((linha = reader.readLine()) != null) {
                try {
                    Receita receita = parseReceita(linha);
                    if (receita != null) {
                        receitas.add(receita);
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao processar linha: " + linha + " - " + e.getMessage());
                }
            }
        }
        
        return receitas;
    }
    
    /**
     * Carrega a lista de despesas do arquivo CSV.
     * 
     * @return Lista de despesas carregadas
     * @throws IOException Se ocorrer erro na leitura do arquivo
     */
    public List<Despesa> carregarDespesas() throws IOException {
        List<Despesa> despesas = new ArrayList<>();
        File arquivo = new File(PASTA_DADOS, ARQUIVO_DESPESAS);
        
        if (!arquivo.exists()) {
            return despesas; // Retorna lista vazia se arquivo não existe
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha = reader.readLine(); // Pula o cabeçalho
            
            while ((linha = reader.readLine()) != null) {
                try {
                    Despesa despesa = parseDespesa(linha);
                    if (despesa != null) {
                        despesas.add(despesa);
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao processar linha: " + linha + " - " + e.getMessage());
                }
            }
        }
        
        return despesas;
    }
    
    /**
     * Converte uma linha CSV em objeto Receita.
     * 
     * @param linha Linha do CSV
     * @return Objeto Receita ou null se erro
     */
    private Receita parseReceita(String linha) {
        if (linha == null || linha.trim().isEmpty()) {
            return null;
        }
        
        String[] campos = linha.split(",", -1);
        if (campos.length < 5) {
            return null;
        }
        
        try {
            int id = Integer.parseInt(campos[0]);
            String descricao = campos[1];
            double valor = Double.parseDouble(campos[2]);
            LocalDate data = LocalDate.parse(campos[3], FORMATO_DATA);
            TipoReceita tipo = TipoReceita.fromString(campos[4]);
            
            return new Receita(id, descricao, valor, data, tipo);
        } catch (NumberFormatException | DateTimeParseException e) {
            return null;
        }
    }
    
    /**
     * Converte uma linha CSV em objeto Despesa.
     * 
     * @param linha Linha do CSV
     * @return Objeto Despesa ou null se erro
     */
    private Despesa parseDespesa(String linha) {
        if (linha == null || linha.trim().isEmpty()) {
            return null;
        }
        
        String[] campos = linha.split(",", -1);
        if (campos.length < 5) {
            return null;
        }
        
        try {
            int id = Integer.parseInt(campos[0]);
            String descricao = campos[1];
            double valor = Double.parseDouble(campos[2]);
            LocalDate data = LocalDate.parse(campos[3], FORMATO_DATA);
            TipoDespesa tipo = TipoDespesa.fromString(campos[4]);
            
            return new Despesa(id, descricao, valor, data, tipo);
        } catch (NumberFormatException | DateTimeParseException e) {
            return null;
        }
    }
    
    /**
     * Verifica se os arquivos de dados existem.
     * 
     * @return true se pelo menos um arquivo existe
     */
    public boolean arquivosExistem() {
        File arquivoReceitas = new File(PASTA_DADOS, ARQUIVO_RECEITAS);
        File arquivoDespesas = new File(PASTA_DADOS, ARQUIVO_DESPESAS);
        return arquivoReceitas.exists() || arquivoDespesas.exists();
    }
}