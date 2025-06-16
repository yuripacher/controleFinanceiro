/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex.controlefinanceiro.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável APENAS pela persistência dos dados em arquivos. Gerencia a
 * leitura e escrita de receitas e despesas usando streams. NÃO faz cálculos -
 * apenas persistência.
 *
 * @author MKB e YPR
 */
public class GerenciadorArquivos {

    private static final String PASTA_DADOS = "dados";
    private static final String ARQUIVO_DADOS = "financeiro.txt";
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Construtor padrão. Inicializa o sistema de arquivos garantindo que a
     * pasta e arquivo de dados existam.
     */
    public GerenciadorArquivos() {
        inicializarSistemaArquivos();
    }

    /**
     * Inicializa a estrutura de pastas e arquivo para armazenamento dos dados.
     * Cria a pasta e arquivo caso não existam.
     */
    private void inicializarSistemaArquivos() {
        criarPastaDados();
        criarArquivoDados();
    }

    /**
     * Cria a pasta "dados" caso ela não exista.
     */
    private void criarPastaDados() {
        File pasta = new File(PASTA_DADOS);
        if (!pasta.exists()) {
            if (pasta.mkdirs()) {
                System.out.println("Pasta 'dados' criada.");
            } else {
                System.err.println("Erro ao criar pasta 'dados'.");
            }
        }
    }

    /**
     * Cria o arquivo "financeiro.txt" dentro da pasta "dados" caso ele não
     * exista.
     */
    private void criarArquivoDados() {
        File arquivo = new File(PASTA_DADOS, ARQUIVO_DADOS);
        try {
            if (!arquivo.exists()) {
                if (arquivo.createNewFile()) {
                    System.out.println("Arquivo 'financeiro.txt' criado.");
                } else {
                    System.err.println("Erro ao criar arquivo 'financeiro.txt'.");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo: " + e.getMessage());
        }
    }

    /**
     * Adiciona uma nova receita no arquivo de dados.
     *
     * O método formata os dados da receita em uma linha de texto e grava no
     * arquivo, acrescentando ao final (append).
     *
     * @param receita Objeto Receita contendo valor, data e categoria.
     */
    public void adicionarReceita(Receita receita) throws IOException {
        salvarLinha("RECEITA;" + receita.getValor() + ";" + receita.getData().format(FORMATO_DATA) + ";" + receita.getTipo().name());
    }

    /**
     * Adiciona uma nova despesa ao arquivo de dados. Grava a despesa em uma
     * linha no arquivo no formato: DESPESA;valor;data;categoria
     *
     * @param despesa Objeto Despesa com valor, data e categoria.
     */
    public void adicionarDespesa(Despesa despesa) throws IOException {
        salvarLinha("DESPESA;" + despesa.getValor() + ";" + despesa.getData().format(FORMATO_DATA) + ";" + despesa.getTipo().name());
    }

    /**
     * Salva uma linha de texto no arquivo de dados, acrescentando ao final.
     *
     * @param linha String contendo os dados formatados a serem salvos.
     */
    private void salvarLinha(String linha) throws IOException {
        File arquivo = new File(PASTA_DADOS, ARQUIVO_DADOS);
        try (FileOutputStream fos = new FileOutputStream(arquivo, true)) {  // 'true' para append
            String linhaComQuebra = linha + "\n";
            byte[] dados = linhaComQuebra.getBytes(StandardCharsets.UTF_8);
            fos.write(dados);
        }
    }

    /**
     * Carrega todas as receitas armazenadas no arquivo de dados. Lê o arquivo
     * inteiro e filtra as linhas que representam receitas.
     *
     * @return Lista de objetos Receita carregados do arquivo.
     */
    public List<Receita> carregarReceitas() throws IOException {
        List<Receita> listaReceitas = new ArrayList<>();
        File arquivo = new File(PASTA_DADOS, ARQUIVO_DADOS);
        if (!arquivo.exists()) {
            return listaReceitas;
        }

        String textoArquivo = lerArquivoComoString(arquivo);

        String[] linhas = textoArquivo.split("\n");
        for (String linha : linhas) {
            if (linha.startsWith("RECEITA;")) {
                Receita r = parseReceita(linha.trim());
                if (r != null) {
                    listaReceitas.add(r);
                }
            }
        }
        return listaReceitas;
    }

    /**
     * Carrega todas as despesas armazenadas no arquivo de dados. Lê o arquivo
     * inteiro e filtra as linhas que representam despesas.
     *
     * @return Lista de objetos Despesa carregados do arquivo.
     */
    public List<Despesa> carregarDespesas() throws IOException {
        List<Despesa> listaDespesas = new ArrayList<>();
        File arquivo = new File(PASTA_DADOS, ARQUIVO_DADOS);
        if (!arquivo.exists()) {
            return listaDespesas;
        }

        String textoArquivo = lerArquivoComoString(arquivo);

        String[] linhas = textoArquivo.split("\n");
        for (String linha : linhas) {
            if (linha.startsWith("DESPESA;")) {
                Despesa d = parseDespesa(linha.trim());
                if (d != null) {
                    listaDespesas.add(d);
                }
            }
        }
        return listaDespesas;
    }

    /**
     * Lê o conteúdo inteiro de um arquivo e retorna como uma String.
     *
     * @param arquivo Arquivo a ser lido.
     * @return Conteúdo do arquivo como String.
     */
    private String lerArquivoComoString(File arquivo) throws IOException {
        try (FileInputStream fis = new FileInputStream(arquivo)) {
            byte[] buffer = new byte[(int) arquivo.length()];
            int lidos = fis.read(buffer);
            if (lidos > 0) {
                return new String(buffer, 0, lidos, StandardCharsets.UTF_8);
            } else {
                return "";
            }
        }
    }

    /**
     * Converte uma linha de texto representando uma receita em um objeto
     * Receita. A linha deve estar no formato: RECEITA;valor;data;categoria
     *
     * @param linha Linha do arquivo representando uma receita.
     * @return Objeto Receita ou null se a linha for inválida.
     */
    private Receita parseReceita(String linha) {
        try {
            String[] campos = linha.split(";");
            if (campos.length != 4) {
                return null;
            }
            double valor = Double.parseDouble(campos[1]);
            LocalDate data = LocalDate.parse(campos[2], FORMATO_DATA);
            CategoriasReceitas categoria = CategoriasReceitas.valueOf(campos[3]);
            return new Receita(valor, data, categoria);
        } catch (Exception e) {
            System.err.println("Erro ao parsear receita: " + e.getMessage());
            return null;
        }
    }

    /**
     * Converte uma linha de texto representando uma despesa em um objeto
     * Despesa. A linha deve estar no formato: DESPESA;valor;data;categoria
     *
     * @param linha Linha do arquivo representando uma despesa.
     * @return Objeto Despesa ou null se a linha for inválida.
     */
    private Despesa parseDespesa(String linha) {
        try {
            String[] campos = linha.split(";");
            if (campos.length != 4) {
                return null;
            }
            double valor = Double.parseDouble(campos[1]);
            LocalDate data = LocalDate.parse(campos[2], FORMATO_DATA);
            CategoriasDespesas categoria = CategoriasDespesas.valueOf(campos[3]);
            return new Despesa(valor, data, categoria);
        } catch (Exception e) {
            System.err.println("Erro ao parsear despesa: " + e.getMessage());
            return null;
        }
    }
}
