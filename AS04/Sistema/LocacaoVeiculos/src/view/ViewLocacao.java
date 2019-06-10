/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import json.JSONArray;
import json.JSONObject;
import model.bean.Locacao;
import model.dao.LocacaoDAO;

/**
 *
 * @author Murilo
 */
public class ViewLocacao extends javax.swing.JFrame {

    String modo;
    String caso = "";

    /**
     * Creates new form viewLocacao
     */
    public ViewLocacao() {
        initComponents();
        setLocationRelativeTo(null);
        iniciarJanelaLocacao();
        readJTable();
        modo = "Navegar";
        manipulaInterfaceLocacao();
        DefaultTableModel modelo = (DefaultTableModel) table_locacao_locacoes.getModel();
        table_locacao_locacoes.setRowSorter(new TableRowSorter(modelo));
    }

    public void limparCamposLocacao() {
        campo_locacao_dataloc.setText("");
        campo_locacao_horaloc.setText("");
        campo_locacao_datadev.setText("");
        campo_locacao_horadev.setText("");
        campo_locacao_numloc.setText("");
        campo_locacao_cpf.setText("");
        campo_locacao_placa.setText("");
    }

    public void readJTable() {

        DefaultTableModel modelo = (DefaultTableModel) table_locacao_locacoes.getModel();
        modelo.setNumRows(0);
        LocacaoDAO pdao = new LocacaoDAO();

        for (Locacao p : pdao.read()) {

            modelo.addRow(new Object[]{
               p.getNumeroLocacao(),
                p.getCpfLocacao(),
                p.getPlacaLocacao(),
                p.getDataLocacao(),
                p.getHorarioLocacao(),
                p.getDataDevolucao(),
                p.getHorarioDevolucao()
            });

        }

    }

    public void manipulaInterfaceLocacao() {
        switch (modo) {
            case "Navegar":
                botao_locacao_salvar.setEnabled(false);
                botao_locacao_cancelar.setEnabled(false);
                botao_locacao_novo.setEnabled(true);
                botao_locacao_editar.setEnabled(false);
                botao_locacao_excluir.setEnabled(false);
                campo_locacao_dataloc.setEnabled(false);
                campo_locacao_horaloc.setEnabled(false);
                campo_locacao_datadev.setEnabled(false);
                campo_locacao_horadev.setEnabled(false);
                campo_locacao_numloc.setEnabled(false);
                campo_locacao_cpf.setEnabled(false);
                campo_locacao_placa.setEnabled(false);
                break;
            case "Novo":
                botao_locacao_salvar.setEnabled(true);
                botao_locacao_cancelar.setEnabled(true);
                botao_locacao_novo.setEnabled(false);
                botao_locacao_editar.setEnabled(false);
                botao_locacao_excluir.setEnabled(false);
                campo_locacao_dataloc.setEnabled(true);
                campo_locacao_horaloc.setEnabled(true);
                campo_locacao_datadev.setEnabled(true);
                campo_locacao_horadev.setEnabled(true);
                campo_locacao_numloc.setEnabled(false);
                campo_locacao_cpf.setEnabled(true);
                campo_locacao_placa.setEnabled(true);
                break;
            case "Editar":
                botao_locacao_salvar.setEnabled(true);
                botao_locacao_cancelar.setEnabled(true);
                botao_locacao_novo.setEnabled(false);
                botao_locacao_editar.setEnabled(false);
                botao_locacao_excluir.setEnabled(false);
                campo_locacao_dataloc.setEnabled(true);
                campo_locacao_horaloc.setEnabled(true);
                campo_locacao_datadev.setEnabled(true);
                campo_locacao_horadev.setEnabled(true);
                campo_locacao_numloc.setEnabled(false);
                campo_locacao_cpf.setEnabled(true);
                campo_locacao_placa.setEnabled(true);
                break;
            case "Excluir":
                botao_locacao_salvar.setEnabled(false);
                botao_locacao_cancelar.setEnabled(false);
                botao_locacao_novo.setEnabled(true);
                botao_locacao_editar.setEnabled(false);
                botao_locacao_excluir.setEnabled(false);
                campo_locacao_dataloc.setEnabled(false);
                campo_locacao_horaloc.setEnabled(false);
                campo_locacao_datadev.setEnabled(false);
                campo_locacao_horadev.setEnabled(false);
                campo_locacao_numloc.setEnabled(false);
                campo_locacao_cpf.setEnabled(false);
                campo_locacao_placa.setEnabled(false);
                break;
            case "Selecao":
                botao_locacao_salvar.setEnabled(false);
                botao_locacao_cancelar.setEnabled(false);
                botao_locacao_novo.setEnabled(true);
                botao_locacao_editar.setEnabled(true);
                botao_locacao_excluir.setEnabled(true);
                campo_locacao_dataloc.setEnabled(false);
                campo_locacao_horaloc.setEnabled(false);
                campo_locacao_datadev.setEnabled(false);
                campo_locacao_horadev.setEnabled(false);
                campo_locacao_numloc.setEnabled(false);
                campo_locacao_cpf.setEnabled(false);
                campo_locacao_placa.setEnabled(false);
                break;
            default:
                System.out.println("Modo inválido");
        }
    }

    public void iniciarJanelaLocacao() {
        campo_locacao_dataloc.setText("");
        campo_locacao_horaloc.setText("");
        campo_locacao_datadev.setText("");
        campo_locacao_horadev.setText("");
        campo_locacao_numloc.setText("");
        campo_locacao_cpf.setText("");
        campo_locacao_placa.setText("");
        botao_locacao_menu.setEnabled(true);
        botao_locacao_importar.setEnabled(true);
        botao_locacao_exportar.setEnabled(true);
    }

    public void gravarInformacaoLocacao() {
        File file = new File("Locacao.json");
        String conteudo;
        try {

            LocacaoDAO pdao = new LocacaoDAO();
            FileWriter f = new FileWriter(file, true);
            for (Locacao p : pdao.read()) {
                JSONObject json = new JSONObject();
                conteudo = p.toJson().toString();
                conteudo += "\r\n";
                f.write(conteudo);
            }
            f.close();
            JOptionPane.showMessageDialog(null, "Dados Exportados com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar esta ação");
        }
    }

    public void carregarInformacaoLocacao() {
        try {
            LocacaoDAO pdao = new LocacaoDAO();
            FileReader fr = new FileReader("Locacao.json");
            BufferedReader br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                Locacao p = new Locacao();
                JSONArray json = new JSONArray(str.toString());
                for (int i = 0; i < json.length(); i++) {
                    JSONObject obj = json.getJSONObject(i);
                    p.setNumeroLocacao(obj.getInt("numero_locacao"));
                    p.setCpfLocacao(obj.getString("cpf_locacao"));
                    p.setPlacaLocacao(obj.getString("placa_locacao"));
                    p.setDataLocacao((Date) obj.get("data_locacao"));
                    p.setDataDevolucao((Date) obj.get("data_devolucao"));
                    p.setHorarioLocacao((Time) obj.get("hora_locacao"));
                    p.setHorarioDevolucao((Time) obj.get("hora_devolucao"));

                    if (i + 1 == json.length()) {
                        pdao.create(p);
                    }
                }
            }
            br.close();
            JOptionPane.showMessageDialog(null, "Dados Importados com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar esta ação");
        }
    }

    public void lerTabelaPorCpf(String cpf) {
        DefaultTableModel modelo = (DefaultTableModel) table_locacao_locacoes.getModel();
        modelo.setNumRows(0);
        LocacaoDAO pdao = new LocacaoDAO();

        for (Locacao p : pdao.lerPorCpf(cpf)) {

            modelo.addRow(new Object[]{
                p.getNumeroLocacao(),
                p.getCpfLocacao(),
                p.getPlacaLocacao(),
                p.getDataLocacao(),
                p.getHorarioLocacao(),
                p.getDataDevolucao(),
                p.getHorarioDevolucao()
            });

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        campo_locacao_dataloc = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        campo_locacao_horaloc = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        campo_locacao_datadev = new javax.swing.JTextField();
        campo_locacao_horadev = new javax.swing.JTextField();
        campo_locacao_numloc = new javax.swing.JTextField();
        botao_locacao_salvar = new javax.swing.JButton();
        botao_locacao_cancelar = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        campo_locacao_cpf = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        campo_locacao_placa = new javax.swing.JTextField();
        botao_locacao_menu = new javax.swing.JButton();
        botao_locacao_exportar = new javax.swing.JButton();
        botao_locacao_importar = new javax.swing.JButton();
        botao_locacao_excluir = new javax.swing.JButton();
        botao_locacao_editar = new javax.swing.JButton();
        botao_locacao_novo = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_locacao_locacoes = new javax.swing.JTable();
        botao_locacao_buscar = new javax.swing.JButton();
        campo_locacao_buscar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Locação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(750, 260));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Data Locação:");

        campo_locacao_dataloc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_locacao_datalocActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Hora Locação:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Data Devolução:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Hora Devolução:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Numero Locação:");

        campo_locacao_datadev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_locacao_datadevActionPerformed(evt);
            }
        });

        campo_locacao_horadev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_locacao_horadevActionPerformed(evt);
            }
        });

        botao_locacao_salvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_locacao_salvar.setText("Salvar");
        botao_locacao_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_locacao_salvarActionPerformed(evt);
            }
        });

        botao_locacao_cancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_locacao_cancelar.setText("Cancelar");
        botao_locacao_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_locacao_cancelarActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("CPF Locação:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Placa Locação:");

        botao_locacao_menu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_locacao_menu.setText("Menu principal");
        botao_locacao_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_locacao_menuActionPerformed(evt);
            }
        });

        botao_locacao_exportar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_locacao_exportar.setText("Exportar Dados");
        botao_locacao_exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_locacao_exportarActionPerformed(evt);
            }
        });

        botao_locacao_importar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_locacao_importar.setText("Importar Dados");
        botao_locacao_importar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_locacao_importarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campo_locacao_datadev)
                    .addComponent(campo_locacao_horadev, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(campo_locacao_numloc, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campo_locacao_horaloc)
                    .addComponent(campo_locacao_dataloc)
                    .addComponent(campo_locacao_cpf, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campo_locacao_placa, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(botao_locacao_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botao_locacao_exportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botao_locacao_salvar, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botao_locacao_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(botao_locacao_importar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(82, 82, 82))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botao_locacao_exportar)
                            .addComponent(botao_locacao_importar))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botao_locacao_cancelar)
                            .addComponent(botao_locacao_salvar))
                        .addGap(54, 54, 54)
                        .addComponent(botao_locacao_menu))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(campo_locacao_dataloc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campo_locacao_horaloc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campo_locacao_datadev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(campo_locacao_horadev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(campo_locacao_numloc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(campo_locacao_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(campo_locacao_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        botao_locacao_excluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_locacao_excluir.setText("Excluir");
        botao_locacao_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_locacao_excluirActionPerformed(evt);
            }
        });

        botao_locacao_editar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_locacao_editar.setText("Editar");
        botao_locacao_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_locacao_editarActionPerformed(evt);
            }
        });

        botao_locacao_novo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_locacao_novo.setText("Novo");
        botao_locacao_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_locacao_novoActionPerformed(evt);
            }
        });

        table_locacao_locacoes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table_locacao_locacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número Locação", "Cpf Cliente", "Placa Veículo", "Data Locação", "Hora Locação", "Data Devolução", "Hora Devolução"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_locacao_locacoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_locacao_locacoesMouseClicked(evt);
            }
        });
        table_locacao_locacoes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                table_locacao_locacoesKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(table_locacao_locacoes);
        if (table_locacao_locacoes.getColumnModel().getColumnCount() > 0) {
            table_locacao_locacoes.getColumnModel().getColumn(6).setResizable(false);
        }

        botao_locacao_buscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_locacao_buscar.setText("Buscar por CPF");
        botao_locacao_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_locacao_buscarActionPerformed(evt);
            }
        });

        campo_locacao_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_locacao_buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botao_locacao_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botao_locacao_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botao_locacao_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(campo_locacao_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(botao_locacao_buscar)
                .addContainerGap())
            .addComponent(jScrollPane3)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campo_locacao_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao_locacao_buscar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botao_locacao_novo)
                        .addComponent(botao_locacao_editar)
                        .addComponent(botao_locacao_excluir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campo_locacao_datalocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_locacao_datalocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_locacao_datalocActionPerformed

    private void campo_locacao_datadevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_locacao_datadevActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_locacao_datadevActionPerformed

    private void campo_locacao_horadevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_locacao_horadevActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_locacao_horadevActionPerformed

    private void botao_locacao_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_locacao_salvarActionPerformed
        // Formatar horarios
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        java.util.Date hLoc = null;
        java.util.Date hDev = null;
        try {
            hLoc = formatador.parse(campo_locacao_horaloc.getText());
            hDev = formatador.parse(campo_locacao_horadev.getText());
        } catch (ParseException ex) {
            Logger.getLogger(ViewLocacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Time time1 = new Time(hLoc.getTime());
        Time time2 = new Time(hDev.getTime());
        // Formatar datas
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dataloc = null;
        java.sql.Date datadev = null;
        try {
            dataloc = new java.sql.Date(fmt.parse(campo_locacao_dataloc.getText()).getTime());
            datadev = new java.sql.Date(fmt.parse(campo_locacao_datadev.getText()).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(ViewLocacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (caso == "Editar") {
            Locacao p = new Locacao();
            LocacaoDAO dao = new LocacaoDAO();
            p.setCpfLocacao(campo_locacao_cpf.getText());
            p.setPlacaLocacao(campo_locacao_placa.getText());
            p.setDataLocacao(dataloc);
            p.setDataDevolucao(datadev);
            p.setHorarioLocacao(time1);
            p.setHorarioDevolucao(time2);
            dao.update(p);
            readJTable();
            modo = "Navegar";
            caso = "";
            manipulaInterfaceLocacao();
            limparCamposLocacao();
        }

        if (caso == "Salvar") {
            Locacao p = new Locacao();
            LocacaoDAO dao = new LocacaoDAO();
            p.setCpfLocacao(campo_locacao_cpf.getText());
            p.setPlacaLocacao(campo_locacao_placa.getText());
            p.setDataLocacao(dataloc);
            p.setDataDevolucao(datadev);
            p.setHorarioLocacao(time1);
            p.setHorarioDevolucao(time2);
            dao.create(p);
            limparCamposLocacao();
            readJTable();
            modo = "Navegar";
            manipulaInterfaceLocacao();
            caso = "";
        }

    }//GEN-LAST:event_botao_locacao_salvarActionPerformed

    private void botao_locacao_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_locacao_cancelarActionPerformed
        limparCamposLocacao();
        modo = "Navegar";
        manipulaInterfaceLocacao();
    }//GEN-LAST:event_botao_locacao_cancelarActionPerformed

    private void botao_locacao_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_locacao_excluirActionPerformed
        if (table_locacao_locacoes.getSelectedRow() != -1) {
            Locacao p = new Locacao();
            LocacaoDAO dao = new LocacaoDAO();
            p.setNumeroLocacao((int) table_locacao_locacoes.getValueAt(table_locacao_locacoes.getSelectedRow(), 0));
            dao.delete(p);
            limparCamposLocacao();
            readJTable();

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um produto para excluir.");
        }
        modo = "Navegar";
    }//GEN-LAST:event_botao_locacao_excluirActionPerformed

    private void botao_locacao_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_locacao_editarActionPerformed
        modo = "Editar";
        caso = "Editar";
        manipulaInterfaceLocacao();

    }//GEN-LAST:event_botao_locacao_editarActionPerformed

    private void botao_locacao_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_locacao_novoActionPerformed
        limparCamposLocacao();
        modo = "Novo";
        caso = "Salvar";
        manipulaInterfaceLocacao();
    }//GEN-LAST:event_botao_locacao_novoActionPerformed

    private void table_locacao_locacoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_locacao_locacoesMouseClicked
        modo = "Selecao";
        manipulaInterfaceLocacao();
        if (table_locacao_locacoes.getSelectedRow() != -1) {

            campo_locacao_numloc.setText(table_locacao_locacoes.getValueAt(table_locacao_locacoes.getSelectedRow(), 0).toString());
            campo_locacao_cpf.setText(table_locacao_locacoes.getValueAt(table_locacao_locacoes.getSelectedRow(), 1).toString());
            campo_locacao_placa.setText(table_locacao_locacoes.getValueAt(table_locacao_locacoes.getSelectedRow(), 2).toString());
            campo_locacao_dataloc.setText(table_locacao_locacoes.getValueAt(table_locacao_locacoes.getSelectedRow(), 3).toString());
            campo_locacao_horaloc.setText(table_locacao_locacoes.getValueAt(table_locacao_locacoes.getSelectedRow(), 4).toString());
            campo_locacao_datadev.setText(table_locacao_locacoes.getValueAt(table_locacao_locacoes.getSelectedRow(), 5).toString());
            campo_locacao_horadev.setText(table_locacao_locacoes.getValueAt(table_locacao_locacoes.getSelectedRow(), 6).toString());
        }
    }//GEN-LAST:event_table_locacao_locacoesMouseClicked

    private void botao_locacao_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_locacao_buscarActionPerformed
        lerTabelaPorCpf(campo_locacao_buscar.getText());
    }//GEN-LAST:event_botao_locacao_buscarActionPerformed

    private void campo_locacao_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_locacao_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_locacao_buscarActionPerformed

    private void table_locacao_locacoesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table_locacao_locacoesKeyReleased
        if (table_locacao_locacoes.getSelectedRow() != -1) {

            campo_locacao_numloc.setText(table_locacao_locacoes.getValueAt(table_locacao_locacoes.getSelectedRow(), 0).toString());
            campo_locacao_cpf.setText(table_locacao_locacoes.getValueAt(table_locacao_locacoes.getSelectedRow(), 1).toString());
            campo_locacao_placa.setText(table_locacao_locacoes.getValueAt(table_locacao_locacoes.getSelectedRow(), 2).toString());
            campo_locacao_dataloc.setText(table_locacao_locacoes.getValueAt(table_locacao_locacoes.getSelectedRow(), 3).toString());
            campo_locacao_horaloc.setText(table_locacao_locacoes.getValueAt(table_locacao_locacoes.getSelectedRow(), 4).toString());
            campo_locacao_datadev.setText(table_locacao_locacoes.getValueAt(table_locacao_locacoes.getSelectedRow(), 5).toString());
            campo_locacao_horadev.setText(table_locacao_locacoes.getValueAt(table_locacao_locacoes.getSelectedRow(), 6).toString());
        }
    }//GEN-LAST:event_table_locacao_locacoesKeyReleased

    private void botao_locacao_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_locacao_menuActionPerformed
       new ViewHome().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_botao_locacao_menuActionPerformed

    private void botao_locacao_exportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_locacao_exportarActionPerformed
        //gravarInformacaoLocacao();
        String janela = "Locacao";
        new ViewExport(janela).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botao_locacao_exportarActionPerformed

    private void botao_locacao_importarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_locacao_importarActionPerformed
        //carregarInformacaoLocacao();
        String janela = "Locacao";
        new ViewImport(janela).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botao_locacao_importarActionPerformed

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
            java.util.logging.Logger.getLogger(ViewLocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewLocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewLocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewLocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewLocacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao_locacao_buscar;
    private javax.swing.JButton botao_locacao_cancelar;
    private javax.swing.JButton botao_locacao_editar;
    private javax.swing.JButton botao_locacao_excluir;
    private javax.swing.JButton botao_locacao_exportar;
    private javax.swing.JButton botao_locacao_importar;
    private javax.swing.JButton botao_locacao_menu;
    private javax.swing.JButton botao_locacao_novo;
    private javax.swing.JButton botao_locacao_salvar;
    private javax.swing.JTextField campo_locacao_buscar;
    private javax.swing.JTextField campo_locacao_cpf;
    private javax.swing.JTextField campo_locacao_datadev;
    private javax.swing.JTextField campo_locacao_dataloc;
    private javax.swing.JTextField campo_locacao_horadev;
    private javax.swing.JTextField campo_locacao_horaloc;
    private javax.swing.JTextField campo_locacao_numloc;
    private javax.swing.JTextField campo_locacao_placa;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable table_locacao_locacoes;
    // End of variables declaration//GEN-END:variables
}
