package apresentacao;

import negocio.ControladorFinanceiro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Tela principal do sistema de controle financeiro.
 * Contém o menu principal e exibe informações resumidas.
 */
public class TelaPrincipal extends JFrame {
    
    private ControladorFinanceiro controlador;
    private JLabel labelSaldoAtual;
    private JLabel labelSaldoTotal;
    private JLabel labelTotalReceitas;
    private JLabel labelTotalDespesas;
    private NumberFormat formatoMoeda;
    
    public TelaPrincipal() {
        this.controlador = new ControladorFinanceiro();
        this.formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        
        initComponents();
        atualizarInformacoes();
        
        // Configurações da janela
        setTitle("Sistema de Controle Financeiro Doméstico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Painel superior com título
        JPanel painelTitulo = criarPainelTitulo();
        add(painelTitulo, BorderLayout.NORTH);
        
        // Painel central com informações
        JPanel painelInfo = criarPainelInformacoes();
        add(painelInfo, BorderLayout.CENTER);
        
        // Painel inferior com botões
        JPanel painelBotoes = criarPainelBotoes();
        add(painelBotoes, BorderLayout.SOUTH);
        
        // Menu
        setJMenuBar(criarMenuBar());
    }
    
    private JPanel criarPainelTitulo() {
        JPanel painel = new JPanel(new FlowLayout());
        painel.setBackground(new Color(52, 152, 219));
        
        JLabel titulo = new JLabel("Sistema de Controle Financeiro Doméstico");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        painel.add(titulo);
        
        return painel;
    }
    
    private JPanel criarPainelInformacoes() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Resumo Financeiro"));
        painel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Saldo Atual
        gbc.gridx = 0; gbc.gridy = 0;
        painel.add(new JLabel("Saldo Atual (até hoje):"), gbc);
        
        gbc.gridx = 1;
        labelSaldoAtual = new JLabel("R$ 0,00");
        labelSaldoAtual.setFont(new Font("Arial", Font.BOLD, 16));
        painel.add(labelSaldoAtual, gbc);
        
        // Saldo Total
        gbc.gridx = 0; gbc.gridy = 1;
        painel.add(new JLabel("Saldo Total:"), gbc);
        
        gbc.gridx = 1;
        labelSaldoTotal = new JLabel("R$ 0,00");
        labelSaldoTotal.setFont(new Font("Arial", Font.BOLD, 16));
        painel.add(labelSaldoTotal, gbc);
        
        // Total Receitas
        gbc.gridx = 0; gbc.gridy = 2;
        painel.add(new JLabel("Total Receitas:"), gbc);
        
        gbc.gridx = 1;
        labelTotalReceitas = new JLabel("R$ 0,00");
        labelTotalReceitas.setFont(new Font("Arial", Font.BOLD, 14));
        labelTotalReceitas.setForeground(new Color(46, 125, 50));
        painel.add(labelTotalReceitas, gbc);
        
        // Total Despesas
        gbc.gridx = 0; gbc.gridy = 3;
        painel.add(new JLabel("Total Despesas:"), gbc);
        
        gbc.gridx = 1;
        labelTotalDespesas = new JLabel("R$ 0,00");
        labelTotalDespesas.setFont(new Font("Arial", Font.BOLD, 14));
        labelTotalDespesas.setForeground(new Color(198, 40, 40));
        painel.add(labelTotalDespesas, gbc);
        
        return painel;
    }
    
    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new GridLayout(2, 4, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Botões principais
        JButton btnReceita = new JButton("Nova Receita");
        btnReceita.setBackground(new Color(76, 175, 80));
        btnReceita.setForeground(Color.WHITE);
        btnReceita.addActionListener(e -> abrirTelaReceita());
        
        JButton btnDespesa = new JButton("Nova Despesa");
        btnDespesa.setBackground(new Color(244, 67, 54));
        btnDespesa.setForeground(Color.WHITE);
        btnDespesa.addActionListener(e -> abrirTelaDespesa());
        
        JButton btnConsultas = new JButton("Consultas");
        btnConsultas.setBackground(new Color(33, 150, 243));
        btnConsultas.setForeground(Color.WHITE);
        btnConsultas.addActionListener(e -> abrirTelaConsultas());
        
        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBackground(new Color(158, 158, 158));
        btnAtualizar.setForeground(Color.WHITE);
        btnAtualizar.addActionListener(e -> atualizarInformacoes());
        
        JButton btnExtrato = new JButton("Extrato Completo");
        btnExtrato.setBackground(new Color(121, 85, 72));
        btnExtrato.setForeground(Color.WHITE);
        btnExtrato.addActionListener(e -> mostrarExtrato());
        
        JButton btnSobre = new JButton("Sobre");
        btnSobre.addActionListener(e -> mostrarSobre());
        
        JButton btnSair = new JButton("Sair");
        btnSair.setBackground(new Color(96, 125, 139));
        btnSair.setForeground(Color.WHITE);
        btnSair.addActionListener(e -> sair());
        
        painel.add(btnReceita);
        painel.add(btnDespesa);
        painel.add(btnConsultas);
        painel.add(btnAtualizar);
        painel.add(btnExtrato);
        painel.add(btnSobre);
        painel.add(new JLabel()); // Espaço vazio
        painel.add(btnSair);
        
        return painel;
    }
    
    private JMenuBar criarMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // Menu Arquivo
        JMenu menuArquivo = new JMenu("Arquivo");
        
        JMenuItem itemReceita = new JMenuItem("Nova Receita");
        itemReceita.addActionListener(e -> abrirTelaReceita());
        
        JMenuItem itemDespesa = new JMenuItem("Nova Despesa");
        itemDespesa.addActionListener(e -> abrirTelaDespesa());
        
        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> sair());
        
        menuArquivo.add(itemReceita);
        menuArquivo.add(itemDespesa);
        menuArquivo.addSeparator();
        menuArquivo.add(itemSair);
        
        // Menu Consultas
        JMenu menuConsultas = new JMenu("Consultas");
        
        JMenuItem itemConsultas = new JMenuItem("Relatórios");
        itemConsultas.addActionListener(e -> abrirTelaConsultas());
        
        JMenuItem itemExtrato = new JMenuItem("Extrato");
        itemExtrato.addActionListener(e -> mostrarExtrato());
        
        menuConsultas.add(itemConsultas);
        menuConsultas.add(itemExtrato);
        
        // Menu Ajuda
        JMenu menuAjuda = new JMenu("Ajuda");
        
        JMenuItem itemSobre = new JMenuItem("Sobre");
        itemSobre.addActionListener(e -> mostrarSobre());
        
        menuAjuda.add(itemSobre);
        
        menuBar.add(menuArquivo);
        menuBar.add(menuConsultas);
        menuBar.add(menuAjuda);
        
        return menuBar;
    }
    
    private void abrirTelaReceita() {
        TelaIncluirReceita tela = new TelaIncluirReceita(this, controlador);
        tela.setVisible(true);
    }
    
    private void abrirTelaDespesa() {
        TelaIncluirDespesa tela = new TelaIncluirDespesa(this, controlador);
        tela.setVisible(true);
    }
    
    private void abrirTelaConsultas() {
        TelaConsultas tela = new TelaConsultas(this, controlador);
        tela.setVisible(true);
    }
    
    private void mostrarExtrato() {
        StringBuilder extrato = new StringBuilder();
        extrato.append("EXTRATO FINANCEIRO COMPLETO\n");
        extrato.append("=" .repeat(50)).append("\n\n");
        
        var linhas = controlador.gerarExtrato();
        if (linhas.isEmpty()) {
            extrato.append("Nenhum lançamento encontrado.");
        } else {
            for (String linha : linhas) {
                extrato.append(linha).append("\n");
            }
        }
        
        JTextArea textArea = new JTextArea(extrato.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(700, 500));
        
        JOptionPane.showMessageDialog(this, scrollPane, "Extrato Completo", 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarSobre() {
        String mensagem = """
            Sistema de Controle Financeiro Doméstico
            Versão 1.0
            
            Desenvolvido para:
            Programação Orientada a Objetos
            Prof. Artur Ricardo Bizon
            
            Funcionalidades:
            • Controle de receitas e despesas
            • Categorização de lançamentos
            • Consultas de saldo
            • Relatórios e extratos
            • Persistência em CSV
            
            Universidade Regional de Blumenau
            """;
        
        JOptionPane.showMessageDialog(this, mensagem, "Sobre", 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void sair() {
        int opcao = JOptionPane.showConfirmDialog(this, 
                "Deseja realmente sair do sistema?", 
                "Confirmar Saída", 
                JOptionPane.YES_NO_OPTION);
        
        if (opcao == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    public void atualizarInformacoes() {
        double[] stats = controlador.obterEstatisticas();
        double totalReceitas = stats[0];
        double totalDespesas = stats[1];
        double saldoTotal = stats[2];
        double saldoAtual = controlador.consultarSaldoAtual();
        
        labelTotalReceitas.setText(formatoMoeda.format(totalReceitas));
        labelTotalDespesas.setText(formatoMoeda.format(totalDespesas));
        labelSaldoTotal.setText(formatoMoeda.format(saldoTotal));
        labelSaldoAtual.setText(formatoMoeda.format(saldoAtual));
        
        // Cores do saldo
        Color corSaldo = saldoAtual >= 0 ? new Color(46, 125, 50) : new Color(198, 40, 40);
        labelSaldoAtual.setForeground(corSaldo);
        
        Color corSaldoTotal = saldoTotal >= 0 ? new Color(46, 125, 50) : new Color(198, 40, 40);
        labelSaldoTotal.setForeground(corSaldoTotal);
    }
}