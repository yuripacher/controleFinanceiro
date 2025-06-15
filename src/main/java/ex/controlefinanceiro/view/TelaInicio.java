/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ex.controlefinanceiro.view;

import ex.controlefinanceiro.model.CategoriasDespesas;
import ex.controlefinanceiro.model.CategoriasReceitas;
import ex.controlefinanceiro.model.ControleFinanceiro;
import ex.controlefinanceiro.model.Despesa;
import ex.controlefinanceiro.model.GerenciadorArquivos;
import ex.controlefinanceiro.model.Receita;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Premiersoft
 */
public class TelaInicio extends javax.swing.JFrame {

    private ControleFinanceiro controle;
    private GerenciadorArquivos gerenciador;

    public TelaInicio() {
        initComponents();
        controle = new ControleFinanceiro();
        gerenciador = new GerenciadorArquivos();

        //carregarDadosIniciais();
        // Configuração do spinner da data de lançamento
        SpinnerDateModel model = new SpinnerDateModel();
        jSpiData.setModel(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(jSpiData, "dd/MM/yyyy");
        jSpiData.setEditor(editor);

        // Configuração dos spinners para data de início e fim do extrato
        SpinnerDateModel model1 = new SpinnerDateModel();
        SpinnerDateModel model2 = new SpinnerDateModel();
        jSpiDataInicio.setModel(model1);
        jSpiDataFim.setModel(model2);

        JSpinner.DateEditor editor1 = new JSpinner.DateEditor(jSpiDataInicio, "dd/MM/yyyy");
        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(jSpiDataFim, "dd/MM/yyyy");
        jSpiDataInicio.setEditor(editor1);
        jSpiDataFim.setEditor(editor2);

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jBtnSaldo = new javax.swing.JButton();
        jLSaldo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jRBtnReceita = new javax.swing.JRadioButton();
        jRBtnDespesa = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jSpiData = new javax.swing.JSpinner();
        jComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jBtnLancar = new javax.swing.JButton();
        jTFValor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSpiDataInicio = new javax.swing.JSpinner();
        jSpiDataFim = new javax.swing.JSpinner();
        jBtnExtrato = new javax.swing.JButton();
        jBtnListarReceitas = new javax.swing.JButton();
        jBtnListarDespesas = new javax.swing.JButton();
        jBtnSaldoPeriodo = new javax.swing.JButton();
        jLSaldoPeriodo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Controle Financeiro");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Consultar saldo atual"));

        jBtnSaldo.setText("Ver Saldo");
        jBtnSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSaldoActionPerformed(evt);
            }
        });

        jLSaldo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLSaldo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnSaldo)
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnSaldo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lançamento"));

        buttonGroup1.add(jRBtnReceita);
        jRBtnReceita.setText("Receita");
        jRBtnReceita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBtnReceitaActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRBtnDespesa);
        jRBtnDespesa.setText("Despesa");
        jRBtnDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBtnDespesaActionPerformed(evt);
            }
        });

        jLabel4.setText("Data");

        jComboBox.setName(""); // NOI18N
        jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Categoria");

        jBtnLancar.setText("Lançar");
        jBtnLancar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLancarActionPerformed(evt);
            }
        });

        jTFValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFValorActionPerformed(evt);
            }
        });

        jLabel3.setText("Valor (R$)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(11, 11, 11)
                        .addComponent(jTFValor, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jRBtnReceita, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRBtnDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(38, 38, 38)
                                .addComponent(jSpiData, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnLancar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTFValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRBtnReceita)
                            .addComponent(jRBtnDespesa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jSpiData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jBtnLancar))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jComboBox.getAccessibleContext().setAccessibleName("");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações por período"));

        jLabel6.setText("Inicio");

        jLabel7.setText("Fim");

        jBtnExtrato.setText("Ver Extrato");
        jBtnExtrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExtratoActionPerformed(evt);
            }
        });

        jBtnListarReceitas.setText("Ver Receitas");
        jBtnListarReceitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnListarReceitasActionPerformed(evt);
            }
        });

        jBtnListarDespesas.setText("Ver Despesas");
        jBtnListarDespesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnListarDespesasActionPerformed(evt);
            }
        });

        jBtnSaldoPeriodo.setText("Ver Saldo");
        jBtnSaldoPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSaldoPeriodoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSpiDataFim)
                                    .addComponent(jSpiDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jBtnListarReceitas, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBtnExtrato, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtnListarDespesas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLSaldoPeriodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(34, 34, 34)
                                .addComponent(jBtnSaldoPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jSpiDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jSpiDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnExtrato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnListarReceitas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnListarDespesas)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLSaldoPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnSaldoPeriodo))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 8)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Version 0.8");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(212, 212, 212)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxActionPerformed

    private void jRBtnDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBtnDespesaActionPerformed
        // TODO add your handling code here:
        jComboBox.removeAllItems();
        for (CategoriasDespesas cat : CategoriasDespesas.values()) {
            jComboBox.addItem(cat);
        }
    }//GEN-LAST:event_jRBtnDespesaActionPerformed

    private void jRBtnReceitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBtnReceitaActionPerformed
        // TODO add your handling code here:
        jComboBox.removeAllItems();
        for (CategoriasReceitas cat : CategoriasReceitas.values()) {
            jComboBox.addItem(cat); // adiciona o enum diretamente
        }
    }//GEN-LAST:event_jRBtnReceitaActionPerformed

    private void jTFValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFValorActionPerformed

    private void jBtnSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSaldoActionPerformed
        // TODO add your handling code here:
        try {
            DecimalFormat df = new DecimalFormat("R$ #,##0.00");

            // Recarrega os dados do arquivo para atualizar listas internas
            controle.carregarDados();

            double saldo = controle.consultarSaldo();
            
            jLSaldo.setText(df.format(saldo));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao gerar extrato: " + e.getMessage());
        }

    }//GEN-LAST:event_jBtnSaldoActionPerformed

    private void jBtnExtratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExtratoActionPerformed
        // TODO add your handling code here:
        Extrato telaExtrato = new Extrato(this, true);

        try {

            Date data1 = (Date) jSpiDataInicio.getValue();
            Date data2 = (Date) jSpiDataFim.getValue();
            LocalDate dataInicio = data1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dataFim = data2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            List<String> extrato = controle.gerarExtrato(dataInicio, dataFim);

            StringBuilder sb = new StringBuilder();
            for (String linha : extrato) {
                sb.append(linha).append("\n");
            }
            telaExtrato.adicionarTexto(sb.toString());
            telaExtrato.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao gerar extrato: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnExtratoActionPerformed

    private void jBtnLancarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLancarActionPerformed
        // TODO add your handling code here:
        try {

            Date data = (Date) jSpiData.getValue();
            Instant instant = data.toInstant();
            LocalDate dataSelecionada = instant.atZone(ZoneId.systemDefault()).toLocalDate();

            String textoValor = jTFValor.getText().trim();
            if (textoValor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe um valor.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double valor = Double.parseDouble(textoValor);
            if (valor <= 0) {
                JOptionPane.showMessageDialog(this, "Informe um valor maior que zero", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (jRBtnReceita.isSelected()) {
                CategoriasReceitas categoria = (CategoriasReceitas) jComboBox.getSelectedItem();
                Receita receita = new Receita(valor, dataSelecionada, categoria);
                gerenciador.adicionarReceita(receita);
                JOptionPane.showMessageDialog(this, "Receita registrada com sucesso!");
            } else if (jRBtnDespesa.isSelected()) {
                CategoriasDespesas categoria = (CategoriasDespesas) jComboBox.getSelectedItem();
                Despesa despesa = new Despesa(valor, dataSelecionada, categoria);
                gerenciador.adicionarDespesa(despesa);
                JOptionPane.showMessageDialog(this, "Despesa registrada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione Receita ou Despesa!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }

            limparCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao lançar valor: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnLancarActionPerformed

    private void jBtnListarReceitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnListarReceitasActionPerformed
        // TODO add your handling code here:
        ListaReceitas telaReceitas = new ListaReceitas(this, true);

        try {

            Date data1 = (Date) jSpiDataInicio.getValue();
            Date data2 = (Date) jSpiDataFim.getValue();
            LocalDate dataInicio = data1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dataFim = data2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            List<Receita> receitas = controle.listarReceitasPorPeriodo(dataInicio, dataFim);

            StringBuilder sb = new StringBuilder();
            for (Receita receita : receitas) {
                sb.append(receita).append("\n");
            }
            telaReceitas.adicionarTexto(sb.toString());
            telaReceitas.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao gerar extrato: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnListarReceitasActionPerformed

    private void jBtnListarDespesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnListarDespesasActionPerformed
        // TODO add your handling code here:
        ListaDespesas telaDespesas = new ListaDespesas(this, true);

        try {

            Date data1 = (Date) jSpiDataInicio.getValue();
            Date data2 = (Date) jSpiDataFim.getValue();
            LocalDate dataInicio = data1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dataFim = data2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            List<Despesa> despesas = controle.listarDespesasPorPeriodo(dataInicio, dataFim);

            StringBuilder sb = new StringBuilder();
            for (Despesa despesa : despesas) {
                sb.append(despesa).append("\n");
            }
            telaDespesas.adicionarTexto(sb.toString());
            telaDespesas.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao gerar extrato: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnListarDespesasActionPerformed

    private void jBtnSaldoPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSaldoPeriodoActionPerformed
        // TODO add your handling code here:
        try {
        DecimalFormat df = new DecimalFormat("R$ #,##0.00");

        // Recarrega os dados do arquivo para garantir que as listas estejam atualizadas
        controle.carregarDados();

        Date data1 = (Date) jSpiDataInicio.getValue();
        Date data2 = (Date) jSpiDataFim.getValue();
        LocalDate dataInicio = data1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataFim = data2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        double saldo = controle.calcularSaldoPorPeriodo(dataInicio, dataFim);

        jLSaldoPeriodo.setText(df.format(saldo));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao calcular saldo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnSaldoPeriodoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInicio().setVisible(true);
            }
        });
    }

    private void limparCampos() {
        jTFValor.setText("");
        jComboBox.removeAllItems();
        buttonGroup1.clearSelection();
        jSpiData.setValue(new Date()); // Volta para a data atual
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBtnExtrato;
    private javax.swing.JButton jBtnLancar;
    private javax.swing.JButton jBtnListarDespesas;
    private javax.swing.JButton jBtnListarReceitas;
    private javax.swing.JButton jBtnSaldo;
    private javax.swing.JButton jBtnSaldoPeriodo;
    private javax.swing.JComboBox<Object> jComboBox;
    private javax.swing.JLabel jLSaldo;
    private javax.swing.JLabel jLSaldoPeriodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRBtnDespesa;
    private javax.swing.JRadioButton jRBtnReceita;
    private javax.swing.JSpinner jSpiData;
    private javax.swing.JSpinner jSpiDataFim;
    private javax.swing.JSpinner jSpiDataInicio;
    private javax.swing.JTextField jTFValor;
    // End of variables declaration//GEN-END:variables
}
