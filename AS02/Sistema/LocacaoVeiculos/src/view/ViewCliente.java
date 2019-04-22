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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import json.JSONArray;
import json.JSONObject;
import model.bean.Cliente;
import model.dao.ClienteDAO;

/**
 *
 * @author Murilo
 */
public class ViewCliente extends javax.swing.JFrame {

    String modo;

    /**
     * Creates new form viewCliente
     */
    public ViewCliente() {
        initComponents();
        setLocationRelativeTo(null);
        iniciarJanelaCliente();
        modo = "Navegar";
        DefaultTableModel modelo = (DefaultTableModel) table_cliente_clientes.getModel();
        table_cliente_clientes.setRowSorter(new TableRowSorter(modelo));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void manipulaInterfaceCliente() {
        switch (modo) {
            case "Navegar":
                botao_cliente_salvar.setEnabled(false);
                botao_cliente_cancelar.setEnabled(false);
                campo_cliente_nome.setEnabled(false);
                campo_cliente_cpf.setEnabled(false);
                campo_cliente_rg.setEnabled(false);
                campo_cliente_nacionalidade.setEnabled(false);
                campo_cliente_telefone.setEnabled(false);
                campo_cliente_nascimento.setEnabled(false);
                campo_cliente_sexo.setEnabled(false);
                botao_cliente_novo.setEnabled(true);
                botao_cliente_editar.setEnabled(false);
                botao_cliente_excluir.setEnabled(false);
                break;
            case "Novo":
                botao_cliente_salvar.setEnabled(true);
                botao_cliente_cancelar.setEnabled(true);
                campo_cliente_nome.setEnabled(true);
                campo_cliente_cpf.setEnabled(true);
                campo_cliente_rg.setEnabled(true);
                campo_cliente_nacionalidade.setEnabled(true);
                campo_cliente_telefone.setEnabled(true);
                campo_cliente_nascimento.setEnabled(true);
                campo_cliente_sexo.setEnabled(true);
                botao_cliente_novo.setEnabled(false);
                botao_cliente_editar.setEnabled(false);
                botao_cliente_excluir.setEnabled(false);
                break;
            case "Editar":
                botao_cliente_salvar.setEnabled(true);
                botao_cliente_cancelar.setEnabled(true);
                campo_cliente_nome.setEnabled(true);
                campo_cliente_cpf.setEnabled(true);
                campo_cliente_rg.setEnabled(true);
                campo_cliente_nacionalidade.setEnabled(true);
                campo_cliente_telefone.setEnabled(true);
                campo_cliente_nascimento.setEnabled(true);
                campo_cliente_sexo.setEnabled(true);
                botao_cliente_novo.setEnabled(false);
                botao_cliente_editar.setEnabled(false);
                botao_cliente_excluir.setEnabled(false);
                break;
            case "Excluir":
                botao_cliente_salvar.setEnabled(false);
                botao_cliente_cancelar.setEnabled(false);
                campo_cliente_nome.setEnabled(false);
                campo_cliente_cpf.setEnabled(false);
                campo_cliente_rg.setEnabled(false);
                campo_cliente_nacionalidade.setEnabled(false);
                campo_cliente_telefone.setEnabled(false);
                campo_cliente_nascimento.setEnabled(false);
                campo_cliente_sexo.setEnabled(false);
                botao_cliente_novo.setEnabled(true);
                botao_cliente_editar.setEnabled(false);
                botao_cliente_excluir.setEnabled(false);
                break;
            case "Selecao":
                botao_cliente_salvar.setEnabled(false);
                botao_cliente_cancelar.setEnabled(false);
                campo_cliente_nome.setEnabled(false);
                campo_cliente_cpf.setEnabled(false);
                campo_cliente_rg.setEnabled(false);
                campo_cliente_nacionalidade.setEnabled(false);
                campo_cliente_telefone.setEnabled(false);
                campo_cliente_nascimento.setEnabled(false);
                campo_cliente_sexo.setEnabled(false);
                botao_cliente_novo.setEnabled(true);
                botao_cliente_editar.setEnabled(true);
                botao_cliente_excluir.setEnabled(true);
                break;
            default:
                System.out.println("Modo inválido");
        }
    }

    public void readJTable() {

        DefaultTableModel modelo = (DefaultTableModel) table_cliente_clientes.getModel();
        modelo.setNumRows(0);
        ClienteDAO pdao = new ClienteDAO();

        for (Cliente p : pdao.read()) {

            modelo.addRow(new Object[]{
                p.getNome(),
                p.getCpf(),
                p.getRg(),
                p.getNacionalidade(),
                p.getTelefone(),
                p.getSexo(),
                p.getNascimento()
            });

        }

    }

    public void limparCamposCliente() {
        campo_cliente_nome.setText("");
        campo_cliente_cpf.setText("");
        campo_cliente_rg.setText("");
        campo_cliente_nacionalidade.setText("");
        campo_cliente_telefone.setText("");
        campo_cliente_nascimento.setText("");
        campo_cliente_sexo.setText("");
    }

    public void iniciarJanelaCliente() {
        campo_cliente_nome.setText("");
        campo_cliente_cpf.setText("");
        campo_cliente_rg.setText("");
        campo_cliente_nacionalidade.setText("");
        campo_cliente_telefone.setText("");
        campo_cliente_nascimento.setText("");
        campo_cliente_sexo.setText("");
        botao_cliente_menu.setEnabled(true);
        botao_cliente_importar.setEnabled(true);
        botao_cliente_exportar.setEnabled(true);
    }

    public void gravarInformacaoCliente() {
        File file = new File("Cliente.json");
        String conteudo;
        try {

            ClienteDAO pdao = new ClienteDAO();
            FileWriter f = new FileWriter(file, true);
            for (Cliente p : pdao.read()) {
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

    public void carregarInformacaoCliente() {
        try {
            ClienteDAO pdao = new ClienteDAO();
            FileReader fr = new FileReader("Cliente.json");
            BufferedReader br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                Cliente p = new Cliente();
                JSONArray json = new JSONArray(str.toString());
                for (int i = 0; i < json.length(); i++) {
                    JSONObject obj = json.getJSONObject(i);
                    p.setNome(obj.getString("nome"));
                    p.setCpf(obj.getString("cpf"));
                    p.setRg(obj.getString("rg"));
                    p.setNacionalidade(obj.getString("nacionalidade"));
                    p.setTelefone(obj.getString("telefone"));
                    p.setSexo(obj.getString("sexo"));
                    p.setNascimento((Date) obj.get("ano"));
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

    public void lerTabelaPorNome(String nome) {

        DefaultTableModel modelo = (DefaultTableModel) table_cliente_clientes.getModel();
        modelo.setNumRows(0);
        ClienteDAO pdao = new ClienteDAO();

        for (Cliente p : pdao.lerPorNome(nome)) {

            modelo.addRow(new Object[]{
                p.getNome(),
                p.getCpf(),
                p.getRg(),
                p.getNacionalidade(),
                p.getTelefone(),
                p.getSexo(),
                p.getNascimento()
            });

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        campo_cliente_nome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        campo_cliente_cpf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        campo_cliente_rg = new javax.swing.JTextField();
        campo_cliente_nacionalidade = new javax.swing.JTextField();
        campo_cliente_telefone = new javax.swing.JTextField();
        campo_cliente_nascimento = new javax.swing.JTextField();
        campo_cliente_sexo = new javax.swing.JTextField();
        botao_cliente_salvar = new javax.swing.JButton();
        botao_cliente_cancelar = new javax.swing.JButton();
        botao_cliente_menu = new javax.swing.JButton();
        botao_cliente_exportar = new javax.swing.JButton();
        botao_cliente_importar = new javax.swing.JButton();
        botao_cliente_editar = new javax.swing.JButton();
        botao_cliente_novo = new javax.swing.JButton();
        botao_cliente_excluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_cliente_clientes = new javax.swing.JTable();
        botao_cliente_buscar = new javax.swing.JButton();
        campo_cliente_buscar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(750, 260));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Nome:");

        campo_cliente_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_cliente_nomeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("CPF:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("RG:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Nacionalidade:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Telefone:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Nascimento:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Sexo:");

        campo_cliente_rg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_cliente_rgActionPerformed(evt);
            }
        });

        campo_cliente_nacionalidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_cliente_nacionalidadeActionPerformed(evt);
            }
        });

        campo_cliente_nascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_cliente_nascimentoActionPerformed(evt);
            }
        });

        botao_cliente_salvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_cliente_salvar.setText("Salvar");
        botao_cliente_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cliente_salvarActionPerformed(evt);
            }
        });

        botao_cliente_cancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_cliente_cancelar.setText("Cancelar");
        botao_cliente_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cliente_cancelarActionPerformed(evt);
            }
        });

        botao_cliente_menu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_cliente_menu.setText("Menu principal");
        botao_cliente_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cliente_menuActionPerformed(evt);
            }
        });

        botao_cliente_exportar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_cliente_exportar.setText("Exportar Dados");
        botao_cliente_exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cliente_exportarActionPerformed(evt);
            }
        });

        botao_cliente_importar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_cliente_importar.setText("Importar Dados");
        botao_cliente_importar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cliente_importarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(campo_cliente_sexo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(campo_cliente_nascimento, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(campo_cliente_telefone, javax.swing.GroupLayout.Alignment.LEADING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(campo_cliente_nacionalidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                .addComponent(campo_cliente_rg))
                            .addGap(27, 27, 27)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(campo_cliente_cpf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(campo_cliente_nome, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(30, 30, 30)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(botao_cliente_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botao_cliente_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botao_cliente_exportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botao_cliente_importar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botao_cliente_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))))
                .addGap(90, 90, 90))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(campo_cliente_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campo_cliente_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(botao_cliente_importar)
                        .addComponent(botao_cliente_exportar)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(campo_cliente_rg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(campo_cliente_nacionalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(campo_cliente_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(campo_cliente_nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botao_cliente_menu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(campo_cliente_sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botao_cliente_cancelar)
                            .addComponent(botao_cliente_salvar))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botao_cliente_editar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_cliente_editar.setText("Editar");
        botao_cliente_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cliente_editarActionPerformed(evt);
            }
        });

        botao_cliente_novo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_cliente_novo.setText("Novo");
        botao_cliente_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cliente_novoActionPerformed(evt);
            }
        });

        botao_cliente_excluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_cliente_excluir.setText("Excluir");
        botao_cliente_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cliente_excluirActionPerformed(evt);
            }
        });

        table_cliente_clientes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table_cliente_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Cpf", "Rg", "Nacionalidade", "Telefone", "Nascimento", "Sexo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_cliente_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_cliente_clientesMouseClicked(evt);
            }
        });
        table_cliente_clientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                table_cliente_clientesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(table_cliente_clientes);

        botao_cliente_buscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_cliente_buscar.setText("Buscar por nome");
        botao_cliente_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cliente_buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botao_cliente_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(botao_cliente_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(botao_cliente_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campo_cliente_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(botao_cliente_buscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campo_cliente_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao_cliente_buscar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botao_cliente_editar)
                        .addComponent(botao_cliente_novo)
                        .addComponent(botao_cliente_excluir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campo_cliente_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_cliente_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_cliente_nomeActionPerformed

    private void campo_cliente_rgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_cliente_rgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_cliente_rgActionPerformed

    private void campo_cliente_nacionalidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_cliente_nacionalidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_cliente_nacionalidadeActionPerformed

    private void campo_cliente_nascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_cliente_nascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_cliente_nascimentoActionPerformed

    private void botao_cliente_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cliente_salvarActionPerformed

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = null;
        try {
            date = sdf1.parse(campo_cliente_nascimento.getText());
        } catch (ParseException ex) {
            Logger.getLogger(ViewCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        Cliente p = new Cliente();
        ClienteDAO dao = new ClienteDAO();
        p.setNome(campo_cliente_nome.getText());
        p.setCpf(campo_cliente_cpf.getText());
        p.setRg(campo_cliente_rg.getText());
        p.setNacionalidade(campo_cliente_nacionalidade.getText());
        p.setTelefone(campo_cliente_telefone.getText());
        p.setSexo(campo_cliente_sexo.getText());
        p.setNascimento((Date) date);
        dao.create(p);

        limparCamposCliente();

        readJTable();

        modo = "Navegar";
        manipulaInterfaceCliente();
        limparCamposCliente();
    }//GEN-LAST:event_botao_cliente_salvarActionPerformed

    private void botao_cliente_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cliente_cancelarActionPerformed
        limparCamposCliente();
        modo = "Navegar";
        manipulaInterfaceCliente();
    }//GEN-LAST:event_botao_cliente_cancelarActionPerformed

    private void botao_cliente_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cliente_editarActionPerformed
        modo = "Editar";
        manipulaInterfaceCliente();

        if (table_cliente_clientes.getSelectedRow() != -1) {

            Cliente p = new Cliente();
            ClienteDAO dao = new ClienteDAO();
            p.setNome(campo_cliente_nome.getText());
            p.setRg(campo_cliente_rg.getText());
            p.setNacionalidade(campo_cliente_nacionalidade.getText());
            p.setTelefone(campo_cliente_telefone.getText());
            p.setSexo(campo_cliente_sexo.getText());
            p.setNascimento((Date) table_cliente_clientes.getValueAt(table_cliente_clientes.getSelectedRow(), 5));
            p.setCpf((String) table_cliente_clientes.getValueAt(table_cliente_clientes.getSelectedRow(), 1));
            dao.update(p);
            limparCamposCliente();

            readJTable();

        }
    }//GEN-LAST:event_botao_cliente_editarActionPerformed

    private void botao_cliente_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cliente_novoActionPerformed
        limparCamposCliente();
        modo = "Novo";
        manipulaInterfaceCliente();
    }//GEN-LAST:event_botao_cliente_novoActionPerformed

    private void botao_cliente_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cliente_excluirActionPerformed
        if (table_cliente_clientes.getSelectedRow() != -1) {
            Cliente p = new Cliente();
            ClienteDAO dao = new ClienteDAO();
            p.setCpf((String) table_cliente_clientes.getValueAt(table_cliente_clientes.getSelectedRow(), 1));
            dao.delete(p);

            limparCamposCliente();
            readJTable();

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um produto para excluir.");
        }
        modo = "Navegar";
    }//GEN-LAST:event_botao_cliente_excluirActionPerformed

    private void table_cliente_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_cliente_clientesMouseClicked
        if (table_cliente_clientes.getSelectedRow() != -1) {

            campo_cliente_nome.setText(table_cliente_clientes.getValueAt(table_cliente_clientes.getSelectedRow(), 0).toString());
            campo_cliente_cpf.setText(table_cliente_clientes.getValueAt(table_cliente_clientes.getSelectedRow(), 1).toString());
            campo_cliente_rg.setText(table_cliente_clientes.getValueAt(table_cliente_clientes.getSelectedRow(), 2).toString());
            campo_cliente_nacionalidade.setText(table_cliente_clientes.getValueAt(table_cliente_clientes.getSelectedRow(), 3).toString());
            campo_cliente_telefone.setText(table_cliente_clientes.getValueAt(table_cliente_clientes.getSelectedRow(), 4).toString());
            campo_cliente_sexo.setText(table_cliente_clientes.getValueAt(table_cliente_clientes.getSelectedRow(), 6).toString());
            campo_cliente_nascimento.setText(table_cliente_clientes.getValueAt(table_cliente_clientes.getSelectedRow(), 5).toString());
        }
    }//GEN-LAST:event_table_cliente_clientesMouseClicked

    private void botao_cliente_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cliente_buscarActionPerformed
        lerTabelaPorNome(campo_cliente_buscar.getText());
    }//GEN-LAST:event_botao_cliente_buscarActionPerformed

    private void table_cliente_clientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table_cliente_clientesKeyReleased
        if (table_cliente_clientes.getSelectedRow() != -1) {

            campo_cliente_nome.setText(table_cliente_clientes.getValueAt(table_cliente_clientes.getSelectedRow(), 0).toString());
            campo_cliente_cpf.setText(table_cliente_clientes.getValueAt(table_cliente_clientes.getSelectedRow(), 1).toString());
            campo_cliente_rg.setText(table_cliente_clientes.getValueAt(table_cliente_clientes.getSelectedRow(), 2).toString());
            campo_cliente_nacionalidade.setText(table_cliente_clientes.getValueAt(table_cliente_clientes.getSelectedRow(), 3).toString());
            campo_cliente_telefone.setText(table_cliente_clientes.getValueAt(table_cliente_clientes.getSelectedRow(), 4).toString());
            campo_cliente_sexo.setText(table_cliente_clientes.getValueAt(table_cliente_clientes.getSelectedRow(), 6).toString());
            campo_cliente_nascimento.setText(table_cliente_clientes.getValueAt(table_cliente_clientes.getSelectedRow(), 5).toString());

        }
    }//GEN-LAST:event_table_cliente_clientesKeyReleased

    private void botao_cliente_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cliente_menuActionPerformed
        new ViewHome().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botao_cliente_menuActionPerformed

    private void botao_cliente_exportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cliente_exportarActionPerformed
        gravarInformacaoCliente();
    }//GEN-LAST:event_botao_cliente_exportarActionPerformed

    private void botao_cliente_importarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cliente_importarActionPerformed
        carregarInformacaoCliente();
    }//GEN-LAST:event_botao_cliente_importarActionPerformed

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
            java.util.logging.Logger.getLogger(ViewCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao_cliente_buscar;
    private javax.swing.JButton botao_cliente_cancelar;
    private javax.swing.JButton botao_cliente_editar;
    private javax.swing.JButton botao_cliente_excluir;
    private javax.swing.JButton botao_cliente_exportar;
    private javax.swing.JButton botao_cliente_importar;
    private javax.swing.JButton botao_cliente_menu;
    private javax.swing.JButton botao_cliente_novo;
    private javax.swing.JButton botao_cliente_salvar;
    private javax.swing.JTextField campo_cliente_buscar;
    private javax.swing.JTextField campo_cliente_cpf;
    private javax.swing.JTextField campo_cliente_nacionalidade;
    private javax.swing.JTextField campo_cliente_nascimento;
    private javax.swing.JTextField campo_cliente_nome;
    private javax.swing.JTextField campo_cliente_rg;
    private javax.swing.JTextField campo_cliente_sexo;
    private javax.swing.JTextField campo_cliente_telefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_cliente_clientes;
    // End of variables declaration//GEN-END:variables
}
