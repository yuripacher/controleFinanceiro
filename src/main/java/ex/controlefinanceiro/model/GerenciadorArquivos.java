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
 * Classe responsável APENAS pela persistência dos dados em arquivos.
 * Gerencia a leitura e escrita de receitas e despesas usando streams.
 * NÃO faz cálculos - apenas persistência.
 *
 * @author Iniciante
 * @version 3.0
 */
public class GerenciadorArquivos {

    private static final String PASTA_DADOS = "dados";
    private static final String ARQUIVO_DADOS = "financeiro.txt";
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public GerenciadorArquivos() {
        inicializarSistemaArquivos();
    }

    private void inicializarSistemaArquivos() {
        criarPastaDados();
        criarArquivoDados();
    }

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

    // --- Métodos para adicionar receita e despesa ---
    public void adicionarReceita(Receita receita) throws IOException {
        salvarLinha("RECEITA;" + receita.getValor() + ";" + receita.getData().format(FORMATO_DATA) + ";" + receita.getTipo().name());
    }

    public void adicionarDespesa(Despesa despesa) throws IOException {
        salvarLinha("DESPESA;" + despesa.getValor() + ";" + despesa.getData().format(FORMATO_DATA) + ";" + despesa.getTipo().name());
    }

    // --- Salva linha no arquivo usando FileOutputStream ---
    private void salvarLinha(String linha) throws IOException {
        File arquivo = new File(PASTA_DADOS, ARQUIVO_DADOS);
        try (FileOutputStream fos = new FileOutputStream(arquivo, true)) {  // 'true' para append
            String linhaComQuebra = linha + "\n";
            byte[] dados = linhaComQuebra.getBytes(StandardCharsets.UTF_8);
            fos.write(dados);
        }
    }

    // --- Carregar receitas ---
    public List<Receita> carregarReceitas() throws IOException {
        List<Receita> listaReceitas = new ArrayList<>();
        File arquivo = new File(PASTA_DADOS, ARQUIVO_DADOS);
        if (!arquivo.exists()) {
            return listaReceitas;
        }

        // ler tudo do arquivo usando FileInputStream
        String textoArquivo = lerArquivoComoString(arquivo);

        // dividir linhas e parsear só linhas RECEITA
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

    // --- Carregar despesas ---
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

    // --- Ler arquivo como String com FileInputStream ---
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

    // --- Parse linhas para objetos ---
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