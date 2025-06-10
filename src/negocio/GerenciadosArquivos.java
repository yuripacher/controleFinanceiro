package negocio;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela persistência dos dados em arquivos.
 * Gerencia a leitura e escrita de receitas e despesas usando streams.
 * 
 * @author Iniciante
 * @version 1.0
 */
public class GerenciadorArquivos {
    
    // Constantes para os arquivos
    private static final String PASTA_DADOS = "dados";
    private static final String ARQUIVO_RECEITAS = "receitas.txt";
    private static final String ARQUIVO_DESPESAS = "despesas.txt";
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
     * Salva a lista de receitas no arquivo usando BufferedWriter.
     * 
     * @param receitas Lista de receitas a serem salvas
     * @throws IOException Se ocorrer erro na escrita do arquivo
     */
    public void salvarReceitas(List<Receita> receitas) throws IOException {
        File arquivo = new File(PASTA_DADOS, ARQUIVO_RECEITAS);
        FileOutputStream fos = new FileOutputStream(arquivo);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        
        try {
            // Salva cada receita
            for (Receita receita : receitas) {
                String linha = receita.getDescricao() + ";" + 
                              receita.getValor() + ";" + 
                              receita.getData().format(FORMATO_DATA) + ";" + 
                              receita.getTipo();
                writer.write(linha);
                writer.newLine();
            }
        } finally {
            writer.close();
            fos.close();
        }
    }
    
    /**
     * Salva a lista de despesas no arquivo usando BufferedWriter.
     * 
     * @param despesas Lista de despesas a serem salvas
     * @throws IOException Se ocorrer erro na escrita do arquivo
     */
    public void salvarDespesas(List<Despesa> despesas) throws IOException {
        File arquivo = new File(PASTA_DADOS, ARQUIVO_DESPESAS);
        FileOutputStream fos = new FileOutputStream(arquivo);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        
        try {
            // Salva cada despesa
            for (Despesa despesa : despesas) {
                String linha = despesa.getDescricao() + ";" + 
                              despesa.getValor() + ";" + 
                              despesa.getData().format(FORMATO_DATA) + ";" + 
                              despesa.getTipo();
                writer.write(linha);
                writer.newLine();
            }
        } finally {
            writer.close();
            fos.close();
        }
    }
    
    /**
     * Carrega a lista de receitas do arquivo usando BufferedReader.
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
        
        FileInputStream fis = new FileInputStream(arquivo);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        
        try {
            String linha;
            while ((linha = reader.readLine()) != null) {
                try {
                    Receita receita = parseReceita(linha);
                    if (receita != null) {
                        receitas.add(receita);
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao processar linha: " + linha);
                }
            }
        } finally {
            reader.close();
            fis.close();
        }
        
        return receitas;
    }
    
    /**
     * Carrega a lista de despesas do arquivo usando BufferedReader.
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
        
        FileInputStream fis = new FileInputStream(arquivo);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        
        try {
            String linha;
            while ((linha = reader.readLine()) != null) {
                try {
                    Despesa despesa = parseDespesa(linha);
                    if (despesa != null) {
                        despesas.add(despesa);
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao processar linha: " + linha);
                }
            }
        } finally {
            reader.close();
            fis.close();
        }
        
        return despesas;
    }
    
    /**
     * Converte uma linha do arquivo em objeto Receita.
     * 
     * @param linha Linha do arquivo
     * @return Objeto Receita ou null se erro
     */
    private Receita parseReceita(String linha) {
        if (linha == null || linha.trim().isEmpty()) {
            return null;
        }
        
        String[] campos = linha.split(";");
        if (campos.length < 4) {
            return null;
        }
        
        try {
            String descricao = campos[0];
            double valor = Double.parseDouble(campos[1]);
            LocalDate data = LocalDate.parse(campos[2], FORMATO_DATA);
            String tipo = campos[3];
            
            return new Receita(descricao, valor, data, tipo);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Converte uma linha do arquivo em objeto Despesa.
     * 
     * @param linha Linha do arquivo
     * @return Objeto Despesa ou null se erro
     */
    private Despesa parseDespesa(String linha) {
        if (linha == null || linha.trim().isEmpty()) {
            return null;
        }
        
        String[] campos = linha.split(";");
        if (campos.length < 4) {
            return null;
        }
        
        try {
            String descricao = campos[0];
            double valor = Double.parseDouble(campos[1]);
            LocalDate data = LocalDate.parse(campos[2], FORMATO_DATA);
            String tipo = campos[3];
            
            return new Despesa(descricao, valor, data, tipo);
        } catch (Exception e) {
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